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

import java.util.Locale;

/**
 * Class provides common functions on number formats.
 * @author Swetank Mohanty
 * @since 0.0.1
 * @version 0.0.1
 */
public class NumberUtility {
	
    /**
     * Method takes Object as parameter and returns decimal number.
     * if argument is float or double and contains tailing zeros
     * it removes them. If argument is float or double then no change in return type.
     * Change the Format of the Number by changing the String Pattern
     */
    public static String changeToDecimalFormat(Object number) {
        BigDecimal bdNumber = new BigDecimal(number.toString());
        bdNumber = bdNumber.stripTrailingZeros();           //Returns a BigDecimal with any trailing zero's removed
        String pattern = "###,##0.0###########";		//To apply formatting when the number of digits in input equals the pattern
        DecimalFormat newFormat = new DecimalFormat(pattern, new DecimalFormatSymbols(Locale.US));
        return newFormat.format(bdNumber);
    }

    /**
	 * Method takes Object as parameter and removes commas from the parameter
	 * @param number
	 * @return the converted number after removing commas
	 */
    public static double removeCommasFromNumber(Object number) {
        try {
            StringBuffer inputNo = new StringBuffer(number.toString());
            if (inputNo.length() > 0) {
                while (inputNo.indexOf(",") != -1) {
                    inputNo.deleteCharAt(inputNo.indexOf(","));
                }
            } else {
                return 0.0;
            }
            return Double.parseDouble(inputNo.toString());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    /**
	 * Some times its required to have a fixed set of decimal places for a 
     * number. We can set that by changing the precision number for a particular
     * input BigDecimal Input String
	 * @param bigDecimalString the {@link BigDecimal} input string
	 * @param precision the precision for the number format
	 * @return the connverted number
     */
    public static String changeToRequiredDecimals(String bigDecimalString, int precision) {
        String newFormattedString = null;
        String afterDecimal = null;
        if (bigDecimalString == null || bigDecimalString.length() == 0) {
            return "0.0";
        }
        if (bigDecimalString.contains(".")) {
            afterDecimal = bigDecimalString.substring(bigDecimalString
                    .indexOf(".") + 1);
            int length = Math.abs((afterDecimal.length() - precision));
            if (afterDecimal.length() < precision) {
                newFormattedString = bigDecimalString;
                for (int i = 0; i < length; i++) {
                    newFormattedString = newFormattedString + "0";
                }
            } else if (afterDecimal.length() > precision) {
                newFormattedString = bigDecimalString.substring(0,
                        bigDecimalString.length() - length);
                if (precision == 0) {
                    newFormattedString = newFormattedString.substring(0,
                            newFormattedString.indexOf("."));
	            } else {
	                newFormattedString = bigDecimalString;
	            }

	        } else {
				if (precision > 0)
					newFormattedString = bigDecimalString + ".";
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
}