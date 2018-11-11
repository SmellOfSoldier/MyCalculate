import java.math.BigDecimal;

public class Othertool
{
    public static int priority(Character operator) {
        switch (operator) {
            case ')':
                return 3;
            case '+':
                return 2;
            case '-':
                return 2;
            case '*':
                return 1;
            case '/':
                return 1;
            default:
                return -1;
        }
    }
    public static Double calculate() {
        BigDecimal first=new BigDecimal(CalculateTool.num.pop());
        BigDecimal second=new BigDecimal(CalculateTool.num.pop());
        switch (CalculateTool.operator.pop()) {
            case '*': return (first.multiply(second)).doubleValue();
            case '/':
                if(first.doubleValue()==0)
                {
                    CalculateTool.flag=true;
                    return -1.0;
                }
                return second.divide(first,3,BigDecimal.ROUND_HALF_UP).doubleValue();
            case '-': return second.subtract(first).doubleValue();
            case '+': return first.add(second).doubleValue();
            default: return 0.0;
        }
    }
}
