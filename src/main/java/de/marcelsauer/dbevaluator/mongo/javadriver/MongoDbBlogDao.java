package de.marcelsauer.dbevaluator.mongo.javadriver;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import de.marcelsauer.dbevaluator.BlogDao;
import de.marcelsauer.dbevaluator.model.Blog;

/**
 * mongo db implementation
 * 
 * @author msauer
 * 
 */
public class MongoDbBlogDao implements BlogDao {

    private static final String POSTS_KEY = "posts";
    private static final String TITLE_KEY = "title";
    private static final String COLLECTION_NAME = "blogs";
    private final DB db;

    public MongoDbBlogDao(DB db) {
        this.db = db;
    }

    @Override
    public Blog load(String title) {
        DBCollection blogs = getBlogs();
        DBObject matcher = new BasicDBObject(TITLE_KEY, title);
        DBObject blog = blogs.findOne(matcher);
        return map(blog);
    }

    @Override
    public void persistOrUpdate(Blog blog) {

        DBObject blogObject = new BasicDBObject();
        blogObject.put(TITLE_KEY, blog.title);
        blogObject.put(POSTS_KEY, blog.posts);
        
        Blog existing = load(blog.title);
        
        if (existing == null) {
            getBlogs().save(blogObject);
        } else {
            DBObject matching = new BasicDBObject(TITLE_KEY, blog.title);
            getBlogs().update(matching, blogObject, true, true);
        }
    }

    @Override
    public Blog delete(String title) {
        Blog blog = load(title);

        DBObject toRemove = new BasicDBObject(TITLE_KEY, title);
        getBlogs().remove(toRemove);

        return blog;
    }

    private DBCollection getBlogs() {
        return db.getCollection(COLLECTION_NAME);
    }

    private Blog map(DBObject blog) {
        Blog mapped = new Blog((String) blog.get(TITLE_KEY));
        // get posts
        mapped.posts.add(null);
        return mapped;
    }

}
