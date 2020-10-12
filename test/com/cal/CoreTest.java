package com.cal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class CoreTest
{
    Core core;
    TreeGenerate tg;
    @BeforeEach
    void setUp()
    {
        core = new Core();
        tg = new TreeGenerate();
    }
    @Test
    public void testCalResult()
    {
        String expression = "9/10 × ( 10 + 4 ) - 5";
        Node root = tg.transExpressionIntoTree(expression);
        core.calResult(root);
        assertEquals("38/5",root.result);
    }
    @Test
    public void isLarger()
    {
        String a = "4/10";
        String b = "1";
        assertFalse(core.isLarger(a,b));
    }
    @Test
    public void testAdjustExpressionTree()
    {
        String expression = "( 10 - 40 ) ÷ 5";
        Node root = tg.transExpressionIntoTree(expression);
        core.calResult(root);
        core.adjustExpressionTree(root);
        assertEquals("5",root.left.content);
        assertEquals("40",root.right.left.content);
        assertEquals("10",root.right.right.content);
    }
    @Test
    public void isExpressionDuplicated()
    {
        String a = "3 + ( 2 + 1 )";
        String b = "1 + 2 + 3";
        String c = "3 + 2 + 1";
        Node rootA = tg.transExpressionIntoTree(a);
        Node rootB = tg.transExpressionIntoTree(b);
        Node rootC = tg.transExpressionIntoTree(c);
        assertTrue(core.isExpressionDuplicated(rootA,rootB));
        assertFalse(core.isExpressionDuplicated(rootB,rootC));
    }
    @Test
    public void testTransTreeToString()
    {
        String expression = "8/10 + 0 × ( 9 + 8 )";
        Node root = tg.transExpressionIntoTree(expression);
        String str = core.transTreeToString(root);
        assertEquals(expression+" ",str);
    }
    @Test
    public void testCreateExpressionTreeList()
    {
        int expressionNum = 10000;
        List<Node> list = core.createExpressionTreeList(expressionNum);

        for(Node node : list)
        {
            String str = core.transTreeToString(node);
            System.out.print("q"+list.indexOf(node)+": "+str);
            System.out.println("r"+list.indexOf(node)+": "+node.result);
        }
        assertEquals(expressionNum,list.size());
    }
}
