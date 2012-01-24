package com.thingtrack.com.vaadin.addons.slidedata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings("serial")
public class BindingSource<BEANTYPE> extends BeanItemContainer<BEANTYPE> implements Serializable {
	private int index = 0;
	private Collection<? extends BEANTYPE>  collection;
	private int level = 0;
	
	private List<IndexChangeListener> indexChangeListeners;
	
	private IndexChangeListener listenerNavigatorToolBar = null;
	private IndexChangeListener listenerDataGridView = null;
	private IndexChangeListener listenerFormView = null;
	
    public BindingSource(Class<? super BEANTYPE> type) throws IllegalArgumentException {
    	super(type);
    	    	    	
    	this.indexChangeListeners = new ArrayList<IndexChangeListener>();
    }
    
    @SuppressWarnings("deprecation")
	public BindingSource(Collection<? extends BEANTYPE> collection) throws IllegalArgumentException {
    	super(collection);
    	
    	this.collection = collection;
    	
    	this.indexChangeListeners = new ArrayList<IndexChangeListener>();
    }
    
	public BindingSource(Class<? super BEANTYPE> type, Collection<? extends BEANTYPE> collection) throws IllegalArgumentException {
		this(type, collection, 0);
		
    	this.collection = collection;
    	
    	this.indexChangeListeners = new ArrayList<IndexChangeListener>();
	}

	public BindingSource(Class<? super BEANTYPE> type, Collection<? extends BEANTYPE> collection, int level) throws IllegalArgumentException {
		super(type, collection);
		
		this.collection = collection;
		
		this.setLevel(level);		
		
		this.indexChangeListeners = new ArrayList<IndexChangeListener>();
		
	}
	
	public BEANTYPE firstItem() {
		BEANTYPE result = firstItemId();
		
		if (result != null)
			index = 0;
		
		if (listenerDataGridView != null)
			listenerDataGridView.bindingSourceIndexChange(new IndexChangeEvent(result, index));
			
		if (listenerFormView != null) {
			BeanItem<BEANTYPE> beanItem = new BeanItem<BEANTYPE>(result);
			
			listenerFormView.bindingSourceIndexChange(new IndexChangeEvent(beanItem, index));
			    			
		}
		
		return result;
	}
    
    public BEANTYPE nextItem() {
    	if (index == size() - 1)
    	{
    		if (listenerDataGridView != null)
    			listenerDataGridView.bindingSourceIndexChange(new IndexChangeEvent(lastItemId(), index));

    		if (listenerFormView != null) {
    			BeanItem<BEANTYPE> beanItem = new BeanItem<BEANTYPE>(lastItemId());
    			
    			listenerFormView.bindingSourceIndexChange(new IndexChangeEvent(beanItem, index));
    			    			
    		}
    		
    		return lastItemId();
    	}
    	
    	index++;
    			
    	if (listenerDataGridView != null)
    		listenerDataGridView.bindingSourceIndexChange(new IndexChangeEvent(getIdByIndex(index), index));
		
		if (listenerFormView != null) {
			BeanItem<BEANTYPE> beanItem = new BeanItem<BEANTYPE>(getIdByIndex(index));
			
			listenerFormView.bindingSourceIndexChange(new IndexChangeEvent(beanItem, index));
		}
		
    	return getIdByIndex(index);
    }

    public BEANTYPE prevItem() {
    	if (index == 0) {
    		if (listenerDataGridView != null)
    			listenerDataGridView.bindingSourceIndexChange(new IndexChangeEvent(firstItemId(), index));
    		
    		if (listenerFormView != null) {
    			BeanItem<BEANTYPE> beanItem = new BeanItem<BEANTYPE>(firstItemId());
    			
    			listenerFormView.bindingSourceIndexChange(new IndexChangeEvent(beanItem, index));
    			    			
    		}
    		
    		return firstItemId();
    	}
    	
    	index--;
    	
    	if (listenerDataGridView != null)
    		listenerDataGridView.bindingSourceIndexChange(new IndexChangeEvent(getIdByIndex(index), index));
    			
		if (listenerFormView != null) {
			BeanItem<BEANTYPE> beanItem = new BeanItem<BEANTYPE>(getIdByIndex(index));
			
			listenerFormView.bindingSourceIndexChange(new IndexChangeEvent(beanItem, index));
			    			
		}
		
    	return getIdByIndex(index);
  
    }
    
    public BEANTYPE lastItem() {
		BEANTYPE result = lastItemId();
		
		if (result != null)
			index = size() - 1;
		
		if (listenerDataGridView != null)
			listenerDataGridView.bindingSourceIndexChange(new IndexChangeEvent(result, index));
		
		if (listenerFormView != null) {
			BeanItem<BEANTYPE> beanItem = new BeanItem<BEANTYPE>(result);
			
			listenerFormView.bindingSourceIndexChange(new IndexChangeEvent(beanItem, index));
			    			
		}
		
		return result;
    }
    
    public BEANTYPE getItemId() {
        return getIdByIndex(index);
        
    }
    
    @Override
    public void addAll(Collection<? extends BEANTYPE> collection) {
    	super.addAll(collection);
    	this.collection = collection;
    	
    }
    
    public int setItemId(Object itemId) {
    	return setItemId(null, false, itemId);
    	
    }
    public int setItemId(Object source, boolean executable, Object itemId) {
    	int _index = 0;
    	    	
    	for(Object item : collection) {
    		if (item.equals(itemId)) {
    	    	index = _index;
    	    	
    	    	if (listenerNavigatorToolBar != null)
    	    		listenerNavigatorToolBar.bindingSourceIndexChange(new IndexChangeEvent(source, executable, itemId, _index + 1));
    	    	
    	    	
        		if (listenerFormView != null) {
        			@SuppressWarnings("unchecked")
					BeanItem<BEANTYPE> beanItem = new BeanItem<BEANTYPE>((BEANTYPE) itemId);
        			
        			listenerFormView.bindingSourceIndexChange(new IndexChangeEvent(source, executable, beanItem, _index + 1));
        			    			
        		}
        		
        		sendEvents(source, executable, itemId, _index + 1);
        		
    			return _index + 1;
    		}
    		
    		_index++;
    	} 
		
    	return _index + 1;
    	
    }
    
    public int getIndex() {
    	return index + 1;
    	
    }
    
    private void sendEvents(Object source, boolean executable, Object itemId, int index) {
    	for(IndexChangeListener listener : indexChangeListeners)
    		listener.bindingSourceIndexChange(new IndexChangeEvent(source, executable, itemId, index));
    	
    }
    
	public void addListenerNavigatorToolBar(IndexChangeListener listener) {
		this.listenerNavigatorToolBar = listener;
		
	}
	public void addListenerDataGridView(IndexChangeListener listener) {
		this.listenerDataGridView = listener;
		
	}
	public void addListenerFormView(IndexChangeListener listener) {
		this.listenerFormView = listener;
		
	}	
	
	public void addListener(IndexChangeListener listener) {
		indexChangeListeners.add(listener);
		
	}	

    
    public interface IndexChangeListener extends Serializable {
        public void bindingSourceIndexChange(IndexChangeEvent event);

    }
		
    public static class IndexChangeEvent {
		private int index;
		private Object register;
		private Object source;
		private boolean executable;
		
		public IndexChangeEvent(Object register, int index) {			
			this(null, false, register, index);
			
		}
		
		public IndexChangeEvent(Object source, boolean executable, Object register, int index) {
			this.source = source;
			this.executable = executable;
			this.index = index;
			this.register = register;
		}
		
		public Object getSource() {
			return this.source;
			
		}
		
		public boolean  isExecutable () {
			return this.executable;
			
		}
		
		public int getIndex() {
			return this.index;
			
		}
		
		public Object getRegister() {
			return this.register;
			
		}
		
	 }
    
    public void Initialize() {
    	BEANTYPE result = firstItem();
    	
    	if (result != null) {
    		if (listenerNavigatorToolBar != null)
    			listenerNavigatorToolBar.bindingSourceIndexChange(new IndexChangeEvent(result, 1));
    		
    	}
    	
    }

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}
   
}
