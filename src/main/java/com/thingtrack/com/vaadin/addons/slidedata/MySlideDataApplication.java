/*
 * Copyright 2009 IT Mill Ltd.
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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.thingtrack.com.vaadin.addons.slidedata.BindingSource.IndexChangeEvent;
import com.thingtrack.com.vaadin.addons.slidedata.BindingSource.IndexChangeListener;
import com.thingtrack.com.vaadin.addons.slidedata.NavigationToolBar.ClickAddButtonListener;
import com.thingtrack.com.vaadin.addons.slidedata.NavigationToolBar.ClickCancelButtonListener;
import com.thingtrack.com.vaadin.addons.slidedata.NavigationToolBar.ClickConfirmButtonListener;
import com.thingtrack.com.vaadin.addons.slidedata.NavigationToolBar.ClickDeleteButtonListener;
import com.thingtrack.com.vaadin.addons.slidedata.NavigationToolBar.ClickEditButtonListener;
import com.thingtrack.com.vaadin.addons.slidedata.NavigationToolBar.ClickNavigationEvent;
import com.thingtrack.com.vaadin.addons.slidedata.NavigationToolBar.ClickRefreshButtonListener;
import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MySlideDataApplication extends Application
{
    private Window window;
    private VerticalLayout mainLayout;
        
    // DataSources
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Offer> offers = new ArrayList();
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private List<OfferLine> offerLines = new ArrayList();
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private List<Invoice> invoices = new ArrayList();
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private List<Product> products = new ArrayList();
	
	// BindingSources
	private BindingSource<Offer> bsOffer;
	private BindingSource<OfferLine> bsOfferLine;
	private BindingSource<Invoice> bsInvoice;
	private BindingSource<Product> bsProduct;
	
    // navigation ToolBar Component
    private NavigationToolBar navigator;
    
    // slider Component
    private Slider slider;
        
    // DataGridView Data Components
	private DataGridView slideDataGridViewOffer; 
	private DataGridView slideDataGridViewOfferLine;
	private DataGridView slideDataGridViewInvoice;
	private DataGridView slideDataGridViewProduct;  

	// DataCardView Data Components
	private DataCardView slideFormViewOffer;
	private DataCardView slideFormViewOfferLine;
	private DataCardView slideFormViewInvoice;
	private DataCardView slideFormViewProduct;
	
	// slide level definitions
	private final int LEVEL_OFFER = 0;
	private final int LEVEL_OFFERDETAIL = 1;
	private final int LEVEL_PRODUCT = 2;
	    
	@Override
    public void init()
    {
		// set main window
        window = new Window("My Vaadin Application");
        setMainWindow(window);
          
        // set full size and none margin for the default window layout
        mainLayout = (VerticalLayout)window.getContent();
        mainLayout.setSizeFull();
        mainLayout.setMargin(false);
        
        // fill Mock Data
        fillMockDataSource();
        
        // create Navigation Toolbar
        createNavigatorToolBar();
                
        // create Slide Node Components
        createSlideComponents();
        
        // initialize  Slider Data Source with Data Mock
        initializeSlideDataSource(offers);
        
    }
   
	private void fillMockDataSource() {
	    	Offer offer01 = new Offer(1, "OF01", "OF01-01", 0, "Offer 01");
	    	offer01.getLines().add(new OfferLine(1, offer01.getOfferId(), 1, "Offer Line Service OF01-01", 100));
	    	offer01.getLines().add(new OfferLine(2, offer01.getOfferId(), 2, "Offer Line Service OF01-02", 150));
	    	offer01.getLines().add(new OfferLine(3, offer01.getOfferId(), 3, "Offer Line Service OF01-03", 90));
	    	for (OfferLine ofl : offer01.getLines()) {
	    		offer01.setTotalPrice(offer01.getTotalPrice() + ofl.getPrice()); 
	    		
	    		ofl.getLines().add(new Product(1, ofl.getOfferLineId(), offer01.getCode() + "-" + ofl.getService(), "Producto " + offer01.getCode() + "-" + ofl.getService()));
	    	}
	    	offer01.getInvoices().add(new Invoice(1, 1, "Invoice 01: " + offer01.getCode(), new Date(10), 230.5f));
	    	offer01.getInvoices().add(new Invoice(2, 1, "Invoice 02: " + offer01.getCode(), new Date(20), 30.8f));
	    	
	    	offers.add(offer01);
	    	
	    	Offer offer02 = new Offer(2, "OF02", "OF02-01", 0, "Offer 02");
	    	offer02.getLines().add(new OfferLine(4, offer02.getOfferId(), 1, "Offer Line Service OF02-01", 50));
	    	for (OfferLine ofl : offer02.getLines()) {
	    		offer02.setTotalPrice(offer02.getTotalPrice() + ofl.getPrice());
	    		
	    		ofl.getLines().add(new Product(1, ofl.getOfferLineId(), offer02.getCode() + "-" + ofl.getService(), "Producto " + offer02.getCode() + "-" + ofl.getService()));
	    	}
	    	offer02.getInvoices().add(new Invoice(3, 2, "Invoice 01: " + offer02.getCode(), new Date(10), 100.4f));
	    	
	    	offers.add(offer02);
	    	
	      	Offer offer03 = new Offer(3, "OF03", "OF03-01", 0, "Offer 03");
	    	offer03.getLines().add(new OfferLine(5, offer03.getOfferId(), 1, "Offer Line Service OF03-01", 670));
	    	offer03.getLines().add(new OfferLine(6, offer03.getOfferId(), 2, "Offer Line Service OF03-02", 200));
	    	for (OfferLine ofl : offer03.getLines()) {
	    		offer03.setTotalPrice(offer03.getTotalPrice() + ofl.getPrice());
	    		
	    		ofl.getLines().add(new Product(1, ofl.getOfferLineId(), offer03.getCode() + "-" + ofl.getService(), "Producto " + offer03.getCode() + "-" + ofl.getService()));
	    	}
	    	offer03.getInvoices().add(new Invoice(4, 3, "Invoice 01: " + offer03.getCode(), new Date(70), 56.9f));
	    	offer03.getInvoices().add(new Invoice(5, 3, "Invoice 02: " + offer03.getCode(), new Date(300), 80.4f));
	    	offer03.getInvoices().add(new Invoice(6, 3, "Invoice 03: " + offer03.getCode(), new Date(10), 12.0f));
	    	
	    	offers.add(offer03);
	    	
	}
	   
    private void createNavigatorToolBar () {
    	// create navigation and initialize the max detail level
    	navigator = new NavigationToolBar();
    	
    	// implement navigation ToolBar events
    	navigator.addListenerRefreshButton(new ClickRefreshButtonListener() {
			@Override
			public void refreshButtonClick(ClickNavigationEvent event) {
				/*if (event.getRegister().getClass().equals(Offer.class)) {
					
				}
				else if (event.getRegister().getClass().equals(OfferLine.class)) {
					
				}
				else if (event.getRegister().getClass().equals(Invoice.class)) {
					
				}
				else if (event.getRegister().getClass().equals(Product.class)) {
					
				}*/
				
			}
			
		});
        navigator.addListenerAddButton(new ClickAddButtonListener() {
			@Override
			public void addButtonClick(ClickNavigationEvent event) {
				//Offer offerSelected = (Offer)event.getRegister();

			}
		});
        navigator.addListenerEditButton(new ClickEditButtonListener() {
			@Override
			public void editButtonClick(ClickNavigationEvent event) { 
				//Offer offerSelected = (Offer)event.getRegister();			
				
			}
		});
        navigator.addListenerDeleteButton(new ClickDeleteButtonListener() {
			@Override
			public void deleteButtonClick(ClickNavigationEvent event) {
				//Offer = (Offer)event.getRegister();
				
			}
		});
        navigator.addListenerConfirmButton(new ClickConfirmButtonListener() {
			@Override
			public void confirmButtonClick(ClickNavigationEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
        navigator.addListenerCancelButton(new ClickCancelButtonListener() {
			@Override
			public void cancelButtonClick(ClickNavigationEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
        
        mainLayout.addComponent(navigator);
        mainLayout.setExpandRatio(navigator, 0.0f);
    }
    
    private void createSlideComponents() {
        // create slider
        slider = new Slider();       
        slider.setSizeFull();
        
        // inject slider to navigation ToolBar
        navigator.setSlider(slider);
        slider.setNavigationToolBar(navigator);
        
        // add  Slider Data Component to main layout
        mainLayout.addComponent(slider);
        mainLayout.setExpandRatio(slider, 1.0f);
        
    	///////////////// LEVEL 0: slide component master: level Offer
    	slideDataGridViewOffer = new DataGridView();       
    	slideDataGridViewOffer.setSizeFull(); 	
		
		slideFormViewOffer = new DataCardView();
		slideFormViewOffer.setSizeFull();
		slideFormViewOffer.setFormFieldFactory(new OfferFieldFactory());
			
		slider.addSlideComponent(LEVEL_OFFER, "Offer", slideDataGridViewOffer, slideFormViewOffer);
		
        ///////////////// LEVEL 1: slide component detail: level Offer Line + Invoice  
        slideDataGridViewOfferLine = new DataGridView();
		slideDataGridViewOfferLine.setSizeFull();
		
		slideFormViewOfferLine = new DataCardView();
		slideFormViewOfferLine.setSizeFull();
		slideFormViewOfferLine.setFormFieldFactory(new OfferLineFieldFactory());
		
    	slideDataGridViewInvoice = new DataGridView();
    	slideDataGridViewInvoice.setSizeFull();
 		
		slideFormViewInvoice = new DataCardView();
		slideFormViewInvoice.setSizeFull();
		slideFormViewInvoice.setFormFieldFactory(new InvoiceFieldFactory());

    	slider.addSlideComponent(LEVEL_OFFERDETAIL, "Offer Detail", slideDataGridViewOfferLine, slideFormViewOfferLine);
    	slider.addSlideComponent(LEVEL_OFFERDETAIL, "Invoice", slideDataGridViewInvoice, slideFormViewInvoice);
    	
		///////////////// LEVEL 2: slide node detail: level Product
        slideDataGridViewProduct = new DataGridView();
		slideDataGridViewProduct.setSizeFull();
		
		slideFormViewProduct = new DataCardView();
		slideFormViewProduct.setSizeFull();
		slideFormViewProduct.setFormFieldFactory(new ProductFieldFactory());
		
		slider.addSlideComponent(LEVEL_PRODUCT, "Product", slideDataGridViewProduct, slideFormViewProduct);
    }
    
    private void initializeSlideDataSource(List<Offer> dataSource) {
    	// create the offer BindingSource from DataSource
    	bsOffer = new  BindingSource<Offer>(Offer.class, dataSource, LEVEL_OFFER);
    	bsOffer.addListener(new IndexChangeListener() {
			@Override
			public void bindingSourceIndexChange(IndexChangeEvent event) {
				if (!event.isExecutable())
					return;
				
				// get offer selected
				Offer offer = (Offer) event.getRegister();
				
				// create Offer Lines BindingSource from Offer DataSource
				bsOfferLine = new  BindingSource<OfferLine>(OfferLine.class, offer.getLines(), LEVEL_OFFERDETAIL);
				bsOfferLine.addListener(new IndexChangeListener() {
					@Override
					public void bindingSourceIndexChange(IndexChangeEvent event) {
						if (!event.isExecutable())
							return;
						
						// get offer selected
						OfferLine offerLine = (OfferLine) event.getRegister();
						
						// create Product BindingSource from Offer Line DataSource
						bsProduct = new  BindingSource<Product>(Product.class, offerLine.getLines(), LEVEL_PRODUCT);
						
 						// inject Product DataSource to the datagridview
				        slideDataGridViewProduct.setBindingSource(bsProduct);		
				        slideDataGridViewProduct.setVisibleColumns(new String[] { "name", "description" } );       
   			            slideDataGridViewProduct.setColumnHeaders(new String[] { "Name", "Description" } );
				        			
   			            // inject Product DataSource to the formview
   			            slideFormViewProduct.setBindingSource(bsProduct);
   			            slideFormViewProduct.setVisibleColumns(new String[] { "name", "description" });
   			         
				        try {
				        	// execute slide movement to the next level
							slider.slideGoto(LEVEL_PRODUCT);
							
							// inject the slide datasource
							navigator.setBindingSource(bsProduct);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
						    	
				// inject Offer Line DataSource to the datagridview
		        slideDataGridViewOfferLine.setBindingSource(bsOfferLine);		
	            slideDataGridViewOfferLine.setVisibleColumns(new String[] { "line", "service", "price" } );       
		        slideDataGridViewOfferLine.setColumnHeaders(new String[] { "L�nea", "Servicio", "Precio" } );
		        
		        // inject Product DataSource to the formview
		        slideFormViewOfferLine.setBindingSource(bsOfferLine);
		        slideFormViewOfferLine.setVisibleColumns(new String[] { "line", "service", "price" });
		            
		        // set Invoice from Offer Detail
				bsInvoice = new  BindingSource<Invoice>(Invoice.class, offer.getInvoices(), LEVEL_OFFERDETAIL);
				
				// inject Offer Line DataSource to the datagridview
				slideDataGridViewInvoice.setBindingSource(bsInvoice);		
				slideDataGridViewInvoice.setVisibleColumns(new String[] { "code", "dateInvoice", "price" } );       
				slideDataGridViewInvoice.setColumnHeaders(new String[] { "C�digo", "Fecha", "Precio" } );
		        
		        // inject Product DataSource to the formview
		        slideFormViewInvoice.setBindingSource(bsInvoice);
		        slideFormViewInvoice.setVisibleColumns(new String[] { "code", "dateInvoice", "price" });
		        
		        try {
		        	// execute slide movement to the next level
					slider.slideGoto(LEVEL_OFFERDETAIL);
					
					// inject the slide datasource for the first detail
					navigator.setBindingSource(bsOfferLine);
				} catch (Exception e) {
					e.printStackTrace();
				}
		        
			}
		});
        
    	// inject Offer DataSource to the datagridview
        slideDataGridViewOffer.setBindingSource(bsOffer);		
        slideDataGridViewOffer.setVisibleColumns(new String[] { "code", "revision", "totalPrice", "comment" } );       
        slideDataGridViewOffer.setColumnHeaders(new String[] { "C�digo", "Revisi�n", "Precio Total", "Comentario"} );
		        
        // inject Offer DataSource to the formview
        slideFormViewOffer.setBindingSource(bsOffer);
        slideFormViewOffer.setVisibleColumns(new String[] { "code", "revision", "totalPrice", "comment" });
		
        try {	
        	// execute slide movement to the next level
			slider.slideGoto(LEVEL_OFFER);
			
        	// inject the slide datasource
			navigator.setBindingSource(bsOffer);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
    
    private class OfferFieldFactory extends DefaultFieldFactory {
        public OfferFieldFactory() {

        }

        @Override
        public Field createField(Item item, Object propertyId,  Component uiContext) {
            Field f = null;

            f = super.createField(item, propertyId, uiContext);
            
            if ("code".equals(propertyId)) {
                TextField tf = (TextField)f;
                //tf.setRequired(true);
                //tf.setRequiredError("Please enter a Code");
                //tf.setWidth(COMMON_FIELD_WIDTH);
                //tf.addValidator(new StringLengthValidator("Code must be 1-3 characters", 1, 3, false));
                
            } else if ("revision".equals(propertyId)) {
                TextField tf = (TextField)f;
                //tf.setRequired(true);
                //tf.setRequiredError("Please enter a Name");
                //tf.setWidth(COMMON_FIELD_WIDTH);
                //tf.addValidator(new StringLengthValidator("Name must be 3-50 characters", 3, 50, false));
                
            } else if ("totalPrice".equals(propertyId)) {
                TextField tf = (TextField)f;
                //tf.setNullRepresentation("");
                //tf.setNullSettingAllowed(true);               
                //tf.setWidth("2em");
                
            } else if ("comment".equals(propertyId)) {
                TextField tf = (TextField)f;
                //tf.setNullRepresentation("");
                //tf.setNullSettingAllowed(true);               
                //tf.setWidth("2em");
                
            }      
            	
            return f;
        }

    } 
    private class OfferLineFieldFactory extends DefaultFieldFactory {
        public OfferLineFieldFactory() {

        }

        @Override
        public Field createField(Item item, Object propertyId,  Component uiContext) {
            Field f = null;

            f = super.createField(item, propertyId, uiContext);
            
            if ("line".equals(propertyId)) {
                TextField tf = (TextField)f;
                //tf.setRequired(true);
                //tf.setRequiredError("Please enter a Code");
                //tf.setWidth(COMMON_FIELD_WIDTH);
                //tf.addValidator(new StringLengthValidator("Code must be 1-3 characters", 1, 3, false));
                
            } else if ("service".equals(propertyId)) {
                TextField tf = (TextField)f;
                //tf.setRequired(true);
                //tf.setRequiredError("Please enter a Name");
                //tf.setWidth(COMMON_FIELD_WIDTH);
                //tf.addValidator(new StringLengthValidator("Name must be 3-50 characters", 3, 50, false));
                
            } else if ("price".equals(propertyId)) {
                TextField tf = (TextField)f;
                //tf.setNullRepresentation("");
                //tf.setNullSettingAllowed(true);               
                //tf.setWidth("2em");
                
            } 
            
            return f;
        }

    }  
    private class ProductFieldFactory extends DefaultFieldFactory {
        public ProductFieldFactory() {

        }

        @Override
        public Field createField(Item item, Object propertyId,  Component uiContext) {
            Field f = null;

            f = super.createField(item, propertyId, uiContext);
            
            if ("name".equals(propertyId)) {
                TextField tf = (TextField)f;
                //tf.setRequired(true);
                //tf.setRequiredError("Please enter a Code");
                //tf.setWidth(COMMON_FIELD_WIDTH);
                //tf.addValidator(new StringLengthValidator("Code must be 1-3 characters", 1, 3, false));
                
            } else if ("description".equals(propertyId)) {
                TextField tf = (TextField)f;
                //tf.setRequired(true);
                //tf.setRequiredError("Please enter a Name");
                //tf.setWidth(COMMON_FIELD_WIDTH);
                //tf.addValidator(new StringLengthValidator("Name must be 3-50 characters", 3, 50, false));
                
            } 
            
            return f;
        }

    }   
    private class InvoiceFieldFactory extends DefaultFieldFactory {
        public InvoiceFieldFactory() {

        }

        @Override
        public Field createField(Item item, Object propertyId,  Component uiContext) {
            Field f = null;

            f = super.createField(item, propertyId, uiContext);
            
            if ("code".equals(propertyId)) {
                TextField tf = (TextField)f;
                tf.setRequired(true);
                tf.setRequiredError("Please enter a Code");
                //tf.setWidth(COMMON_FIELD_WIDTH);
                tf.addValidator(new StringLengthValidator("Code must be 1-3 characters", 1, 3, false));
                
            } else if ("dateInvoice".equals(propertyId)) {
            	@SuppressWarnings("unused")
				DateField tf = (DateField)f;
                tf.setRequired(false);
                //tf.setRequiredError("Please enter a Name");
                //tf.setWidth(COMMON_FIELD_WIDTH);
                //tf.addValidator(new StringLengthValidator("Name must be 3-50 characters", 3, 50, false));
                
            } 
            
            return f;
        }

    }
    
}
