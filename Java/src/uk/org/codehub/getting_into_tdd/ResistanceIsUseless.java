package uk.org.codehub.getting_into_tdd;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

/*
This is a bonus problem that builds on resistance.py. We add a
new type of component - the switch. A switch is either open or
closed:

    <switch value="open" />
    <switch value="closed" />

No current can flow through an open switch and its resistance is
effectively infinite. A closed switch however has no resistance and
it is as if the resistance was zero. For example:

    <parallel>
        <switch value="open" />
        <series>
            <resistor value = "1" />
            <resistor value = "5" />
            <switch value="closed" />
        </series>
    </parallel>

In this example, the inner 'series' can ignore the closed switch and the
outer 'parallel' can ignore the open switch. So this boils down to
a resistor of 1 in series with one of 5 = overall 6.

Adding switches means explicitly copying with zero and infinity, so
you need to decide how to represent those values and make sure that
your series and parallel computations work properly with them.

*/

class ResistanceIsUseless {
	
	// Freebie helper function for working with XML.
	static Element fromString( String component_as_string ) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse( new InputSource( new StringReader( component_as_string) ) );
			return doc.getDocumentElement();
		} catch ( Exception e ) {
			throw new RuntimeException( "Error while parsing component description", e );
		}
	}

	// Free helper function for working with XML. Iterating over the children of an XML 
	// Element is rather clumsy using the W3C DOM, as that is designed for in-place modification
	// rather than free-wheeling functional programming.
	static Iterable< Element > getChildren( Element e ) {
		return Children.getIterable( e );
	}
	
	/**
	 * Given an XML element x that describes an arrangement of resistors, returns the 
	 * overall resistance. If the element does not conform to the expected form a 
	 * RuntimeException is thrown - we don't care which for this problem.
	 * @param x an arrangement of resistors
	 * @return overall electrical resistance
	 */
	public static double xresistance( Element x ) {
		return 0.0;
	}

	/**
	 * Given an XML string that denotes an arrangement of resistors wired in series and parallel,
	 * calculate the overall resistance
	 * @param component_as_string The arrangement as a string in XML format
	 * @return The overall resistance as a floating point (double precision)
	 */
	public static double resistance( String component_as_string ) {
		return 0.0;
	}

}