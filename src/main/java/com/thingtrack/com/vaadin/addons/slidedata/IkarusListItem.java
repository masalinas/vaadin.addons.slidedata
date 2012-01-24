package com.thingtrack.com.vaadin.addons.slidedata;

import java.util.Iterator;
import java.util.LinkedList;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.ClientWidget;
import com.vaadin.ui.Component;
import com.thingtrack.com.vaadin.addons.slidedata.gwt.client.VIkarusListItem;

/**
 * ######IkarusListItem#####
 *  -Vaadin representation of HTML LI tag
 * 
 * @author Alper Turkyilmaz - VelocitiSoftware - 2011
 * @version 1.0.2
 * 
 */
@SuppressWarnings("serial")
@ClientWidget(VIkarusListItem.class)
public class IkarusListItem extends AbstractLayout {

	protected LinkedList<Component> components = new LinkedList<Component>();

	public void addComponent(Component c) {

		components.add(c);
		try {
			super.addComponent(c);
			requestRepaint();
		} catch (IllegalArgumentException e) {
			components.remove(c);
			throw e;
		}
	}

	public void addComponentAsFirst(Component c) {
		components.addFirst(c);
		try {
			super.addComponent(c);
			requestRepaint();
		} catch (IllegalArgumentException e) {
			components.remove(c);
			throw e;
		}
	}

	public void addComponent(Component c, int index) {
		components.add(index, c);
		try {
			super.addComponent(c);
			requestRepaint();
		} catch (IllegalArgumentException e) {
			components.remove(c);
			throw e;
		}
	}

	public void removeComponent(Component c) {
		components.remove(c);
		super.removeComponent(c);
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
		// Gets the locations
		int oldLocation = -1;
		int newLocation = -1;
		int location = 0;
		for (final Iterator<Component> i = components.iterator(); i.hasNext();) {
			final Component component = i.next();

			if (component == oldComponent) {
				oldLocation = location;
			}
			if (component == newComponent) {
				newLocation = location;
			}
			location++;
		}

		if (oldLocation == -1) {
			addComponent(newComponent);
		} else if (newLocation == -1) {
			removeComponent(oldComponent);
			addComponent(newComponent, oldLocation);
		} else {
			if (oldLocation > newLocation) {
				components.remove(oldComponent);
				components.add(newLocation, oldComponent);
				components.remove(newComponent);
				components.add(oldLocation, newComponent);
			} else {
				components.remove(newComponent);
				components.add(oldLocation, newComponent);
				components.remove(oldComponent);
				components.add(newLocation, oldComponent);
			}
			requestRepaint();
		}
	}
}
