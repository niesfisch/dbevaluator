package de.marcelsauer.dbevaluator;

import de.marcelsauer.dbevaluator.model.Blog;

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
public interface BlogDao {

	/**
	 * persists the blog to the datastore
	 * 
	 * @param blog
	 *            to persist, must not be null
	 */
	void persistOrUpdate(Blog blog);

	/**
	 * @param title
	 *            of the blog to load, must not be null or empty
	 * @return the blog
	 */
	Blog load(String title);

	/**
	 * @param title
	 *            of the blog to delete, must not be null or empty
	 * @return blog that was deleted
	 */
	Blog delete(String title);

	/**
	 * @param blog
	 *            to be deleted
	 */
	void delete(Blog blog);
}
