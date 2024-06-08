package subCode;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class Noder {
    public static TreeNode TreeNoder(ArrayList<String[]> lines)
    {
        TreeNode root = new TreeNode("root",null,' ');
        for (String[] line : lines) {
            TreeNode cur = root;
            String province_id = line[1];
            String korean_name = line[2];
            String english_name = line[3];
            String province_color = line[4];
            String county = line[6];
            String countyColor = line[7];
            String duchy = line[9];
            String duchyColor = line[10];
            String kingdom = line[13];
            String kingdomColor = line[14];
            String empire = line[17];
            String empireColor = line[18];
            boolean found;

            TreeNode province = new TreeNode(province_id,province_color, 'b',english_name, korean_name);
            TreeNode.updateInfo(province,line[20],"culture");
            TreeNode.updateInfo(province,line[21],"religion");
            TreeNode.updateInfo(province,line[22],"holding");

            if(!empire.isEmpty())
            {
                found = false;
                for(TreeNode inst : cur.childs)
                {
                    if(inst.name.equals(empire))
                    {
                        cur = inst;
                        found = true;
                        break;
                    }
                }
                if(!found)
                {
                    TreeNode insert = new TreeNode(empire,empireColor,'e');
                    insert.parent = cur;
                    cur.childs.add(insert);
                    cur = insert;
                }
            }

            found = false;
            for(TreeNode inst : cur.childs)
            {
                if(inst.name.equals(kingdom))
                {
                    cur = inst;
                    found = true;
                    break;
                }
            }
            if(!found)
            {
                TreeNode insert = new TreeNode(kingdom,kingdomColor,'k');
                insert.parent = cur;
                cur.childs.add(insert);
                cur = insert;
            }

            if(!duchy.isEmpty()) {
                found = false;
                for (TreeNode inst : cur.childs) {
                    if (inst.name.equals(duchy)) {
                        cur = inst;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    TreeNode insert = new TreeNode(duchy, duchyColor, 'd');
                    insert.parent = cur;
                    cur.childs.add(insert);
                    cur = insert;
                }
            }

            if(!county.isEmpty()) {
                found = false;
                for (TreeNode inst : cur.childs) {
                    if (inst.name.equals(county)) {
                        cur = inst;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    TreeNode insert = new TreeNode(county, countyColor, 'c');
                    insert.parent = cur;
                    cur.childs.add(insert);
                    cur = insert;
                }
            }

            if(!duchy.isEmpty()&&!county.isEmpty()) {
                province.parent = cur;
                cur.childs.add(province);
            }
            else
                System.out.println("Ignored");
        }


        return root;

    }

    public static void TreeUpdater(TreeNode root,ArrayList<String[]> lines)
    {
        for (String[] line : lines) {
            String province_id = line[1];
            String korean_name = line[2];
            String english_name = line[3];
            String province_color = line[4];
            String countyName = line[5];
            String county = line[6];
            String countyColor = line[7];
            String duchyName = line[8];
            String duchy = line[9];
            String duchyColor = line[10];
            String duchyCapital = line[11];
            String kingdomName = line[12];
            String kingdom = line[13];
            String kingdomColor = line[14];
            String kingdomCapital = line[15];
            String empireName = line[16];
            String empire = line[17];
            String empireColor = line[18];
            String empireCapital = line[19];
            String culture = line[20];
            String religion = line[21];
            String holding = line[22];
            String landscape = line[23];

            TreeNode province = DFS(root,province_id);
            if(province!=null) {
                do {
                    switch (province.tier) {
                        case 'b': {
                            if (province.kr.isEmpty())
                                TreeNode.updateInfo(province, korean_name, "kr");
                            if (province.eng.isEmpty())
                                TreeNode.updateInfo(province, english_name, "eng");
                            break;
                        }
                        case 'c':{
                            if(province.kr.isEmpty())
                                TreeNode.updateInfo(province,countyName,"kr");
                        }
                        case 'd':{
                            if(province.kr.isEmpty())
                                TreeNode.updateInfo(province,duchyName,"kr");
                        }
                        case 'k':{
                            if(province.kr.isEmpty())
                                TreeNode.updateInfo(province,kingdomName,"kr");
                        }
                        case 'e':{
                            if(province.kr.isEmpty())
                                TreeNode.updateInfo(province,empireName,"kr");
                        }
                    }
                    province = province.parent;
                }while(province.parent!=null);
            }
            else
            {
                System.out.println("Error on "+province_id);
            }

        }



    }

    public static TreeNode DFS(TreeNode node, String target)
    {
        TreeNode result =null;

        for(TreeNode child : node.childs)
        {
            if(node.name.equals("k_seoul"))
                System.out.println(child.name);
            if(child.name.equals(target))
            {
                return child;
            }
            result = DFS(child,target);
            if(result!=null)
                return result;
        }

        return result;
    }
}
