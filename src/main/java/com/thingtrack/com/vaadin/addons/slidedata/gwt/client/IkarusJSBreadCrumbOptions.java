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
/**
 * ######IkarusJSBreadCrumbOptions##### 
 * -Breadcrumb options that are passed to JQuery.
 * -Needs JQuery js.
 * @author Alper Turkyilmaz - VelocitiSoftware - 2011
 * @version 1.0.2
 * 
 */
public class IkarusJSBreadCrumbOptions extends JavaScriptObject {

	protected IkarusJSBreadCrumbOptions() {

	}

	public final native String getShowSpeed()
	/*-{
		return this.showSpeed;
	}-*/;

	public final native void setShowSpeed(String showSpeed)
	/*-{
		this.showSpeed = showSpeed;
	}-*/;

	public final native String getHideSpeed()
	/*-{
		return this.hideSpeed;
	}-*/;

	public final native void setHideSpeed(String hideSpeed)
	/*-{
		this.hideSpeed = hideSpeed;
	}-*/;

	public final native boolean getCollapsible()
	/*-{
		return this.collapsible;
	}-*/;

	public final native void setCollapsible(boolean collapsible)
	/*-{
		this.collapsible = collapsible;
	}-*/;

	public final native int getCollapsedWidth()
	/*-{
		return this.collapsedWidth;
	}-*/;

	public final native void setCollapsedWidth(int collapsedWidth)
	/*-{
		this.collapsedWidth = collapsedWidth;
	}-*/;

}
