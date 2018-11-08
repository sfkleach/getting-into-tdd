"""
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
        dict(
            thedate='2018/11/08',
            yourname='${firstname} ${lastname}',
            firstname='Steve',
            lastname='Leach'
        )
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

    applyMacros( '${left}OK${right}', dict( left='${', right='}', OK='NOT OK' ) )

Also, as variable names are limited to the usual alphanumerics and underscores,
be careful not to get faked out by things like "${Ha ha ha}", which should
not be matched.

Bonus Problem - the Circular Substitution
-----------------------------------------
YOU are the hero in this exciting bonus installment! A tricky situations has
arisen and the lead designer is nowhere to be found (in a meeting). Our bonus
problem is to cope with circular macro definitions. YOU must make the key
decision on what to do and implement it:

    applyMacros( '${loop}', dict( loop='${loop}' ) )

"""

def applyMacros( template, macros ):
    """
    Apply a set of macros to a template string.
    :param template_string: The string to have substitutions applied.
    :param macros: Keyword arguments that define the macros.
    :return:
    """
    pass