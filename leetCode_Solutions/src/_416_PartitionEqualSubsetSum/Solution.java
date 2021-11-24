package _416_PartitionEqualSubsetSum;

/*给你一个 只包含正整数 的 非空 数组nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。


示例 1：

输入：nums = [1,5,11,5]
输出：true
解释：数组可以分割成 [1, 5, 5] 和 [11] 。
示例 2：

输入：nums = [1,2,3,5]
输出：false
解释：数组不能分割成两个元素和相等的子集。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class Solution {
    // 问题可以转化为不考虑价值的01背包问题 ,nums[i]表示i号物品的容量，背包容量为数组和/2 .注意必须将背包装满。
    //F(n,c)考虑将n个物品填满容量为C的背包
    //F(i,c)=F(i-1,C)||F(i-1,C-w[i])

//方法一 额外辅助空间

    //memo[i][j]值为-1，0，1 ,分别代表着在nums【0，i】中选择部分元素凑成j的状态：
    // -1 -》未被使用
    // 0  -》不能
    // 1  -》能
    int[][] memo;
    //方法一：递归+记忆化搜索
    //时间复杂度O（2^n）
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++)
            sum += nums[i];
        if (sum % 2 != 0) return false;//如果数组和为 奇数，那么就返回false

        int c = sum / 2;

        memo = new int[n][c + 1];

        return tryPartition(nums, nums.length, sum / 2);
    }

    //该函数表示 在nums【0,index】中能否找到一些能够装满容量为capacity的背包的元素
    private boolean tryPartition(int[] nums, int index, int capacity) {
        if (capacity == 0) return true;
        if (index < 0 || capacity < 0) return false;
        if (memo[index][capacity] != -1)
            return memo[index][capacity] == 1;

        memo[index][capacity] = (tryPartition(nums, index - 1, capacity) || tryPartition(nums, index - 1, capacity - nums[index])) ? 1 : 0;
        return memo[index][capacity] == 1;
    }


    //方法二 动态规划 自底向上
//    时间复杂度为：O（n*sum）
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++)
            sum += nums[i];
        if (sum % 2 != 0) return false;//如果数组和为 奇数，那么就返回false
        int capacity = sum / 2;
        //memo【i】表示：在nums【0-index】中能否找到元素，使之装满容量为i的背包
        //index根据当前环境判断
        boolean[] memo = new boolean[capacity + 1];
        //index = 0 ，在nums【0】中能否找到元素，使之装满容量为i的背包
        for (int i = 0; i <= capacity; i++)
            memo[i] = (nums[0] == i);
        //index从1开始,计算nums【0，index】中能否装满容量为j的背包
        for (int index = 1; index < n; index++)
            for (int j = capacity; j >= nums[index]; j--)
                memo[j] = memo[j] || memo[j - nums[index]];

        return memo[capacity];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 7};
        System.out.println(new Solution().canPartition(nums));
        System.out.println(new Solution().canPartition2(nums));
    }
}
