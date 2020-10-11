import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Core
{
    private ExpressionGenerate expressionGenerate = new ExpressionGenerate();
    private TreeGenerate treeGenerate = new TreeGenerate();
    private List<Node> expressionList = new ArrayList<>();
    protected void calResult(Node root)
    {
        if(root.isNum)
            return ;
        calResult(root.left);
        calResult(root.right);
        root.result = CalculateUtil.calNum(root.left.result,root.content,root.right.result);
    }
    protected void adjustExpressionTree(Node root)
    {
        if(root.isNum)
            return;
        adjustExpressionTree(root.left);
        adjustExpressionTree(root.right);
        switch (root.content)
        {
            case "-":
                if(isLarger(root.right.result,root.left.result))
                    Node.switchLeftAndRight(root);
                break;
            case "÷":
                if(isLarger(root.left.result,root.right.result))
                    Node.switchLeftAndRight(root);
                break;
        }
        root.result = CalculateUtil.calNum(root.left.result,root.content,root.right.result);
    }
    protected boolean isLarger(String num1,String num2)
    {
        return !CalculateUtil.calNum(num1,"-",num2).contains("-");
    }
    protected boolean isExpressionDuplicated(Node nodeA,Node nodeB)
    {
        if(nodeA==null^nodeB==null)
            return false;
        if(nodeA==null)
            return true;
        if(nodeA.content.equals(nodeB.content) )
        {
            if(isExpressionDuplicated(nodeA.left,nodeB.left)&&isExpressionDuplicated(nodeA.right,nodeB.right))
                return true;
            if(isExpressionDuplicated(nodeA.left,nodeB.right)&&isExpressionDuplicated(nodeA.right,nodeB.left))
                return true;
        }
        return false;
    }
    protected String transTreeToString(Node root)
    {
        if(root==null)
            return "";
        String lResult = transTreeToString(root.left);
        String rResult = transTreeToString(root.right);
        if(!root.isNum)
        {
            if(!root.left.isNum&&treeGenerate.isSignPrior(root.content,root.left.content))
                lResult = "( "+lResult+") ";
            if(!root.right.isNum&&treeGenerate.isSignPrior(root.content,root.right.content))
                rResult = "( "+rResult+") ";
        }
        return lResult + root.content +" "+rResult;
    }
    public List<Node> createExpressionTreeList(int expressionNum)
    {
        List<String> strList = new ArrayList<>();
        List<Node> nodeList = new ArrayList<>();
        List<Node> resultList = new ArrayList<>();
        int sameExpressionCount = 0;
        for(int i = 0;i<expressionNum;i++)
            strList.add(expressionGenerate.createExpression(new Random().nextInt(3)+1));
        for(String str : strList)
            nodeList.add(treeGenerate.transExpressionIntoTree(str));
        for(Node node : nodeList)
        {
            boolean addFlag = true;
            calResult(node);
            adjustExpressionTree(node);
            for(Node resultNode : resultList)
                if(resultNode.result.equals(node.result)&&isExpressionDuplicated(resultNode,node))
                {
                    addFlag = false;
                    sameExpressionCount++;
                }
            if(addFlag)
                resultList.add(node);
        }
        if(sameExpressionCount!=0)
            resultList.addAll(createExpressionTreeList(sameExpressionCount));
        return resultList;
    }
}
