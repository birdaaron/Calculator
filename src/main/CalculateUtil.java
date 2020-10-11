public class CalculateUtil
{
    public static String calNum(String num1,String sign,String num2)
    {
        if(!num1.contains("/")&&!num2.contains("/"))
            return calNaturalNum(num1,sign,num2);
        else if(num1.contains("/"))
            return calFraction(num1,sign,num2+"/1");
        else
            return calFraction(num1+"/1",sign,num2);
    }
    public static String calNaturalNum(String num1Str,String sign,String num2Str)
    {
        int num1 = Integer.parseInt(num1Str);
        int num2 = Integer.parseInt(num2Str);
        switch (sign)
        {
            case "+":
                return String.valueOf(num1+num2);
            case "-":
                return String.valueOf(num1-num2);
            case "×":
                return String.valueOf(num1*num2);
            case "÷":
                return divideFraction(num1Str+"/1",num2Str+"/1");
        }
        return null;
    }

    public static String calFraction(String fractionStr1,String sign,String fractionStr2)
    {
        switch (sign)
        {
            case "+":
                return addFraction(fractionStr1,fractionStr2);
            case "-":
                return minusFraction(fractionStr1,fractionStr2);
            case "×":
                return timeFraction(fractionStr1,fractionStr2);
            case "÷":
                return divideFraction(fractionStr1,fractionStr2);
        }
        return null;
    }
    protected static int getGCD(int num1,int num2)
    {
        return num2==0?num1:getGCD(num2,num1%num2);
    }
    private static int getNumerator(String fractionStr)
    {
        String[] array = fractionStr.split("/");
        return Integer.parseInt(array[0]);
    }
    private static int getDenominator(String fractionStr)
    {
        String[] array = fractionStr.split("/");
        return Integer.parseInt(array[1]);
    }
    private static String addFraction(String fractionStr1,String fractionStr2)
    {
        int n = getNumerator(fractionStr1)*getDenominator(fractionStr2)
                + getNumerator(fractionStr2)*getDenominator(fractionStr1);
        int d = getDenominator(fractionStr1)*getDenominator(fractionStr2);
        int gcd = getGCD(n,d);
        if(n==0)
            return "0";
        else if(n/gcd==d/gcd)
            return "1";
        else if(d/gcd==1)
            return String.valueOf(n/gcd);
        return (n / gcd) +"/"+(d/gcd);
    }
    private static String minusFraction(String fractionStr1,String fractionStr2)
    {
        int n = getNumerator(fractionStr1)*getDenominator(fractionStr2)
                - getNumerator(fractionStr2)*getDenominator(fractionStr1);
        int d = getDenominator(fractionStr1)*getDenominator(fractionStr2);
        int gcd = getGCD(n,d);
        if(gcd<0)
            gcd = -1*gcd;
        if(n==0)
            return "0";
        else if(n/gcd==d/gcd)
            return "1";
        else if(d/gcd==1)
            return String.valueOf(n/gcd);
        return (n / gcd) +"/"+(d/gcd);
    }
    private static String timeFraction(String fractionStr1,String fractionStr2)
    {
        int n = getNumerator(fractionStr1)*getNumerator(fractionStr2);
        int d = getDenominator(fractionStr1)*getDenominator(fractionStr2);
        int gcd = getGCD(n,d);
        if(n==0)
            return "0";
        else if(n/gcd==d/gcd)
            return "1";
        else if(d/gcd==1)
            return String.valueOf(n/gcd);
        return (n / gcd) +"/"+(d/gcd);
    }
    private static String divideFraction(String fractionStr1,String fractionStr2)
    {
        int n = getNumerator(fractionStr1)*getDenominator(fractionStr2);
        int d = getDenominator(fractionStr1)*getNumerator(fractionStr2);
        int gcd = getGCD(n,d);
        if(n==0)
            return "0";
        else if(n/gcd==d/gcd)
            return "1";
        else if(d/gcd==1)
            return String.valueOf(n/gcd);
        return (n / gcd) +"/"+(d/gcd);
    }
}
