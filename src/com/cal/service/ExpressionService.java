package com.cal.service;

import com.cal.Core;
import com.cal.Node;

import java.util.ArrayList;
import java.util.List;

public class ExpressionService
{
    private Core core = new Core();
    private List<String> answerList = new ArrayList<>();
    public List<String> getExpressionList(int expressionNum)
    {
        List<Node> nodeList = core.createExpressionTreeList(expressionNum);
        List<String> strList = new ArrayList<>();
        for(Node node : nodeList)
        {
            int index = nodeList.indexOf(node)+1;
            strList.add("第"+index+"题："+ core.transTreeToString(node)+"=");
            answerList.add(node.result);
        }

        return strList;
    }
    public List<String> getAnswerList()
    {
        return answerList;
    }
}
