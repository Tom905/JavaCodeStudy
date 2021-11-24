package _64_MinimumPathSum;

import java.util.Scanner;

/*
给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，
使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。


输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
输出：7
解释：因为路径 1→3→1→1→1 的总和最小。
示例 2：

输入：grid = [[1,2,3],[4,5,6]]
输出：12

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-path-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

*/
public class Solution {

    public int minPathSum(int[][] grid) {
        assert grid.length == 0 || grid[0].length == 0 || grid == null;
        int rowLength = grid.length;
        int colLength = grid[0].length;

        int[][] memo = new int[rowLength][colLength];
        memo[0][0] = grid[0][0];
        for (int i = 1; i < colLength; i++)

            memo[0][i] = grid[0][i] + memo[0][i - 1];

        for (int i = 1; i < rowLength; i++)
            memo[i][0] = grid[i][0] + memo[i - 1][0];
        for (int i = 1; i < rowLength; i++)
            for (int j = 1; j < colLength; j++) {
                memo[i][j] = Math.min(memo[i - 1][j], memo[i][j - 1]) + grid[i][j];
            }
      /*  for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++)
                System.out.print(memo[i][j] + " ");
            System.out.println();
        }*/
        return memo[rowLength - 1][colLength - 1];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = in.nextInt();
        Solution solution = new Solution();
        System.out.println(solution.minPathSum(grid));
    }
}
/*
4 4
1 3 4 8
3 2 2 4
5 7 1 9
2 3 2 3


*/