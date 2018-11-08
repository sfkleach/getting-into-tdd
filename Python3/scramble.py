"""
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

You should write a function scramble that takes the secret and some input
text and returns the scrambled text. To make the problem a bit more fun:

  * Preserve any non-alphabetic characters e.g. 'hello, world!' should
    become 'jeoom, vmfou!' (At least I think it should - doing this quickly!)

  * Preserve the case of alphabetic characters e.g. 'Steve' becomes 'Cweze'.

N.B. Technically this is a cipher not a code. In fact it is a 'monoalphabetic
substitution cipher.' (I only add this because cryptoheads will spin clean off
if I don't.)
"""

def scramble( *, secret, plaintext ):
    """
    Take two mandatory named parameters, a secret and some plaintext to
    scramble. Returns the scrambled result.
    :param secret: A 26-letter string that is a permutation of the alphabet (lower case).
    :param plaintext: A string that is text to scramble.
    :return: The scrambled text.
    """
    pass