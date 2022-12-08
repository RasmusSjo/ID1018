package AssignmentTwo;

// SynonymHandler

/****************************************************************

SynonymHandler handles information about synonyms for various
words.

The synonym data can be read from a file and handled in various
ways. These data consists of several lines, where each line begins
with a word, and this word is followed with a number of synonyms.

The synonym line for a given word can be obtained. It is possible
to add a synonym line, and to remove the synonym line for a given
word. Also a synonym for a particular word can be added, or
removed. The synonym data can be sorted. Lastly, the data can be
written to a given file.

Author: Fadil Galjic

****************************************************************/

import java.io.*;    // FileReader, BufferedReader, PrintWriter,
                     // IOException
import java.util.*;  // LinkedList

class SynonymHandler
{
	// readSynonymData reads the synonym data from a given file
	// and returns the data as an array
    public static String[] readSynonymData (String synonymFile)
                                         throws IOException
    {
        BufferedReader reader = new BufferedReader(
	        new FileReader(synonymFile));
        LinkedList<String> synonymLines = new LinkedList<>();
        String synonymLine = reader.readLine();
        while (synonymLine != null)
        {
			synonymLines.add(synonymLine);
			synonymLine = reader.readLine();
		}
		reader.close();

		String[] synonymData = new String[synonymLines.size()];
		synonymLines.toArray(synonymData);

		return synonymData;
    }

    // writeSynonymData writes a given synonym data to a given
    // file
    public static void writeSynonymData (String[] synonymData,
        String synonymFile) throws IOException
    {
        PrintWriter writer = new PrintWriter(synonymFile);
        for (String synonymLine : synonymData)
            writer.println(synonymLine);
        writer.close();

    }

    // synonymLineIndex accepts synonym data, and returns the
    // index of the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	private static int synonymLineIndex (String[] synonymData,
        String word)
    {
		int delimiterIndex = 0;
		String w = "";
		int i = 0;
		boolean wordFound = false;
		while (!wordFound  &&  i < synonymData.length)
		{
		    delimiterIndex = synonymData[i].indexOf('|');
		    w = synonymData[i].substring(0, delimiterIndex).trim();
		    if (w.equalsIgnoreCase(word))
				wordFound = true;
			else
				i++;
	    }

	    if (!wordFound)
	        throw new IllegalArgumentException(
		        word + " not present");

	    return i;
	}

    // getSynonymLine accepts synonym data, and returns
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
    public static String getSynonymLine (String[] synonymData,
        String word)
    {
		int index = synonymLineIndex(synonymData, word);

	    return synonymData[index];
	}

    // addSynonymLine accepts synonym data, and adds a given
    // synonym line to the data
	public static String[] addSynonymLine (String[] synonymData,
	    String synonymLine)
	{
		String[] synData = new String[synonymData.length + 1];
		System.arraycopy(synonymData, 0, synData, 0, synonymData.length);
		synData[synData.length - 1] = synonymLine;

	    return synData;
	}

    // removeSynonymLine accepts synonym data, and removes
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	public static String[] removeSynonymLine (String[] synonymData,
	    String word)
	{
		// add code here
		// The array to be returned
		String[] synData = new String[synonymData.length - 1];
		// Throws the specified exception if the word isn't present in the list
		int removeLineIndex = synonymLineIndex(synonymData, word);

		for (int i = 0, old = 0; old < synData.length; i++, old++) {
			// Skip the line to be removed by increasing the index
			if (i == removeLineIndex) {
				old++;
			}
			synData[i] = synonymData[old];
		}
		return synData;
	}

    // addSynonym accepts synonym data, and adds a given
    // synonym for a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	public static void addSynonym (String[] synonymData,
	    String word, String synonym)
	{
        // add code here
		// Throws the specified exception
		int synIndex = synonymLineIndex(synonymData, word);
		synonymData[synIndex] += ", " + synonym;
	}

    // removeSynonym accepts synonym data, and removes a given
    // synonym for a given word.
    // If the given word or the given synonym is not present, an
    // exception of the type IllegalArgumentException is thrown.
	public static void removeSynonym (String[] synonymData,
	    String word, String synonym)
	{
        // add code here
		// Throws the specified exception if the word isn't present
		int synIndex = synonymLineIndex(synonymData, word);
		// Throws the specified exception if the synonym isn't present
		if (!synonymData[synIndex].contains(synonym)) {
			throw new IllegalArgumentException(
					synonym + "not present");
		}

		// Check to see if the synonym is followed by a ','. I.e. it's not the last synonym
		String synRemove = synonymData[synIndex].contains(
				synonym + ", ") ? synonym + ", " : synonym;

		synonymData[synIndex] = synonymData[synIndex].replace(synRemove, "");
	}

	// My own method
	// getSynonyms returns an array containing all the
	// synonyms in the given synonym line
	private static String[] getSynonyms(String synLine) {
		// Get the synonym part of the string and remove any leading and trailing whitespaces
		String synPart = synLine.substring(synLine.indexOf('|') + 1).trim();
		// Split the string using the following
		return synPart.split(", ");
	}

    // sortIgnoreCase sorts an array of strings, using
    // the selection sort algorithm
    private static void sortIgnoreCase (String[] strings)
    {
        // add code here
		for (int i = 0; i < strings.length - 1; i++) {

			String temp = strings[i];
			int newIndex = i;
			for (int j = i + 1; j < strings.length; j++) {
				// If strings[j] comes before temp lexicographically, reassign
				// temp to that string, this way we find the string that comes
				// first in lexicographical order
				if (temp.compareTo(strings[j]) > 0) {
					temp = strings[j];
					newIndex = j;
				}
			}
			// Swap the string at i with the one at newIndex
			strings[newIndex] = strings[i];
			strings[i] = temp;
		}
	}

    // sortSynonymLine accepts a synonym line, and sorts
    // the synonyms in this line
    public static String sortSynonymLine (String synonymLine)
    {
	    // add code here
		// Get an array containing the synonyms and then sort that array
		String[] synonyms = getSynonyms(synonymLine);
		sortIgnoreCase(synonyms);
		StringBuilder sortedSynLine = new StringBuilder(
				synonymLine.substring(0, synonymLine.indexOf('|') + 1)
		);

		for (int i = 0; i < synonyms.length; i++) {
			String synonym = ", " + synonyms[i];
			// We don't want to add a comma in front of the first synonym
			if (i == 0) {
				synonym = " " + synonyms[i];
			}
			sortedSynLine.append(synonym);
		}

		return sortedSynLine.toString();
	}

    // sortSynonymData accepts synonym data, and sorts its
    // synonym lines and the synonyms in these lines
	public static void sortSynonymData (String[] synonymData)
	{
        // add code here
		// Sort the synonyms in each line
		for (int i = 0; i < synonymData.length; i++) {
			synonymData[i] = sortSynonymLine(synonymData[i]);
		}
		// Sort the lines
		sortIgnoreCase(synonymData);
	}
}