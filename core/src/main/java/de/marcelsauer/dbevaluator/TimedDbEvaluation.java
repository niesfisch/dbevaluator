package de.marcelsauer.dbevaluator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

/**
 * DB Evaluator Copyright (C) 2010 Marcel Sauer <marcel DOT sauer AT gmx DOT de>
 * 
 * This file is part of DB Evaluator.
 * 
 * DB Evaluator is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * DB Evaluator is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * DB Evaluator. If not, see <http://www.gnu.org/licenses/>.
 */
public class TimedDbEvaluation implements DbEvaluation {

	private static final Log log = LogFactory.getLog(TimedDbEvaluation.class);
    public final List<SingleResult> singleResults = new ArrayList<SingleResult>();
    public Result combinedResult;

    private final StopWatch stopWatch;
    private final DbEvaluation[] evaluations;

    public TimedDbEvaluation(DbEvaluation[] evaluations, StopWatch stopWatch) {
        this.evaluations = evaluations;
        this.stopWatch = stopWatch;
    }

    @Override
    public void run(LoggingCallback log) {
        for (DbEvaluation evaluation : evaluations) {
        	beforeRun(evaluation);
            run(evaluation, log);
            afterRun(evaluation);
        }
    }

	private void afterRun(DbEvaluation evaluation) {
		NDC.pop();
		
		long durationTimeMillis = stopWatch.durationTimeMillis();
		log.debug("finished  " + evaluation.getClass().getSimpleName() + " in " + durationTimeMillis + " (ms)");

		combinedResult = new Result();
		combinedResult.durationMillis += durationTimeMillis;

		Result result = new Result();
		result.durationMillis = durationTimeMillis;

		singleResults.add(new SingleResult(result, evaluation));
	}

	private void beforeRun(DbEvaluation evaluation) {
		String evaluationName = evaluation.getClass().getSimpleName();
		log.debug("running " + evaluationName + " evaluation");
		NDC.push("     ");
	}

	private void run(DbEvaluation evaluation, LoggingCallback log) {
		stopWatch.reset();
		stopWatch.start();
		
		evaluation.run(log);
		
		stopWatch.stop();
	}

    public class SingleResult {
        public final Result result;
        public final DbEvaluation evaluation;

        private SingleResult(Result result, DbEvaluation evaluation) {
            this.result = result;
            this.evaluation = evaluation;
        }
    }

}
