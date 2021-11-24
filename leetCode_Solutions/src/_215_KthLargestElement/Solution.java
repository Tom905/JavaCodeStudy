package _215_KthLargestElement;

/*
* 题目描述：
* 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
【请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。】

* */
public class Solution {

    int findKthLargest(int[] arr, int k) {
        return kth_elem(arr, 0, arr.length - 1, arr.length-k);
    }

    private int kth_elem(int[] arr, int l, int r, int index) {
      int q=partition(arr,l,r);
      if(q==index )
          return arr[q];
      else{
          return q < index ? kth_elem(arr,q+1,r,index):
                  kth_elem(arr,l,q-1,index) ;
      }

    }

    private static void swap(int[] arr, int l, int r) {
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }

    private static int partition(int[] arr, int l, int r) {
        int p = (int) (Math.random() * (r - l + 1)) + l;

        swap(arr, p, l);

        int pivot = arr[l];

        int j = l; // arr[l+1...j] < v ; arr[j+1...i] > v
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < pivot) {
                j++;
                swap(arr, i, j);
            }
        }
        swap(arr, l, j);
        return j;
    }
}
