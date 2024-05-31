package subCode;

import java.util.ArrayList;

public class TreeNode {
    public TreeNode parent;
    public String name;
    public ArrayList<TreeNode> childs = new ArrayList<>();
    public String color;

    public char tier;

    public String eng;

    public String kr;

    public String culture;

    public String religion;

    public String holding;

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

    public static TreeNode addProvinceInfo(TreeNode node, String culture, String religion, String holding)
    {
        node.culture = culture;
        node.religion = religion;
        node.holding = holding;

        return node;
    }
}
