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

import java.io.File;

/**
 * General utility methods
 */
public class Utils {

	/**
	 * Gets the Kumva home directory
	 * @return the directory
	 */
	public static File homeDirectory() {
		String path = System.getProperty("user.home") + File.separator + ".Kumva";
		File folder = new File(path);
		
		if (!folder.exists())
			folder.mkdirs();
		
		return folder;
	}
}
