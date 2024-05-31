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
            String county = line[5];//test only. add 1 later.
            String duchy = line[8];//test only. add 1 later.
            String kingdom = line[12];
            String empire = line[15];
            boolean found;

            TreeNode province = new TreeNode(province_id,province_color, 'b',english_name, korean_name);
            province = TreeNode.addProvinceInfo(province,line[18],line[19],line[20]);

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
                    TreeNode insert = new TreeNode(empire,line[16],'e');
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
                TreeNode insert = new TreeNode(kingdom,line[13],'k');
                insert.parent = cur;
                cur.childs.add(insert);
                cur = insert;
            }

            found = false;
            for(TreeNode inst : cur.childs)
            {
                if(inst.name.equals(duchy))
                {
                    cur = inst;
                    found = true;
                    break;
                }
            }
            if(!found)
            {
                TreeNode insert = new TreeNode(duchy,line[10],'d');
                insert.parent = cur;
                cur.childs.add(insert);
                cur = insert;
            }

            found = false;
            for(TreeNode inst : cur.childs)
            {
                if(inst.name.equals(county))
                {
                    cur = inst;
                    found = true;
                    break;
                }
            }
            if(!found)
            {
                TreeNode insert = new TreeNode(county,line[7],'c');
                insert.parent = cur;
                cur.childs.add(insert);
                cur = insert;
            }

            province.parent = cur;
            cur.childs.add(province);
        }

        return root;

    }
}
