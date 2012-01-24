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

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Container;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.RenderSpace;
import com.vaadin.terminal.gwt.client.UIDL;
/**
 * ######VIkarusUList##### 
 
 * @author Alper Turkyilmaz - VelocitiSoftware - 2011
 * @version 1.0.2
 * 
 */
public class VIkarusUList extends ComplexPanel implements Container {
	private static String CLASSNAME = "v-ul";

	protected ApplicationConnection client;
	protected String id;
	protected Map<String, Widget> childPIDs = new HashMap<String, Widget>();

	public VIkarusUList() {
		setElement(DOM.createElement("UL"));
	}

	public void add(Widget w) {
		super.add(w, getElement());
	}

	public void insert(Widget w, int beforeIndex) {
		super.insert(w, getElement(), beforeIndex, true);
	}

	public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
		this.client = client;
		this.id = uidl.getId();

		if (client.updateComponent(this, uidl, false)) {
			return;
		}

		Map<String, Widget> newChildPIDs = new HashMap<String, Widget>();
		for (final Iterator<Object> i = uidl.getChildIterator(); i.hasNext();) {
			final UIDL r = (UIDL) i.next();
			Paintable li = (Paintable) client.getPaintable(r);
			li.updateFromUIDL(r, client);
			Widget widgetLi = (Widget) li;
			childPIDs.remove(r.getId());
			newChildPIDs.put(r.getId(), widgetLi);
			if (!hasChildComponent(widgetLi)) {
				add(widgetLi);
			}
		}
		Collection<Widget> toBeRemovedWidgets = childPIDs.values();
		for (Iterator iterator = toBeRemovedWidgets.iterator(); iterator.hasNext();) {
			Widget widget = (Widget) iterator.next();
			remove(widget);
		}
		childPIDs = newChildPIDs;
	}

	public boolean hasChildComponent(Widget component) {
		return component.getParent() == this;
	}

	public void replaceChildComponent(Widget oldComponent, Widget newComponent) {
		int index = getWidgetIndex(oldComponent);
		if (index >= 0) {
			remove(oldComponent);
			insert(newComponent, index);
		}
	}

	public void updateCaption(Paintable component, UIDL uidl) {
	}

	public boolean requestLayout(Set<Paintable> children) {
		return true;
	}

	public RenderSpace getAllocatedSpace(Widget child) {
		return new RenderSpace(child.getOffsetWidth(), child.getOffsetHeight());
	}
}
