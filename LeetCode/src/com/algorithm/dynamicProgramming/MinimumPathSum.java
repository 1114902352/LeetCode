package com.algorithm.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最小路径和
 */
public class MinimumPathSum {
    /**
     * 给定如下一个三角形二维数组，从最顶端开始往下遍历，将遍历节点值相加，求最小值
     * 注意对于节点node[i][j]，往下一层的路径只有node[i-1][j]或者node[i-1][j+1]
     *
     * 2
     * 3 4
     * 6 5 7
     * 4 1 8 3
     * 如上路径，可以
     *
     *
     * d(1,1)=arr[1,1]
     * d(2,1)=Min(d(1,1))+arr[2,1]
     * d(2,2)=Min(d(1,1))+arr[2,2]
     * d(3,1)=Min(d(2,1))+arr[3,1]
     * d(3,2)=Min{d(2,1),d(2,2)}+arr[3,2]
     * d(3,3)=Min{d(2,2)}+arr[3,3]
     * d(4,1)=Min{d(3,1)}+arr[4,1]
     * d(4,2)=Min{d(3,1),d(3,2)}+arr[4,2]
     * d(4,3)=Min{d(3,2),d(3,3)}+arr[4,3]
     * d(4,4)=Min{d(3,3)}+arr[4,4]
     * ...
     * d(i,j)=Min{d(i-1,j),d(i-1,j-1)}+arr[i,j],其中
     */
    public static int minimumTotal(List<List<Integer>> triangle){
        int[][] dp = new int[triangle.size()][triangle.get(triangle.size()-1).size()];
        for(int i=0;i<triangle.size();i++){
            for(int j=0;j<triangle.get(i).size();j++){
                dp[i][j] = triangle.get(i).get(j);
                if(i-1<0){
                    continue;
                }
                if(j-1<0){
                    dp[i][j]=dp[i-1][j]+dp[i][j];
                    continue;
                }
                if(j==i){
                    dp[i][j]=dp[i-1][j-1]+dp[i][j];
                    continue;
                }
                if(dp[i-1][j-1]<dp[i-1][j]){
                    dp[i][j]=dp[i-1][j-1]+dp[i][j];
                }else{
                    dp[i][j]=dp[i-1][j]+dp[i][j];
                }
            }
        }
        for (int[] row : dp) {
            for (int column : row) {
                System.out.print(column+"\t");
            }
            System.out.println();
        }
        int min = dp[dp.length-1][0];
        for(int i = 1;i<dp[dp.length-1].length;i++){
            if(min > dp[dp.length-1][i]){
                min = dp[dp.length-1][i];
            }
        }
        return min;
    }

    public static void testMiniNumTotal(){
        List<List<Integer>> trangle = new ArrayList<>();
        trangle.add(Arrays.asList(2));
        trangle.add(Arrays.asList(3,4));
        trangle.add(Arrays.asList(6,5,7));
        trangle.add(Arrays.asList(4,1,8,3));
        int res = MinimumPathSum.minimumTotal(trangle);
        System.out.println(res);
    }

//    Input:
//            [
//            [1,3,1],
//            [1,5,1],
//            [4,2,1]
//            ]
//    Output: 7
//    Explanation: Because the path 1→3→1→1→1 minimizes the sum.
    /**
     * 给定一个MxN的非负数组，寻找一条从左顶端到右底端的路径，使路径上的节点"和"最小
     * 且每次只能往下或者往右移动
     * dp[i][j]表示到达节点(i,j)路径和的最小值
     * 动态转移方程为
     * dp[i][j] = Min{dp[i-1][j],dp[i][j-1]}
     */
    public static int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(i == 0 && j==0){
                    dp[i][j] = grid[i][j];
                    continue;
                }
                if(i == 0){
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                    continue;
                }
                if(j == 0){
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                    continue;
                }
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }

    public static void main(String[] args) {
//        testMiniNumTotal();
    }
}
