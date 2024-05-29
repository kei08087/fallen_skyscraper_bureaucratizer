package subCode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Localization {





    public static void Local(ArrayList<String[]> list)
    {
        String baronKRDir = "data/localization/korean/fs_baronies_l_korean.yml";
        String baronENDir = "data/localization/english/fs_baronies_l_english.yml";
        String titleKRDir = "data/localization/korean/fs_titles_l_korean.yml";
        String titleENDir = "data/localization/english/fs_titles_l_english.yml";

        File baronKRF = new File(baronKRDir);
        File baronENF = new File(baronENDir);
        File titleKRF = new File(titleKRDir);
        File titleENF = new File(titleENDir);




        HashSet<String> counties = new HashSet<>();
        HashSet<String> duchy = new HashSet<>();
        HashSet<String> kingdom = new HashSet<>();
        HashSet<String> empire = new HashSet<>();

        try {
            if (baronKRF.createNewFile()) {
                System.out.println("fs_baronies_l_korean.yml has been created");
            } else {
                System.out.println("fs_baronies_l_korean.yml is already exists.");
            }

            if (baronENF.createNewFile()) {
                System.out.println("fs_baronies_l_english.yml has been created");
            } else {
                System.out.println("fs_baronies_l_english.yml is already exists.");
            }

            if (titleKRF.createNewFile()) {
                System.out.println("fs_titles_l_korean.yml has been created");
            } else {
                System.out.println("fs_titles_l_korean.yml is already exists.");
            }

            if (titleENF.createNewFile()) {
                System.out.println("fs_titles_l_english.yml has been created");
            } else {
                System.out.println("fs_titles_l_english.yml is already exists.");
            }

            FileWriter bKR = new FileWriter(baronKRF);
            FileWriter bEN = new FileWriter(baronENF);
            FileWriter tKR = new FileWriter(titleKRF);
            FileWriter tEN = new FileWriter(titleENF);

            tKR.write("l_korean:\n");
            tEN.write("l_english:\n");
            bKR.write("l_korean:\n");
            bEN.write("l_english:\n");

            for (String[] line : list) {
                if (!line[15].isEmpty() && empire.add(line[15]))
                {
                    tKR.write(" "+line[15]+":0 \""+line[14]+"\"\n");
                    tEN.write(" "+line[15]+":0 \""+line[15].substring(2).toUpperCase()+"\"\n");
                }
                if(kingdom.add(line[12]))
                {
                    tKR.write(" "+line[12]+":0 \""+line[11]+"\"\n");
                    tEN.write(" "+line[12]+":0 \""+line[12].substring(2).toUpperCase()+"\"\n");
                }
                if(duchy.add(line[9]))
                {
                    //tKR.write(" "+line[9]+":0 \""+line[8]+"\"\n");
                    //tEN.write(" "+line[9]+":0 \""+line[9].substring(2).toUpperCase()+"\"\n");
                }
                if(counties.add(line[6]))
                {
                    //tKR.write(" "+line[6]+":0 \""+line[5]+"\"\n");
                    //tEN.write(" "+line[6]+":0 \""+line[6].substring(2).toUpperCase()+"\"\n");
                }

                bKR.write(" "+line[1]+":0 \""+line[2]+"\"\n");
                bEN.write(" "+line[1]+":0 \""+line[3].toUpperCase()+"\"\n");
            }
            bKR.close();
            bEN.close();
            tKR.close();
            tEN.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
