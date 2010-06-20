package de.marcelsauer.dbevaluator;

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
public class TestConfig {
	private static final String SYSTEM_PROPERTY_BLOGS = "blogs";
	private static final String SYSTEM_PROPERTY_POSTS = "posts";
	private static final String SYSTEM_PROPERTY_DUMP_INFO = "printCapturedInfo";
	private static final int NUMBER_OF_POSTS = 10000;
	private static final int NUMBER_OF_BLOGS = 10;

	/**
	 * @return the number of blogs to be created, subclasses are free to
	 *         override
	 */
	public int numberOfBlogsToCreated() {
		if (System.getProperty(SYSTEM_PROPERTY_BLOGS) != null) {
			return Integer.parseInt(System.getProperty(SYSTEM_PROPERTY_BLOGS));
		}
		return NUMBER_OF_BLOGS;
	}

	/**
	 * @return the amount of posts per blog that should be created
	 */
	public int numberOfPostsPerBlogToBeCreated() {
		if (System.getProperty(SYSTEM_PROPERTY_POSTS) != null) {
			return Integer.parseInt(System.getProperty(SYSTEM_PROPERTY_POSTS));
		}
		return NUMBER_OF_POSTS;
	}

	/**
	 * @return if the captured output during a test run should be printed to STDOUT
	 */
	public boolean shouldDumpCapturedOutput() {
		if (System.getProperty(SYSTEM_PROPERTY_DUMP_INFO) != null) {
			String value = String.valueOf(System.getProperty(SYSTEM_PROPERTY_DUMP_INFO));
			return (value != null) ? "true".equals(value) : false;
		}
		return false;
	}
}
