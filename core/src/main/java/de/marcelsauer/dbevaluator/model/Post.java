package de.marcelsauer.dbevaluator.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DB Evaluator Copyright (C) 2010 Marcel Sauer <marcel DOT sauer AT gmx DOT de>
 * 
 * This file is part of DB Evaluator.
 * 
 * DB Evaluator is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * DB Evaluator is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * DB Evaluator. If not, see <http://www.gnu.org/licenses/>.
 */
public class Post {
	public Blog blog;
	public Date date;
	public String author;
	public String headline;
	public String content;
	public Set<String> tags = new HashSet<String>();

	@Override
	public String toString() {
		return "Post [author=" + author + ", content=" + content + ", date="
				+ date + ", headline=" + headline + "]";
	}

	public void addTag(String tag) {
		this.tags.add(tag);
	}
}
