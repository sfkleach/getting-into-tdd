"""
Background
----------
This is a nice starter example. We're asked to 'scramble' a message using
a simple code where each letter of the alphabet is turned into a different
one. The secret code is supplied as string that is a permutation of the
26 letters of the alphabet. e.g.

    secret = 'bsgueinjrlkotymqdfcwxzvaph'

To figure out how to translate the letter 'x', for example, you just line it
below the ordinary alphabet (in a fixed pitch font) and read from top to bottom.
In this case 'x' turns into 'a'.

    'abcdefghijklmnopqrstuvwxyz'
    'bsgueinjrlkotymqdfcwxzvaph'

N.B. Technically this is a cipher not a code. In fact it is a 'monoalphabetic
substitution cipher.' (I only add this because cryptoheads will spin clean off
if I don't.)


Problem
-------
You should write a function scramble that takes the secret and some input
text and returns the scrambled text. To make the problem a bit more fun:

  * Preserve any non-alphabetic characters e.g. 'hello, world!' should
    become 'jeoom, vmfou!' (At least I think it should - doing this quickly!)

  * Preserve the case of alphabetic characters e.g. 'Steve' becomes 'Cweze'.

Graded Scenarios
----------------

  * The simplest scenario would be the empty string. This isn't a great choice
    though because it doesn't drive anything useful. Only takes a few seconds
    to write so do it anyway - it's going to help with the next test.

        # You should write something like this, proving that this test isn't purposeful.
        def scramble( *, secret, plaintext ):
            return ''

  * The next simplest is a single lower-case character as input. This does
    force you to implement the transformation. And the fact that you have to
    deal with 0-character and 1-character strings means you have enough
    justification to write the loop. I expect to see the code
    turn into something like this:

        # You'll want to give this helper some TTD goodness.
        def transform( ch, *, secret ):
            return secret[ ord( ch ) - ord( 'a' ) ]

        def scramble( *, secret, plaintext ):
            return ''.join( [ transform( ch, secret=secret ) for ch in plaintext ] )

    If you like functional programming maybe you'll go this way:

        def transform( secret ):
            return lambda ch: secret[ ord( ch ) - ord( 'a' ) ]

        def mapstring( f, text ):
            return ''.join( map( f, text ) )

        def scramble( *, secret, plaintext ):
            return mapstring( transform( secret ), plaintext )

  * Now factor in non-alphabetic characters. That should drive you to
    tweak the transform.

        def transform( ch, *, secret ):
            try:
                return secret[ ord( ch ) - ord( 'a' ) ]
            except IndexError:
                return ch

  * And then preserve case:

        def transform( ch, *, secret ):
            if ch.isupper():
                return transform( ch.lower(), secret=secret ).upper()
            else:
                try:
                    return secret[ ord( ch ) - ord( 'a' ) ]
                except IndexError:
                    return ch

  * Finally wrap up with the sad-paths. What happens if secret isn't 26 long?
    what happens if it is longer? What happens if you don't pass plaintext as
    a string - and does it matter?

"""

secret = 'bsgueinjrlkotymqdfcwxzvaph'


def scramble( *, secret, plaintext ):
    """
    Take two mandatory named parameters, a secret and some plaintext to
    scramble. Returns the scrambled result.
    :param secret: A 26-letter string that is a permutation of the alphabet (lower case).
    :param plaintext: A string that is text to scramble.
    :return: The scrambled text.
    """
    pass

_alphabet = [ chr(i) for i in range( ord('a'), ord('z') + 1 ) ]

def checkSecretIsValid( *, secret ):
    # sorted will return a list of single-letter strings.
    if sorted( secret ) == _alphabet:
        return secret
    else:
        raise Exception( 'Invalid secret supplied: {}'.format( secret ) )
