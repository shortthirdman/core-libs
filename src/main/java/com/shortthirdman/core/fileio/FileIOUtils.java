/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shortthirdman.core.fileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * @author Swetank Mohanty
 * @since 0.0.1
 * @version 0.0.1
 */
public final class FileIOUtils {

	private FileIOUtils() {
	
	}

	/**
	 * @param source
	 * @param destination
	 */
	public static void copyFiles(File source, File destination) {
	    try (InputStream is = new FileInputStream(source);
	    	 OutputStream os = new FileOutputStream(destination)) {
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } catch (IOException ioe) {
	    	ioe.printStackTrace();
	    }
	}
	
	/**
	 * @param source
	 * @param destination
	 * @throws IOException
	 */
	public static void copyFile(String source, String destination) throws IOException {
		try (InputStream is = new FileInputStream(new File(source));
	     OutputStream os = new FileOutputStream(new File(destination))) {
	        int length;
	        byte[] bytes = new byte[1024];
	        // copy data from input stream to output stream
	        while ((length = is.read(bytes)) != -1) {
	            os.write(bytes, 0, length);
	        }
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * @param source
	 * @param destination
	 * @throws IOException
	 */
	public static void copyFile(File source, File destination) throws IOException {
	    FileChannel sourceChannel = null;
	    FileChannel destChannel = null;
	    try {
	        sourceChannel = new FileInputStream(source).getChannel();
	        destChannel = new FileOutputStream(destination).getChannel();
	        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
	    } finally {
           sourceChannel.close();
           destChannel.close();
	    }
	}
	
	/**
     * @param dir
     */
    public static void removeDirectory(File dir) {
    	if (dir.isDirectory()) {
    		File[] files = dir.listFiles();
    		if (files != null && files.length > 0) {
    			for (File aFile : files) {
    				removeDirectory(aFile);
    			}
    		}
    		dir.delete();
    	} else {
    		dir.delete();
    	}
    }

    /**
     * @param dir
     */
    public static void cleanDirectory(File dir) {
    	if (dir.isDirectory()) {
    		File[] files = dir.listFiles();
    		if (files != null && files.length > 0) {
    			for (File aFile : files) {
    				removeDirectory(aFile);
    			}
    		}
    	}
    }

}
