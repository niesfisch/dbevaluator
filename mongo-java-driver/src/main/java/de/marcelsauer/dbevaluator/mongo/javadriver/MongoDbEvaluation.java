package de.marcelsauer.dbevaluator.mongo.javadriver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import de.marcelsauer.dbevaluator.BlogDao;
import de.marcelsauer.dbevaluator.DbEvaluation;
import de.marcelsauer.dbevaluator.LogCallback;
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
public class MongoDbEvaluation implements DbEvaluation {

	private final MongoDbBlogDao mongoDao;
	private LogCallback log;

	public MongoDbEvaluation(MongoDbBlogDao mongoDao) {
		this.mongoDao = mongoDao;
	}

	@Override
	public void persist(Collection<Blog> blogs) throws UnsupportedOperationException {
		for (Blog blog : blogs) {
			mongoDao.persistOrUpdate(blog);
			log.log("persisted blog '" + blog.title + "' to db.");
		}
	}

	@Override
	public Collection<Blog> load(Collection<String> blogTitles) throws UnsupportedOperationException {
		Collection<Blog> blogs = new ArrayList<Blog>();
		for (String title : blogTitles) {
			Blog blog = mongoDao.load(title);
			blogs.add(blog);
			log.log("loaded blog: '" + title + "' with id: " + blog.id);
			log.push("     ");
			for (Post post : blog.posts) {
				log.log("loaded post with content '" + post.content + "'");
			}
			log.pop();
		}
		return blogs;
	}

	@Override
	public void setLogCallback(LogCallback logCallback) {
		this.log = logCallback;
	}

	@Override
	public void commit() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("transactions are not supported by Mongo DB");
	}

	@Override
	public void startTransaction() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("transactions are not supported by Mongo DB");
	}

	@Override
	public Collection<Post> findPostsWithTags(String ... tags) throws UnsupportedOperationException {
		return mongoDao.findPostsWithTags(tags);
	}

	@Override
	public void beforeTestrun() throws UnsupportedOperationException {
		mongoDao.clearCollection("blogs");
	}
}
