package subCode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class History {
    public static void toHistory(TreeNode root)
    {
        for (TreeNode inst : root.childs) {
            String dir = "data/history/province/fs_" + inst.name + ".txt";
            File target = new File(dir);


            try {
                if (target.createNewFile()) {
                    System.out.println(inst.name + ".txt has been created");
                } else {
                    System.out.println(inst.name + ".txt is already exists.");
                }

                FileWriter writer = new FileWriter(target,false);
                module(inst,writer,0);

                writer.close();
                System.out.println(inst.name + ".txt is now written");


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void module(TreeNode cur, FileWriter writer, int tabs) throws IOException
    {
        String sharp = "";
        for(int i=0;i<=tabs;i++)
            sharp = sharp + "#";
        if(cur.tier=='b') {
            writer.write(cur.name.substring(2) + " = { # " + cur.eng + " " + cur.kr + "\n");
            if(!cur.culture.isEmpty())
                writer.write("\tculture = "+cur.culture+"\n\n");
            if(!cur.religion.isEmpty())
                writer.write("\treligion = "+cur.religion+"\n\n");
            writer.write("\tholding = "+cur.holding+"\n");
            writer.write("}\n");
        }
        else
            writer.write(sharp+cur.name+"\n");





        for(TreeNode sub : cur.childs)
        {
            module(sub,writer, tabs+1);
        }


    }
}
