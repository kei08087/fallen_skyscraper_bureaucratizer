package subCode;

import java.util.ArrayList;
import java.util.Arrays;

public class TreeNode {
    public TreeNode parent=null;
    public String name;
    public ArrayList<TreeNode> childs = new ArrayList<>();
    public String color;

    public char tier;

    public String eng="";

    public String kr="";

    public String culture="";

    public String religion="";

    public String holding="";

    public String landscape="";

    public String titleOthers="";

    public String provinceOthers="";

    public String capital="";

    public String holder="";

    public TreeNode(String name, String color, char tier)
    {
        this.name = name;
        this.color = color;
        this.tier = tier;
        parent = null;
    }

    public TreeNode(String name, String color, char tier, String eng, String kr)
    {
        this.name = name;
        this.color = color;
        this.tier = tier;
        this.eng = eng;
        this.kr = kr;
        parent = null;
    }

    public static void addProvinceInfo(TreeNode node, String culture, String religion, String holding)
    {
        node.culture = culture;
        node.religion = religion;
        node.holding = holding;
    }

    public static void setParent(TreeNode parent, TreeNode child)
    {
        child.parent = parent;
    }

    public static void updateInfo(TreeNode node, String data, String option){


        if(!data.isEmpty()) {
            switch (option) {
                case "name":
                    if (node.name.isEmpty() || !node.name.equals(data)) {
                        node.name = data;
                    }
                    break;

                case "color":
                    if (node.color.isEmpty() || !node.color.equals(data)) {
                        node.color = data;
                    }
                    break;

                case "culture":
                    if (node.culture.isEmpty() || !node.culture.equals(data)) {
                        node.culture = data;
                    }
                    break;

                case "religion":
                    if (node.religion.isEmpty() || !node.religion.equals(data)) {
                        node.religion = data;
                    }
                    break;

                case "kr":
                    if (node.kr.isEmpty() || !node.kr.equals(data)) {
                        node.kr = data;
                    }
                    break;

                case "eng":
                    if (node.eng.isEmpty() || !node.eng.equals(data)) {
                        node.eng = data;
                    }
                    break;

                case "capital":
                    if (node.capital.isEmpty() || !node.capital.equals(data)) {
                        node.capital = data;
                    }
                    break;

                case "holding":
                    if (node.holding.isEmpty() || !node.holding.equals(data)) {
                        node.holding = data;
                    }
                    break;

                case "landscape":
                    if (node.landscape.isEmpty() || !node.landscape.equals(data)) {
                        node.landscape = data;
                    }
                    break;

                case "titleOthers":
                    if (node.titleOthers.isEmpty() || !node.titleOthers.equals(data)) {
                        node.titleOthers = node.titleOthers + data + "\n";
                    }
                    break;

                case "holder":
                    if (node.holder.isEmpty() || !node.holder.equals(data)) {
                        node.holder = data;
                    }
                    break;

                case "provinceOthers":
                    if (node.provinceOthers.isEmpty() || !node.provinceOthers.equals(data)) {
                        node.provinceOthers = node.provinceOthers + data + "\n";
                    }
                    break;

                default:
                    // Optional: Handle unexpected values of data[1]
                    System.out.println("Unexpected attribute: " + option);
                    break;
            }
        }
    }
}
