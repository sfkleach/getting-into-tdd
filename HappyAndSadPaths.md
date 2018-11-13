# Happy And Sad Paths

When we're designing the behaviour of a method, we usually start by focussing
on what happens when everything goes right - the so-called ***happy path***.
The happy-path is distinguished by all our assumptions and expectations about
normal operation being satisfied. 

For example, we might be writing a method to fetch a web page and check for
URLs. The happy-path would be that we are given a valid URL *and* that there
is a website listening at the end of that URL *and* that the website responds
with a status code of 200. This captures what we think of as the normal 
situation.

Another example might be looking up records in a database. The happy path is
that we have database connection string, that connects to a database with 
credentials that give us the permission to do what we need to. 

On a ***sad-path*** one or more of the pre-requisites for a happy-path are, 
alas, not satisfied. The URL is badly-formed. The database user has the wrong
permissions. The network is down. 

In all these cases the method will still grind on and do *something*. And we
can either not worry about what that will be and cross our fingers that things
will be all right on the night ... or we take control. And he obvious way to 
take control, which is usually the right option, then we have to set up "sad 
scenarios" and treat them like any other unit test.


