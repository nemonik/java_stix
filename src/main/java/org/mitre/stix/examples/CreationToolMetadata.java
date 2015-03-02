/**
 * Copyright (c) 2015, The MITRE Corporation. All rights reserved.
 * See LICENSE for complete terms.
 */
package org.mitre.stix.examples;

import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.UUID;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;

import org.mitre.cybox.common_2.ToolInformationType;
import org.mitre.cybox.common_2.ToolsInformationType;
import org.mitre.stix.common_1.InformationSourceType;
import org.mitre.stix.common_1.StructuredTextType;
import org.mitre.stix.stix_1.STIXHeaderType;
import org.mitre.stix.stix_1.STIXPackage;
import org.mitre.stix.util.Schema;

/**
 * Build a STIX Document with Tool Information
 * 
 * Same as {@link https
 * ://raw.githubusercontent.com/STIXProject/python-stix/master
 * /examples/creation_tool_metadata.py}.
 * 
 * @author nemonik (Michael Joseph Walsh <github.com@nemonik.com>)
 *
 */
public class CreationToolMetadata {

	public CreationToolMetadata() {
	}

	/**
	 * @param args
	 * @throws JAXBException
	 * @throws ParserConfigurationException
	 * @throws DatatypeConfigurationException
	 */
	public static void main(String[] args) throws JAXBException,
			ParserConfigurationException, DatatypeConfigurationException {

		// Get time for now.
		XMLGregorianCalendar now = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(
						new GregorianCalendar(TimeZone.getTimeZone("UTC")));

		STIXHeaderType header = new STIXHeaderType()
				.withDescription(new StructuredTextType("Example", null))
				.withInformationSource(
						new InformationSourceType().withTools(new ToolsInformationType()
								.withTools(new ToolInformationType()
										.withName(
												"org.mitre.stix.examples.CreationToolMetadata")
										.withVendor("The MITRE Corporation"))));

		STIXPackage stixPackage = new STIXPackage()
				.withSTIXHeader(header)
				.withVersion("1.1.1")
				.withTimestamp(now)
				.withId(new QName("http://example.com/", "package-"
						+ UUID.randomUUID().toString(), "example"));

		System.out.println(stixPackage.toXMLString());

		System.out.println(Schema.getInstance().validate(
				stixPackage.toXMLString()));

	}
}
