public class Examintool
{
    public static boolean examineError(StringBuilder  sb)
    {
        initial_handle(sb);
        char [] leftoperator=new char[]{'+','-','*','/'};
        char [] rightoperator=new char[]{'+','/','*'};
        int leftnum=0;      //右括号数量
        int rightnum=0;     //左括号数量
        for(int index=0;index<sb.length();index++)
        {
            if(sb.charAt(index)=='.')     //小数点检查
            {
                if(index==0 && (sb.charAt(index+1)<='9'&&sb.charAt(index+1)>='0'))
                {
                    sb.insert(index,0);
                }
                else if(index==sb.length()-1 && (sb.charAt(index-1)<='9'&&sb.charAt(index-1)>='0'))
                {
                    sb.insert(index+1,0);
                }
                else if(index!=0 && index!=sb.length()-1)
                {
                    if(sb.charAt(index-1)>='0'&&sb.charAt(index-1)<='9' && (sb.charAt(index+1)<'0'||sb.charAt(index+1)>'9'))
                    {
                        sb.insert(index+1,0);
                    }
                    else if((sb.charAt(index-1)<'0'||sb.charAt(index-1)>'9') && sb.charAt(index+1)>='0'&&sb.charAt(index+1)<='9')
                    {
                        sb.insert(index,0);
                    }
                    else if((sb.charAt(index-1)>'9'||sb.charAt(index-1)<'0') &&(sb.charAt(index+1)>'9'||sb.charAt(index+1)<'0'))
                    {
                        return  false;
                    }
                }
            }
            else if(sb.charAt(index)=='(')
            {
                leftnum++;
            }
            else if(sb.charAt(index)==')')
            {
                rightnum++;
                if(leftnum<rightnum)
                {
                    return false;
                }
            }
            else if(sb.charAt(index)=='*' ||sb.charAt(index)=='+'|| sb.charAt(index)=='/') //判断运算符
            {
                if(index==0 || index==sb.length()-1)
                {
                    return false;
                }
                else if(sb.charAt(index-1)=='('|| sb.charAt(index+1)==')')
                {
                    return  false;
                }
                else
                {
                    for(int i=0;i<leftoperator.length;i++)
                    {
                        if(sb.charAt(index-1)==leftoperator[i])
                        {
                            return false;
                        }
                    }
                    for(int j=0;j<rightoperator.length;j++)
                    {
                        if(sb.charAt(index+1)==rightoperator[j])
                        {
                            return false;
                        }
                    }
                    if(sb.charAt(index+1)=='-')
                    {
                        int k=index;
                        for(;k<sb.length();k++)
                        {
                            if(sb.charAt(k)<='9'&&sb.charAt(k)>='0')
                                break;
                        }
                        if (k==sb.length())
                        {
                            return false;
                        }
                    }
                }
            }
            else if(sb.charAt(index)=='-')
            {
                if(index==sb.length()-1)
                {
                    return false;
                }
                else if(index!=sb.length()-1 && (sb.charAt(index+1)=='*'||sb.charAt(index+1)=='/'||sb.charAt(index+1)=='+'))
                {
                    return false;
                }
                else if(sb.charAt(index+1)=='-')
                {
                    int i=index;
                    for(;i<sb.length();i++)
                    {
                        if(sb.charAt(i)>='0'&&sb.charAt(i)<'9')
                        {
                            break;
                        }
                    }
                    if(i==sb.length())
                    {
                        return false;
                    }
                }
            }
        }
        if(leftnum!=rightnum)
        {
            return false;
        }
        return true;
    }

    public static void initial_handle(StringBuilder sb)
    {
        boolean transform=false;
        int front;
        int end;
        for(int i=0;i<sb.length();i++)
        {
            if(sb.charAt(i)=='-')
            {
                int num=0;
                front=i;
                for(end=i;end<sb.length();end++,num++)
                {
                    if(sb.charAt(end)!='-')
                        break;
                }
                if(num%2==0)
                {
                    sb.replace(front, end, "+");
                    transform=true;
                }
                else
                    sb.replace(front,end,"-");
                if(i!=0 &&i!=sb.length()-1 && (sb.charAt(i-1)=='/'||sb.charAt(i-1)=='*')&& (sb.charAt(i+1)<='9'&&sb.charAt(i+1)>'0'))
                {
                    for(end=i+1;end<sb.length();end++)
                    {
                        if(!(sb.charAt(end)<='9'&&sb.charAt(end)>='0'))
                        {
                            break;
                        }
                    }
                    sb.insert(end,")");
                    sb.insert(i,"(");
                }
                if(transform)
                    i--;
            }
            else if(sb.charAt(i)=='+')
            {
                front=i;
                for(end=i;end<sb.length();end++)
                {
                    if(sb.charAt(end)!='+')
                        break;
                }
                sb.replace(front,end,"+");
                if(i==0)
                {
                    sb.replace(i,i+1,"");
                }
                else if(i!=sb.length()-1 && (sb.charAt(i-1)=='-'||sb.charAt(i-1)=='*'||sb.charAt(i-1)=='/')&&(sb.charAt(i+1)<='9'&&sb.charAt(i+1)>='0'))
                {
                    sb.replace(i,i+1,"");
                }
            }
        }
    }
}

