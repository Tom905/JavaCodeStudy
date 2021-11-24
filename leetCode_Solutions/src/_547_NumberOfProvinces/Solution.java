package _547_NumberOfProvinces;

import java.util.TreeSet;
/*有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。

省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。

给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。

返回矩阵中 省份 的数量。



示例 1：
输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
输出：2


示例 2：
输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
输出：3

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/number-of-provinces
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class Solution {
    private interface UF {
        int getSize();
        boolean isConnected(int p, int q);
        void unionElements(int p, int q);
    }

    // 我们的第一版Union-Find
    private class UnionFind1 implements UF {

        private int[] id;    // 我们的第一版Union-Find本质就是一个数组

        public UnionFind1(int size) {

            id = new int[size];

            // 初始化, 每一个id[i]指向自己, 没有合并的元素
            for (int i = 0; i < size; i++)
                id[i] = i;
        }

        @Override
        public int getSize(){
            return id.length;
        }

        // 查找元素p所对应的集合编号
        // O(1)复杂度
        private int find(int p) {
            if(p < 0 || p >= id.length)
                throw new IllegalArgumentException("p is out of bound.");

            return id[p];
        }

        // 查看元素p和元素q是否所属一个集合
        // O(1)复杂度
        @Override
        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        // 合并元素p和元素q所属的集合
        // O(n) 复杂度
        @Override
        public void unionElements(int p, int q) {

            int pID = find(p);
            int qID = find(q);

            if (pID == qID)
                return;

            // 合并过程需要遍历一遍所有元素, 将两个元素的所属集合编号合并
            for (int i = 0; i < id.length; i++)
                if (id[i] == pID)
                    id[i] = qID;
        }
    }
    public int findCircleNum(int[][] M){
        int n = M.length;
        UnionFind1 uf=new UnionFind1(n);
        for(int i = 0 ; i < n ; i ++)
            for(int j = 0 ; j < i ; j ++){
                if(M[i][j]==1)
                    uf.unionElements(i,j);
            }

        TreeSet<Integer> set = new TreeSet<>();
            for(int i=0;i<n ;i++)
                set.add(uf.find(i));
            return set.size() ;
    }
}
