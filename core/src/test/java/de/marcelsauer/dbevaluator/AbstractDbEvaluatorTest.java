package de.marcelsauer.dbevaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import de.marcelsauer.dbevaluator.TimedDbEvaluation.Result;
import de.marcelsauer.dbevaluator.model.Blog;
import de.marcelsauer.dbevaluator.model.Post;

/**
 * DB Evaluator Copyright (C) 2010 Marcel Sauer <marcel DOT sauer AT gmx DOT de>
 * 
 * This file is part of DB Evaluator.
 * 
 * DB Evaluator is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * DB Evaluator is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * DB Evaluator. If not, see <http://www.gnu.org/licenses/>.
 */
public abstract class AbstractDbEvaluatorTest {

	private static final Log log = LogFactory.getLog(AbstractDbEvaluatorTest.class);

	/**
	 * creates the concrete {@link DbEvaluation}
	 * 
	 * @param blogs
	 *            to be created
	 * @return
	 * @throws Exception
	 */
	public abstract DbEvaluation createDbEvaluation() throws Exception;

	// this would be injected via DI management in a real app :)
	private final CapturingLogCallback logCallback = new CapturingLogCallback();
	private final TestConfig testConfig = new TestConfig();

	@Test
	public void runSingleEvaluation() throws Exception {
		TimedDbEvaluation timedEvaluation = getTimedEvaluationToRun();
		timedEvaluation.run();
		resultsFor(timedEvaluation);
	}

	private void resultsFor(TimedDbEvaluation timedEvaluation) {
		dumpCapturedOutput();
		System.out.println("---------------------------------------------------------------------");
		dumpTotals();
		dumpResults(timedEvaluation);
		System.out.println("---------------------------------------------------------------------");
	}

	private void dumpResults(TimedDbEvaluation timedEvaluation) {
		Collection<Result> results = timedEvaluation.getResults();

		long totalTimeTakenInMillis = calculateTotalTimeTakenInMillis(results);
		System.out.println("total time taken (ms): " + totalTimeTakenInMillis);
		for (Result result : results) {
			if (result.operationNotSupported()) {
				System.out.println("operation not supported. " + result.ex.getMessage());
			} else {
				System.out.println(String.format("action performed: %s in %s (ms)", result.operationPerformed,
						result.durationMillis));
			}
		}
	}

	private long calculateTotalTimeTakenInMillis(Collection<Result> singleResults) {
		long total = 0;
		for (Result result : singleResults) {
			total += result.durationMillis;
		}
		return total;
	}

	private void dumpTotals() {
		System.out.println("total number of blogs processed: " + testConfig.numberOfBlogsToCreated());
		int totalPosts = testConfig.numberOfBlogsToCreated() * testConfig.numberOfPostsPerBlogToBeCreated();
		System.out.println(String.format("total number of posts processed: %s (%s * %s)", totalPosts, testConfig
				.numberOfBlogsToCreated(), testConfig.numberOfPostsPerBlogToBeCreated()));
	}

	private void dumpCapturedOutput() {
		if (testConfig.shouldDumpCapturedOutput()) {
			System.out.println("captured output during runtime:");
			for (String toLog : logCallback.getCaptured()) {
				System.out.println(toLog);
			}
		}
	}

	/**
	 * wiring of all the actions to perform
	 */
	private TimedDbEvaluation getTimedEvaluationToRun() throws Exception {
		StopWatch stopWatch = new StopWatch();

		Collection<Blog> blogs = createBlogs();

		DbEvaluation evaluation = createDbEvaluation();

		DbEvaluation[] evaluations = new DbEvaluation[] { evaluation };
		TimedDbEvaluation timedEvaluation = new TimedDbEvaluation(evaluations, stopWatch, logCallback, blogs);
		return timedEvaluation;
	}

	private Collection<Blog> createBlogs() {
		Collection<Blog> blogs = new ArrayList<Blog>();
		for (int blogNr = 1; blogNr <= testConfig.numberOfBlogsToCreated(); blogNr++) {
			Blog blog = new Blog("the mighty db evaluation " + blogNr);
			for (int postNr = 1; postNr <= testConfig.numberOfPostsPerBlogToBeCreated(); postNr++) {
				Post post = new Post();
				post.date = new Date();
				post.author = "Marcel";
				post.headline = "the " + postNr + ". post of blog " + blogNr;
				post.content = "the " + postNr + ". content of blog " + blogNr;
				addTagsTo(post);
				blog.add(post);
			}
			blogs.add(blog);
		}
		return blogs;
	}

	private void addTagsTo(Post post) {
		post.addTag("first tag");
		post.addTag("second tag");
		post.addTag("third tag");
	}
}