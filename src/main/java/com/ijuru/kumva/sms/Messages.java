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

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ijuru.kumva.Entry;
import com.ijuru.kumva.Meaning;
import com.ijuru.kumva.Revision;

/**
 * Formatting methods for SMS messages
 */
public class Messages {

	protected static final int MAX_CHARS = 140;
	protected static final String ENTRY_SEPARATOR = " | ";
	protected static final String ERROR_MESSAGE = "Sorry but an error occurred";
	protected static final String EMPTY_MESSAGE = "Sorry no results were found for '%s'";
	
	/**
	 * Formats an SMS message from a list of revisions
	 * @param entries the entries
	 * @return the SMS message body
	 */
	public static String searchResult(List<Entry> entries) {
		StringBuilder builder = new StringBuilder();
		
		for (int e = 0; e < entries.size(); ++e) {
			Entry entry = entries.get(e);
			String entryStr = formatEntry(entry);
			
			if (e > 0)
				builder.append(ENTRY_SEPARATOR);
			
			builder.append(entryStr);
			
			if (builder.length() >= MAX_CHARS)
				break;
		}
		
		String message = builder.toString();
		
		if (message.length() > MAX_CHARS)
			message = StringUtils.abbreviate(message, MAX_CHARS);
		
		return message;
	}
	
	/**
	 * Gets the message to use if an error occurred
	 * @return the SMS message body
	 */
	public static String errorOccurred() {
		return ERROR_MESSAGE;
	}
	
	/**
	 * Gets the message to use if no results were returned
	 * @param query the query
	 * @return the SMS message body
	 */
	public static String noResultsReturned(String query) {
		String message = EMPTY_MESSAGE.replace("%s", query);
		
		if (message.length() > MAX_CHARS)
			message = StringUtils.abbreviate(message, MAX_CHARS);
		
		return message;
	}
	
	/**
	 * Formats a single entry
	 * @param entry the entry
	 * @return the formatted string
	 */
	private static String formatEntry(Entry entry) {
		StringBuilder builder = new StringBuilder();
		Revision revision = entry.getRevisions().get(0);
		
		builder.append(revision.getPrefix() + revision.getLemma());
		
		if (!StringUtils.isEmpty(revision.getWordClass()))
			builder.append("[" + revision.getWordClass() + "]");
		
		builder.append(" - ");
		
		for (int m = 0; m < revision.getMeanings().size(); ++m) {
			Meaning meaning = revision.getMeanings().get(m);
			
			if (m > 0)
				builder.append(", ");
			
			builder.append(meaning.getText());
		}
		
		return builder.toString();
	}
}
