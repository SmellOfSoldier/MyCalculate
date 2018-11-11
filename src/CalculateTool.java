import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Stack;

public class CalculateTool
{

    public static int leftnum = 0;        //左括号数目
    public static int rightnum = 0;       //右括号数目
    public static Stack<Double> num = new Stack<Double>();      //存放数字
    public static Stack<Character> operator = new Stack<Character>();   //存放操作符
    public static boolean flag;  //判断是否除零
    public  boolean Calculate(StringBuilder originalstring){
        Scanner scan=new Scanner(System.in);
            leftnum=0;
            rightnum=0;
            num.clear();
            operator.clear();
            flag=false;
            StringBuilder sb=new StringBuilder(originalstring);
            System.out.println(sb.toString());
            if(!Examintool.examineError(sb))              //对字符串进行检查，如果有误则即使终止计算
            {
                CalculateFrame.getLabel2().setText("error formula input" );
                return true;
            }
            char []s=sb.toString().toCharArray();
            outloop: for(int index=0; index<s.length;index++)
            {
                if(s[index]=='(')
                {
                    operator.push(s[index]);
                    leftnum++;
                    continue;
                }
                else if(s[index]==')')
                {
                    while(operator.lastElement()!='(')
                    {
                        Double result =Othertool.calculate();
                        num.push(result);
                    }
                    operator.pop();
                    leftnum--;

                }
                else if(s[index]>='0'&&s[index]<='9')       //判断是否为数字
                {
                    int end=index+1;
                    while(end<s.length&&(s[end]>='0'&&s[end]<='9'||s[end]=='.'))
                    {
                        end++;
                    }
                    Double tnum=Double.valueOf(new String(s,index,end-index));
                    num.push(tnum);
                    index=end-1;
                    continue;
                }
                else            //剩下的全是运算符
                {
                    if(s[index]=='-'&& (index==0 || (!(s[index-1]<='9'&&s[index-1]>='0')&&s[index-1]!=')')))    //判断是否为“取反”运算符
                    {
                        num.push(0.0);

                    }
                    if(operator.empty())
                    {
                        operator.push(s[index]);
                        continue;
                    }
                    else if(operator.lastElement()=='('|| Othertool.priority(s[index]) < Othertool.priority(operator.lastElement()))
                    {
                        operator.push(s[index]);
                        continue;
                    }
                    else
                    {
                        while(operator.lastElement()!='('&& Othertool.priority(operator.lastElement()) <= Othertool.priority(s[index])) {
                            Double result = Othertool.calculate();
                            if(flag)
                            {
                                CalculateFrame.getLabel2().setText("error！divide zero");
                                return  true;
                            }
                            num.push(result);
                            if (operator.empty() || operator.lastElement() == '(') {
                                break;
                            }
                        }
                        operator.push(s[index]);
                    }

                }
            }
            while(!operator.empty())        //最终操作对没有运算完的符合进行运算
            {
                Double result=Othertool.calculate();
                if(flag)
                {
                    CalculateFrame.getLabel2().setText("error！divide zero");
                    return true ;
                }
                num.push(result);
            }
            if(!flag)
            {
                if(num.isEmpty())
                {
                    CalculateFrame.getLabel2().setText("0.0");
                }
                else
                {
                    CalculateFrame.getLabel2().setText(String.valueOf(num.pop()));
                }
            }
        return  false;
    }
}
