package de.marcelsauer.dbevaluator.mongo;

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
        start = System.currentTimeMillis();
    }

    public long durationTimeMillis() {
        return end - start;
    }
}
