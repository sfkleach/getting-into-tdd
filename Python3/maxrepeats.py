"""
This is a bonus problem that relates to the nucleotide example - but is a
lot harder. Given a DNA sequence (string consisting of the letters a, c, g & t,
find the longest subsequence that occurs at different positions in the DNA.

For example: in this sequence, good answers are 'aaga' and 'ccag'

    "aacacttcaccaggtatcgtgaaggctcaagattacccagagaacctttgcaatataaga"

In the longer DNA_Sequence in the nucleotide.py file, a good answer would
be 'cctaagtaatta'.

Because there many be many equally long sub-strings that fit this, the
result should be a generator for all the possible candidates.
"""

def maxrepeats( *, dna ):
    """
    Given a string, find the longest sub-strings that occur in more than
    one place inside the string.
    :param dna: the string to find repeats inside
    :return: generator for the longest sub-strings
    """
    pass

