package com.thingtrack.com.vaadin.addons.slidedata.gwt.client;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.DOM;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.UIDL;
import com.vaadin.terminal.gwt.client.ui.VCustomComponent;

public class VSlideData extends VCustomComponent {

	private ApplicationConnection client;
	private String id;

	public VSlideData() {
		setElement(DOM.createElement("DIV"));

	}

	public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
		super.updateFromUIDL(uidl, client);

		id = uidl.getId();
		
		getWidget().getElement().setId("slideData" + id);

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				// initialize slider jquery component
				slideData(id);

			}
		});

		if (uidl.hasAttribute("function")) {
			String function = uidl.getStringAttribute("function");

			if (function == "slideGoto") {
				int slideNumber = uidl.getIntAttribute("slideNumber");
				
				slideGoto(id, slideNumber);
				
			}
			
		}

	}

	public static native void slideData(String id)
	/*-{
	 	  	$wnd.$("#slideData"+id).sliders({ delay: 2500, speed: 500, play: false, keyboardEvents: false });
	}-*/;

	public static native void slideGoto(String id, int i)
	/*-{
	 	  	$wnd.$("#slideData"+id).sliders('goto', i);
	}-*/;

}
