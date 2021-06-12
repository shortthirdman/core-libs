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

/**
 * Companion object for Operation that may be used to express constraints on
 * request parameters.
 * @author shortthirdman
 * @since 1.1.0
 */
public class ParameterConstraint {

	private String name;

	private ParameterLocation in;

	private boolean required;

	private boolean recopy;

	private String mustMatchRegexp;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the in
	 */
	public ParameterLocation getIn() {
		return in;
	}

	/**
	 * @param in the in to set
	 */
	public void setIn(ParameterLocation in) {
		this.in = in;
	}

	/**
	 * @return the required
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * @param required the required to set
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * @return the recopy
	 */
	public boolean isRecopy() {
		return recopy;
	}

	/**
	 * @param recopy the recopy to set
	 */
	public void setRecopy(boolean recopy) {
		this.recopy = recopy;
	}

	/**
	 * @return the mustMatchRegexp
	 */
	public String getMustMatchRegexp() {
		return mustMatchRegexp;
	}

	/**
	 * @param mustMatchRegexp the mustMatchRegexp to set
	 */
	public void setMustMatchRegexp(String mustMatchRegexp) {
		this.mustMatchRegexp = mustMatchRegexp;
	}
}
