package com.cal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

class TreeGenerateTest
{
    TreeGenerate tg;
    @BeforeEach
    void setUp()
    {
        tg = new TreeGenerate();
    }
    @Test
    public void isSignPrior()
    {
        String plus = "+";
        String minus = "-";
        String time = "×";
        String divide = "÷";
        assertTrue(tg.isSignPrior(plus,plus));
        assertTrue(tg.isSignPrior(minus,minus));
        assertTrue(tg.isSignPrior(minus,plus));
        assertTrue(tg.isSignPrior(plus,minus));

        assertTrue(tg.isSignPrior(time,plus));
        assertTrue(tg.isSignPrior(time,minus));

        assertTrue(tg.isSignPrior(time,divide));
        assertTrue(tg.isSignPrior(divide,time));
        assertTrue(tg.isSignPrior(divide,divide));
        assertTrue(tg.isSignPrior(time,time));
    }
    @Test
    public void testPushExpressionIntoStack()
    {
        Deque<Node> signStack = new ArrayDeque<>();
        Deque<Node> numStack = new ArrayDeque<>();
        tg.pushExpressionIntoStack(signStack,numStack,"3 - 2 + 3");
        assertNotEquals(0,signStack.size());
        assertNotEquals(0,numStack.size());
        for(Node node : signStack)
            System.out.println(node.content);
        for(Node node : numStack)
            System.out.println(node.content);
    }
    @Test
    public void testTransStackToTree()
    {
        Deque<Node> signStack = new ArrayDeque<>();
        Deque<Node> numStack = new ArrayDeque<>();
        tg.pushExpressionIntoStack(signStack,numStack,"9/10 × ( 10 + 4 ) - 5");
        Node node = tg.transStackToTree(signStack,numStack);
        assertNotNull(node);
        Node.inOrder(node, new Node.OperationInTraversal()
        {
            @Override
            public void operate(Node node)
            {
                System.out.print(node.content);
            }
        });
    }
    @Test
    public void testTransExpressionIntoTree()
    {
        String expression = "9/10 × ( 10 + 4 ) - 5";
        Node root = tg.transExpressionIntoTree(expression);
        assertNotNull(root);

        Node.inOrder(root, new Node.OperationInTraversal()
        {
            @Override
            public void operate(Node node)
            {
                System.out.println(node.content);
            }
        });
    }


}