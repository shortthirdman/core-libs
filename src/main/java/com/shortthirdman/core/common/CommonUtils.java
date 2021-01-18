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
package com.shortthirdman.core.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author Swetank Mohanty
 * @since 0.0.1
 * @version 0.0.1
 */
public class CommonUtils {
	
	private static String defaultPattern = "###,##0.0###########";

	/**
	 * Converts a delimited text string into list of string
	 * 
	 * @param delimitedText
	 * @param delimiter
	 * @return
	 */
	public static List<String> convertToList(String delimitedText, String delimiter) {
		List<String> result = new ArrayList<>();
		try {
			String[] commaSeparatedArr = delimitedText.split(delimiter);
			result = Arrays.stream(commaSeparatedArr).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Parse the date string value with date format
	 * 
	 * @param dateValue
	 * @param dateFormat
	 * @return
	 */
	public static LocalDate parseDate(String dateValue, String dateFormat) {
		if (dateFormat == null || dateFormat.isEmpty()) {
			return LocalDate.parse(dateValue, DateTimeFormatter.BASIC_ISO_DATE);
		} else {
			return LocalDate.parse(dateValue, DateTimeFormatter.ofPattern(dateFormat, Locale.ENGLISH));
		}
	}

	/**
	 * Convert a snake-case text string to camel-case
	 * 
	 * @apiNote Capitalize first letter of string. Replace the first occurrence of
	 *          letter that present after the underscore, to capitalize form of next
	 *          letter of underscore
	 * @param text
	 * @return the camel-case converted text string
	 */
	public static String snakeToCamel(String text) {
		text = text.substring(0, 1).toUpperCase() + text.substring(1);
		while (text.contains(CommonConstants.UNDERSCORE)) {
			text = text.replaceFirst("_[a-z]",
					String.valueOf(Character.toUpperCase(text.charAt(text.indexOf(CommonConstants.UNDERSCORE) + 1))));
		}
		return text;
	}

	/**
	 * Convert a camel-case text string to snake-case
	 * 
	 * @apiNote Replace the given regex with replacement string and convert it to
	 *          lower case.
	 * @param text
	 * @return snake-case converted text string
	 */
	public static String camelToSnake(String text) {
		String regex = "([a-z])([A-Z]+)";
		String replacement = "$1_$2";
		text = text.replaceAll(regex, replacement).toLowerCase();
		return text;
	}

	/**
	 * Checks if the list is valid - whether the list is empty or size is zero
	 * 
	 * @param list
	 * @return boolean value
	 */
	public static Boolean isListValid(List<?> list) {
		if (list != null) {
			if (list.isEmpty() || list.size() == 0) {
				return Boolean.FALSE;
			} else {
				return Boolean.TRUE;
			}
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * @param textValue
	 * @return
	 */
	public static String trimTextValue(String textValue) {
		String trimmedText;
		if (textValue == null) {
			trimmedText = null;
		} else {
			trimmedText = textValue.toString().trim();
		}
		return trimmedText;
	}

	/**
	 * @param amount
	 * @param precision
	 * @param pattern
	 * @param locale
	 * @return
	 */
	public static String formatCurrency(double amount, int precision, String pattern, Locale locale) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		DecimalFormat df = (DecimalFormat) nf;
		df.setMinimumFractionDigits(precision);
		df.setMaximumFractionDigits(precision);
		df.setDecimalSeparatorAlwaysShown(true);
		df.applyPattern(pattern);
		return df.format(amount);
	}

	/**
	 * @param amount
	 * @param precision
	 * @param pattern
	 * @param locale
	 * @return
	 */
	public static String formatNumber(double amount, int precision, String pattern, Locale locale) {
		NumberFormat nf = NumberFormat.getNumberInstance(locale);
		DecimalFormat df = (DecimalFormat) nf;
		df.setMinimumFractionDigits(precision);
		df.setMaximumFractionDigits(precision);
		df.setDecimalSeparatorAlwaysShown(true);
		df.applyPattern(pattern);
		return df.format(amount);
	}

	/**
	 * @param amount
	 * @param precision
	 * @param locale
	 * @return
	 */
	public static String formatCurrency(double amount, int precision, Locale locale) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		nf.setMinimumFractionDigits(precision);
		nf.setMaximumFractionDigits(precision);
		return nf.format(amount);
	}

	/**
	 * @param amount
	 * @param precision
	 * @param locale
	 * @return
	 */
	public static String formatNumber(double amount, int precision, Locale locale) {
		NumberFormat nf = NumberFormat.getNumberInstance(locale);
		nf.setMinimumFractionDigits(precision);
		nf.setMaximumFractionDigits(precision);
		return nf.format(amount);
	}
	
    /**
     * Changes the format of the number by changing the string pattern.
	 * If argument is float or double and contains tailing zeros, it removes them.
	 * If argument is float or double then no change in return type.
	 * 
     * @param number
     * @param pattern
     * @param locale
     * @return
     */
    public static String changeToDecimalFormat(Object number, String pattern, Locale locale) {
        BigDecimal bdNumber = new BigDecimal(number.toString());
        bdNumber = bdNumber.stripTrailingZeros();           //Returns a BigDecimal with any trailing zero's removed
        if (pattern == null) {
        	pattern = defaultPattern; //To apply formatting when the number of digits in input equals the pattern
        }
        DecimalFormat newFormat = new DecimalFormat(pattern, new DecimalFormatSymbols(locale));
        return newFormat.format(bdNumber);
    }

    /**
     * Removes commas from the number string
     * @param number
     * @return
     */
    public static double removeCommasFromNumber(Object number) {
        try {
            StringBuffer inputNo = new StringBuffer(number.toString());
            if (inputNo.length() > 0) {
                while (inputNo.indexOf(CommonConstants.COMMA) != -1) {
                    inputNo.deleteCharAt(inputNo.indexOf(CommonConstants.COMMA));
                }
            } else {
                return 0.0;
            }
            return Double.parseDouble(inputNo.toString());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    /* Some times its required to have a fixed set of decimal places for a 
     * number. We can set that by changing the precision number for a particular
     * input BigDecimal Input String
     */
    public static String changeToRequiredDecimals(String bigDecimalString, int precision) {
        String newFormattedString = null;
        String afterDecimal = null;
        if (bigDecimalString == null || bigDecimalString.length() == 0) {
            return "0.0";
        }
        if (bigDecimalString.contains(CommonConstants.DOT_PERIOD)) {
            afterDecimal = bigDecimalString.substring(bigDecimalString.indexOf(CommonConstants.DOT_PERIOD) + 1);
            int length = Math.abs((afterDecimal.length() - precision));
            if (afterDecimal.length() < precision) {
                newFormattedString = bigDecimalString;
                for (int i = 0; i < length; i++) {
                    newFormattedString = newFormattedString + "0";
                }
            } else if (afterDecimal.length() > precision) {
                newFormattedString = bigDecimalString.substring(0, bigDecimalString.length() - length);
                if (precision == 0) {
                    newFormattedString = newFormattedString.substring(0, newFormattedString.indexOf(CommonConstants.DOT_PERIOD));
	            } else {
	                newFormattedString = bigDecimalString;
	            }

	        } else {
				if (precision > 0)
					newFormattedString = bigDecimalString + CommonConstants.DOT_PERIOD;
				else
					newFormattedString = bigDecimalString;
				for (int i = 0; i < precision; i++) {
					newFormattedString = newFormattedString + "0";
				}
	        }
        }
        return newFormattedString;
    }
    
    /**
     * Checks if the year is leap year or not
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
		if ((year % 100 != 0) || (year % 400 == 0)) {
			return true;
		}
		 return false;
	}
}
