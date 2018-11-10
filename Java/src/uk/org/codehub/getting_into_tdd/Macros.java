package uk.org.codehub.getting_into_tdd;

import java.util.Map;

/*
This problem arose at work a couple of years ago. We needed to be able
to generalise our web-application dictionary so it could perform substitutions
depending on a bunch of application settings. I have reworded it a bit to
make it easier to understand in the context of a workshop.

Vision
------
Given a template-string and a dictionary of replacements, that maps NAMEs to
REPLACEMENTs, find occurrences of '${NAME}' in the template-string and
replace them. If any REPLACEMENT text also has '${...}' in it, then process
that before replacing it.

For example:

    applyMacros(
        "The date today is ${thedate}, ${yourname}.",
        new TreeMap() {{
            put( "thedate", "2018/11/08" );
            put( "yourname", "${firstname} ${lastname}" );
            put( "firstname", "Steve" );
            put( "lastname", "Leach" );
        }}
    )

should result in:

    "The date today is 2018/11/08, Steve Leach."

Macro names are limited to the usual choice of alphanumerics and underscores
and do not start with a digit. This eliminates some rather uninteresting
complications.

Gotchas
-------
You have to watch out for 'accidentally' creating interpolated variables
like this:

    applyMacros( "${left}OK${right}", new TreeMap() {{ put( "left", "${" ); put( "right", "}"; put( "OK", "NOT OK" ) }} )

Also, as variable names are limited to the usual alphanumerics and underscores,
be careful not to get faked out by things like "${Ha ha ha}", which should
not be matched.

Bonus Problem - the Circular Substitution
-----------------------------------------
YOU are the hero in this exciting bonus installment! A tricky situations has
arisen and the lead designer is nowhere to be found (in a meeting). Our bonus
problem is to cope with circular macro definitions. YOU must make the key
decision on what to do and implement it:

    applyMacros( "${loop}", new TreeMap() {{ put( loop", "${loop}" ); }} )

This bonus problem makes this a fair bit harder. Why not do a mini-spike, which
means working in a temporary branch you solemnly promise to throw away, figure
out how you do it and THEN use that knowledge to write the tests you need to 
instantiate any helper methods, etc? By breaking it up into two stages you avoid
having to figure out the design at the same time as learning test-driven
development!

*/

import java.util.regex.*;  


//  Design Suggestion: start by making Macros be a wrapper around the Map so that you
//	write the answer almost entirely as methods on the Macros class rather than static
//  functions. It will feel much more idiomatic. It's not cheating to do this because
//  the Macros class is a completely blank slate and has no purpose other than being a
//  place to put the initial template for applyMacros.
class Macros {
	
	public static String applyMacros( String template, Map< String, String > macros ) {
	    return null;
	}

}
