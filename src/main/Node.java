public class Node
{
    Node parent;
    Node left;
    Node right;
    String content;
    String result;
    boolean isNum;
    public Node(Node parent,String content, String result, boolean isNum)
    {
        this.parent = parent;
        this.content = content;
        this.isNum = isNum;
        this.result = result;
    }
    public static void postOrder(Node root,OperationInTraversal oit)
    {
        if(root == null)
            return;
        postOrder(root.left,oit);
        postOrder(root.right,oit);
        oit.operate(root);
    }
    public static void inOrder(Node root,OperationInTraversal oit)
    {
        if(root == null)
            return;
        inOrder(root.left,oit);
        oit.operate(root);
        inOrder(root.right,oit);
    }
    public interface OperationInTraversal
    {
        void operate(Node node);
    }
}
