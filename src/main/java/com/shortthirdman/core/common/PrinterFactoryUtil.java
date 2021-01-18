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

import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;

/**
 * A collection of printer management utility methods.
 * @author Swetank Mohanty
 * @since 0.0.1
 * @version 0.0.1
 */
public class PrinterFactoryUtil {

	/**
	 * @return
	 */
	public static Attribute[] getDefaultPrinterAttributes() {
		PrintService printer = PrintServiceLookup.lookupDefaultPrintService();
        if (printer != null) {
            AttributeSet attributes = printer.getAttributes();
            for (Attribute a : attributes.toArray()) {
                String name = a.getName();
                String value = attributes.get(a.getClass()).toString();
                System.out.println(name + " : " + value);
            }
            return attributes.toArray();
        } else {
        	return null;
        }
	}
	
	/**
	 * @return
	 */
	public static List<String> getAllPrinterNames() {
		List<String> printerNames = new ArrayList<String>();
		PrintService[] printServices = PrinterJob.lookupPrintServices();
        for (PrintService printService : printServices) {
            String name = printService.getName();
            printerNames.add(name.trim());
        }
        return printerNames;
	}
	
	/**
	 * 
	 */
	public static String getDefaultPrinterService() {
		PrintService service = PrintServiceLookup.lookupDefaultPrintService();
		String printServiceName = null;
        if (service != null) {
            printServiceName = service.getName();
        }
        return printServiceName;
	}
}
