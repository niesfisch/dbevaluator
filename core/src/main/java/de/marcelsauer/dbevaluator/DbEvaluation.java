package de.marcelsauer.dbevaluator;

import java.util.Collection;

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
public interface DbEvaluation {

	/**
	 * @param logCallback
	 *            to be used to capture runtime information
	 */
	void setLogCallback(LogCallback logCallback);

	/**
	 * starts a new transaction in the underlaying datastore
	 * 
	 * @throws UnsupportedOperationException
	 *             if starting of transactions is not supported
	 */
	void startTransaction() throws UnsupportedOperationException;

	/**
	 * commit the currently running transaction in the underlaying datastore
	 * 
	 * @throws UnsupportedOperationException
	 *             if starting of transactions is not supported
	 */
	void commit() throws UnsupportedOperationException;

	/**
	 * @param blog
	 *            to persist, containing the whole object graph
	 * @throws UnsupportedOperationException
	 *             if persisting is not supported
	 */
	void persist(Collection<Blog> blogs) throws UnsupportedOperationException;

	/**
	 * @param blogTitles
	 *            blogs to load
	 * @return Collection<Blog> the fully eagerly initialized blogs (full graph)
	 * @throws UnsupportedOperationException
	 *             if loading of blogs is not supported
	 */
	Collection<Blog> load(Collection<String> blogTitles) throws UnsupportedOperationException;

	/**
	 * @param tags
	 *            to find the posts for
	 * @return fully loaded (eagerly) posts found
	 * @throws UnsupportedOperationException
	 */
	Collection<Post> findPostsWithTags(String... tags) throws UnsupportedOperationException;

	/**
	 * called once per db evaluation before all the tests are run. this is the
	 * place to clean the datastore or do other pre test stuff.
	 */
	void beforeTestrun();

	/**
	 * called once per db evaluation after all tests have been run. this is the
	 * place to clean the datastore or do other pre test stuff.
	 */
	void afterTestrun();
}
