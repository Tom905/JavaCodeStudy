package _120_Triangle;

import java.util.LinkedList;
import java.util.List;

/*
给定一个三角形 triangle ，找出自顶向下的最小路径和。

每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。

示例 1：

输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
输出：11
解释：如下面简图所示：
2
3 4
6 5 7
4 1 8 3
自顶向下的最小路径和为11（即，2+3+5+1= 11）。
示例 2：

输入：triangle = [[-10]]
输出：-10

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/triangle
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
public class Solution {
    /*
    *f[i][j] ==  f[i−1][0]+c[i][0],                     j=0
                 f[i−1][i−1]+c[i][i],                  j=i
                 min(f[i−1][j−1],f[i−1][j])+c[i][j],  otherwise

    * */
    // 动态规划
    public int minimumTotal(List<List<Integer>> triangle) {

        int n = triangle.size();
        int[][] memo = new int[n][n];

        memo[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {

            memo[i][0] = memo[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                memo[i][j] = Math.min(memo[i - 1][j - 1], memo[i - 1][j]) + triangle.get(i).get(j);
            }
            memo[i][i] = memo[i - 1][i - 1] + triangle.get(i).get(i);
        }
        //输出memo数组
          /*    for(int i=0 ;i<n ;i++){

            for(int j=0 ; j<n;j++)
                System.out.print(memo[i][j]+" ");
            System.out.println();
        }*/
        int min = memo[n - 1][0];
        for (int i = 1; i < n; i++)
            min = Math.min(min, memo[n - 1][i]);
        return min;
    }

    public static void main(String[] args) {
        List<Integer> list1 = new LinkedList<Integer>();
        list1.add(2);

        List<Integer> list2 = new LinkedList<Integer>();
        list2.add(3);
        list2.add(4);
        List<Integer> list3 = new LinkedList<Integer>();

        list3.add(6);
        list3.add(5);
        list3.add(7);

        List<Integer> list4 = new LinkedList<Integer>();
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);
        List<List<Integer>> triangle = new LinkedList<>();
        triangle.add(list1);
        triangle.add(list2);
        triangle.add(list3);
        triangle.add(list4);

        Solution solutin = new Solution();
        System.out.println(solutin.minimumTotal(triangle));
    }
}
