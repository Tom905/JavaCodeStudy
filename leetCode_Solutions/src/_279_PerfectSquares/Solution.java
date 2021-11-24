package _279_PerfectSquares;

/*给定正整数n，找到若干个完全平方数（比如1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。

完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。

 

示例 1：

输入：n = 12
输出：3
解释：12 = 4 + 4 + 4



示例 2：

输入：n = 13
输出：2
解释：13 = 4 + 9

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/perfect-squares
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class Solution {
    int memo[];
    //递归+记忆化搜索
    //这个数若能由完全平方数组成，那么必然落在【1，根号n】之间
    public int numSquares(int n) {
        memo = new int[n + 1];
        for (int i = 0; i <= n; i++) memo[i] = -1;
        return tryNumSquares(n);
    }

    private int tryNumSquares(int n) {

        if (n <= 0 ) return 0;
        if (memo[n] != -1) return memo[n];
        if (isPerfectSquare(n)) {
            memo[n] = 1;
            return 1;
        }
        int ret = Integer.MAX_VALUE;
        for (int i = 1; i <Math.sqrt(n); i++) {
            ret = Math.min(ret, tryNumSquares(i*i) + tryNumSquares(n - i*i));
        }
        memo[n] = ret;

        return ret;
    }
//    动态规划
    public int numSquares2(int n){

        memo = new int[n + 1];
        for (int i = 0; i <= n; i++) memo[i] = -1;
        memo[0]=0;
        int  ret;
        for(int i = 1 ; i <=n ;i++){
            ret = Integer.MAX_VALUE;
          for(int j=1 ; i-j*j>=0 ;j++ )
              ret=Math.min(ret,memo[i-j*j]);
          memo[i]=ret+1;
        }
        for(int a :memo)
            System.out.print(a);
        System.out.println();
        return memo[n];
    }
    private boolean isPerfectSquare(int x) {
        double ret = Math.sqrt(x);
        return (ret - (int) ret) == 0;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().numSquares(4));
    }
}
