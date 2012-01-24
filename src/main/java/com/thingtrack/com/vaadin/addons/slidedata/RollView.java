package com.thingtrack.com.vaadin.addons.slidedata;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class RollView extends VerticalLayout implements BindingSourceComponent {
	private Component dataGridView;
	private Component dataCardView;
	
	private DisplayMode displayMode = DisplayMode.GRID; 
	
	public enum DisplayMode {
	    GRID,
	    CARD
	}
	
	public RollView(Component dataGridView, Component dataCardView) {
		setImmediate(false);
		setSizeFull();
		
		// set DataGridView Visible default
		dataGridView.setSizeFull();
		dataGridView.setVisible(true);
		
		addComponent(dataGridView);
		setExpandRatio(dataGridView, 1.0f);
		
		// set DataCardView not Visible default
		dataCardView.setVisible(false);
		dataCardView.setSizeFull();	
		
		addComponent(dataCardView);
		setExpandRatio(dataCardView, 0.0f); 
				
		this.dataGridView = dataGridView;
		this.dataCardView = dataCardView;
		
	}

	public Component getDataGridView() {
		return dataGridView;
		
	}

	public void setDataGridView(Component dataGridView) {
		this.dataGridView = dataGridView;
		
	}
	
	public Component getDataFormView() {
		return dataCardView;
		
	}

	public void setCardView(Component dataCardView) {
		this.dataCardView = dataCardView;
		
	}
	
	public void setDisplayMode(DisplayMode viewMode) {
		this.displayMode = viewMode;
		
		if (viewMode == DisplayMode.GRID) {
			dataGridView.setVisible(true);
			dataCardView.setVisible(false);
			
			setExpandRatio(dataGridView, 1.0f);
			setExpandRatio(dataCardView, 0.0f);
			
			this.displayMode = DisplayMode.GRID;	
		}
		else {
			dataGridView.setVisible(false);
			dataCardView.setVisible(true);
			
			setExpandRatio(dataGridView, 0.0f);
			setExpandRatio(dataCardView, 1.0f);
			
			this.displayMode = DisplayMode.CARD;
		}
			
	}

	public DisplayMode getDisplayMode() {
		return displayMode;
	}

	@Override
	public BindingSource<?> getBindingSource() {
		if (dataGridView.isVisible())
			return ((BindingSourceComponent)dataGridView).getBindingSource();
		else if (dataCardView.isVisible())
			return ((BindingSourceComponent)dataCardView).getBindingSource();
		
		return null;
	}

	@Override
	public void setBindingSource(BindingSource<?> bindingSource) {
		if (dataGridView.isVisible())
			((BindingSourceComponent)dataGridView).setBindingSource(bindingSource);
		else if (dataCardView.isVisible())
			((BindingSourceComponent)dataCardView).setBindingSource(bindingSource);
	}
}
