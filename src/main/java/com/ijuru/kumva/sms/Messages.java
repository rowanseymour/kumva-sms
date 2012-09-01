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
import com.ijuru.kumva.search.SearchResult;

/**
 * Formatting methods for SMS messages
 */
public class Messages {

	protected static final int MAX_CHARS = 140;
	
	protected static final String ENTRY_SEPARATOR = " | ";
	protected static final String MEANING_MARKER = " - ";
	protected static final String MEANING_SEPARATOR = ", ";
	
	protected static final String EMPTY_MESSAGE = "Sorry no results were found for '%s'";
	protected static final String ERROR_MESSAGE1 = "Sorry an error has occurred";
	protected static final String ERROR_MESSAGE2 = " - please contact %s";
	
	/**
	 * Formats an SMS message from a list of revisions
	 * @param entries the entries
	 * @return the SMS message body
	 */
	public static String searchResult(SearchResult result, String query) {
		StringBuilder builder = new StringBuilder();
		List<Entry> entries = result.getMatches();
		
		if (entries.size() > 0) {
			for (int e = 0; e < entries.size(); ++e) {
				Entry entry = entries.get(e);
				String entryStr = formatEntry(entry);
				
				if (e > 0)
					builder.append(ENTRY_SEPARATOR);
				
				builder.append(entryStr);
				
				if (builder.length() >= MAX_CHARS)
					break;
			}
		}
		else {
			builder.append(EMPTY_MESSAGE.replace("%s", query));
		}
		
		String message = builder.toString();
		
		if (message.length() > MAX_CHARS)
			message = StringUtils.abbreviate(message, MAX_CHARS);
		
		return message;
	}
	
	public static String errorOccurred() {
		StringBuilder builder = new StringBuilder();
		builder.append(ERROR_MESSAGE1);
		
		String supportEmail = Context.getOptions().getSupportEmail();
		if (StringUtils.isNotEmpty(supportEmail))
			builder.append(ERROR_MESSAGE2.replace("%s", supportEmail));
			
		return builder.toString();
	}
	
	/**
	 * Formats a single entry
	 * @param entry the entry
	 * @return the formatted string
	 */
	protected static String formatEntry(Entry entry) {
		StringBuilder builder = new StringBuilder();
		Revision revision = entry.getRevisions().get(0);
		
		if (!StringUtils.isEmpty(revision.getPrefix()))
			builder.append(revision.getPrefix());
		
		builder.append(revision.getLemma());
		
		builder.append(MEANING_MARKER);
		
		for (int m = 0; m < revision.getMeanings().size(); ++m) {
			Meaning meaning = revision.getMeanings().get(m);
			
			if (m > 0)
				builder.append(MEANING_SEPARATOR);
			
			builder.append(meaning.getText());
		}
		
		return builder.toString();
	}
}
