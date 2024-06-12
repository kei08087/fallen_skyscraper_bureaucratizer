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
            boolean found;

            TreeNode province = new TreeNode(province_id,province_color, 'b',english_name, korean_name);
            TreeNode.updateInfo(province,culture,"culture");
            TreeNode.updateInfo(province,religion,"religion");
            TreeNode.updateInfo(province,holding,"holding");

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
                    TreeNode.updateInfo(insert,empireCapital,"capital");
                    TreeNode.updateInfo(insert,empireName,"kr");
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
                TreeNode.updateInfo(insert,kingdomCapital,"capital");
                TreeNode.updateInfo(insert,kingdomName,"kr");
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
                    TreeNode.updateInfo(insert,duchyCapital,"capital");
                    TreeNode.updateInfo(insert,duchyName,"kr");
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
                    TreeNode.updateInfo(insert,countyName,"kr");
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
                            break;
                        }
                        case 'd':{
                            if(province.kr.isEmpty()) {
                                //System.out.println("in case: "+province.name+"\tupdate: "+duchyName);
                                TreeNode.updateInfo(province, duchyName, "kr");
                            }
                            break;
                        }
                        case 'k':{
                            if(province.kr.isEmpty())
                                TreeNode.updateInfo(province,kingdomName,"kr");
                            break;
                        }
                        case 'e':{
                            if(province.kr.isEmpty())
                                TreeNode.updateInfo(province,empireName,"kr");
                            break;
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
