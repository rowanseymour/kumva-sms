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

import org.codehaus.jackson.map.ObjectMapper;

import com.ijuru.kumva.remote.RemoteDictionary;

/**
 * Application context
 */
public class Context {

	private static RemoteDictionary dictionary;
	private static Options options;
	
	/**
	 * Starts the application
	 * @throws Exception 
	 */
	public static void startApplication() throws Exception {
		// Look for environment variable 
		String optionsJSON = System.getenv("KUMVA_OPTIONS");
		if (optionsJSON == null)
			throw new Exception("KUMVA_OPTIONS environment variable not defined");
			
		ObjectMapper mapper = new ObjectMapper();
		options = mapper.readValue(optionsJSON, Options.class);
		
		if (options.getDictionaryURL() == null)
			throw new Exception("No dictionary URL specified");
			
		dictionary = new RemoteDictionary(options.getDictionaryURL());
	}
	
	public static void destroyApplication() {
		
	}
	
	public static Options getOptions() {
		return options;
	}

	/**
	 * Gets the dictionary
	 * @return the dictionary
	 */
	public static RemoteDictionary getDictionary() {
		return dictionary;
	}
}
