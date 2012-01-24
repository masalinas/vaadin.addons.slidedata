package com.thingtrack.com.vaadin.addons.slidedata;

import java.util.Arrays;

import com.thingtrack.com.vaadin.addons.slidedata.BindingSource.IndexChangeEvent;
import com.thingtrack.com.vaadin.addons.slidedata.BindingSource.IndexChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Form;


@SuppressWarnings("serial")
public class DataCardView extends Form implements BindingSourceComponent, IndexChangeListener {
	private BindingSource<?> bindingSource; 
	private BeanItem<?> beanItem;
	private DefaultFieldFactory defaultFieldFactory;
	private String[] visibleColumns;
	
	public DataCardView() {
		super();
		
		// define default DataCardView properties
		DefaultDataCardViewConfig();
		
	}
	
	private void DefaultDataCardViewConfig() {
		// we want explicit 'apply'
		this.setWriteThrough(false);
		
		// no invalid values in datamodel
		this.setInvalidCommitted(false);

	}
	
	public void setBeanItemSource(BeanItem<?> beanItem) {
		this.beanItem = beanItem;
			
		// bind to POJO via BeanItem
		setItemDataSource(beanItem); 

	}
	
	@Override
	public BindingSource<?> getBindingSource() {
		return this.bindingSource;
		
	}
	
	@Override
	public void setBindingSource(BindingSource<?> bindingSource) {
		this.bindingSource = bindingSource;
		
		// set BindingSource Item index change listener
		bindingSource.addListenerFormView((IndexChangeListener)this);
		
	}
	
	public void setBeanItemSource(DefaultFieldFactory defaultFieldFactory) {
		this.defaultFieldFactory = defaultFieldFactory;
		
		setFormFieldFactory(defaultFieldFactory);
		
	}

	public void setVisibleColumns(String[] visibleColumns) {
		this.visibleColumns = visibleColumns;
		
	}
	
	@Override
	public void bindingSourceIndexChange(IndexChangeEvent event) {
		if (event.getRegister() != null) {
			// bind to POJO via BeanItem
			setBeanItemSource((BeanItem<?>) event.getRegister()); 
			
			// set columns visible
			setVisibleItemProperties(Arrays.asList(visibleColumns));
		}

	}

}
