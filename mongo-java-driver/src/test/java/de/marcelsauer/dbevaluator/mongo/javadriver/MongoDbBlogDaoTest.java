package de.marcelsauer.dbevaluator.mongo.javadriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import de.marcelsauer.dbevaluator.BlogDao;
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
public class MongoDbBlogDaoTest {

	private static final String UPDATED_AUTHOR = "the updated author";
	private static final String HEADLINE = "the headline";
	private static final String CONTENT = "the content";
	private static final String AUTHOR = "the author";
	private static final String TITLE = "test blog";
	private final BlogDao dao;

	public MongoDbBlogDaoTest() throws Exception {
		dao = new Context().getMongoDbBlogDao();
	}

	@Before
	public void setup() {
		cleanup();
	}

	private void cleanup() {
		dao.delete(TITLE);
		assertNull(dao.load(TITLE));
	}

	@Test
	public void thatDeleteWorks() {
		Blog blog = getBlog();
		dao.persistOrUpdate(blog);

		assertNotNull(dao.load(TITLE));

		dao.delete(TITLE);
		assertNull(dao.load(TITLE));
	}

	@Test
	public void thatLoadWorks() {
		Blog blog = getBlog();
		dao.persistOrUpdate(blog);

		Blog blogFromDb = dao.load(TITLE);
		assertEquals(TITLE, blogFromDb.title);

		Post post = blogFromDb.posts.get(0);
		assertTrue(blogFromDb.posts.size() == 1);
		assertEquals(AUTHOR, post.author);
		assertEquals(CONTENT, post.content);
		assertEquals(HEADLINE, post.headline);
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
