package de.marcelsauer.dbevaluator;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.Mongo;

import de.marcelsauer.dbevaluator.TimedDbEvaluation.SingleResult;
import de.marcelsauer.dbevaluator.model.Blog;
import de.marcelsauer.dbevaluator.model.Post;
import de.marcelsauer.dbevaluator.mongo.MongoDbBlogDao;
import de.marcelsauer.dbevaluator.mongo.MongoDbEvaluation;
import de.marcelsauer.dbevaluator.mongo.StopWatch;

public class DbEvaluatorTest {

    private static final int MAX_POSTS = 10;

    @Test
    public void runAll() throws Exception {

        TimedDbEvaluation timedEvaluation = getTimedEvaluationToRun();

        timedEvaluation.run();

        dumpResults(timedEvaluation);
    }

    private void dumpResults(TimedDbEvaluation timedEvaluation) {
        List<SingleResult> singleResults = timedEvaluation.singleResults;

        System.out.println("total time taken: " + timedEvaluation.combinedResult.durationMillis);
        System.out.println("single results: ");

        for (SingleResult singleResult : singleResults) {
            System.out.println("db: " + singleResult.evaluation.getClass().getSimpleName());
            System.out.println("duration: " + singleResult.result.durationMillis);
        }
    }

    /**
     * wiring of all the actions to perform
     */
    private TimedDbEvaluation getTimedEvaluationToRun() throws Exception {
        StopWatch stopWatch = new StopWatch();

        Blog blog = createBlog();

        DbEvaluation mongoEval = createMongoDbEvaluation(blog);

        DbEvaluation[] evaluations = new DbEvaluation[] { mongoEval };
        TimedDbEvaluation timedEvaluation = new TimedDbEvaluation(evaluations, stopWatch);
        return timedEvaluation;
    }

    private DbEvaluation createMongoDbEvaluation(Blog blog) throws Exception {
        Mongo mongo = new Mongo(Config.MongoDb.DB_SERVER, Config.MongoDb.DB_SERVER_PORT);
        DB db = mongo.getDB(Config.MongoDb.DB_NAME);

        MongoDbBlogDao mongoDao = new MongoDbBlogDao(db);
        DbEvaluation mongoEval = new MongoDbEvaluation(mongoDao, blog);
        return mongoEval;
    }

    private Blog createBlog() {
        Blog blog = new Blog("the mighty db evaluation");
        for (int i = 1; i <= MAX_POSTS; i++) {
            Post post = new Post();
            post.date = new Date();
            post.author = "Marcel";
            post.headline = "the 1. blog post";
            post.content = "the 1. content";
            blog.add(post);
        }
        return blog;
    }
}
