package uk.org.codehub.getting_into_tdd;

import java.util.Iterator;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * This is a simple helper method for working with org.w3c.dom.Node.
 * It is not considered to be part of the problems for the workshop.
 *
 */
public class Children implements Iterator< Element > {
	
	Element child;

	public Children( Element element ) {
		this.child = advance( element.getFirstChild() );
	}
	
	private static Element advance( Node child ) {
		while ( child != null && !( child instanceof Element ) ) {
			child = child.getNextSibling();
		}			
		return (Element)child;
	}

	@Override
	public boolean hasNext() {
		return this.child != null;
	}

	@Override
	public Element next() {
		Element c = this.child;
		this.child = advance( this.child );
		return c;
	}
	
	public static Iterable< Element > getIterable( Element e ) {
		return () -> new Children( e );
	}
	
}