package de.marcelsauer.dbevaluator.mongo.javadriver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.ObjectId;

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
public class MongoToDomainMapper {

	public Blog toBlog(DBObject blogFromDb) {
		Blog blog = new Blog((String) blogFromDb.get(Constants.TITLE_KEY));
		blog.id = ((ObjectId) blogFromDb.get("_id")).toString();
		// get posts
		blog.posts.addAll(toPosts((BasicDBList) blogFromDb.get(Constants.POSTS_KEY), blog));
		return blog;
	}

	private Collection<Post> toPosts(BasicDBList posts, Blog blog) {
		Collection<Post> mapped = new ArrayList<Post>();
		for (Iterator<Object> iter = posts.iterator(); iter.hasNext();) {
			BasicDBObject postFromDb = (BasicDBObject) iter.next();
			Post post = new Post();
			post.blog = blog;
			post.author = postFromDb.getString(Constants.AUTHOR);
			post.content = postFromDb.getString(Constants.CONTENT);
			post.date = (Date) postFromDb.get(Constants.DATE);
			post.headline = postFromDb.getString(Constants.HEADLINE);
			post.tags = toDomainTags((BasicDBList) postFromDb.get(Constants.TAGS));
			mapped.add(post);
		}

		return mapped;
	}

	private Set<String> toDomainTags(BasicDBList tagsFromDb) {
		Set<String> tags = new HashSet<String>();
		if (tagsFromDb != null) {
			for (Iterator<Object> iter = tagsFromDb.iterator(); iter.hasNext();) {
				String tag = (String) iter.next();
				if (!StringUtils.isEmpty(tag)) {
					tags.add(tag);
				}
			}
		}
		return tags;
	}
}
