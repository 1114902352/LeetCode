package com.algorithm.data.string;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        this.generate(res,"(",n-1,n);
        return res;
    }

    private void generate(List<String> res,String current,int left,int right){
        if(left > right){// 不允许右括号比左括号用得快的情况
            return;
        }
        if(left <= 0 && right <=0){
            res.add(current);
        }
        if(left > 0){
            generate(res,current+'(',left-1,right);
        }
        if(right > 0){
            generate(res,current+')',left,right-1);
        }
    }

    public static void main(String[] args) {
        GenerateParenthesis obj = new GenerateParenthesis();
        System.out.println(JSONObject.toJSONString(obj.generateParenthesis(3)));
    }
}
