package _343_IntegerBreak;


/*
给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。

示例 1:

输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1。
示例 2:

输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 ×3 ×4 = 36。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/integer-break
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
public class Solution {
    int[] memo; //memo【i】: 记录元素i进行分割（至少分割两部分）后得到的最大乘积

    public Solution() {
    }

    //解法一 递归求解+记忆搜索 自顶向下
    public int integerBreak(int n) {
        if (n == 1) return 1;


        memo = new int[n + 1];
        for (int i = 0; i < n + 1; i++)
            memo[i] = -1;
        memo[1] = 1;

        return tryIntegerBreak(n);

    }

    private int tryIntegerBreak(int n) {
        if (1 == n) return 1;
        int ret = -1;
        if (memo[n] != -1) return memo[n];
        for (int i = 1; i < n; i++) {
            ret = max(ret, i * (n - i), i * tryIntegerBreak(n - i));

        }
        memo[n] = ret;
        return ret;


    }

    // 解法二 动态规划 自底向上
    public int integerBreak2(int n) {
        memo = new int[n + 1];
        int ret = -1;
        for (int i = 2; i <= n; i++)
            for (int j = 1; j <= i - 1; j++)//j+(i-j)
            {
                memo[i] = max(memo[i], j * (i - j), j * memo[i - j]);
            }
     /*  for(int a:memo)
           System.out.print(a+" ");*/
        return memo[n];
    }

    private int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    public static void main(String[] args) {

        System.out.println(new Solution().integerBreak2(6));

    }
}
