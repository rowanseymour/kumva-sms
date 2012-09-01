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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ijuru.kumva.Entry;
import com.ijuru.kumva.Meaning;
import com.ijuru.kumva.Revision;
import com.ijuru.kumva.search.SearchResult;
import com.ijuru.kumva.sms.Messages;

import static org.junit.Assert.*;

/**
 * Test case for Messages
 */
public class MessagesTest {

	@BeforeClass
	public static void setUp() throws Exception {
		Context.startApplication();
	}
	
	@AfterClass
	public static void tearDown() {
		Context.destroyApplication();
	}
	
	@Test
	public void searchResult() {
		Revision revision1 = new Revision();
		revision1.setWordClass("n");
		revision1.setPrefix("iki");
		revision1.setLemma("zami");
		revision1.addMeaning(new Meaning("exam", 0));
		Entry entry1 = new Entry();
		entry1.addRevision(revision1);
		
		Revision revision2 = new Revision();
		revision2.setLemma("muraho");
		revision2.addMeaning(new Meaning("hello", 0));
		Entry entry2 = new Entry();
		entry2.addRevision(revision2);
		
		// Test with single result #1
		SearchResult result1 = new SearchResult(null, Collections.singletonList(entry1));
		assertEquals("ikizami - exam", Messages.searchResult(result1, "test"));
		
		// Test with single result #2
		SearchResult result2 = new SearchResult(null, Collections.singletonList(entry2));
		assertEquals("muraho - hello", Messages.searchResult(result2, "test"));
		
		// Test with combined result
		List<Entry> entries3 = new ArrayList<Entry>();
		entries3.add(entry1);
		entries3.add(entry2);
		SearchResult result3 = new SearchResult(null, entries3);
		assertEquals("ikizami - exam | muraho - hello", Messages.searchResult(result3, "test"));
		
		// Test with empty result set
		SearchResult resultEmpty = new SearchResult(null, new ArrayList<Entry>());
		assertEquals("Sorry no results were found for 'test'", Messages.searchResult(resultEmpty, "test"));
	}

	@Test
	public void errorOccurred() {	
		// Test without support email address
		assertEquals("Sorry an error has occurred", Messages.errorOccurred());
		
		// Test with support email address
		Context.getOptions().setSupportEmail("test@test.com");
		assertEquals("Sorry an error has occurred - please contact test@test.com", Messages.errorOccurred());
	}
}
