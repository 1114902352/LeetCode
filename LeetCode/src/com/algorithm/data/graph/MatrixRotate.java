package com.algorithm.data.graph;

//  .   .   .   .   .   .
//  .   .   .   .   .   .
//  .   .   .   .   .   .
//  .   .   .   .   .   .
//  .   .   .   .   .   .
//  .   .   .   .   .   .
/**
 * 二维数组90度旋转
 * 挖坑、填坑
 * 需要旋转的点，只需要包含以下
 * 行 i in [0,n/2)
 * 列 j in [i,n-1-i)
 */
public class MatrixRotate {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = i; j < n - 1 - i; ++j) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = tmp;
            }
        }
    }
}
