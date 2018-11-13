# Background

This is a nice starter example. We're asked to 'scramble' a message using
a simple code where each letter of the alphabet is turned into a different
one. The secret code is supplied as string that is a permutation of the
26 letters of the alphabet. e.g.

```python
secret = 'bsgueinjrlkotymqdfcwxzvaph'
```

To figure out how to translate the letter 'x', for example, you just line it
below the ordinary alphabet (in a fixed pitch font) and read from top to bottom.
In this case 'x' turns into 'a'.

```python
'abcdefghijklmnopqrstuvwxyz'
'bsgueinjrlkotymqdfcwxzvaph'
```

N.B. Technically this is a cipher not a code. In fact it is a 'monoalphabetic
substitution cipher.' (I only add this because cryptoheads will spin clean off
if I don't.)


# Problem
You should write a function scramble that takes the secret and some input
text and returns the scrambled text. To make the problem a bit more fun:

  * Preserve any non-alphabetic characters e.g. 'hello, world!' should
    become 'jeoom, vmfou!' (At least I think it should - doing this quickly!)

  * Preserve the case of alphabetic characters e.g. 'Steve' becomes 'Cweze'.

Here's a template to start with - assuming you are using Python3 - and you can 
also use [scramble.py](Python3/scramble.py) from this repo. At the moment the
example code is all in Python3 but if this proves popular I will add other
languages.

```python
def scramble( *, secret, plaintext ):
    """
    Take two mandatory named parameters, a secret and some plaintext to
    scramble. Returns the scrambled result.
    :param secret: A 26-letter string that is a permutation of the alphabet (lower case).
    :param plaintext: A string that is text to scramble.
    :return: The scrambled text.
    """
    pass
```

# Graded Scenarios

## Scenario 1: Empty String 
The simplest scenario would be the empty string. This isn't a great choice
though because it doesn't drive anything useful. Only takes a few seconds
to write so do it anyway - it's going to help with the next test.

If you are using the pytest framework, you'll create a file test_scramble.py
and write a test that looks something like this:

```python
from scramble import *

a_secret = 'bsgueinjrlkotymqdfcwxzvaph'

def test_scramble_EmptyString():
    # I like to write my asserts in the order EXPECTED == ACTUAL.
    assert '' == scramble( secret=a_secret, plaintext='' )
```

You can try running the unit test, as a skeleton definition for scramble is
already sketched out in scramble.py. Unsurprisingly it fails and we are "on
red".


Our job is to get back to "green" using a simple, direct approach that covers
the failing test. We are not licensed to go and implement lots of code. So, in
this case, should write something like this ...


```python
def scramble( *, secret, plaintext ):
    return ''
```

Yes, it looks a little absurd. But it gets us back on 'green', it is simple and 
direct. The fact of the matter is that our initial unit test isn't very
purposeful, expressing very little about what we want scramble to do. So this
minimal response is exactly right. Don't sweat the small stuff.

## Scenario 2: One Character

The next simplest is a single lower-case character as input. This does
force you to implement the transformation. And the fact that you have to
deal with 0-character and 1-character strings means you have enough
justification to write the loop. 

### Breakout Helper Function: transform

As it is fairly obvious that we're going to process the plaintext one 
character at a time, I would break out a helper function ```transform```
to translate just one character. So I would go into ```scramble.py``` and
sketch out that function:

```python
def transform( ch, *, secret ):
    pass
```
Here is a unit test you might write (added to test_scramble.py). I have chosen
throw several test values at it using pytest's decorator for passing parameters
to a test. 

```python
import pytest
@pytest.mark.parametrize( 
    "test_value,expected", 
    [
        ( "z", "h" ),
        ( "a", "b" ),
        ( "b", "s" ),
    ] 
)
def test_transform_OneAlphabeticCharacter( test_value, expected ):
    actual = transform( test_value, secret=a_secret )
    assert expected == actual
```

We run the test, check we're on red, and then respond by adding some code. Now
if we were being silly we could respond by writing code like this ...

```python
def transform( ch, *, secret ):
    return (
        "h" if ch == "z" else
        "b" if ch == "a" else
        "s"
    )
```

... but this is actually more clumsy and less direct than writing the code
properly as:

```python
def transform( ch, *, secret ):
    return secret[ ord( ch ) - ord( 'a' ) ]
```

And that's why we used ```parametrize```: using multiple test values drives us
away from value-sensitive code and towards logical answers.


### Use Helper Function transform to Implement scramble


Now we can flip back to ```test_scramble.py``` and write a unit test for that:

```python
@pytest.mark.parametrize( 
    "test_value,expected", 
    [
        ( "z", "h" ),
        ( "a", "b" ),
        ( "b", "s" ),
    ] 
)
def test_scramble_OneAlphabeticCharacter( test_value, expected ):
    ciphertext = scramble( secret=a_secret, plaintext=test_value )
    assert expected == ciphertext
```

In response, we need to write something like this:

```python
def scramble( *, secret, plaintext ):
    return ''.join( [ transform( ch, secret=secret ) for ch in plaintext ] )
```

### An Alternative to Think About

If you like functional programming maybe you would have wanted to do this
way. How would you go about writing tests to evolve the code this way?

```python
def transform( secret ):
    return lambda ch: secret[ ord( ch ) - ord( 'a' ) ]

def mapstring( f, text ):
    return ''.join( map( f, text ) )

def scramble( *, secret, plaintext ):
    return mapstring( transform( secret ), plaintext )
```

## Scenario 3: Non-Alphabetic Characters

Now factor in non-alphabetic characters. You could extend the unit test for
transform or write another one.

```python
@pytest.mark.parametrize( 
    "test_value,expected", 
    [
        ( "!", "!" ),
        ( "$", "$" )
        ( "7", "7" ),
    ] 
)
def test_transform_OneNonAlphabeticCharacter( test_value, expected ):
    actual = transform( test_value, secret=a_secret )
    assert expected == actual
```

That should drive you to tweak the transform.

```python
def transform( ch, *, secret ):
    try:
        return secret[ ord( ch ) - ord( 'a' ) ]
    except IndexError:
        return ch
```

### Think About

There's no difference between ```test_transform_OneAlphabeticCharacter``` and 
```test_transform_OneNonAlphabeticCharacter``` apart from their name. If we want
to share their code, how should we do that? (Hint: It's not complicated.) What
are the merits of sharing vs not-sharing their code?

## Scenario 4: Preserve Case

Nearly done! Consider preserving upper and lower case. In ```test_scramble.py``` we
want to add a test like this:

```python
@pytest.mark.parametrize( 
    "test_value,expected", 
    [
        ( "x", "a" ),
        ( "X", "A" ),
        ( "z", "h" ),   # We had this one before but no harm in repeating.
        ( "Z", "H" ),
    ] 
)
def test_transform_CasePreservation( test_value, expected ):
    actual = transform( test_value, secret=a_secret )
    assert expected == actual
```


This should push us towards something like this:

```python
def transform( ch, *, secret ):
    if ch.isupper():
        return transform( ch.lower(), secret=secret ).upper()
    else:
        try:
            return secret[ ord( ch ) - ord( 'a' ) ]
        except IndexError:
            return ch
```

## Scenario 5: Sad Paths

Finally we wrap up with the [sad-paths](HappyAndSadPath.md), the unfortunate pathways through the 
code that happen when assumptions about normal use are violated.

  * What happens if secret isn't 26 long?
  * What happens if it is longer? 
  * What happens if you don't pass plaintext as a string - and does it matter? 

Time to make some decisions!

### Valid Secret


