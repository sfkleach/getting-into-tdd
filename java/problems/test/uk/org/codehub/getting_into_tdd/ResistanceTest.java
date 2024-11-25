package uk.org.codehub.getting_into_tdd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.w3c.dom.*;

import uk.org.codehub.getting_into_tdd.Resistance;

class ResistanceTest {

	@Test
	void testFromString() {
		Element e = Resistance.fromString( "<foo value='99'/>" );
		String x = e.getTagName();
		assertEquals(  "foo", x );
		String value = e.getAttribute( "value" );
		assertEquals( "99", value );
	}

}
