/**
 * Copyright 2011 Rowan Seymour
 * 
 * This file is part of Kumva.
 *
 * Kumva is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Kumva is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Kumva. If not, see <http://www.gnu.org/licenses/>.
 */

package com.ijuru.kumva.sms.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ijuru.kumva.remote.RemoteSearch;
import com.ijuru.kumva.search.Search;
import com.ijuru.kumva.search.SearchResult;
import com.ijuru.kumva.sms.Context;
import com.ijuru.kumva.sms.Messages;

public class SearchServlet extends HttpServlet {
	
	protected static final Logger log = LogManager.getLogger(SearchServlet.class);

	private static final long serialVersionUID = 1L;
	
	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();		
		String query = request.getParameter("q");
		
		if (query == null || query.length() == 0)
			return;
		
		query = query.trim();
		
		String keyword = Context.getOptions().getInputKeyword();
		
		// Strip keyword if one is specified
		if (StringUtils.isNotEmpty(keyword) && query.toLowerCase().startsWith(keyword + " ")) {
			query = query.substring((keyword + " ").length());
		}
		
		int timeout = Context.getOptions().getConnectionTimeout();
		String searchRef = Context.getOptions().getSearchRef();
		
		try {
			Search search = new RemoteSearch(Context.getDictionary(), timeout);
			SearchResult result = search.execute(query, 10, searchRef);
			String message = Messages.searchResult(result, query);
			
			out.write(message);
		}
		catch (Exception ex) {
			out.write(Messages.errorOccurred());
			
			log.warn("Remote search failed");
		}
	}
}
