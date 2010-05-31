package de.marcelsauer.dbevaluator.mongo.javadriver;

import org.apache.commons.lang.Validate;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import de.marcelsauer.dbevaluator.BlogDao;
import de.marcelsauer.dbevaluator.model.Blog;
import de.marcelsauer.dbevaluator.model.Constants;

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
public class MongoDbBlogDao implements BlogDao {

	private final DB db;

	// this would be injected in a real world apps ;-)
	private final DomainToMongoMapper toMongo;
	private final MongoToDomainMapper fromMongo;

	public MongoDbBlogDao(DB db, DomainToMongoMapper toMongo, MongoToDomainMapper fromMongo) {
		this.db = db;
		this.toMongo = toMongo;
		this.fromMongo = fromMongo;
	}

	@Override
	public Blog load(String title) {
		Validate.notEmpty(title);
		DBCollection blogs = getBlogs();
		DBObject matcher = new BasicDBObject(Constants.TITLE_KEY, title);
		DBObject blog = blogs.findOne(matcher);
		return (blog != null) ? fromMongo.toBlog(blog) : null;
	}

	@Override
	public void persistOrUpdate(Blog blog) {
		Validate.notNull(blog);
		Validate.notEmpty(blog.title);

		Blog existing = load(blog.title);

		DBObject mappedBlog = toMongo.toPersistableBlog(blog);
		if (existing == null) {
			getBlogs().save(mappedBlog);
		} else {
			DBObject matching = new BasicDBObject(Constants.TITLE_KEY, blog.title);
			getBlogs().update(matching, mappedBlog);
		}
	}

	@Override
	public Blog delete(String title) {
		Validate.notEmpty(title);
		Blog existing = load(title);
		DBObject toRemove = new BasicDBObject(Constants.TITLE_KEY, title);
		getBlogs().remove(toRemove);

		return existing;
	}

	private DBCollection getBlogs() {
		return db.getCollection(Constants.COLLECTION_NAME);
	}

	@Override
	public void delete(Blog blog) {
		Validate.notNull(blog);
		delete(blog.title);
	}

}
