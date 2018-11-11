import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;

public class CalculateFrame
{
    public boolean haveerror=false;             //判断上一次计算是否有错
    public static Double result;                 //存放每次计算结果
    public static boolean end=false;
    private static   StringBuilder s=new StringBuilder();
    private static Label label1=new Label();
    private static Label label2=new Label();
    private static Panel p=new Panel();          //用于存放输入和计算结果的标签
    private static Panel p2=new Panel();        //用于存放数字和算术符号的按钮
    private static Panel p3=new Panel();        //用于存放历史记录
    private  static Frame f=new Frame("计算器");
    private  String[] name={"AC","C","=","+","7","8","9","-","4","5","6","*","1","2","3","/",".","0","(",")"};

    static {
        label1.setFont(new Font("",1,20));
        label2.setFont(new Font("",1,20));
        label1.setBackground(new Color(242,242,242));
        label2.setBackground(new Color(242,242,242));
        f.setLocation(800,300);
        f.setSize(500,500);
    }

    public static Label getLabel2(){return label2;}
    public static StringBuilder getS(){return s;}
    class Mykeylistener extends KeyAdapter
    {
        public void keyTyped (KeyEvent e)
        {
            char c=e.getKeyChar();
            if(end&&c!='=')
            {
                if(!haveerror&&(c=='*'||c=='/'||c=='+'||c=='-'||c=='、'))
                {
                    s.replace(0,s.length(),label2.getText());
                    label1.setText(s.toString());
                    label2.setText("");
                    end=false;
                }
                else if(!haveerror&&(c=='('||c=='（'))
                {
                    s.replace(0,s.length(),"(");
                    s.append(label2.getText());
                    label1.setText(s.toString());
                    label2.setText("");
                    end=false;
                    return;
                }
                else
                {
                    label1.setText("");
                    label2.setText("");
                    end = false;
                }
            }
            switch (c)
            {
                case KeyEvent.VK_ESCAPE:
                    label1.setText("");
                    label2.setText("");
                    s.replace(0,s.length(),"");
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    if(s.length()!=0)
                    {
                        s.replace(s.length()-1,s.length(),"");
                    }
                    label1.setText(s.toString());
                    break;
                case '=':
                    if(s.length()!=0)
                    {
                        end=true;
                        haveerror=new CalculateTool().Calculate(s);
                        s.replace(0, s.length(), "");
                    }
                    break;
                case '*':
                    s.append("*");
                    label1.setText(s.toString());
                    break;
                case'/':
                    s.append("/");
                    label1.setText(s.toString());
                    break;
                case'、':
                    s.append("/");
                    label1.setText(s.toString());
                    break;
                case'+':
                    s.append("+");
                    label1.setText(s.toString());
                    break;
                case'-':
                    s.append("-");
                    label1.setText(s.toString());
                    break;
                case'.':
                    s.append(".");
                    label1.setText(s.toString());
                    break;
                case'(':
                    s.append("(");
                    label1.setText(s.toString());
                    break;
                case '（':
                    s.append("(");
                    label1.setText(s.toString());
                    break;
                case ')':
                    s.append(")");
                    label1.setText(s.toString());
                    break;
                case '）':
                    s.append(")");
                    label1.setText(s.toString());
                    break;
                default:
                    if(c>='0'&&c<='9')
                        s.append(c);
                    label1.setText(s.toString());
            }
        }
    }

    class Mylistener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String es=e.getActionCommand();
            if(end && !es.equals("="))
            {
                if(!haveerror&&(es.equals("*")||es.equals("/")||es.equals("+")||es.equals("-")))
                {
                    s.replace(0,s.length(),label2.getText());
                    label1.setText(s.toString());
                    label2.setText("");
                    end=false;
                }
                else if(!haveerror&&(es.equals("(")))
                {
                    s.replace(0,s.length(),"(");
                    s.append(label2.getText());
                    label1.setText(s.toString());
                    label2.setText("");
                    end=false;
                    return;
                }
                else
                {
                    label1.setText("");
                    label2.setText("");
                    end = false;
                }
            }
            switch (es)
            {
                case "AC":
                    label1.setText("");
                    label2.setText("");
                    s.replace(0,s.length(),"");
                    break;
                case"C":
                    if(s.length()!=0)
                    {
                        s.replace(s.length()-1,s.length(),"");
                    }
                    label1.setText(s.toString());
                    break;
                case "=":
                    end=true;
                    haveerror=new CalculateTool().Calculate(s);
                    s.replace(0, s.length(), "");
                    break;
                case "*":
                    s.append("*");
                    label1.setText(s.toString());
                    break;
                case"/":
                    s.append("/");
                    label1.setText(s.toString());
                    break;
                case"+":
                    s.append("+");
                    label1.setText(s.toString());
                    break;
                case"-":
                    s.append("-");
                    label1.setText(s.toString());
                    break;
                case".":
                    s.append(".");
                    label1.setText(s.toString());
                    break;
                case"(":
                    s.append("(");
                    label1.setText(s.toString());
                    break;
                case ")":
                    s.append(")");
                    label1.setText(s.toString());
                    break;
                default:
                    String ts=e.getActionCommand();
                    s.append(ts);
                    label1.setText(s.toString());
            }
        }
    }
    public void StartCalculate()        //启动计算器
    {
        p.setLayout(new GridLayout(5,4,4,4));
        for(int i=0;i<name.length;i++)
        {
            Button b=new Button(name[i]);
            b.setFont(new Font("",1,20));
            b.addActionListener(new Mylistener());
            b.addKeyListener(new Mykeylistener());
            p.add(b);
        }
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("计算器正在关闭");
                System.exit(0);
            }
        });
        p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
        p2.add(label1);
        p2.add(label2);
        f.add(p2,BorderLayout.NORTH);
        f.add(new Scrollbar());
        f.add(p);
        //f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args)
    {

        new CalculateFrame().StartCalculate();
    }

}
