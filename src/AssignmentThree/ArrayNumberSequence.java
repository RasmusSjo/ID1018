package AssignmentThree;

// ArrayNumberSequence.java

import java.util.Arrays;

/****************************************************************

ArrayNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses an array to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

public class ArrayNumberSequence implements NumberSequence
{
	// numbers in the sequence
    private double[] numbers;

    // create the sequence
    public ArrayNumberSequence (double[] numbers)
    {
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");

		this.numbers = new double[numbers.length];
		for (int i = 0; i < numbers.length; i++)
		    this.numbers[i] = numbers[i];
	}

    // toString returns the character string representing this
    // sequence
	public String toString ()
	{
		String s = "";
		for (int i = 0; i < numbers.length - 1; i++)
		    s = s + numbers[i] + ", ";
		s = s + numbers[numbers.length - 1];

		return s;
	}

	// add code here

	@Override
	public int length() {
		return numbers.length;
	}

	@Override
	public double upperBound() {
		double bound = numbers[0];
		// Find the largest value (upper bound) in the sequence
		for (double number : numbers) {
			if (bound < number) {
				bound = number;
			}
		}
		return bound;
	}

	@Override
	public double lowerBound() {
		double bound = numbers[0];
		// Find the smallest value (lower bound) in the sequence
		for (double number : numbers) {
			if (bound > number) {
				bound = number;
			}
		}
		return bound;
	}

	@Override
	public double numberAt(int position) throws IndexOutOfBoundsException {
		return numbers[position];
	}

	@Override
	public int positionOf(double number) {
		int pos = -1;
		// Find the position of the first occurrence of the given number
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == number) {
				pos = i;
				break;
			}
		}
		return pos;
	}

	@Override
	public boolean isIncreasing() {
		for (int i = 0; i < numbers.length - 1; i++) {
			// If the number at position i is larger than the one
			// at i + 1, the sequence isn't increasing
			if (numbers[i] > numbers[i + 1]) return false;
		}
		return true;
	}

	@Override
	public boolean isDecreasing() {
		for (int i = 1; i < numbers.length; i++) {
			// If the number at position i - 1 is smaller than the one
			// at i, the sequence isn't decreasing
			if (numbers[i - 1] < numbers[i]) return false;
		}
		return true;
	}

	@Override
	public boolean contains(double number) {
		int i = 0;
		while (i < numbers.length) {
			if (numbers[i] == number) return true;
			i++;
		}
		return false;
	}

	@Override
	public void add(double number) {
		double[] newNums = new double[numbers.length + 1];
		// Copy the old array to the new one
		System.arraycopy(numbers, 0, newNums , 0, numbers.length);
		// Add the new number at the end of the sequence
		newNums[newNums.length - 1] = number;
		numbers = newNums;
	}

	@Override
	public void insert(int position, double number) {
		double[] newNums = new double[numbers.length + 1];
		// Throw an exception if we try to insert the element at an invalid position
		if (position < 0 || numbers.length < position) {
			throw new IndexOutOfBoundsException(
					"Index " + position + " out of bounds for length " + numbers.length);
		}

		for (int i = 0, old = 0; i < newNums.length; i++) {
			// Insert the number at the given position or copy an old number
			if (i == position) {
				newNums[i] = number;
			}
			else {
				newNums[i] = numbers[old++];
			}
		}
		numbers = newNums;
	}

	@Override
	public void removeAt(int position) throws IndexOutOfBoundsException {
		double[] newNums = new double[numbers.length - 1];
		// Throw an exception if we try to remove an element at a non-existing position
		// in the sequence or if the sequence is of length 2
		if (position < 0 || numbers.length <= position) {
			throw new IndexOutOfBoundsException(
					"Index " + position + " out of bounds for length " + numbers.length);
		}
		if (numbers.length == 2) {
			throw new IllegalStateException("No elements can be removed from this sequence");
		}

		for (int i = 0, old = 0; i < newNums.length; i++) {
			// Remove the element by not copying it
			if (i == position) {
				old++;
			}
			newNums[i] = numbers[old++];
		}
		numbers = newNums;
	}

	@Override
	public double[] asArray() {
		return numbers;
	}
}