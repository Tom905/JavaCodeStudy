package _63_UniquePaths2;

/*一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？



网格中的障碍物和空位置分别用 1 和 0 来表示。

 

示例 1：


输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
输出：2
解释：
3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右
示例 2：


输入：obstacleGrid = [[0,1],[0,0]]
输出：1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/unique-paths-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class Solution {



    //动态规划 + 滚动数组
    //时间复杂度 O(n^2)
    //空间复杂度 O（n*m） ->  优化为O（m）【 m为列数】
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rowLength = obstacleGrid.length;
        int colLength = obstacleGrid[0].length;
        int memo[] = new int [colLength] ;
        //int memo[][] = new int[rowLength][colLength];
        memo[0]=obstacleGrid[0][0] == 0 ? 1: 0;
        for (int i = 0; i < rowLength; i++)
            for (int j = 0; j < colLength; j++) {
                if (obstacleGrid[i][j] == 1)
                    memo[j]=0;
                else if(j-1>=0 && obstacleGrid[i][j] ==0)
                    memo[j]+=memo[j-1];
            }
        return memo[colLength-1];

    }

    public static void main(String[] args) {
        System.out.println(new Solution().uniquePathsWithObstacles(new int[][]{{0, 1}, {0, 0}}));
    }

}
