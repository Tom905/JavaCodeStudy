import _198_HouseRobber.Solution;

import java.util.Scanner;

/*
 * 0-1 背包问题
 **/
public class Knapsack_01 {
    int[][] memo; //memo[i][j]记录从编号为【0……i】的商品中挑选，使之成为最大值（背包容量为j)

    //方法一：递归 + 记忆搜索
    public int bestValue(int[] weight, int[] value, int capacity) {
        int rowLength = weight.length;
        int colLength = capacity + 1;
        //初始化 memo 数组
        memo = new int[rowLength][colLength];
        for (int i = 0; i < rowLength; i++)
            for (int j = 0; j < colLength; j++)
                memo[i][j] = -1;

        int ret = bestValue(weight, value, rowLength - 1, capacity);
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++)
                System.out.print(memo[i][j] + " ");
            System.out.println();
        }
        return ret;
    }

    //该方法计算从商品列表【0……index】中挑选部分商品，使之成为最大值（背包容量为capacity）
    private int bestValue(int[] weight, int[] value, int index, int capacity) {
        if (capacity <= 0 || index < 0) return 0;
        if (memo[index][capacity] != -1)
            return memo[index][capacity];
        int res = bestValue(weight, value, index - 1, capacity);
        if (capacity >= weight[index])
            res = Math.max(res, value[index] + bestValue(weight, value, index - 1, capacity - weight[index]));
        memo[index][capacity] = res;
        return res;
    }

    //方法二：动态规划
    /*时间复杂度：O(n*C)
    * 空间复杂度：O(n*C) --> 可优化为O(2*C) -> 可优化为O(C)
    * */
    public int bestValue2(int[] weight, int[] value, int capacity) {

        int n = weight.length;
        memo = new int[n][capacity + 1];  //O(n*C)
       // memo = new int[2][capacity+1];  O(2*C)
        //memo = new int[capacity+1] ;    O(C)
        for (int i = 0; i < n; i++)
            for (int j = 0; j < capacity + 1; j++)
                memo[i][j] = -1;
        for (int i=0 ; i <= capacity ;i++)
                memo[0][i] = (i>weight[0]?value[0]:0);
              //memo[i]=  (i>weight[0]?value[0]:0);
            for (int i = 1; i < n; i++)
                for (int j = 0; j <= capacity; j++) {

                    memo[i][j] =  memo[i - 1][j];
                    //  memo[i%2][j] =  memo[(i - 1)%2][j];
                    if (j >= weight[i]) {
                        memo[i][j] = Math.max(value[i] + memo[i - 1][j - weight[i]], memo[i][j]);
//                        memo[i%2][j] = Math.max(value[i] + memo[(i-1)%2][j - weight[i]], memo[i%2][j]);
                    }
                }
            /*for(int i=0 ; i<n;i++)
            *   for(int j = capacity ; j>=w[i];j--)
            *   {
            *       memo[j]=Math.max(memo[j],v[i] + memo[j-w[i]]);
            * }*/
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < capacity+1; j++)
                System.out.print(memo[i][j] + " ");
            System.out.println();
        }
        return memo[n - 1][capacity];       //O(n*C)
        //return memo[(n - 1)%2][capacity];   O(2*C)
        //return memo[capacity];              O(C)
    }


    public static void main(String[] args) {
        int weight[] = new int[3];
        int value[] = new int[3];
        int capacity = 5;
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 3; i++)
            weight[i] = in.nextInt();
        for (int i = 0; i < 3; i++)
            value[i] = in.nextInt();
        System.out.println("递归+记忆化搜索 自顶向下 memo[][]:");
        System.out.println(new Knapsack_01().bestValue(weight, value, 5));
        System.out.println("动态规划 自底向上 memo[][]:");
        System.out.println(new Knapsack_01().bestValue2(weight,value,5));

    }
}
/*
1 2 3
6 10 12
* */