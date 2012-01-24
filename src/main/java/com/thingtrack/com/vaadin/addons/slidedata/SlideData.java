package com.thingtrack.com.vaadin.addons.slidedata;

import java.util.List;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.ClientWidget;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

import com.thingtrack.com.vaadin.addons.slidedata.Slider.SlideNode;
import com.thingtrack.com.vaadin.addons.slidedata.gwt.client.VSlideData;

@SuppressWarnings("serial")
@ClientWidget(VSlideData.class)
public class SlideData extends CustomComponent {
	private VerticalLayout mainLayout;
	
	private int slideNumber;
	private String function;
	private List<SlideNode> slideNodes;
	
    public SlideData() {
    	mainLayout = new VerticalLayout();
    	mainLayout.setImmediate(false);
    	mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
    	setCompositionRoot(mainLayout);
    	
	}
	
    public void setFunction(String function) {
    	this.function = function;
    	
    }
    
    public void setSlideNodes(List<SlideNode> slideNodes) {
    	this.slideNodes = slideNodes;
    	
    }
    
    public void addSlideComponent(Component slideComponent){
    	slideComponent.setSizeFull();
    	mainLayout.addComponent(slideComponent);
    	
    	//setCompositionRoot(slideComponent);
    	mainLayout.setExpandRatio(slideComponent, 1.0f);
    	
    }
    
	public void slideGoto(int slideNumber) {
		this.function = "slideGoto";
		this.slideNumber = slideNumber;
		
		// redraw Slide components
		redrawSlideNodes(slideNumber);
		
		// repaint component
		requestRepaint();
		
	}
	
	private void redrawSlideNodes(int level) {
		for(SlideNode slideNode : slideNodes) {
			if (slideNode.getLevel() == level) {
				slideNode.getSlideComponent().setVisible(true);
				mainLayout.setExpandRatio(slideNode.getSlideComponent(), 1.0f);
			}
			else {
				slideNode.getSlideComponent().setVisible(false);
				mainLayout.setExpandRatio(slideNode.getSlideComponent(), 0.0f);
			}
		}
		
	}
	
	@Override
	public void paintContent(PaintTarget target) throws PaintException {
		super.paintContent(target);

		// Paint any component specific content by setting attributes
		// These attributes can be read in updateFromUIDL in the widget.
		if (function != null) {
			target.addAttribute("function", function);
			target.addAttribute("slideNumber", slideNumber);
			
		}

	}

}
