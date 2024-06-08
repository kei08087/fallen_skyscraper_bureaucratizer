package subCode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class CSVWriter {

    private static HashMap<Integer, LinkedList<String>> map = new HashMap<>();
    private static TreeNode[] checker = new TreeNode[4];
    private static boolean empire = false;
    private static int maxProvince = 4500;
    public static void toCSV(TreeNode node){

        String dir = "data/csv/output.csv";
        File target = new File(dir);

        starter();
        searchDown(node,-1);
        try {
            if (target.createNewFile()) {
                System.out.println("output.csv has been created");
            } else {
                System.out.println("output.csv already exists.");
            }

            FileWriter writer = new FileWriter(target,false);

            int cell_num=map.get(0).size();

            for(int i=0;i<=maxProvince;i++)
            {
                if(map.containsKey(i))
                {
                    for(String inst : map.get(i))
                    {
                        writer.write(inst);
                        writer.write(",");
                    }
                    writer.write("\n");
                }
                else
                {
                    addBlank(i,cell_num);
                    i--;

                }

            }

            writer.close();
            System.out.println("output.csv is now written");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void starter()
    {
        LinkedList<String> start = new LinkedList<>();
        start.add("프로빈스 번호");
        start.add("인게임 코드");
        start.add("한국어 명칭");
        start.add("영문 명칭");
        start.add("RGB");
        start.add("백작령");
        start.add("백작령 코드");
        start.add("백작령 색상");
        start.add("공작령");
        start.add("공작령 코드");
        start.add("공작령 색상");
        start.add("공작령 수도");
        start.add("왕국령");
        start.add("왕국령 코드");
        start.add("왕국령 색상");
        start.add("왕국령 수도");
        start.add("제국령");
        start.add("제국령 코드");
        start.add("제국령 색상");
        start.add("제국령 수도");
        start.add("문화");
        start.add("종교");
        start.add("홀딩");
        start.add("지형");
        start.add("특이사항");
        start.add("end");

        map.put(0,start);
    }

    public static void addBlank(int current, int size)
    {
        LinkedList<String> blank = new LinkedList<>();
        for(int i=0;i<size;i++)
        {
            if(i==0)
                blank.add(""+current);
            else if(i==1)
                blank.add("b_"+current);
            else if(i==size-1)
                blank.add("end");
            else
                blank.add("");
        }
        map.put(current,blank);
    }

    public static void searchDown(TreeNode node, int deep){

        /*for(int i=0;i<4;i++)
        {
            System.out.print("Slot "+i+" : ");
            if(checker[i]!=null)
            {
                System.out.print(checker[i].name+" ");
            }
            else
                System.out.print("\t");
        }
        System.out.println();*/
        if(node.tier=='b')
        {
            int maxC=2;
            if(empire)
                maxC=3;
            LinkedList<String> list = new LinkedList<>();
            list.add(node.name.substring(2));
            list.add(node.name);
            list.add(node.kr);
            list.add(node.eng);
            list.add("\""+node.color.replace(" ",",")+"\"");
            for(int i=maxC;i>=0;i--)
            {
                //System.out.println(checker[i].name);
                TreeNode inst = checker[i];
                list.add(inst.kr);
                list.add(inst.name);
                list.add("\""+inst.color.replace(" ",",")+"\"");
                if(inst.tier!='c')
                    list.add(inst.capital);
            }
            if(!empire)
            {
                list.add("");
                list.add("");
                list.add("");
                list.add("");
            }
            list.add(node.culture);
            list.add(node.religion);
            list.add(node.holding);
            list.add(node.landscape);
            list.add("");
            list.add("end");

            map.put(Integer.parseInt(node.name.substring(2)),list);
        }
        else {
            for(TreeNode child : node.childs)
            {
                if(deep!=-1)
                    checker[deep] = node;
                if(deep==-1&&child.tier=='e')
                {
                    empire=true;
                }
                else if(deep == -1)
                {
                    empire=false;
                }
                searchDown(child,deep+1);
                for(int i=3;i>deep;i--)
                    checker[i]=null;
            }
        }
    }

}
