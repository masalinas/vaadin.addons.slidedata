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
package com.thingtrack.com.vaadin.addons.slidedata;

import java.util.Iterator;
import java.util.LinkedList;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.ClientWidget;
import com.vaadin.ui.Component;

import com.thingtrack.com.vaadin.addons.slidedata.gwt.client.VIkarusUList;
/**
 * ######IkarusUList#####
 *  -Vaadin representation of HTML UL tag
 * 
 * @author Alper Turkyilmaz - VelocitiSoftware - 2011
 * @version 1.0.2
 * 
 */
@SuppressWarnings("serial")
@ClientWidget(VIkarusUList.class)
public class IkarusUList extends AbstractLayout {

	protected LinkedList<Component> components = new LinkedList<Component>();

	public void addComponent(Component c) {
		IkarusListItem li = (IkarusListItem) c;

		components.add(li);
		try {
			super.addComponent(li);
			requestRepaint();
		} catch (IllegalArgumentException e) {
			components.remove(li);
			throw e;
		}
	}

	public void addComponentAsFirst(Component c) {
		IkarusListItem li = (IkarusListItem) c;
		components.addFirst(li);
		try {
			super.addComponent(li);
			requestRepaint();
		} catch (IllegalArgumentException e) {
			components.remove(li);
			throw e;
		}
	}

	public void addComponent(Component c, int index) {
		IkarusListItem li = (IkarusListItem) c;
		components.add(index, li);
		try {
			super.addComponent(li);
			requestRepaint();
		} catch (IllegalArgumentException e) {
			components.remove(li);
			throw e;
		}
	}

	public void removeComponent(Component c) {
		IkarusListItem li = (IkarusListItem) c;
		components.remove(li);
		super.removeComponent(li);
		requestRepaint();
	}

	public Iterator<Component> getComponentIterator() {
		return components.iterator();
	}

	public int getComponentCount() {
		return components.size();
	}

	public void paintContent(PaintTarget target) throws PaintException {
		super.paintContent(target);
		// Adds all items in all the locations
		for (Component c : components) {
			// Paint child component UIDL
			c.paint(target);
		}

	}

	public void replaceComponent(Component oldComponent, Component newComponent) {
		IkarusListItem oldli = (IkarusListItem) oldComponent;
		IkarusListItem newli = (IkarusListItem) newComponent;

		// Gets the locations
		int oldLocation = -1;
		int newLocation = -1;
		int location = 0;
		for (final Iterator<Component> i = components.iterator(); i.hasNext();) {
			final Component component = i.next();

			if (component == oldli) {
				oldLocation = location;
			}
			if (component == newli) {
				newLocation = location;
			}

			location++;
		}

		if (oldLocation == -1) {
			addComponent(newli);
		} else if (newLocation == -1) {
			removeComponent(oldli);
			addComponent(newli, oldLocation);
		} else {
			if (oldLocation > newLocation) {
				components.remove(oldli);
				components.add(newLocation, oldli);
				components.remove(newli);
				components.add(oldLocation, newli);
			} else {
				components.remove(newli);
				components.add(oldLocation, newli);
				components.remove(oldli);
				components.add(newLocation, oldli);
			}

			requestRepaint();
		}
	}

	public Component getFirstComponent() {
		return components.getFirst();
	}

	public Component getLastComponent() {
		return components.getLast();
	}

	

}
