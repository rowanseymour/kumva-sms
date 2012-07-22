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

import java.util.Properties;

import com.ijuru.kumva.remote.RemoteDictionary;

/**
 * Application context
 */
public class Context {

	private static RemoteDictionary dictionary;
	private static Properties properties;

	/**
	 * Gets the dictionary
	 * @return the dictionary
	 */
	public static RemoteDictionary getDictionary() {
		return dictionary;
	}

	/**
	 * Sets the dictionary
	 * @param dictionary the dictionary
	 */
	public static void setDictionary(RemoteDictionary dictionary) {
		Context.dictionary = dictionary;
	}

	/**
	 * Gets the runtime properties
	 * @return the properties
	 */
	public static Properties getRuntimeProperties() {
		return properties;
	}

	/**
	 * Sets the runtime properties
	 * @param properties the properties
	 */
	public static void setRuntimeProperties(Properties properties) {
		Context.properties = properties;
	}
}
