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
class DbEvaluationTransactionWrapper implements DbEvaluation {

	private DbEvaluation delegate;

	public DbEvaluationTransactionWrapper(DbEvaluation delegate) {
		this.delegate = delegate;
	}

	@Override
	public void beforeTestrun() throws UnsupportedOperationException {
		delegate.startTransaction();
		try {
			this.delegate.beforeTestrun();
		} finally {
			delegate.commit();
		}
	}

	@Override
	public void commit() throws UnsupportedOperationException {
		this.delegate.commit();
	}

	@Override
	public Collection<Post> findPostsWithTags(String... tags) throws UnsupportedOperationException {
		delegate.startTransaction();
		try {
			return this.delegate.findPostsWithTags(tags);
		} finally {
			delegate.commit();
		}
	}

	@Override
	public Collection<Blog> load(Collection<String> blogTitles) throws UnsupportedOperationException {
		delegate.startTransaction();
		try {
			return this.delegate.load(blogTitles);
		} finally {
			delegate.commit();
		}
	}

	@Override
	public void persist(Collection<Blog> blogs) throws UnsupportedOperationException {
		delegate.startTransaction();
		try {
			this.delegate.persist(blogs);
		} finally {
			delegate.commit();
		}
	}

	@Override
	public void setLogCallback(LogCallback logCallback) {
		this.delegate.setLogCallback(logCallback);

	}

	@Override
	public void startTransaction() throws UnsupportedOperationException {
		this.delegate.startTransaction();
	}
}