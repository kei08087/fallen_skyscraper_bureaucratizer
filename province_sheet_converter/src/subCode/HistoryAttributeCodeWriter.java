package subCode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HistoryAttributeCodeWriter {
    public static void toCode(TreeNode node)
    {

        for(TreeNode fileHead : node.childs)
        {
            String dir = "data/history/titles/"+fileHead.name+".txt";
            File target = new File(dir);

            try {
                if (target.createNewFile()) {
                    System.out.println(fileHead.name + ".txt has been created");
                } else {
                    System.out.println(fileHead.name + ".txt already exists.");
                }

                FileWriter writer = new FileWriter(target,false);
                TreeNodeModule(fileHead,writer,0);

                writer.close();
                System.out.println(fileHead.name + ".txt is now written");


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void TreeNodeModule(TreeNode node, FileWriter writer, int tabs)
    {
        if(!node.titleHistory.childs.isEmpty())
            AttributeNodeModule(node.titleHistory,writer,tabs);
        for(TreeNode child : node.childs)
        {
            TreeNodeModule(child,writer,tabs);
        }
    }

    public static void AttributeNodeModule(AttributeNode node, FileWriter writer, int tabs)
    {
        String tab = getTab(tabs);
        System.out.println(node.type+"\t"+node.value);
        try {
            if (node.type.equals("root") || node.type.equals("Date")) {
                writer.write(tab + node.value + " = {\n");
            }
            else if(node.scope)
            {
                writer.write(tab+node.type+ " = {\n");
            }
            else if(node.type.equals("laws"))
            {
                writer.write(tab+node.value+"\n");
            }
            else
            {
                writer.write(tab+node.type+" = "+node.value+"\n");
            }
            for (AttributeNode child : node.childs) {
                AttributeNodeModule(child, writer, tabs + 1);
            }
            if(node.scope)
                writer.write(tab+"}\n");
        }
        catch(IOException e)
        {
            System.out.println("error splat");
        }
    }

    public static String getTab(int tabs)
    {
        return "\t".repeat(Math.max(0, tabs));
    }
}
