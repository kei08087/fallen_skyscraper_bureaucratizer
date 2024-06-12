package subCode;

public class AttrString {
    public String value;
    public int scopeStack;

    public AttrString(String value,int stack)
    {
        this.value=value;
        this.scopeStack=stack;
    }
}
