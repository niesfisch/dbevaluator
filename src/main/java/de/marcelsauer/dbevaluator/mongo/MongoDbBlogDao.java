package de.marcelsauer.dbevaluator.mongo;

import com.mongodb.Mongo;

import de.marcelsauer.dbevaluator.BlogDao;
import de.marcelsauer.dbevaluator.model.Blog;

/**
 * mongo db implementation
 * 
 * @author msauer
 * 
 */
public class MongoDbBlogDao implements BlogDao {

    private final Mongo mongo;

    public MongoDbBlogDao(Mongo mongo) {
        this.mongo = mongo;
    }

    @Override
    public Blog load(String name) {
        return null;
    }

    @Override
    public void persist(Blog blog) {
    }

}
