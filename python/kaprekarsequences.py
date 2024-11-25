"""
Background
----------
6174 is known as Kaprekar's constant after the Indian mathematician
D. R. Kaprekar. This number is has the following strange property - if you
repeatedly follow the process below you always end up at 6174:

  * Take any four-digit number, using at least two different digits. (Leading zeros are allowed.)
  * Arrange the digits in descending and then in ascending order to get two four-digit numbers
    - adding leading zeros if necessary.
  * Subtract the smaller number from the bigger number.

The above calculation, known as Kaprekar's routine, reaches the fixed point,
6174, in at most 8 iterations. Once 6174 is reached, the process will continue
yielding 7641 – 1467 = 6174. For example, choose 3524:

    5432 – 2345 = 3087
    8730 – 0378 = 8352
    8532 – 2358 = 6174
    7641 – 1467 = 6174

Problem
-------
Your function should return the sequence of numbers starting from the first
until a fix-point is reached. So the above example would return
[ 3524, 3087, 8352, 6174]. Here's some handy test-data.

    1.    6174 -> [6174]
    2.    8532 -> [8532, 6174]
    3.    4266 -> [4266, 4176, 6174]
    4.    3524 -> [3524, 3087, 8352, 6174]
    5.    2111 -> [2111, 999, 8991, 8082, 8532, 6174]
    6.    9831 -> [9831, 8442, 5994, 5355, 1998, 8082, 8532, 6174]

"""


def kaprekarsSequence( N ):
    """
    Given a number with up to 4 digits, return the convergence sequence
    from repeatedly applying Kaprekar's routine.
    :param N: The original number
    :return: A list starting with the original number and the results of
    applying Kaprekar's routine once, twice, etc until it converges on
    a fix-point.
    """
    pass