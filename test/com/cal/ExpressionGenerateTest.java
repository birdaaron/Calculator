package com.cal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionGenerateTest
{
    private ExpressionGenerate eg;
    @BeforeEach
    public void setUp()
    {
        eg = new ExpressionGenerate();
    }

    @Test
    public void isNaturalNum()
    {
       String n = eg.getNaturalNum(10);
       assertFalse(n.contains(".")||n.contains("/"));
    }
    @Test
    public void isSign()
    {
        String n = eg.getSign();
        assertTrue(n.contains("+")||n.contains("-")||n.contains("×")||n.contains("÷"));
    }
    @Test
    public void isProperFraction()
    {
        String n = eg.getProperFraction();
        if(n.contains("/"))
        {
            int index = n.indexOf("/");
            String numerator = n.substring(0,index);
            String denominator = n.substring(index+1);
            assertTrue(Integer.parseInt(numerator)<=Integer.parseInt(denominator));
        }
        else
            fail();
    }

    @Test
    public void testCreateExpression()
    {
        int signNum = 3;
        int expressionNum = 100;
        for(int i = 0;i<expressionNum;i++)
        {
            String expression = eg.createExpression(signNum);
            assertFalse(expression.isEmpty());
            System.out.println(expression);
        }

    }

}