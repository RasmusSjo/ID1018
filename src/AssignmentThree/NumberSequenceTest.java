package AssignmentThree;

// NumberSequenceTest.java

/****************************************************************

NumberSequenceTest is a test program for the classes
ArrayNumberSequence and LinkedNumberSequence.

Author
Fadil Galjic

****************************************************************/

import static java.lang.System.out;

class NumberSequenceTest
{
    public static void main (String[] args)
    {
		double[] realNumbers =
		    {1.0, 2.0, 3.0, 5.0, 8.0, 13.0, 21.0};
        NumberSequence sequence = null;
        // sequence = new ArrayNumberSequence(realNumbers);
        // sequence = new LinkedNumberSequence(realNumbers);
        out.println("the sequence:");
        out.println(sequence);
        out.println();

        // add code here
    }
}