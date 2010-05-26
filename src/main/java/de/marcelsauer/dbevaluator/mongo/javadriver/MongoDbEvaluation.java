package de.marcelsauer.dbevaluator.mongo.javadriver;

import de.marcelsauer.dbevaluator.DbEvaluation;
import de.marcelsauer.dbevaluator.model.Blog;

public class MongoDbEvaluation implements DbEvaluation {

    private final MongoDbBlogDao mongoDao;
    private final Blog blog;

    public MongoDbEvaluation(MongoDbBlogDao mongoDao, Blog blog) {
        this.mongoDao = mongoDao;
        this.blog = blog;
    }

    @Override
    public void run() {
        // do everything....
    }
}
