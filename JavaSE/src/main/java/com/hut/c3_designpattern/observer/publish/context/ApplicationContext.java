package com.hut.c3_designpattern.observer.publish.context;

import com.hut.c3_designpattern.observer.publish.events.Event;
import com.hut.c3_designpattern.observer.publish.listeners.Listener;

public interface ApplicationContext {

    public void publish(Event event) throws Exception;

    void attach(Listener listener) throws Exception;

}
