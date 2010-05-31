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
public class StopWatch {

	private long start;
	private long end;

	private enum State {
		STOPPED, STARTED, RUNNING
	};

	private State state = State.STOPPED;

	public void reset() {
		state = State.STOPPED;
		start = 0;
		end = 0;
	}

	public void start() {
		if (state != State.STOPPED) {
			throw new IllegalStateException("stop watch first!");
		}
		state = State.STARTED;
		start = System.currentTimeMillis();
	}

	public void stop() {
		if (state != State.STARTED) {
			throw new IllegalStateException("start watch first!");
		}
		state = State.STOPPED;
		end = System.currentTimeMillis();
	}

	public long durationTimeMillis() {
		return end - start;
	}
}
