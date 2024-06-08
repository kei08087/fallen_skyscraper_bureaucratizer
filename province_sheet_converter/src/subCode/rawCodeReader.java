package subCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class rawCodeReader {
    public static void codeReader(TreeNode node)
    {
        String path1 = "data/rawCode/landed_titles";
        String path2 = "data/rawCode/history/provinces";
        String path3 = "data/rawCode/history/titles";

        System.out.println("reading landed_titles");
        readTitle(node,path1);
        System.out.println("reading history province");
        readHistoryProvince(node,path2);

    }

    public static void readTitle(TreeNode node, String path)
    {
        File folder = new File(path);

        if(folder.exists()&&folder.isDirectory())
        {
            File[] files = folder.listFiles();
            if(files!=null)
            {
                for(File file: files)
                {

                    TreeNode cur = node;

                    boolean empire = false;

                    if(file.isFile())
                    {
                        System.out.println("Reading file: "+file.getName());
                        try {
                            Path paths = Paths.get(file.getAbsolutePath());
                            BufferedReader br = new BufferedReader(new FileReader(paths.toString()));
                            String line;
                            while ((line=br.readLine())!=null)
                            {
                                String keep = line;
                                line = line.replace("\t","").strip();
                                line = line.replace(""+(char)62579,"");
                                if(line.isEmpty())
                                    continue;
                                char front = line.charAt(0);


                                if(front=='@')
                                    continue;
                                if(front=='}')
                                    continue;


                                String[] context = line.split("=");

                                String definer = context[0].replace(" ","");



                                switch (definer)
                                {
                                    case "color": {
                                        String color = context[1].replace("{","").replace("}","").strip();
                                        TreeNode.updateInfo(cur,color,"color");
                                        break;
                                    }
                                    case "capital": {
                                        String capital = context[1].strip();
                                        TreeNode.updateInfo(cur,capital,"capital");
                                        break;
                                    }
                                    case "definite_form":{
                                        TreeNode.updateInfo(cur,line,"titleOthers");
                                        break;
                                    }
                                    case "ai_primary_priority": case "cultural_names": case "can_create":{
                                        int stack=1;
                                        TreeNode.updateInfo(cur,keep,"titleOthers");
                                        do{
                                            line = br.readLine();
                                            TreeNode.updateInfo(cur,line,"titleOthers");
                                            if(line.contains("{"))
                                            {
                                                stack++;
                                            }
                                            if(line.contains("}"))
                                            {
                                                stack--;
                                            }
                                        }while(stack!=0);
                                        break;
                                    }
                                    default:
                                    {
                                        if(front=='e')
                                        {
                                            empire = true;
                                            while(cur.tier!=' ')
                                            {
                                                cur = cur.parent;
                                            }
                                            boolean found=false;
                                            for(TreeNode inst : cur.childs)
                                            {
                                                if(inst.name.equals(definer))
                                                {
                                                    cur = inst;
                                                    found=true;
                                                    break;
                                                }
                                            }
                                            if(!found)
                                            {
                                                TreeNode insert = new TreeNode(definer,"",'e');
                                                insert.parent = cur;
                                                cur.childs.add(insert);
                                                cur = insert;
                                            }
                                        }
                                        else if(front=='k')
                                        {
                                            if(empire) {
                                                while (cur.tier != 'e') {
                                                    cur = cur.parent;
                                                }
                                            }
                                            else
                                            {
                                                while (cur.tier != ' ') {
                                                    cur = cur.parent;
                                                }
                                            }
                                            boolean found=false;
                                            for(TreeNode inst : cur.childs)
                                            {
                                                if(inst.name.equals(definer))
                                                {
                                                    cur = inst;
                                                    found=true;
                                                    break;
                                                }
                                            }
                                            if(!found)
                                            {
                                                TreeNode insert = new TreeNode(definer,"",'k');
                                                insert.parent = cur;
                                                cur.childs.add(insert);
                                                cur = insert;
                                            }
                                        }
                                        else if(front=='d')
                                        {
                                            while(cur.tier!='k')
                                            {
                                                cur = cur.parent;
                                            }
                                            boolean found=false;
                                            for(TreeNode inst : cur.childs)
                                            {
                                                if(inst.name.equals(definer))
                                                {
                                                    cur = inst;
                                                    found=true;
                                                    break;
                                                }
                                            }
                                            if(!found)
                                            {
                                                TreeNode insert = new TreeNode(definer,"",'d');
                                                insert.parent = cur;
                                                cur.childs.add(insert);
                                                cur = insert;
                                            }
                                        }
                                        else if(front=='c')
                                        {
                                            while(cur.tier!='d')
                                            {
                                                cur = cur.parent;
                                            }
                                            boolean found=false;
                                            for(TreeNode inst : cur.childs)
                                            {
                                                if(inst.name.equals(definer))
                                                {
                                                    cur = inst;
                                                    found=true;
                                                    break;
                                                }
                                            }
                                            if(!found)
                                            {
                                                TreeNode insert = new TreeNode(definer,"",'c');
                                                insert.parent = cur;
                                                cur.childs.add(insert);
                                                cur = insert;
                                            }
                                        }
                                        else if(front=='b')
                                        {
                                            while(cur.tier!='c')
                                            {
                                                cur = cur.parent;
                                            }
                                            boolean found=false;
                                            for(TreeNode inst : cur.childs)
                                            {
                                                if(inst.name.equals(definer))
                                                {
                                                    cur = inst;
                                                    found=true;
                                                    break;
                                                }
                                            }
                                            if(!found)
                                            {
                                                TreeNode insert = new TreeNode(definer,"",'b');
                                                insert.parent = cur;
                                                cur.childs.add(insert);
                                                cur = insert;
                                            }
                                        }
                                    }
                                }
                            }
                        }catch (IOException e)
                        {
                            System.out.println("Error splat in landed_title");
                        }
                    }
                }
            }
        }
    }

    public static void readHistoryProvince(TreeNode node, String path)
    {
        File folder = new File(path);
        if(folder.exists()&&folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {

                    TreeNode cur = node;


                    if(file.isFile())
                    {
                        System.out.println("Reading file: "+file.getName());
                        try {
                            Path paths = Paths.get(file.getAbsolutePath());
                            BufferedReader br = new BufferedReader(new FileReader(paths.toString()));
                            String line;
                            while ((line = br.readLine()) != null) {
                                line = line.replace(""+(char)65279,"").replaceAll("(^ +)|( +$)","");
                                if(line.isEmpty())
                                    continue;
                                char front = line.charAt(0);
                                if(front=='#')
                                {

                                    String navigation=line;

                                    navigation=navigation.replace("#","").replaceAll("(^ +)|( +$)","");
                                    int index = navigation.indexOf(" ");
                                    if(index!=-1)
                                        navigation=navigation.substring(0,index);
                                    navigation=navigation.strip();

                                    boolean found = false;
                                    do {
                                        for (TreeNode inst : cur.childs) {

                                            if (inst.name.equals(navigation)) {
                                                cur = inst;
                                                found = true;
                                                break;
                                            }
                                        }
                                        if(found)
                                            break;
                                        cur = cur.parent;
                                    }while(cur.parent!=null);

                                    if(!found)
                                    {
                                        System.out.println("Current node: "+cur.name);
                                        System.out.println("Cannot find "+navigation);

                                        System.exit(1);
                                    }
                                }
                                else if(front=='\t')
                                {
                                    String[] context = line.split("=");
                                    String definer = context[0].strip();
                                    switch (definer)
                                    {
                                        case "holding":{
                                            String holding = context[1].strip();
                                            TreeNode.updateInfo(cur,holding,"holding");
                                            break;
                                        }
                                        case "religion":{
                                            String religion = context[1].strip();
                                            TreeNode.updateInfo(cur,religion,"religion");
                                            break;
                                        }
                                        case "culture":{
                                            String culture = context[1].strip();
                                            TreeNode.updateInfo(cur,culture,"culture");
                                            break;
                                        }
                                        default:{
                                            String other = line;
                                            TreeNode.updateInfo(cur,other,"provinceOthers");
                                        }

                                    }
                                }
                                else if(front=='}')
                                {
                                    cur = cur.parent;
                                }
                                else
                                {
                                    String[] context = line.split("=");
                                    String definer = context[0].replace(" ","");
                                    definer = "b_"+definer;
                                    boolean found = false;
                                    for(TreeNode inst : cur.childs)
                                    {
                                        if(inst.name.equals(definer))
                                        {
                                            cur = inst;
                                            found=true;
                                            break;
                                        }
                                    }
                                    if(!found)
                                    {
                                        System.out.println("Current node: "+cur.name);
                                        System.out.println("Cannot find "+definer+"\n");
                                        System.exit(1);
                                    }
                                }
                            }

                        }catch(IOException e) {
                            System.out.println("Error splat in history/province");
                        }
                    }
                }
            }
        }
    }

    public static void readHistoryTitle(TreeNode node, String path)
    {

    }
}
