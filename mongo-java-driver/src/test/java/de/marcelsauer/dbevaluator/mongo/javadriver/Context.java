package de.marcelsauer.dbevaluator.mongo.javadriver;

import com.mongodb.DB;
import com.mongodb.Mongo;

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
public class Context {

	/**
	 * this would be handled by a DI framework in the real world ;)
	 * 
	 * @return the fully initialized mongo db blog dao
	 * @throws Exception
	 */
	public MongoDbBlogDao getMongoDbBlogDao() throws Exception {
		Config conf = new Config(this.getClass().getClassLoader().getResourceAsStream("db.properties"));
		Mongo mongo = new Mongo(conf.getDbServer(), conf.getDbServerPort());
		DB db = mongo.getDB(conf.getDbName());

		// in a real app they would be interface based ;-)
		DomainToMongoMapper toMongo = new DomainToMongoMapper();
		MongoToDomainMapper fromMongo = new MongoToDomainMapper();

		return new MongoDbBlogDao(db, toMongo, fromMongo);
	}
}
