package subCode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class toText {
    public static void toLandedTitles(TreeNode root) {

        for (TreeNode inst : root.childs) {
            String dir = "data/landed_titles/" + inst.name + ".txt";
            File target = new File(dir);


            try {
                if (target.createNewFile()) {
                    System.out.println(inst.name + ".txt has been created");
                } else {
                    System.out.println(inst.name + ".txt is already exists.");
                }

                FileWriter writer = new FileWriter(target,false);
                writer.write("@correct_culture_primary_score = 100\n" +
                        "@better_than_the_alternatives_score = 50\n" +
                        "@always_primary_score = 1000\n\n");
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
        String startTab = "";
        String tab;
        for(int i=0;i<tabs;i++)
        {
            startTab = startTab + "\t";

        }
        tab = startTab + "\t";

        if(cur.tier=='b')
            writer.write(startTab+cur.name+" = { # "+cur.eng+" "+cur.kr+"\n");
        else
            writer.write(startTab+cur.name+" = {\n");

        if(cur.tier=='b') {
            String id = cur.name.substring(2);
            writer.write(tab + "province = " +id+"\n\n");
        }

        writer.write(tab+"color = { "+cur.color+" }\n\n");

        if(cur.tier!='b'&&cur.tier!='c')
            writer.write(tab+"capital = \n\n");


        for(TreeNode sub : cur.childs)
        {
            module(sub,writer,tabs+1);
        }

        writer.write(startTab+"}\n");
    }
}
