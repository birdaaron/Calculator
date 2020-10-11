import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import java.util.Stack;

public class TreeGenerate
{
    private int getRightBracketIndex(String[] array,int leftBracketIndex)
    {
        for(int j = leftBracketIndex+1;j<array.length;j++)
            if(array[j].equals(")"))
                return j;
        return 0;
    }
    private String getExpressionInBracket(String[] expressionArray,int left,int right)
    {
        StringBuilder subExpressionBuilder =  new StringBuilder();
        for(int j = left+1;j<right;j++)
            subExpressionBuilder.append(expressionArray[j]).append(" ");
        return subExpressionBuilder.toString();
    }

    protected void pushExpressionIntoStack(Deque<Node> signStack,Deque<Node> numStack,String expression)
    {
        String[] expressionArray = expression.split(" ");//
        for(int i = 0;i<expressionArray.length;i++)
        {
            switch (expressionArray[i])
            {
                case "(":
                    int rightBracketIndex = getRightBracketIndex(expressionArray, i);
                    String subExpression = getExpressionInBracket(expressionArray, i,rightBracketIndex);
                    numStack.push(transExpressionIntoTree(subExpression));
                    i = rightBracketIndex;

                    break;
                case "+":
                case "-":
                    if(!signStack.isEmpty())
                    {
                        String lastestSign = signStack.getFirst().content;
                        Node node = null;
                        if(lastestSign.equals("×")||lastestSign.equals("÷"))
                            node = createTree(signStack,numStack);
                        numStack.push(node);
                    }
                case "×":
                case "÷":
                    signStack.push(new Node(null,expressionArray[i],null,false));
                    break;
                default:
                    numStack.push(new Node(null,expressionArray[i],expressionArray[i],true));
                    break;
            }

        }
    }
    protected Node createTree(Deque<Node> signStack,Deque<Node> numStack)
    {
        if(!signStack.isEmpty())
        {
            Node lastestSign = signStack.poll();
            lastestSign.right = numStack.poll();
            lastestSign.left = numStack.poll();
            return lastestSign;
        }
        return null;
    }
    protected Node transStackToTree(Deque<Node> signStack,Deque<Node> numStack)
    {
        while (!signStack.isEmpty())
            numStack.push(createTree(signStack,numStack));
        return numStack.poll();
    }
    public Node transExpressionIntoTree(String expression)
    {
        Deque<Node> signStack = new ArrayDeque<>();
        Deque<Node> numStack = new ArrayDeque<>();
        pushExpressionIntoStack(signStack,numStack,expression);
        return transStackToTree(signStack,numStack);
    }
}
