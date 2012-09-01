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

/**
 * Application options
 */
public class Options {

	private String dictionaryURL;
	private int connectionTimeout = 10000;
	private String searchRef = "sms";
	private String supportEmail;
	private String inputKeyword;
	
	/**
	 * @return the dictionaryURL
	 */
	public String getDictionaryURL() {
		return dictionaryURL;
	}
	
	/**
	 * @param dictionaryURL the dictionaryURL to set
	 */
	public void setDictionaryURL(String dictionaryURL) {
		this.dictionaryURL = dictionaryURL;
	}
	
	/**
	 * @return the connectionTimeout
	 */
	public int getConnectionTimeout() {
		return connectionTimeout;
	}
	
	/**
	 * @param connectionTimeout the connectionTimeout to set
	 */
	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}
	
	/**
	 * @return the searchRef
	 */
	public String getSearchRef() {
		return searchRef;
	}
	
	/**
	 * @param searchRef the searchRef to set
	 */
	public void setSearchRef(String searchRef) {
		this.searchRef = searchRef;
	}
	
	/**
	 * @return the supportEmail
	 */
	public String getSupportEmail() {
		return supportEmail;
	}
	
	/**
	 * @param supportEmail the supportEmail to set
	 */
	public void setSupportEmail(String supportEmail) {
		this.supportEmail = supportEmail;
	}
	
	/**
	 * @return the inputKeyword
	 */
	public String getInputKeyword() {
		return inputKeyword;
	}
	
	/**
	 * @param inputKeyword the inputKeyword to set
	 */
	public void setInputKeyword(String inputKeyword) {
		this.inputKeyword = inputKeyword;
	}
}
