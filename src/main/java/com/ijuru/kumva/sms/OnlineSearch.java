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

package com.ijuru.kumva.sms;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

import com.ijuru.kumva.Entry;
import com.ijuru.kumva.site.Dictionary;
import com.ijuru.kumva.xml.EntryListener;
import com.ijuru.kumva.xml.QueryXMLHandler;

/**
 * An remote search which using the Kumva XML API
 */
public class OnlineSearch implements EntryListener {
	
	private Dictionary dictionary;
	private int timeout;
	private List<Entry> results = new ArrayList<Entry>();
	
	/**
	 * Constructs an online search from the given dictionary
	 * @param dictionary the dictionary
	 * @param timeout the connection timeout
	 */
	public OnlineSearch(Dictionary dictionary, int timeout) {
		this.timeout = timeout;
		this.dictionary = dictionary;
	}
	
	/**
	 * @see com.ijuru.kumva.search.Search#doSearch(String)
	 */
	public SearchResult doSearch(String query, int limit) {	
		try {		
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			QueryXMLHandler handler = new QueryXMLHandler();
			
			handler.addListener(this);
			
			// Create URL connection to the XML API
			URL url = dictionary.createQueryURL(query, limit, "sms");
			URLConnection connection = url.openConnection();
			
			// Request GZIP compression and specify timeout
			connection.setRequestProperty("Accept-Encoding", "gzip");
			connection.setConnectTimeout(timeout);
			connection.setReadTimeout(timeout);
			
			// Detect GZIP compression if used
			InputStream stream = connection.getInputStream();
			if ("gzip".equals(connection.getContentEncoding()))
				stream = new GZIPInputStream(stream);
			
			// Start SAX parser
			InputSource source = new InputSource(stream);
			parser.parse(source, handler);
			stream.close();
			
			return new SearchResult(handler.getSuggestion(), results);	
			
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @see com.ijuru.kumva.xml.EntryListener#found(Entry)
	 */
	public void found(Entry definition) {
		results.add(definition);
	}
}
