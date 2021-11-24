package _322_CoinChange;

import java.util.Arrays;

/*给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。

计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。

你可以认为每种硬币的数量是无限的。

 

示例 1：

输入：coins = [1, 2, 5], amount = 11
输出：3
解释：11 = 5 + 5 + 1
示例 2：

输入：coins = [2], amount = 3
输出：-1
示例 3：

输入：coins = [1], amount = 0
输出：0
示例 4：

输入：coins = [1], amount = 1
输出：1
示例 5：

输入：coins = [1], amount = 2
输出：2

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/coin-change
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class Solution {

    int[] memo;

    //递归+记忆化搜索
    public int coinChange(int[] coins, int amount) {
        memo = new int[amount + 1];
        for (int i = 0; i < memo.length; i++)
            memo[i] = -99;

        return tryCoinChange(coins, amount);


    }

    /**/
    private int tryCoinChange(int[] coins, int currAmount) {
        if (currAmount == 0)
            return 0;
        if (currAmount < 0)
            return -1;

        if (memo[currAmount] != -99) return memo[currAmount];

        int ret = Integer.MAX_VALUE;

        for (int i = 0; i < coins.length; i++) {
            int temp = tryCoinChange(coins, currAmount - coins[i]);
            if (temp >= 0 && temp < ret) {
                ret = temp + 1;
            }
        }
        memo[currAmount] = (ret == Integer.MAX_VALUE) ? -1 : ret;
        return memo[currAmount];
    }


    //动态规划
    /*注意： 为数组赋初值 amount+1 作为是否任何一种硬币组合能组成总金额 的标志：
     * 若不能组成，则memo【amount】一直为amount+1 ，所以返回-1 ；
     * 若能组成，则返回memo【amount】的值 。
     *【判断金额凑不出的小技巧：先初始化DP table各个元素为amount + 1（代表不可能存在的情况），在遍历时如果金额凑不出则不更新，于是若最后结果仍然是amount + 1，则表示金额凑不出】
     * */
    public int coinChange2(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, amount + 1);
        memo[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++)

                if (coins[j] <= i)
                    memo[i] = Math.min(memo[i], memo[i - coins[j]] + 1);
        }

        return memo[amount] > amount ? -1 : memo[amount];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().coinChange2(new int[]{186, 419, 83, 408}, 6249));
    }
}
