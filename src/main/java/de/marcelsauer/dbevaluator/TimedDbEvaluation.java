package de.marcelsauer.dbevaluator;

import java.util.ArrayList;
import java.util.List;


public class TimedDbEvaluation implements DbEvaluation {

    public final List<SingleResult> singleResults = new ArrayList<SingleResult>();
    public Result combinedResult;

    private final StopWatch stopWatch;
    private final DbEvaluation[] evaluations;

    public TimedDbEvaluation(DbEvaluation[] evaluations, StopWatch stopWatch) {
        this.evaluations = evaluations;
        this.stopWatch = stopWatch;
    }

    @Override
    public void run() {
        Result combinedResult = new Result();

        for (DbEvaluation evaluation : evaluations) {
            stopWatch.reset();
            stopWatch.start();

            evaluation.run();

            stopWatch.stop();

            combinedResult.durationMillis += stopWatch.durationTimeMillis();

            Result result = new Result();
            result.durationMillis = stopWatch.durationTimeMillis();

            singleResults.add(new SingleResult(result, evaluation));
        }
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
