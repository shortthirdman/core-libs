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
package com.shortthirdman.core.network;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Swetank Mohanty
 * @since 0.0.1
 * @version 0.0.1
 */
public class NetworkUtils {

	private static String[] browserNames = { "firefox", "mozilla-firefox", "mozilla", "konqueror", "netscape",
			"opera" };

	/**
	 * @param url
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public static void openBrowser(String url) throws IOException {
		final String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("windows") != -1) {
			url = appendUrlForWindows2000(os, url);
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
			return;
		} else if (os.indexOf("mac") != -1) {
			Runtime.getRuntime().exec(new String[] { "open", url });
			return;
		} else {
			Runtime runtime = Runtime.getRuntime();
			for (int i = 0; i < browserNames.length; i++) {
				runtime.exec(new String[] { browserNames[i], url });
				return;
			}
		}
	}

	/**
	 * @param url
	 * @param browserPath
	 * @throws IOException
	 */
	public static void openBrowser(String url, final String browserPath) throws IOException {
		final String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("windows") != -1) {
			url = appendUrlForWindows2000(os, url);
		}
		Runtime.getRuntime().exec(new String[] { browserPath, url });
	}

	/**
	 * @param os
	 * @param url
	 * @return
	 */
	private static String appendUrlForWindows2000(final String os, final String url) {
		String ret = url;
		if (os.indexOf("2000") != -1) {
			if (url.toLowerCase().endsWith(".html") || url.toLowerCase().endsWith(".htm")) {
				ret += "#";
			}
		}
		return ret;
	}

	/**
	 * @return list of IPv4 addresses
	 */
	public static List<String> getHostAddress() {
		Enumeration<NetworkInterface> netInf = null;
		String displayName = null;
		String hostAddress = null;
		List<String> ipAddresses = new ArrayList<String>();
		try {
			// get all interfaces; ethernet, wifi, virtual... etc
			netInf = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException se) {
			throw new RuntimeException(se);
		}

		if (netInf == null) {
			throw new RuntimeException("No network interfaces found.");
		}

		while (netInf.hasMoreElements()) {
			NetworkInterface element = netInf.nextElement();
			try {
				if (element.isVirtual() || element.isLoopback()) {
					// discard virtual and loopback interface (127.0.0.1)
					continue;
				}

				// rest are either Wifi or ethernet interfaces
				// loop through and print the IPs
				Enumeration<InetAddress> addresses = element.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress ip = addresses.nextElement();
					if (ip instanceof Inet4Address) {
						if (ip.isSiteLocalAddress() && element.isUp()) {
							displayName = element.getDisplayName();
							hostAddress = ip.getHostAddress();
							ipAddresses.add(hostAddress);
						}
					}
				}
			} catch (SocketException se) {
				se.printStackTrace();
			}
		}
		return ipAddresses;
	}
}
