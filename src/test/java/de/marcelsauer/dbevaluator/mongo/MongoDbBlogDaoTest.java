package de.marcelsauer.dbevaluator.mongo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.Mongo;

import de.marcelsauer.dbevaluator.BlogDao;
import de.marcelsauer.dbevaluator.Config;
import de.marcelsauer.dbevaluator.model.Blog;
import de.marcelsauer.dbevaluator.model.Post;

public class MongoDbBlogDaoTest {

    private static final String UPDATED_AUTHOR = "the updated author";
    private static final String HEADLINE = "the headline";
    private static final String CONTENT = "the content";
    private static final String AUTHOR = "the author";
    private static final String TITLE = "test blog";
    private final BlogDao dao;

    public MongoDbBlogDaoTest() throws Exception {
        Mongo mongo = new Mongo(Config.MongoDb.DB_SERVER, Config.MongoDb.DB_SERVER_PORT);
        DB db = mongo.getDB(Config.MongoDb.DB_NAME);
        dao = new MongoDbBlogDao(db);
    }

    @Test
    public void thatPersistOrUpdateWorks() {
        Blog blog = getBlog();
        dao.persistOrUpdate(blog);

        Blog blogFromDb = dao.load(TITLE);
        assertEquals(TITLE, blogFromDb.title);

        assertTrue(blogFromDb.posts.size() == 1);
        Post post = blogFromDb.posts.get(0);
        assertEquals(AUTHOR, post.author);
        assertEquals(CONTENT, post.content);
        assertEquals(HEADLINE, post.headline);

        // now we update
        blog.posts.get(0).author = UPDATED_AUTHOR;
        dao.persistOrUpdate(blog);

        blogFromDb = dao.load(TITLE);
        assertEquals(TITLE, blogFromDb.title);
        assertTrue(blogFromDb.posts.size() == 1);
        post = blogFromDb.posts.get(0);
        assertEquals(UPDATED_AUTHOR, post.author);
        assertEquals(CONTENT, post.content);
        assertEquals(HEADLINE, post.headline);

    }

    private Blog getBlog() {
        Blog blog = new Blog(TITLE);

        Post post = new Post();
        post.author = AUTHOR;
        post.content = CONTENT;
        post.headline = HEADLINE;
        post.date = new Date();

        blog.posts.add(post);
        return blog;
    }
}
