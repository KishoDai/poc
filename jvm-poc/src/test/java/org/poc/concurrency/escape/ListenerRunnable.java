package org.poc.concurrency.escape;


import java.util.List;

public class ListenerRunnable implements Runnable {
    private EventSource<AEventListener> source;

    public ListenerRunnable(EventSource<AEventListener> source) {
        this.source = source;
    }

    @Override
    public void run() {
        List<AEventListener> listeners = null;
        try {
            listeners = source.retrieveListeners();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (AEventListener eventListener : listeners) {
            eventListener.onEvent(new Object());
        }
    }

}