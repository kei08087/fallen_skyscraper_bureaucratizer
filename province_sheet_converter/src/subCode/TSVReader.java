package subCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TSVReader {
    public static ArrayList<String[]> Reader() throws IOException{
        ArrayList<String[]> collections = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("data/excel_output.tsv"));

        String line;
        line = br.readLine();;
        int count = 0;
        while(((line = br.readLine())!=null))
        {
            String[] collect = line.split("\t");

            if(collect[3].isEmpty())
                continue;

            collect[4] = collect[4].replace("\"","").replace(","," ");
            collect[7] = collect[7].replace("\"","").replace(","," ");
            collect[10] = collect[10].replace("\"","").replace(","," ");
            collect[13] = collect[13].replace("\"","").replace(","," ");
            if(!collect[16].isEmpty())
                collect[16] = collect[16].replace("\"","").replace(","," ");

            collections.add(collect);
        }
        br.close();
        return collections;
    }
}