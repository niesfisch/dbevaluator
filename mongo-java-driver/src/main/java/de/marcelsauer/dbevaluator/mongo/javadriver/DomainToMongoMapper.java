package de.marcelsauer.dbevaluator.mongo.javadriver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import de.marcelsauer.dbevaluator.model.Blog;
import de.marcelsauer.dbevaluator.model.Constants;
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
public class DomainToMongoMapper {

	public DBObject toPersistablePost(Post post) {
		DBObject mapped = new BasicDBObject();
		mapped.put(Constants.AUTHOR, post.author);
		mapped.put(Constants.CONTENT, post.content);
		mapped.put(Constants.DATE, post.date);
		mapped.put(Constants.HEADLINE, post.headline);
		return mapped;
	}

	public Collection<DBObject> toPersistablePosts(Collection<Post> posts) {
		List<DBObject> mapped = new ArrayList<DBObject>();
		for (Post post : posts) {
			mapped.add(toPersistablePost(post));
		}
		return mapped;
	}

	public DBObject toPersistableBlog(Blog blog) {
		DBObject mapped = new BasicDBObject();
		mapped.put(Constants.TITLE_KEY, blog.title);
		mapped.put(Constants.POSTS_KEY, toPersistablePosts(blog.posts));
		return mapped;
	}

}
