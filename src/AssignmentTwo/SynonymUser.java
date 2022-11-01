package AssignmentTwo;

// SynonymUser.java

/****************************************************************

SynonymUser reads the synonym data from a given file. This data
is used and modified in various ways. Finally, the modified data
is written to a new file.

See:
thesaurus.com

Author: Fadil Galjic

****************************************************************/

import java.io.*;  // IOException
import static java.lang.System.out;

class SynonymUser
{
    public static void main (String[] args) throws IOException
    {
        String[] synonymData = SynonymHandler.readSynonymData(
                "SynonymData1.txt");
        out.println(1);
        println(synonymData);

        String synonymLine = SynonymHandler.getSynonymLine(
			synonymData, "beautiful");
        out.println(2);
        out.println(synonymLine + "\n");
        synonymLine =
            "glowing | luminous, vibrant, flaming, gleaming";
        synonymData = SynonymHandler.addSynonymLine(
			synonymData, synonymLine);
        out.println(3);
        println(synonymData);
        synonymData = SynonymHandler.removeSynonymLine(
			synonymData, "clever");
        out.println(4);
        println(synonymData);
        SynonymHandler.addSynonym(synonymData, "powerful",
            "brilliant");
        out.println(5);
        println(synonymData);
        SynonymHandler.removeSynonym(synonymData, "beautiful",
            "appealing");
        out.println(6);
        println(synonymData);
        SynonymHandler.sortSynonymData(synonymData);
        out.println(7);
        println(synonymData);

        SynonymHandler.writeSynonymData(synonymData,
                "SynonymData2.txt");
    }

    public static void println (String[] synonymData)
    {
        for (String synonymLine : synonymData)
            out.println(synonymLine);
        out.println("");
	}
}