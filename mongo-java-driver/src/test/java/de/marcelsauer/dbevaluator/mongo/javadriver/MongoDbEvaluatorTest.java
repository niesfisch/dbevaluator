package de.marcelsauer.dbevaluator.mongo.javadriver;

import java.util.Collection;

import com.mongodb.DB;
import com.mongodb.Mongo;

import de.marcelsauer.dbevaluator.AbstractDbEvaluatorTest;
import de.marcelsauer.dbevaluator.DbEvaluation;
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
public class MongoDbEvaluatorTest extends AbstractDbEvaluatorTest {

	private final Config conf = new Config(this.getClass().getClassLoader().getResourceAsStream("db.properties"));

	@Override
	protected int numberOfBlogsToCreate() {
		return 10;
	}

	@Override
	protected int numberOfPostsPerBlogToBeCreated() {
		return 100;
	}

	@Override
	public DbEvaluation createDbEvaluation(Collection<Blog> blogs) throws Exception {
		Mongo mongo = new Mongo(conf.getDbServer(), conf.getDbServerPort());
		DB db = mongo.getDB(conf.getDbName());

		MongoDbBlogDao mongoDao = new MongoDbBlogDao(db);
		DbEvaluation mongoEval = new MongoDbEvaluation(mongoDao, blogs);
		return mongoEval;
	}
}
