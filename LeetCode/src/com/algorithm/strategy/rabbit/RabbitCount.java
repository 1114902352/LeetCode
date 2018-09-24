package com.algorithm.strategy.rabbit;

/**
 * 有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
 */
public class RabbitCount {

    /**
     * (前月)兔子总对数 = (前月)成年兔子对数+(前月)幼年兔子对数
     * 成年兔子对数 = 前月成年兔子对数+前月幼年兔子对数 = 前月总对数
     * 幼年兔子对数 = 前月成年兔子对数 = 大前月兔子总对数（由上两条公式合并得到）
     * 兔子总对数 = 前月兔子总对数+大前月兔子总对数
     * 符合斐波那契规律
     * 1        2       3      4       5       6       7       8       9 ...
     * 1        1       2      3       5       8       13      21      34 ...
     * 1+0      1+0     1+1    2+1     3+2     5+3     8+5     13+8    21+13...
     *
     * @param n 第几月
     * @return 该月兔子数量
     */
    public static long invoke(int n){
        if(n == 1){
            return 1;
        }
        long ret = 0;
        long x = 0;// 大前月
        long y = 1;// 前月
        for(int i=2;i<=n;i++){
            ret = x + y;
            x = y;
            y = ret;
        }
        return ret;
    }

    public static void main(String[] args) {
        for(int i = 1;i<=100;i++){
            System.out.println(RabbitCount.invoke(i));
        }
    }
}
