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

import com.ijuru.kumva.Entry;
import com.ijuru.kumva.Revision;

public class Messages {

	/**
	 * Formats an SMS message from a list of revisions
	 * @param entries the entries
	 * @return the SMS message body
	 */
	public static String searchResult(List<Entry> entries) {
		StringBuilder builder = new StringBuilder();
		
		for (int e = 0; e < entries.size(); ++e) {
			Entry entry = entries.get(e);
			Revision revision = entry.getRevisions().get(0);
			
			if (e > 0)
				builder.append("|");
			
			builder.append(revision.getPrefix() + revision.getLemma());
		}
		
		return builder.toString();
	}
	
	/**
	 * Gets the message to use if an error occurred
	 * @return the SMS message body
	 */
	public static String errorOccurred() {
		return "Sorry but an error occurred";
	}
}
