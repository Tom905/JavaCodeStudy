import com.sun.xml.internal.bind.v2.model.core.ArrayInfo;

import java.util.Random;

//    内容四 最大子序和
public class Solution_4 {
    //内容四 用到的类
    static class ArrayInfo {
        // 对于一个区间【L ,R】:
        private int l_MaxSum;//表示以L为左端点的最大子段和
        private int r_MaxSum;//表示以R为右端点的最大子段和
        private int l_rMaxSum;  //表示[L,R]内最大的子段和（即结果）
        private int l_rSum;//表示[L,R]的总和

        public ArrayInfo(int l_MaxSum, int r_MaxSum, int l_rMaxSum, int l_rSum) {
            this.l_MaxSum = l_MaxSum;
            this.r_MaxSum = r_MaxSum;
            this.l_rMaxSum = l_rMaxSum;
            this.l_rSum = l_rSum;
        }

        public int getL_rSum() {
            return l_rSum;
        }

        public void setL_rSum(int l_rSum) {
            this.l_rSum = l_rSum;
        }


        public int getL_MaxSum() {
            return l_MaxSum;
        }

        public void setL_MaxSum(int l_MaxSum) {
            this.l_MaxSum = l_MaxSum;
        }

        public int getR_MaxSum() {
            return r_MaxSum;
        }

        public void setR_MaxSum(int r_MaxSum) {
            this.r_MaxSum = r_MaxSum;
        }

        public int getL_rMaxSum() {
            return l_rMaxSum;
        }

        public void setL_rMaxSum(int l_rMaxSum) {
            this.l_rMaxSum = l_rMaxSum;
        }
    }


    //    内容四 最大子序和
    public static int maxSubArray(int[] arr) {
        return maxSubArray(arr, 0, arr.length - 1).getL_rMaxSum();
    }

    private static ArrayInfo maxSubArray(int[] arr, int l, int r) {
        if (l == r)
            return new ArrayInfo(arr[l], arr[l], arr[l], arr[l]);
        int mid = (l + r) / 2;
        ArrayInfo LInfo = maxSubArray(arr, l, mid);
        ArrayInfo RInfo = maxSubArray(arr, mid + 1, r);
        return getL_RInfo(LInfo, RInfo);

    }

    private static ArrayInfo getL_RInfo(ArrayInfo l, ArrayInfo r) {
        int l_MaxSum = Math.max(l.getL_MaxSum(), l.getL_rSum() + r.getL_MaxSum());
        int r_MaxSum = Math.max(r.getR_MaxSum(), r.getL_rSum() + l.getR_MaxSum());
        int l_rMaxSum = Math.max(Math.max(l.getL_rMaxSum(), r.getL_rMaxSum()), l.getR_MaxSum() + r.getL_MaxSum());
        int l_rSum = l.getL_rSum() + r.getL_rSum();
        return new ArrayInfo(l_MaxSum, r_MaxSum, l_rMaxSum, l_rSum);
    }


    public static void main(String[] args) {
        int arr[] = new int[20];
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            arr[i] = random.nextInt(100)-20;
        }
        for(int i :arr)
            System.out.print(i+" ");
        System.out.println();
        System.out.println(Solution_4.maxSubArray(arr) );
    }

}
