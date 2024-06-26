package subCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TSVReader {

    public static ArrayList<String[]> BasicSheetReader() throws IOException{
        ArrayList<String[]> collections = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("data/excel_basic_sheet.tsv"));

        String line;
        line = br.readLine();;


        while(((line = br.readLine())!=null))
        {
            String[] collect = line.split("\t");


            if(collect[4].isEmpty())
                continue;

            collect[4] = collect[4].replace("\"","").replace(","," ");
            collect[7] = collect[7].replace("\"","").replace(","," ");
            collect[10] = collect[10].replace("\"","").replace(","," ");
            collect[14] = collect[14].replace("\"","").replace(","," ");
            if(!collect[18].isEmpty())
                collect[18] = collect[18].replace("\"","").replace(","," ");

            collections.add(collect);
        }
        br.close();
        return collections;
    }

    public static ArrayList<String[]> readHistoryTitle() throws IOException
    {
        ArrayList<String[]> chuncks=new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("data/excel_title_history_sheet.tsv"));
        String line;
        while((line = br.readLine())!=null)
        {
            String[] collection = line.split("\t");
            chuncks.add(collection);
        }
        return chuncks;
    }

}
