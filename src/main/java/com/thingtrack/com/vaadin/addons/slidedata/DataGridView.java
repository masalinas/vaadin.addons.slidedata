package com.thingtrack.com.vaadin.addons.slidedata;

import java.io.Serializable;

import com.thingtrack.com.vaadin.addons.slidedata.BindingSource.IndexChangeEvent;
import com.thingtrack.com.vaadin.addons.slidedata.BindingSource.IndexChangeListener;

import com.vaadin.terminal.gwt.client.MouseEventDetails;
import com.vaadin.ui.Table;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction;

@SuppressWarnings("serial")
public class DataGridView extends Table implements BindingSourceComponent, IndexChangeListener, ItemClickListener {
	private BindingSource<?> bindingSource;	
    private BindingSourceChangeDataGridViewListener listener;
    
	public DataGridView() {
		this(null);
		
	}
	
	public DataGridView(String caption) {
		super(caption);
				
		// define default DataGridView properties
		DefaultDataGridViewConfig();
		
		// add Shortcuts listeners
		addShortCuts();
		
		// add Item Click Listener
		addListener((ItemClickListener)this);
	}
	
	private void DefaultDataGridViewConfig() {
		this.setSelectable(true);
		this.setImmediate(true);

	}
	
	private void addShortCuts() {
		this.addShortcutListener(new ShortcutListener("Enter", ShortcutAction.KeyCode.ENTER, new int[0]) {		
			@Override
			public void handleAction(Object sender, Object target) {
				DataGridView slideComponent = (DataGridView)target;
				
				if (slideComponent == null)
					return;
				
				// send bound data and execute the slide movement
				if (slideComponent.getValue() != null)
					slideComponent.getBindingSource().setItemId(this, true, slideComponent.getValue());
				
			}
		});
					
		this.addShortcutListener(new ShortcutListener("Down", ShortcutAction.KeyCode.ARROW_DOWN, new int[0]) {		
			@Override
			public void handleAction(Object sender, Object target) {
				DataGridView slideComponent = (DataGridView)target;
				
				if (slideComponent == null)
					return;
				
				// send bound data and execute the slide movement
				if (slideComponent.getValue() != null) {					
					for (int index = 0; index < slideComponent.getContainerPropertyIds().size(); index ++) {
						if (slideComponent.getIdByIndex(index) == slideComponent.getValue()) {
							slideComponent.getBindingSource().setItemId(slideComponent.getIdByIndex(index + 1));
							
							return;
						}
					}
					
				}
				
			}
		});
		
		this.addShortcutListener(new ShortcutListener("Up", ShortcutAction.KeyCode.ARROW_UP, new int[0]) {		
			@Override
			public void handleAction(Object sender, Object target) {
				DataGridView slideComponent = (DataGridView)target;
				
				if (slideComponent == null)
					return;
				
				// send bound data and execute the slide movement
				if (slideComponent.getValue() != null) {					
					for (int index = 0; index < slideComponent.getContainerPropertyIds().size(); index ++) {
						if (slideComponent.getIdByIndex(index) == slideComponent.getValue()) {
							slideComponent.getBindingSource().setItemId(slideComponent.getIdByIndex(index - 1));
							
							return;
						}
					}
					
				}
				
			}
		});
		
	}
	
	@Override
	public void setBindingSource(BindingSource<?> bindingSource) {
		this.bindingSource = bindingSource;
	
		bindingSource.addListenerDataGridView((IndexChangeListener)this);

		setContainerDataSource(bindingSource);
		
		if (listener != null)
			listener.bindingSourceChangeDataGridView(new BindingSourceChangeDataGrifViewEvent(this, bindingSource));
		
	}
	
	@Override
	public BindingSource<?> getBindingSource() {
		return this.bindingSource;
		
	}
	
	@Override
	public void bindingSourceIndexChange(IndexChangeEvent event) {
		if (bindingSource != null) 
			select(event.getRegister());
		
	}

	@Override
	public void itemClick(ItemClickEvent event) {	
		// send bound data and execute the slide movement from LEFT MOUSE Click Button
		if (event.getItemId() != null && event.getButton() == MouseEventDetails.BUTTON_RIGHT)				
			bindingSource.setItemId(this, true, event.getItemId());
		else if (event.getItemId() != null && event.getButton() == MouseEventDetails.BUTTON_LEFT)
			bindingSource.setItemId(event.getItemId());
		else if (event.getItemId() != null && event.isDoubleClick()) {
			
		}
		
	}

	public void addListener(BindingSourceChangeDataGridViewListener listener) {
		this.listener = listener;
		
	}
	
    public interface BindingSourceChangeDataGridViewListener extends Serializable {
        public void bindingSourceChangeDataGridView(BindingSourceChangeDataGrifViewEvent event);

    }
    
    public static class BindingSourceChangeDataGrifViewEvent {
    	private DataGridView source;
		private BindingSource<?> bindingSource;
		
		public BindingSourceChangeDataGrifViewEvent(DataGridView source, BindingSource<?> bindingSource) {
			this.setSource(source);
			this.setBindingSource(bindingSource);
			
		}

		public void setBindingSource(BindingSource<?> bindingSource) {
			this.bindingSource = bindingSource;
			
		}

		public BindingSource<?> getBindingSource() {
			return bindingSource;
			
		}

		public void setSource(DataGridView source) {
			this.source = source;
		}

		public DataGridView getSource() {
			return source;
		}
		
	 }
}
