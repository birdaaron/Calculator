package com.cal;
import java.util.ArrayDeque;
import java.util.Deque;

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
    protected boolean isSignPrior(String signA,String signB)
    {
        switch (signA)
        {
            case "+":
            case "-":
                if(signB.equals("×")||signB.equals("÷"))
                    return false;
                return true;
            case "×":
            case "÷":
                return true;
        }
        return false;
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
                case "×":
                case "÷":
                    if(!signStack.isEmpty())
                    {
                        String lastSign = signStack.getFirst().content;
                        if(isSignPrior(lastSign,expressionArray[i]))
                            numStack.push(createTree(signStack,numStack));
                    }
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
