package subCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;

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
        System.out.println("reading landscape");
        readLandscape(node);
        System.out.println("reading history title");
        readHistoryTitle(node,path3);

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
                                    cur = cur.parent;
                                if(front=='#')
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
                                    case "definite_form": case "ruler_uses_title_name": case "no_automatic_claims": case "always_follows_primary_heir": case "destroy_if_invalid_heir": case "landless": case "de_jure_drift_disabled": case "male_names":{
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
                                    {System.out.println(definer);
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
                                                while (cur.tier!=' '&&cur.tier != 'e') {
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

                                            while(cur.tier!=' '&&cur.tier!='k')
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
        File folder = new File(path);
        if(folder.exists()&&folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        System.out.println("Reading file: " + file.getName());
                        try {
                            Path paths = Paths.get(file.getAbsolutePath());
                            BufferedReader br = new BufferedReader(new FileReader(paths.toString()));
                            String line;
                            Node inst = node;
                            int stack = 0;
                            while ((line = br.readLine()) != null) {
                                line = line.replace("" + (char) 65279, "").strip();

                                if (line.isEmpty())
                                    continue;
                                if(line.charAt(0)=='#')
                                    continue;
                                if(line.contains("="))
                                {
                                    String[] content = line.split("=");
                                    String definer = content[0].strip();

                                    if(stack==0)
                                    {
                                        inst = searchDown(node,definer).titleHistory;
                                        if(inst != null)
                                            AttributeNode.AttributeValueUpdate((AttributeNode)inst,definer);
                                        stack++;
                                    }
                                    else if(content[1].contains("{"))
                                    {
                                        stack++;
                                        switch (definer) {
                                            case "holder": case "effect": case "if": case "limit":case "succession_laws": {
                                                AttributeNode attr = new AttributeNode(definer, true);
                                                if (inst instanceof AttributeNode) {
                                                    attr.parent = (AttributeNode) inst;
                                                    ((AttributeNode)inst).childs.add(attr);
                                                    inst = attr;
                                                } else {
                                                    System.out.println("Node cast error");
                                                    System.exit(2);
                                                }
                                                break;
                                            }
                                            default:
                                            {
                                                AttributeNode attr = new AttributeNode("Date", true);
                                                AttributeNode.AttributeValueUpdate(attr,definer);
                                                if (inst instanceof AttributeNode) {
                                                    attr.parent = (AttributeNode) inst;
                                                    ((AttributeNode)inst).childs.add(attr);
                                                    inst = attr;
                                                } else {
                                                    System.out.println("Node cast error");
                                                    System.exit(2);
                                                }
                                                break;
                                            }
                                        }

                                    }
                                    else
                                    {
                                        AttributeNode attr = new AttributeNode(definer,false);
                                        AttributeNode.AttributeValueUpdate(attr,content[1].strip());
                                        if(inst instanceof AttributeNode) {
                                            attr.parent = (AttributeNode) inst;
                                            ((AttributeNode)inst).childs.add(attr);
                                        }
                                        else
                                        {
                                            System.out.println("Node cast error");
                                            System.exit(2);
                                        }
                                    }
                                }
                                else if(!line.contains("}"))
                                {
                                    AttributeNode attr = new AttributeNode("law",false);
                                    AttributeNode.AttributeValueUpdate(attr,line.strip());
                                    assert inst instanceof AttributeNode;
                                    attr.parent=(AttributeNode) inst;
                                    ((AttributeNode)inst).childs.add(attr);

                                }
                                if(line.contains("}"))
                                {
                                    stack--;
                                    if(((AttributeNode)inst).parent!=null) {
                                        inst = ((AttributeNode)inst).parent;
                                    }
                                }
                            }
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void readLandscape(TreeNode node)
    {
        String dir = "data/landscape/00_province_terrain.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(dir));
            String line;
            HashMap<Integer, LinkedList<String>> map = BasicTSVWriter.toSheet(node);
            while ((line = br.readLine()) != null) {
                line=line.replace(""+(char)65279,"");
                TreeNode cur = node;
                if (line.contains("=")) {
                    String[] content = line.split("=");
                    switch (content[0]) {
                        case "default_land": case "default_sea": case "default_coastal_sea":
                            break;
                        default: {
                            int key = Integer.parseInt(content[0].strip());
                            if (!map.get(key).get(17).isEmpty()) {
                                for (TreeNode child : cur.childs) {
                                    if (child.name.equals(map.get(key).get(17))) {
                                        cur = child;
                                        break;
                                    }
                                }
                            }
                            for (TreeNode child : cur.childs) {
                                if (child.name.equals(map.get(key).get(13))) {
                                    cur = child;
                                    break;
                                }
                            }
                            for (TreeNode child : cur.childs) {
                                if (child.name.equals(map.get(key).get(9))) {
                                    cur = child;
                                    break;
                                }
                            }
                            for (TreeNode child : cur.childs) {
                                if (child.name.equals(map.get(key).get(6))) {
                                    cur = child;
                                    break;
                                }
                            }
                            for (TreeNode child : cur.childs) {
                                if (child.name.equals(map.get(key).get(1))) {
                                    cur = child;
                                    TreeNode.updateInfo(cur, content[1], "landscape");
                                    break;
                                }
                            }

                        }
                    }
                }
            }
            br.close();
        }
        catch(IOException e)
        {
            System.out.println("Error on reading landscape");
        }
    }

    public static TreeNode searchDown(TreeNode node,String target)
    {
        TreeNode result = null;
        if(node.name.equals(target))
        {
            return node;
        }
        for(TreeNode child : node.childs)
        {
            result = searchDown(child,target);
            if(result!=null)
                break;
        }
        return result;
    }
}
