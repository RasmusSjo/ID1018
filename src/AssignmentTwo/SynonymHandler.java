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
		String[] synData = new String[synonymData.length - 1];
		int removeLineIndex = synonymLineIndex(synonymData, word);

		for (int i = 0; i < synData.length; i++) {
			int newIndex = i;
			if (i >= removeLineIndex) {
				newIndex++;
			}
			synData[i] = synonymData[newIndex];
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
		int synIndex = synonymLineIndex(synonymData, word);

		if (!synonymData[synIndex].contains(synonym)) {
			throw new IllegalArgumentException(
					synonym + "not present");
		}

		String synRemove = synonymData[synIndex].contains(
				synonym + ", ")? synonym + ", " : synonym;

		synonymData[synIndex] = synonymData[synIndex].replace(synRemove, "");
	}

	// getSynonyms returns an array containing all the
	// synonyms in the given synonym line
	private static String[] getSynonyms(String synLine) {
		String synPart = synLine.substring(synLine.indexOf('|') + 1);

		boolean filtered = false;
		while (!filtered) {
			if (synPart.length() > 0 && synPart.charAt(0) == ' ') {
				synPart = synPart.substring(1);
			}
			if (synPart.charAt(0) != ' ') {
				filtered = true;
			}
		}

		return synPart.split("\\W+", 0);
	}

    // sortIgnoreCase sorts an array of strings, using
    // the selection sort algorithm
    private static void sortIgnoreCase (String[] strings)
    {
        // add code here
		for (int i = 0; i < strings.length - 1; i++) {
			int firstIndex = i;
			String first = strings[i];

			for (int j = i + 1; j < strings.length; j++) {
				if (first.compareTo(strings[j]) > 0) {
					firstIndex = j;
					first = strings[j];
				}
			}

			strings[firstIndex] = strings[i];
			strings[i] = first;
		}
	}

    // sortSynonymLine accepts a synonym line, and sorts
    // the synonyms in this line
    public static String sortSynonymLine (String synonymLine)
    {
	    // add code here
		String[] synonyms = getSynonyms(synonymLine);
		sortIgnoreCase(synonyms);
		String sortedSynLine =  synonymLine.substring(0, synonymLine.indexOf('|') + 1);

		for (int i = 0; i < synonyms.length; i++) {
			String synonym = " " + synonyms[i] + ",";
			if (i == synonyms.length - 1) {
				synonym = " " + synonyms[i];
			}
			sortedSynLine += (synonym);
		}
		return sortedSynLine;
	}

    // sortSynonymData accepts synonym data, and sorts its
    // synonym lines and the synonyms in these lines
	public static void sortSynonymData (String[] synonymData)
	{
        // add code here
		for (int i = 0; i < synonymData.length; i++) {
			synonymData[i] = sortSynonymLine(synonymData[i]);
		}
		sortIgnoreCase(synonymData);
	}
}