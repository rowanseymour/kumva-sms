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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ijuru.kumva.remote.RemoteDictionary;

/**
 * Listens for webapp stop/start
 */
public class Listener implements ServletContextListener {

	protected static final Logger log = LogManager.getLogger(Listener.class);
	
	private static final String PROPS_FILENAME = "kumva-sms.properties";
	
	/**
	 * Web application is being deployed or started
	 */
	public void contextInitialized(ServletContextEvent event) {	
		log.info("Initializing Kumva SMS proxy");
		
		File homeDir = Utils.homeDirectory();
		File propsFile = new File(homeDir, PROPS_FILENAME);
		
		log.info("Using home directory '" + homeDir.getAbsolutePath() + "'");
	
		try {
			Properties properties = new Properties();
			properties.load(new FileReader(propsFile));
			
			Context.setRuntimeProperties(properties);
			
			String dictionaryURL = properties.getProperty("dictionary.url");
			Context.setDictionary(new RemoteDictionary(dictionaryURL));
			
			log.info("Loaded properties from '" + propsFile.getAbsolutePath() + "'");
			
		} catch (FileNotFoundException e) {
			log.error("Properties file '" + propsFile.getAbsolutePath() + "' doesn't exist");
		} catch (Exception e) {
			log.error("Properties file '" + propsFile.getAbsolutePath() + "' couldn't be read");
		}
	}
	
	/**
	 * Web application is being undeployed or stopped
	 */
	public void contextDestroyed(ServletContextEvent event) {
		log.info("Destroying Kumva SMS proxy");
		
		Context.setDictionary(null);
	}
}
