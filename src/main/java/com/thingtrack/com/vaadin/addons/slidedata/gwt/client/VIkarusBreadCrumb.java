/* 
 * Copyright 2011 VelocitiSoftware
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.thingtrack.com.vaadin.addons.slidedata.gwt.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.UIDL;
import com.vaadin.terminal.gwt.client.ui.VCustomComponent;
/**
 * ######VIkarusBreadCrumb##### 
  * -Needs JQuery js and ikarusbreadcrumb.js
 * @author Alper Turkyilmaz - VelocitiSoftware - 2011
 * @version 1.0.2
 * 
 */
public class VIkarusBreadCrumb extends VCustomComponent {

	private ApplicationConnection client;
	private String id;

	public VIkarusBreadCrumb() {

	}

	public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
		super.updateFromUIDL(uidl, client);
		id = uidl.getId();

		getWidget().getElement().setId("breadCrumb" + id);
		final IkarusJSBreadCrumbOptions options = JavaScriptObject.createObject().cast();
		options.setShowSpeed(uidl.getStringAttribute("showspeed"));
		options.setHideSpeed(uidl.getStringAttribute("hidespeed"));
		options.setCollapsible(uidl.getBooleanAttribute("collapsible"));
		options.setCollapsedWidth(uidl.getIntAttribute("collapsedwidth"));
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				breadCrumb(id, options);
				

			}
		});
		
		
	}

	public static native void breadCrumb(String id, IkarusJSBreadCrumbOptions options)
	/*-{
	 	  	$wnd.$("#breadCrumb"+id).ikarusBreadcrumbs(options);
	}-*/;
	
}
