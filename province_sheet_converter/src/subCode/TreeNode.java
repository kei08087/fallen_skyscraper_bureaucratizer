package subCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class TreeNode extends Node {
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

    public LinkedList<String> titleOthers = new LinkedList<>();

    public LinkedList<String> provinceOthers = new LinkedList<>();

    public AttributeNode titleHistory;

    public String capital="";

    public String holder="";

    public TreeNode(String name, String color, char tier)
    {
        AttributeNode attribute = new AttributeNode("root",true);
        AttributeNode.AttributeValueUpdate(attribute,name);
        this.name = name;
        this.color = color;
        this.tier = tier;
        this.titleHistory=attribute;
        parent = null;
    }

    public TreeNode(String name, String color, char tier, String eng, String kr)
    {
        AttributeNode attribute = new AttributeNode("root",true);
        AttributeNode.AttributeValueUpdate(attribute,name);
        this.name = name;
        this.color = color;
        this.tier = tier;
        this.eng = eng;
        this.kr = kr;
        this.titleHistory=attribute;
        parent = null;
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
                    node.titleOthers.add(data);
                    break;

                case "holder":
                    if (node.holder.isEmpty() || !node.holder.equals(data)) {
                        node.holder = data;
                    }
                    break;

                case "provinceOthers":
                    node.provinceOthers.add(data);
                    break;


                default:
                    // Optional: Handle unexpected values of data[1]
                    System.out.println("Unexpected attribute: " + option);
                    break;
            }
        }
    }
}

