package de.marcelsauer.dbevaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import de.marcelsauer.dbevaluator.TimedDbEvaluation.SingleResult;
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

	private static final int AMOUNT_POSTS = 2;
	private static final int AMOUNT_BLOGS = 1;
	private static final Log log = LogFactory.getLog(AbstractDbEvaluatorTest.class);

	abstract DbEvaluation createDbEvaluation(Collection<Blog> blogs) throws Exception;

	@Test
	public void runAll() throws Exception {
		TimedDbEvaluation timedEvaluation = getTimedEvaluationToRun();
		timedEvaluation.run();
		dumpResults(timedEvaluation);
	}

	/**
	 * @return the number of blogs to be created, subclasses are free to
	 *         override
	 */
	protected int numberOfBlogsToCreate() {
		return AMOUNT_BLOGS;
	}

	/**
	 * @return the amount of posts per blog that should be created
	 */
	protected int numberOfPostsPerBlogToBeCreated() {
		return AMOUNT_POSTS;
	}

	private void dumpResults(TimedDbEvaluation timedEvaluation) {
		List<SingleResult> singleResults = timedEvaluation.singleResults;

		System.out.println();
		System.out.println("total time taken (ms): " + timedEvaluation.combinedResult.durationMillis);
		for (SingleResult singleResult : singleResults) {
			long durationMillis = singleResult.result.durationMillis;
			String evaluator = singleResult.evaluation.getClass().getSimpleName();
			System.out.println(evaluator + "  " + durationMillis + " (ms)");
		}
	}

	/**
	 * wiring of all the actions to perform
	 */
	private TimedDbEvaluation getTimedEvaluationToRun() throws Exception {
		StopWatch stopWatch = new StopWatch();

		Collection<Blog> blog = createBlog();

		DbEvaluation evaluation = createDbEvaluation(blog);

		DbEvaluation[] evaluations = new DbEvaluation[] { evaluation };
		TimedDbEvaluation timedEvaluation = new TimedDbEvaluation(evaluations, stopWatch);
		return timedEvaluation;
	}

	private Collection<Blog> createBlog() {
		Collection<Blog> blogs = new ArrayList<Blog>();
		for (int blogNr = 1; blogNr <= numberOfBlogsToCreate(); blogNr++) {
			Blog blog = new Blog("the mighty db evaluation " + blogNr);
			for (int postNr = 1; postNr <= numberOfPostsPerBlogToBeCreated(); postNr++) {
				Post post = new Post();
				post.date = new Date();
				post.author = "Marcel";
				post.headline = "the " + postNr + ". post of blog " + blogNr;
				post.content = "the " + postNr + ". content of blog " + blogNr;
				blog.add(post);
			}
			blogs.add(blog);
		}
		return blogs;
	}
}
