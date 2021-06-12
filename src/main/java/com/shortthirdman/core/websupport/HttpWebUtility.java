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
package com.shortthirdman.core.websupport;

import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Utility class for various HTTP and web support
 * @author shortthirdman
 * @since 1.1.0
 *
 */
public class HttpWebUtility {

	/**
	 * Returns the {@link Cookie} value by provided name
	 * @param request
	 * @param name
	 * @return
	 */
	public static Optional<String> getServletCookie(HttpServletRequest request, String name) {
		return Arrays.stream(request.getCookies())
				.filter(cookie -> name.equals(cookie.getName()))
				.map(Cookie::getValue)
				.findFirst();
	}
	
	/**
	 * Validate that a parameter constraint it respected or violated. Return a message if violated.
	 * @param request {@link HttpServletRequest} holding parameters to validate
	 * @param constraint Constraint to apply to one request parameter.
	 * @return A string representing constraint violation if any. null otherwise.
	 */
	public static String validateConstraint(HttpServletRequest request, ParameterConstraint constraint) {
	   String value = null;
	   if (ParameterLocation.HEADER == constraint.getIn()) {
	      value = request.getHeader(constraint.getName());
	   } else if (ParameterLocation.QUERY == constraint.getIn()) {
	      value = request.getParameter(constraint.getName());
	   }

	   if (value != null) {
	      if (constraint.getMustMatchRegexp() != null && !Pattern.matches(constraint.getMustMatchRegexp(), value)) {
	            return "Parameter " + constraint.getName() +  " should match " + constraint.getMustMatchRegexp();
	      }
	   } else  {
	      if (constraint.isRequired()) {
	         return "Parameter " + constraint.getName() +  " is required";
	      }
	   }
	   return null;
	}
}
