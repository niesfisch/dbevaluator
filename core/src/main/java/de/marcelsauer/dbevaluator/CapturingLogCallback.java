package de.marcelsauer.dbevaluator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

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
public class CapturingLogCallback implements LogCallback {
	private final List<String> captured = new ArrayList<String>();
	private final Deque<String> stack = new ArrayDeque<String>();

	@Override
	public void log(String toLog) {
		String peek = stack.peek();
		if (peek != null) {
			captured.add(peek + toLog);
		} else {
			captured.add(toLog);
		}
	}

	public List<String> getCaptured() {
		return Collections.unmodifiableList(captured);
	}

	@Override
	public void pop() {
		stack.pop();
	}

	@Override
	public void push(String string) {
		stack.push(string);
	}
}
