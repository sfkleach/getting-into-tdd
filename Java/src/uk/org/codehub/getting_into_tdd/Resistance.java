package uk.org.codehub.getting_into_tdd;

/*
Goal is to be able to take an XML input that is a schematic of resistors
placed in series and in parallel as part of an electrical circuit (we don't
look at the rest of the circuit). Our job is to  calculate the effective resistance
of the set of resistors.

You need to know that:

  * The resistance of a resistor is its value (as a float).

  * The resistance of a series of elements is the sum of their resistances.

  * The resistance of elements placed in parallel is trickier. One
    way to explain it is as the sum of conductances, where conductance is
    just the reciprocal of resistance i.e. conductance( S ) == resistance( S )

    Alternatively we can just write it as this slightly clumsy formula. If we have
    elements E1, E2, ... En placed in parallel and their resistances
    are R1, R2, ... Rn respectively then the total resistance R is

        R = 1 / ( 1/R1 + 1/R2 + ... + 1/Rn )

e.g. The following example should have resistance 1 / ( 1/3 + 1/( 1 + 5 ) ) == 2.0

    <parallel>
        <resistor value="3"/>
        <series>
            <resistor value = "1" />
            <resistor value = "5" />
        </series>
    </parallel>

The input will be provided as an XML string. You will find it helpful to know
that you can turn it into an XML element using xml.etree.ElementTree.fromstring.
If E is an XML element you can:

  * Get the element name as E.tag
  * Get an attribute 'value' as E.get( 'value' )
  * Get the nth child using E[n]
  * Iterate over the children using for i in E

*/

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import java.io.StringReader;

public class Resistance {
	
	// Freebie helper function for working with XML. This isn't a problem about reading XML
	// (unless you want it to be) so these come pre-built for you.
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
