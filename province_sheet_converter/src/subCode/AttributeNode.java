package subCode;

import java.util.LinkedList;

public class AttributeNode extends Node {
    public String type;

    public String value="";

    public AttributeNode parent = null;

    public LinkedList<AttributeNode> childs = new LinkedList<>();

    public boolean scope;

    public AttributeNode(String type, boolean scope)
    {
        this.type=type;
        this.scope=scope;
    }

    public static void AttributeValueUpdate(AttributeNode node, String data)
    {
        node.value=data;
    }

}
