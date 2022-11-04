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
		int index = 0;
		double number = 0;

		if (position < 0 || length() <= position) {
			throw new IndexOutOfBoundsException(
					"Index " + position + " out of bounds for length " + length());
		}

		Node n = first;
		while (n != null) {
			if (index == position) {
				number = n.number;
				break;
			}
			n = n.next;
			index++;
		}

		return number;
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
		while (n.next != null) {
			if (n.number > n.next.number) {
				increasing = false;
				break;
			}
			n = n.next;
		}

		return increasing;
	}

	@Override
	public boolean isDecreasing() {
		boolean decreasing = true;

		Node n = first;
		while (n.next != null) {
			if (n.number < n.next.number) {
				decreasing = false;
				break;
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
		int index = 0;

		if (position < 0 || length() < position) {
			throw new IndexOutOfBoundsException(
					"Index " + position + " out of bounds for length " + length());
		}

		Node n = first;
		Node prev = null;
		while (n != null) {
			if (index == position) {
				if (position == 0) {
					first = node;
				}
				else {
					prev.next = node;
				}
				node.next = n;
				break;
			}
			else if (position == length() && n.next == null) {
				n.next = node;
				break;
			}

			prev = n;
			n = n.next;
			index++;
		}

	}

	@Override
	public void removeAt(int position) throws IndexOutOfBoundsException {
		int index = 0;

		if (position < 0 || length() <= position) {
			throw new IndexOutOfBoundsException(
					"Index " + position + " out of bounds for length " + length());
		}
		if (length() == 2) {
			throw new IllegalStateException("No elements can be removed from this sequence");
		}

		Node n = first;
		Node prev = first;
		while (n != null) {
			if (position == 0) {
				first = n.next;
				break;
			}
			else if (index == position) {
				prev.next = n.next;
				break;
			}

			prev = n;
			n = n.next;
			index++;
		}
	}

	@Override
	public double[] asArray() {
		double[] seq = new double[0];

		Node n = first;
		while (n != null) {
			double[] arr = new double[seq.length + 1];

			System.arraycopy(seq, 0 , arr, 0, seq.length);
			arr[arr.length - 1] = n.number;

			seq = arr;
			n = n.next;
		}

		for (double v : seq) {
			System.out.print(v);
		}

		return seq;
	}
}