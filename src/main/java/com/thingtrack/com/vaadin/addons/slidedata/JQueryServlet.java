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

import java.io.BufferedWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.vaadin.terminal.gwt.server.ApplicationServlet;
import com.vaadin.terminal.gwt.server.WebBrowser;

/**
 * ######JQueryServlet##### -This class is used to insert, JQuery js files to
 * the pages of the application.
 * 
 * 
 * @author Alper Turkyilmaz - VelocitiSoftware - 2011
 * @version 1.0.2
 * 
 */
@SuppressWarnings("serial")
public class JQueryServlet extends ApplicationServlet {

	protected void writeAjaxPageHtmlHeader(BufferedWriter page, String title, String themeUri, HttpServletRequest request) throws IOException {
		page.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\n");

		WebBrowser browser = getApplicationContext(request.getSession()).getBrowser();
		if (browser.isIE()) {
			// Chrome frame in all versions of IE (only if Chrome frame is
			// installed)
			page.write("<meta http-equiv=\"X-UA-Compatible\" content=\"chrome=1\"/>\n");
		}
		// Add favicon links
		page.write("<style type=\"text/css\">" + "html, body {height:100%;margin:0;}</style>");
		page.write("<script type=\"text/javascript\" src=\"" + themeUri + "/../js/jquery-1.6.2.min.js\"></script>");
		page.write("<script type=\"text/javascript\" src=\"" + themeUri + "/../js/ikarusbreadcrumbs.js\"></script>");
		page.write("<script type=\"text/javascript\" src=\"" + themeUri + "/../js/jquery.sliders.js\"></script>");
	}
}
