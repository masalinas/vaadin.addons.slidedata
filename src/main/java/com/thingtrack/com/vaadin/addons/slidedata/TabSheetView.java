package com.thingtrack.com.vaadin.addons.slidedata;

import java.io.Serializable;

import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;

@SuppressWarnings("serial")
public class TabSheetView extends TabSheet implements BindingSourceComponent, SelectedTabChangeListener {
	private SelectedTabViewChangeListener listener;
	
	public TabSheetView() {
		super();
			
		addListener((SelectedTabChangeListener)this);
		
	}
		
	@Override
	public BindingSource<?> getBindingSource() {
		Component tabDataSelected = this.getSelectedTab();
		RollView dataRollViewSelected = (RollView)tabDataSelected;
		
		if (dataRollViewSelected != null)
			return (BindingSource<?>)dataRollViewSelected.getBindingSource();
	
		return null;
	}

	@Override
	public void setBindingSource(BindingSource<?> bindingSource) {
		Component tabDataSelected = this.getSelectedTab();
		RollView dataRollViewSelected = (RollView)tabDataSelected;
		
		if (dataRollViewSelected != null)
			dataRollViewSelected.setBindingSource(bindingSource);
		
	}

	@Override
	public void selectedTabChange(SelectedTabChangeEvent event) {
		Component tabDataSelected = event.getTabSheet().getSelectedTab();
		RollView dataRollViewSelected = (RollView)tabDataSelected;
		
		if (listener!= null && dataRollViewSelected != null)
			listener.TabViewChange(new SelectedTabViewChangeEvent(event.getComponent(), dataRollViewSelected.getBindingSource()));
		
	}

	public void addListener(SelectedTabViewChangeListener listener) {
		this.listener = listener;
		
	}
	
	public interface SelectedTabViewChangeListener extends Serializable {
        public void TabViewChange(SelectedTabViewChangeEvent event);

    }
		
    public static class SelectedTabViewChangeEvent {
		private Component component;
		private BindingSource<?> bindingSource;
		
		public SelectedTabViewChangeEvent(Component component, BindingSource<?> bindingSource) {			
			this.component = component;
			this.bindingSource = bindingSource;
			
		}

		public void setComponent(Component component) {
			this.component = component;
		}

		public Component getComponent() {
			return component;
		}

		public void setBindingSource(BindingSource<?> bindingSource) {
			this.bindingSource = bindingSource;
		}

		public BindingSource<?> getBindingSource() {
			return bindingSource;
		}
		
	 }
    
}
