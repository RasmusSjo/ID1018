package AssignmentThree;

// ArrayNumberSequence.java

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
		boolean increasing = true;

		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i - 1] > numbers[i]) {
				increasing = false;
				break;
			}
		}

		return increasing;
	}

	@Override
	public boolean isDecreasing() {
		boolean decreasing = true;

		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i - 1] < numbers[i]) {
				decreasing = false;
				break;
			}
		}

		return decreasing;
	}

	@Override
	public boolean contains(double number) {
		boolean exists = false;

		int i = 0;
		while (i < numbers.length && !exists) {
			if (numbers[i] == number) {
				exists = true;
			}
			i++;
		}

		return exists;
	}

	@Override
	public void add(double number) {
		double[] newNums = new double[numbers.length + 1];
		System.arraycopy(numbers, 0, newNums , 0, numbers.length);
		newNums[newNums.length - 1] = number;
		numbers = newNums;
	}

	@Override
	public void insert(int position, double number) {
		double[] newNums = new double[numbers.length + 1];

		if (position < 0 || numbers.length < position) {
			throw new IndexOutOfBoundsException(
					"Index " + position + " out of bounds for length " + numbers.length);
		}

		int oldIndex = 0;
		for (int i = 0; i < newNums.length; i++) {
			if (i == position) {
				newNums[i] = number;
			}
			else {
				newNums[i] = numbers[oldIndex];
				oldIndex++;
			}
		}

		numbers = newNums;
	}

	@Override
	public void removeAt(int position) throws IndexOutOfBoundsException {
		double[] newNums = new double[numbers.length - 1];

		if (position < 0 || numbers.length <= position) {
			throw new IndexOutOfBoundsException(
					"Index " + position + " out of bounds for length " + numbers.length);
		}
		if (numbers.length == 2) {
			throw new IllegalStateException("No elements can be removed from this sequence");
		}

		int oldIndex = 0;
		for (int i = 0; i < newNums.length; i++) {
			if (i == position) {
				oldIndex++;
			}
			newNums[i] = numbers[oldIndex];
			oldIndex++;
		}

		numbers = newNums;
	}

	@Override
	public double[] asArray() {
		return numbers;
	}
}