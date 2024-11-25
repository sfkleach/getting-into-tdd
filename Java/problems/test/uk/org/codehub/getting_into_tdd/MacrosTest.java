package uk.org.codehub.getting_into_tdd;

import static org.junit.jupiter.api.Assertions.*;

import java.util.TreeMap;

import org.junit.jupiter.api.Test;

class MacrosTest {

	@Test
	void testApply() {
		String expected = "The date today is 2018/11/08, Steve Leach.";
		String actual = Macros.applyMacros(
	        "The date today is ${thedate}, ${yourname}.",
	        new TreeMap< String, String >() {{
	            put( "thedate", "2018/11/08" );
	            put( "yourname", "${firstname} ${lastname}" );
	            put( "firstname", "Steve" );
	            put( "lastname", "Leach" );
	        }}
	    );
		assertEquals( expected, actual );
	}

	@Test
	void testApplyMacros() {
		fail( "Not yet implemented" );
	}

}
