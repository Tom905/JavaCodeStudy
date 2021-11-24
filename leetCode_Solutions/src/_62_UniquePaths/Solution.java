package _62_UniquePaths;

/*一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

问总共有多少条不同的路径？

 

示例 1：


输入：m = 3, n = 7
输出：28
示例 2：

输入：m = 3, n = 2
输出：3
解释：
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右
3. 向下 -> 向右 -> 向下
示例 3：

输入：m = 7, n = 3
输出：28
示例 4：

输入：m = 3, n = 3
输出：6

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/unique-paths
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class Solution {
    int memo[][];

    //递归 + 记忆化搜索
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0)
            return -1;
        memo = new int[m + 1][n + 1];
        for (int i = 1; i <=m; i++)
            for (int j = 1; j <=n; j++)
                memo[i][j] = -1;
        return trySearchPath(m, n);

    }

    //计算机器人到达 （x，y）不同路径条数
    private int trySearchPath(int x, int y) {
        if (x == 1 || y == 1) return 1;
        if (memo[x][y] != -1)
            return memo[x][y];
        int ret = trySearchPath(x, y - 1) + trySearchPath(x - 1, y);
        memo[x][y] = ret;
        return ret;
    }

    //动态规划
    public int uniquePaths1(int m, int n) {
        assert m <= 0 || n <= 0;
        int memo[][] = new int[m][n];
        for (int i = 0; i < n; i++)
            memo[0][i] = 1;
        for (int j = 0; j < m; j++)
            memo[j][0] = 1;

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) {
                memo[i][j] += (memo[i - 1][j] + memo[i][j - 1]);
            }
        return memo[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().uniquePaths(2, 2));
    }
}
