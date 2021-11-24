import java.util.*;
// 内容五 判断二叉树是否为BST
public class Solution_5 {
    int v[], a[], b[];
    Solution_5(int N) {
        v = new int[N];
        a = new int[N];
        b = new int[N];
        flag=true;
        min=Integer.MIN_VALUE ;
    }
    public void set(int rootIndex,int rootVaule,int leftChildIndex,int rightChildIndex){
        this.v[rootIndex]=rootVaule;
        this.a[rootIndex]=leftChildIndex;
        this.b[rootIndex]=rightChildIndex;

    }
    int min;
    boolean flag;

// 使用递归，中序遍历BST的特点是，遍历的元素是依次递增的
//    如果当前结点的值比前一个结点的值大，则不是BST
    public void isBST(int rootIndex) {

        if (a[rootIndex] != 0)
             isBST(a[rootIndex]);
        if (min > v[rootIndex])
            flag = false ;
        min = v[rootIndex];

        if (b[rootIndex] != 0)
             isBST(b[rootIndex]);

    }


    //测试方法
    public static void main(String[] args) {


        Scanner sc= new Scanner(System.in);
        int T ;
        int m,r;
        int v,a,b;
        T=sc.nextInt();

        while(T >0){
            m=sc.nextInt();
            r= sc.nextInt();
            System.out.println("m="+m+" r="+r);
            Solution_5 solution = new Solution_5(m+1);
            for(int i= 1 ;i<=m ;i++)
            {
                v= sc.nextInt();
                a=sc.nextInt();
                b=sc.nextInt();
                System.out.println(" v="+v+" a="+a+" b="+b);
                solution.set(i,v,a,b);


            }
            solution.isBST(r);
            if(solution.flag )
                System.out.println("yes");
            else
                System.out.println("no");
            T--;
        }

    }
}

/*
输入用例
1
6 1
20 2 3
15 4 5
25 6 0
13 0 0
17 0 0
27 0 0

*/
