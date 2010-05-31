package de.marcelsauer.dbevaluator;

import org.apache.log4j.NDC;

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
public interface LogCallback {

	/**
	 * logs a message during testrun
	 * 
	 * @param toLog
	 */
	void log(String toLog);

	/**
	 * pushes a string onto the logging stack. this will be prefixed for all
	 * subsequent calls to {@link LogCallback#log(String)}. this is silimar
	 * to {@link NDC#push(String)}
	 * 
	 * @param string
	 *            to log
	 */
	void push(String string);

	/**
	 * pops the last string from the stack. this is silimar to {@link NDC#pop()}
	 */
	void pop();
}
