"""
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
"""

def xresistance( x ):
    """
    Given an XML element x returns the overall resistance. If the element does
    not conform to the expected form a ValueError exception is thrown.
    :param x: The XML element to evaluate
    :return: The overall resistance of the components
    """
    pass


def resistance( string ):
    """
    Given an XML string that denotes resistors wired in series and parallel,
    calculate the overall resistance.
    :param string: The sub-circuit as a string
    :return: The overall resistance as a number
    """
    pass