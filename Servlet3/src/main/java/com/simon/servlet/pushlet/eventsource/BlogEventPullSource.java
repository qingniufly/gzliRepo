package com.simon.servlet.pushlet.eventsource;

import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventPullSource;

public class BlogEventPullSource extends EventPullSource {

	@Override
	protected long getSleepTime() {
		return 1000;
	}

	@Override
	protected Event pullEvent() {
		Event event = Event.createDataEvent("blog_event");
		event.setField("blog_name", "Hello, World");
		return event;
	}

}
