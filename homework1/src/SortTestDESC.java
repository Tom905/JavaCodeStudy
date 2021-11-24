import java.util.Arrays;
import java.util.Random;

public class SortTestDESC {

    //堆排序算法
    private static void heapSort(int[] arr) {
        //建堆
        for (int i = (arr.length - 2) / 2; i >= 0; i--)
            shiftDown(arr, arr.length, i);

        //堆排序
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, i, 0);
            shiftDown(arr, i, 0);
        }
    }

    //下 沉 操 作
    /*  n:数组当前需要调整的元素个数
        k：要调整的元素下标
     * 思路：比较（k，leftChild（k），rightChild（k））三者的大小 ，
     * 如果：1）k结点值最大，则停止操作
     *      2）如果leftChild（k）或rightChild（k）最大，与k结点交换
     * k = 【leftChild（k）或rightChild（k）】对应的最大值
     * 循环终止条件 rightChild（k) >= n
     * */
    private static void shiftDown(int[] arr, int n, int k) {
        while (2 * k + 1 < n) {
            int j = 2 * k + 1; //左孩子
            if (j + 1 < n && arr[j + 1] < arr[j])
                j++;
            if (arr[k] <= arr[j]) break;
            swap(arr, k, j);
            k = j;
        }
    }

    //插入排序算法
    private static void insertSort1(int[] arr) {
        int i;
        for (int j = 1; j < arr.length; j++) {
            int key = arr[j];
            for (i = j - 1; i >= 0; i--) {
                if (arr[i] < key)
                    arr[i + 1] = arr[i];
                else break;
            }
            arr[i + 1] = key;
        }
    }

    //合并排序算法
    private static void mergeSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] aux = Arrays.copyOfRange(arr, l, r + 1);
        //初始化，i指向左半部分的起始索引位置l ，j指向右半部分起始索引位置mid+1
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) { //如果左半部分元素已经全部处理完毕
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {//如果右半部分元素已经全部处理完毕
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l] > aux[j - l]) {//左半部分所指元素 >右半部分所指元素
                arr[k] = aux[i - l];
                i++;
            } else { //左半部分所指元素 <= 右半部分所指元素
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    //快速排序算法
    private static void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int p = partition(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);

    }

    //    单路快速排序  数组中数据相同时 时间复杂度O(n^2)
    private static int partition(int[] arr, int l, int r) {
        int p = (int) (Math.random() * (r - l + 1)) + l;

        swap(arr, p, l);

        int pivot = arr[l];

        int j = l; // arr[l+1...j] < v ; arr[j+1...i] > v
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] > pivot) {
                j++;
                swap(arr, i, j);
            }
        }
        swap(arr, l, j);
        return j;
    }

    //    三路快速排序 针对数组中数据相等时的时间复杂度为O（n）级别
    private static void quickSort3Ways(int[] arr, int l, int r) {
        if (l >= r) return;
        int p = (int) (Math.random() * (r - l + 1)) + l;
        swap(arr, p, l);

        int pivot = arr[l];
        int lt = l; //arr[l+1 , lt] < v

        int i = l + 1;//arr[lt+1...i) == v

        int gt = r + 1;//arr[gt....r] > v

        while (i < gt) {
            if (arr[i] > pivot) {
                swap(arr, i, lt + 1);
                i++;
                lt++;
            } else if (arr[i] <pivot) {
                swap(arr, i, gt - 1);
                gt--;
            } else { //arr[i] == v
                i++;
            }
        }
        swap(arr, l, lt);
        quickSort3Ways(arr, l, lt - 1);
        quickSort3Ways(arr, gt, r);
    }

    private static void swap(int[] arr, int l, int r) {
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }

    public static void testInsertSort(int[] arr) {

        //测试插入排序算法 时间性能
        double startTime = System.nanoTime();
        insertSort1(arr);
        double endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("The time of InsertSort is :" + time + " s");

    }

    public static void testMergeSort(int[] arr) {

        //测试归并排序算法 时间性能
        double startTime = System.nanoTime();
        mergeSort(arr, 0, arr.length - 1);
        double endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("The time of MergeSort is :" + time + " s");

    }

    public static void testQuickSort(int[] arr) {

        //测试快速排序算法 时间性能
        double startTime = System.nanoTime();
        quickSort(arr, 0, arr.length - 1);
        double endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("The time of QuickSort is :" + time + " s");

    }

    public static void testHeapSort(int[] arr) {
        //测试堆排序算法 时间性能
        double startTime = System.nanoTime();
        heapSort(arr);
        double endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("The time of HeapSort is :" + time + " s");
    }
    public static void testQuickSort3Ways(int[] arr) {
        //测试三路快速排序算法 时间性能
        double startTime = System.nanoTime();
        quickSort3Ways(arr,0,arr.length-1);
        double endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("The time of QuickSort3Ways is :" + time + " s");
    }
    // 打印arr数组的所有内容
    public static void printArray(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();

        return;
    }


    public static void main(String[] args) {

        int[] arr1, arr2, arr3, arr4,arr5;
        int n = 20;
        arr1 = new int[n];
        arr2 = new int[n];
        arr4 = new int[n];
        arr5 = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr1[i] = random.nextInt(100);
        }
        arr2 = Arrays.copyOfRange(arr1, 0, n);
        arr3 = Arrays.copyOfRange(arr1, 0, n);
        arr4 = Arrays.copyOfRange(arr1, 0, n);
        arr5 = Arrays.copyOfRange(arr1, 0, n);

        SortTestDESC.testQuickSort3Ways(arr1);
        SortTestDESC.testMergeSort(arr2);
        SortTestDESC.testHeapSort(arr3);
        SortTestDESC.testInsertSort(arr4);
        SortTestDESC.testQuickSort3Ways(arr5);

        SortTestDESC.printArray(arr1);
        SortTestDESC.printArray(arr2);
        SortTestDESC.printArray(arr3);
        SortTestDESC.printArray(arr4);
        SortTestDESC.printArray(arr5);
//        SortTest.printArray(arr1);

    }
}
