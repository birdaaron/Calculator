package com.cal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateUtilTest
{
    @Test
    public void testCalNaturalNum()
    {
        String result1 = CalculateUtil.calNaturalNum("10","+","5");
        String result2 = CalculateUtil.calNaturalNum("10","-","5");
        String result3 = CalculateUtil.calNaturalNum("10","×","5");
        String result4 = CalculateUtil.calNaturalNum("5","÷","10");
        assertEquals("15",result1);
        assertEquals("5",result2);
        assertEquals("50",result3);
        assertEquals("1/2",result4);
    }
    @Test
    public void testCalFraction()
    {
        String result1 = CalculateUtil.calFraction("3/4","+","2/5");
        String result2 = CalculateUtil.calFraction("1/10","-","1/5");
        String result3 = CalculateUtil.calFraction("2/3","×","1/3");
        String result4 = CalculateUtil.calFraction("3/8","÷","10/24");
        assertEquals("23/20",result1);
        assertEquals("-1/10",result2);
        assertEquals("2/9",result3);
        assertEquals("9/10",result4);

    }
    @Test
    public void testGetGCD()
    {
        int a = 36;
        int b = 20;
        assertEquals(4,CalculateUtil.getGCD(a,b));
    }
    @Test void calNum()
    {
        String a = "10";
        String b = "1/10";
        String sign = "-";
        assertEquals("99/10",CalculateUtil.calNum(a,sign,b));
    }
}