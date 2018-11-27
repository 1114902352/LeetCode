package com.algorithm.data.BinaryTree;


import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Test {


    public static void main(String[] args) {
        Integer[] array = {1,2,3,4,5,6,7};
        TreeNode root = BinaryTreeUtil.createBinaryTree(array);
        BTreePrinter.printNode(root);
    }
}
