package subCode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class HistoryAttributeTSVWriter {

    public static void HistoryTitleWriter(TreeNode node)
    {
        LinkedList<LinkedList<AttrString>> list = toSheet(node);
        writeTSVSheet(list);
        writeCSVSheet(list);
    }


    public static void writeTSVSheet(LinkedList<LinkedList<AttrString>> list)
    {
        String baseDir = "data/csv/excel_title_history.tsv";
        File target = new File(baseDir);
        try {
            if (target.createNewFile()) {
                System.out.println("excel_title_history.tsv has been created");
            } else {
                System.out.println("excel_title_history.tsv already exists.");
            }

            FileWriter writer = new FileWriter(target, false);
            for(LinkedList<AttrString> subList : list)
            {
                System.out.println(subList.getFirst().value);
                boolean needBlank = false;
                int valueStack=0;
                boolean valueDouble = false;
                boolean valueSingle = false;
                for (AttrString cell : subList) {
                    if(cell.value.equals("*")) {
                        valueDouble = true;
                    }
                    else if(cell.value.equals("&"))
                    {
                        valueSingle=true;
                    }
                    /*else if(cell.value.equals("#"))
                    {
                        writer.write("\n");
                        continue;
                    }*/
                    if(valueDouble||valueSingle)
                    {
                        valueStack++;
                    }
                    if(needBlank)
                    {
                        for(int i=0;i<cell.scopeStack;i++)
                            writer.write("\t");
                        needBlank=false;
                    }
                    writer.write(cell.value);
                    writer.write("\t");
                    if(valueStack==3&&valueDouble)
                    {
                        writer.write("\n");
                        valueStack=0;
                        valueDouble=false;
                        needBlank=true;
                    }
                    else if(valueStack==2&&valueSingle)
                    {
                        writer.write("\n");
                        valueStack=0;
                        valueSingle=false;
                        needBlank=true;
                    }
                }
            }
            writer.close();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void writeCSVSheet(LinkedList<LinkedList<AttrString>> list)
    {
        String baseDir = "data/csv/excel_title_history.csv";
        File target = new File(baseDir);
        try {
            if (target.createNewFile()) {
                System.out.println("excel_title_history.csv has been created");
            } else {
                System.out.println("excel_title_history.csv already exists.");
            }

            FileWriter writer = new FileWriter(target, false);
            for(LinkedList<AttrString> subList : list)
            {
                boolean needBlank = false;
                int valueStack=0;
                boolean valueDouble = false;
                boolean valueSingle = false;
                for (AttrString cell : subList) {
                    if(cell.value.equals("*")) {
                        valueDouble = true;
                    }
                    else if(cell.value.equals("&"))
                    {
                        valueSingle=true;
                    }
                    /*else if(cell.value.equals("#"))
                    {
                        writer.write("\n");
                        continue;
                    }*/
                    if(valueDouble||valueSingle)
                    {
                        valueStack++;
                    }
                    if(needBlank)
                    {
                        for(int i=0;i<cell.scopeStack;i++)
                            writer.write(",");
                        needBlank=false;
                    }
                    writer.write(cell.value);
                    writer.write(",");

                    if(valueStack==3&&valueDouble)
                    {
                        writer.write("\n");
                        valueStack=0;
                        valueDouble=false;
                        needBlank=true;
                    }
                    else if(valueStack==2&&valueSingle)
                    {
                        writer.write("\n");
                        valueStack=0;
                        valueSingle=false;
                        needBlank=true;
                    }
                }
                //writer.write("\n");
            }
            writer.close();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static LinkedList<LinkedList<AttrString>> toSheet(TreeNode node)
    {
        LinkedList<LinkedList<AttrString>> list = new LinkedList<>();
        TreeNodeSearch(node,list);
        return list;
    }

    public static void TreeNodeSearch(TreeNode node, LinkedList<LinkedList<AttrString>> list)
    {
        LinkedList<AttrString> attrList = new LinkedList<>();
        AttrNodeSearch(node.titleHistory,attrList,0);
        if(!attrList.getFirst().value.isEmpty())
            list.add(attrList);
        for(TreeNode child : node.childs)
        {
            TreeNodeSearch(child,list);
        }
    }

    public static void AttrNodeSearch(AttributeNode node,LinkedList<AttrString> list, int stack)
    {
        if(node.scope)
        {
            if(node.type.equals("Date")||node.type.equals("root"))
            {
                list.add(new AttrString(node.value,stack));
            }
            else
            {
                list.add(new AttrString(node.type,stack));
            }
        }
        else
        {
            if(node.type.equals("law"))
            {
                list.add(new AttrString("&",stack));
                list.add(new AttrString(node.value, stack));
            }
            else {
                list.add(new AttrString("*", stack));
                list.add(new AttrString(node.type, stack));
                list.add(new AttrString(node.value, stack));
            }
        }
        for (AttributeNode child : node.childs)
        {
            if(!child.childs.isEmpty() || !child.scope)
                AttrNodeSearch(child,list,stack+1);
            /*else
            {
                list.add(new AttrString("#",stack));
            }*/
        }

    }
}
