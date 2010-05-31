package de.marcelsauer.dbevaluator;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

import de.marcelsauer.dbevaluator.model.Blog;

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
public class TimedDbEvaluation {

	private static final Log log = LogFactory.getLog(TimedDbEvaluation.class);

	private final StopWatch stopWatch;
	private final DbEvaluation[] evaluations;
	private final LogCallback logCallback;
	private final Collection<Blog> blogs;
	private Collection<Result> results;

	public TimedDbEvaluation(DbEvaluation[] evaluations, StopWatch stopWatch, LogCallback logCallback,
			Collection<Blog> blogs) {
		this.evaluations = evaluations;
		this.stopWatch = stopWatch;
		this.logCallback = logCallback;
		this.blogs = blogs;
	}

	public Collection<Result> getResults() {
		return results;
	}

	public void run() {
		for (DbEvaluation evaluation : evaluations) {
			beforeRun(evaluation);
			this.results = run(evaluation);
			afterRun(evaluation);
		}
	}

	private void afterRun(DbEvaluation evaluation) {
		NDC.pop();

		long durationTimeMillis = stopWatch.durationTimeMillis();
		log.debug("finished  " + evaluation.getClass().getSimpleName() + " in " + durationTimeMillis + " (ms)");
	}

	private void beforeRun(DbEvaluation evaluation) {
		String evaluationName = evaluation.getClass().getSimpleName();
		log.debug("running " + evaluationName + " evaluation");
		NDC.push("     ");
	}

	private Collection<Result> run(DbEvaluation evaluation) {
		Collection<Result> results = new ArrayList<Result>();

		evaluation.setLogCallback(logCallback);

		persist(evaluation, results);
		load(evaluation, results);

		return results;
	}

	private void load(DbEvaluation evaluation, Collection<Result> results) {
		stopWatch.reset();
		stopWatch.start();
		Result result = new Result();
		result.evaluation = evaluation;
		result.operationPerformed = "load";
		try {
			evaluation.load(extractTitles());
		} catch (UnsupportedOperationException e) {
			result.ex = e;
		} finally {
			stopWatch.stop();
			result.durationMillis = stopWatch.durationTimeMillis();
			results.add(result);
		}
	}

	private void persist(DbEvaluation evaluation, Collection<Result> results) {
		stopWatch.reset();
		stopWatch.start();
		Result result = new Result();
		result.evaluation = evaluation;
		result.operationPerformed = "persist";
		try {
			evaluation.persist(blogs);
		} catch (UnsupportedOperationException e) {
			result.ex = e;
		} finally {
			stopWatch.stop();
			result.durationMillis = stopWatch.durationTimeMillis();
			results.add(result);
		}
	}

	private Collection<String> extractTitles() {
		Collection<String> titles = new ArrayList<String>();
		for (Blog blog : blogs) {
			titles.add(blog.title);
		}
		return titles;
	}

	public static class Result {
		public long durationMillis;
		public DbEvaluation evaluation;
		public String operationPerformed;
		public UnsupportedOperationException ex;

		public boolean operationNotSupported() {
			return ex != null;
		}
	}

}
