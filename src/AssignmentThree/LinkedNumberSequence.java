package AssignmentThree;

// LinkedNumberSequence.java

/****************************************************************

LinkedNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses linked nodes to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

public class LinkedNumberSequence implements NumberSequence
{

	private class Node
	{
		public double number;
		public Node next;

		public Node (double number)
		{
			this.number = number;
			next = null;
		}
	}

	// the first node in the node-sequence
    private Node first;

    // create the sequence
    public LinkedNumberSequence (double[] numbers)
    {
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");

        first = new Node(numbers[0]);
        Node n = first;
		for (int i = 1; i < numbers.length; i++)
		{
			n.next = new Node(numbers[i]);
			n = n.next;
		}
	}

    // toString returns the character string representing this
    // sequence
	public String toString ()
	{
		String s = "";
		Node n = first;
		while (n.next != null)
		{
		    s = s + n.number + ", ";
		    n = n.next;
		}
		s = s + n.number;

		return s;
	}

    // add code here

	@Override
	public int length() {
		int count = 0;

		Node n = first;
		while (n != null) {
			n = n.next;
			count++;
		}

		return count;
	}

	@Override
	public double upperBound() {
		double bound = first.number;

		Node n = first;
		while (n != null) {
			if (bound < n.number) {
				bound = n.number;
			}
			n = n.next;
		}

		return bound;
	}

	@Override
	public double lowerBound() {
		double bound = first.number;

		Node n = first;
		while (n != null) {
			if (bound > n.number) {
				bound = n.number;
			}
			n = n.next;
		}

		return bound;
	}

	@Override
	public double numberAt(int position) throws IndexOutOfBoundsException {
		if (position < 0 || length() <= position) {
			throw new IndexOutOfBoundsException(
					"Index " + position + " out of bounds for length " + length());
		}

		int index = 0;

		Node n = first;
		while (n != null) {
			if (index == position) {
				return n.number;
			}
			n = n.next;
			index++;
		}

		return -1;
	}

	@Override
	public int positionOf(double number) {
		int index = 0;
		boolean found = false;

		Node n = first;
		while (n != null) {
			if (n.number == number) {
				found = true;
				break;
			}

			n = n.next;
			index++;
		}

		return found? index : -1;
	}

	@Override
	public boolean isIncreasing() {
		boolean increasing = true;

		Node n = first;
		while (n.next != null && increasing) {
			if (n.number > n.next.number) {
				increasing = false;
			}
			n = n.next;
		}
		return increasing;
	}

	@Override
	public boolean isDecreasing() {
		boolean decreasing = true;

		Node n = first;
		while (n.next != null && decreasing) {
			if (n.number < n.next.number) {
				decreasing = false;
			}
			n = n.next;
		}
		return decreasing;
	}

	@Override
	public boolean contains(double number) {
		boolean exists = false;

		Node n = first;
		while (n != null) {
			if (n.number == number) {
				exists = true;
				break;
			}

			n = n.next;
		}

		return exists;
	}

	@Override
	public void add(double number) {
		Node node = new Node(number);

		Node n = first;
		while (n != null) {
			if (n.next == null) {
				n.next = node;
				break;
			}

			n = n.next;
		}
	}

	@Override
	public void insert(int position, double number) {
		Node node = new Node(number);

		if (position < 0 || length() < position) {
			throw new IndexOutOfBoundsException(
					"Index " + position + " out of bounds for length " + length());
		}

		Node n = first;
		Node prev = null;
		for (int i = 0; i <= position; i++) {
			if (i == position) {
				if (position == 0) {
					first = node;
				}
				else {
					prev.next = node;
				}
				node.next = n;
				break;
			}

			prev = n;
			n = n.next;
		}

	}

	@Override
	public void removeAt(int position) throws IndexOutOfBoundsException {
		if (position < 0 || length() <= position) {
			throw new IndexOutOfBoundsException(
					"Index " + position + " out of bounds for length " + length());
		}
		if (length() == 2) {
			throw new IllegalStateException("No elements can be removed from this sequence");
		}

		Node n = first;
		Node prev = first;
		for (int i = 0; i <= position; i++) {
			if (position == 0) {
				first = n.next;
			}
			else if (i == position) {
				prev.next = n.next;
			}

			prev = n;
			n = n.next;
		}
	}

	@Override
	public double[] asArray() {
		double[] seq = new double[length()];

		Node n = first;
		for (int i = 0; i < seq.length; i++){
			seq[i] = n.number;
			n = n.next;
		}

		return seq;
	}
}