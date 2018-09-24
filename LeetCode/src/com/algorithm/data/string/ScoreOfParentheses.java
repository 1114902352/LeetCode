package com.algorithm.data.string;

import java.util.Stack;

public class ScoreOfParentheses {

    // 0 1 2 3 4 5 6 7
    // ( ( ) ( ( ) ) )
    // 设置cnt为 未形成'()'的左括号个数
    // 找到'(', cnt++
    // 找到')'，cnt-- ,计算之前部分得分，然后继续往右走
    public static int scoreOfParentheses(String s){
        int cnt = 0; // 未形成'()'的左括号个数
        int res = 0;
        char last = ' '; // 上一个字符
        char[] sc = s.toCharArray();
        for (char ch:sc) {
            if (ch == '(') { // 出现新的左括号
                cnt++;
            }
            else {// 存在一个 ')' ，就必出现一个()
                cnt--; // 形成'()'之后，去掉一个左括号数量
                if (last == '(') {
                    res = res + 2^cnt;
                }
            }
            // 更新上一个字符
            last = ch;
        }

        return res;
    }

    int i;

    public int invoke(String S) {
        int res =0;
        char[] sc = S.toCharArray();
        for(i=0;i<sc.length;i++)
        {
            if(sc[i]=='(' && sc[i+1]==')')
                res+=1;
            else if(sc[i]=='(' && sc[i+1]=='(')
            {
                i+=1;
                int temp = dfs(sc,1);
                //cout<<temp<<endl;
                res =res + 2* temp;
            }
        }
        return res;
    }

    private int dfs(char[] sc,int left)
    {
        //cout<<"i : "<<i<<endl;
        int len =sc.length;
        int res=0;
        for(;i<len;i++)
        {
            //cout<<"i:"<<i<<" "<<S[i]<<" "<<S[i+1]<<" "<<left<<endl;
            if(sc[i]=='(' && sc[i+1]=='(')
            {
                i+=1;
                int temp = dfs(sc,1);
                //cout<<temp<<" "<<left<<" "<<i<<endl;
                res = res + 2* temp;
                continue;
            }
            else if(sc[i]=='(' && sc[i+1]==')' )
            {
                res+=1;
            }
            if(sc[i]=='(') left++;
            if(sc[i]==')') left--;
            if(left==0) return res;
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "(()(()))";
        ScoreOfParentheses obj = new ScoreOfParentheses();
        System.out.println(obj.invoke(s));
    }
}
