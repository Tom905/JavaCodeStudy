package _209_MinimumSizeSubarraySum;
/*给定一个含有n个正整数的数组和一个正整数 target 。

找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并
返回其长度。如果不存在符合条件的子数组，返回 0 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
*/
/*
输入：target = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的子数组。
*/
public class Solution {

    public int minSubArrayLen(int target, int[] nums) {
        int l = 0, r = -1;//nums[l,r]为我们的滑动窗口
        int sum = 0;
        int res = nums.length + 1;
        int count=0;
        while (l < nums.length) {
            count++;
            System.out.println("-----");
            System.out.println("l="+l+",r="+r +",sum="+sum);

            if (r + 1 < nums.length && sum < target) {
                System.out.println(sum+" < "+target);
                r++;
                sum += nums[r];
                System.out.println("处理后的l="+l+",r="+r+",sum="+sum);
            } else {
                System.out.println(sum+" >= "+target);
                sum -= nums[l];
                l++;
                System.out.println("处理后的l="+l+",r="+r+",sum="+sum);

            }

            if (sum >= target) {
                System.out.println(sum+">="+target);
                res = Math.min(res, r - l + 1);
                System.out.println("此时的l="+l+",r="+r+",sum="+sum);

            }

        }
        System.out.println(count);
        if (res == nums.length + 1)
            return 0;
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 3};
        int res = new Solution().minSubArrayLen(2, arr);
//        时间复杂度：O(2n)

        System.out.println(res);
    }
}
