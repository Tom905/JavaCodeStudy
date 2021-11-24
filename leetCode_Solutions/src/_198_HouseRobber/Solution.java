package _198_HouseRobber;

/*
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
示例 1：

输入：[1,2,3,1]
输出：4
解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
偷窃到的最高金额 = 1 + 3 = 4 。

示例 2：
输入：[2,7,9,3,1]
输出：12
解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/house-robber
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
public class Solution {
    int[] memo;

    private void printf() {
        for (int a : memo)
            System.out.print(a + " ");
        System.out.println();
    }

    //动态规划 考虑另一个状态memo[i]表示从[0,i]中挑选物品使之成为最大收益
    public int rob3_practice(int[] nums) {
        int n = nums.length;
        assert n == 0;

        memo = new int[n]; //memo[i]:表示[考虑]抢劫【i……n)所能获得的最大收益
        memo[0] = nums[0];
        for (int i = 1; i < n; i++)
            for (int j = i; j >= 0; j--) {
                memo[i] = Math.max(memo[i], (j - 2 >= 0 ? memo[j - 2] : 0) + nums[j]);
            }
        printf();
        return memo[n - 1];

    }

    //    动态规划 自底向上
    public int rob2(int[] nums) {
        int n = nums.length;
        memo = new int[n]; //memo[i]:表示[考虑]抢劫【i……n)所能获得的最大收益

        memo[n - 1] = nums[n - 1];//从最后一个开始向前规划

        for (int i = n - 2; i >= 0; i--)//从n-2 到 0
        {
            for (int j = i; j < n; j++)
                //【当前物品j的价值+memo[j+2]】 与 【memo[i]】 取最大值 存到 memo[i]中
                //j+2<n 表示j+2不能越界
                memo[i] = Math.max(memo[i], nums[j] + (j + 2 < n ? memo[j + 2] : 0));

        }
        printf();
        return memo[0];
    }

    //递归+记忆化搜索，自顶向下
    public int rob(int[] nums) {
        memo = new int[nums.length];//memo[i]:表示[考虑]抢劫【i……n)所能获得的最大收益
        for (int i = 0; i < nums.length; i++)
            memo[i] = -1;

        int ret = f(nums, 0);
        printf();
        return ret;

    }

    //表示从nums[x……n]中所能获得的最大值
    private int f(int[] nums, int x) {
        if (x >= nums.length)
            return 0;
        if (memo[x] != -1)
            return memo[x];
        int res = 0;
        for (int i = x; i < nums.length; i++)
            res = Math.max(nums[i] + f(nums, i + 2), res);
        memo[x] = res;
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1};

        System.out.println("rob :");
        System.out.println(new Solution().rob(nums));
        System.out.println("rob 2 :");

        System.out.println(new Solution().rob2(nums));
        System.out.println("rob 3:");

        System.out.println(new Solution().rob3_practice(nums));
    }
}
