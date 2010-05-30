package de.marcelsauer.dbevaluator.mongo.javadriver;

import java.util.Collection;

import org.apache.log4j.NDC;

import de.marcelsauer.dbevaluator.DbEvaluation;
import de.marcelsauer.dbevaluator.LoggingCallback;
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
	private Collection<Blog> blogs;

	public MongoDbEvaluation(MongoDbBlogDao mongoDao, Collection<Blog> blogs) {
		this.mongoDao = mongoDao;
		this.blogs = blogs;
	}

	@Override
	public void run(LoggingCallback log) {
		for (Blog blog : blogs) {
			mongoDao.persistOrUpdate(blog);
			log.log("persisted blog '" + blog.title + "' to db.");
		}

		for (Blog blog : blogs) {
			Blog blogFromDb = mongoDao.load(blog.title);
			log.log("loaded blog: '" + blog.title + "' with id: " + blogFromDb.id);
			log.push("     ");
			for (Post post : blogFromDb.posts) {
				log.log("loaded post with content '" + post.content + "'");
			}
			log.pop();
		}
	}
}
