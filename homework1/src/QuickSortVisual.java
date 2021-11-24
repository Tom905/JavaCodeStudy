import java.util.Random;

public class QuickSortVisual {

    public void quickSort(int[] arr, int l, int r) {

        if (l >= r) return;
        System.out.print("arr: ["+l+","+r+"] ");
        int p = partition(arr, l, r);
        System.out.println("pivot = "+arr[p]);
        printArray(arr,arr[p]);
        System.out.println("-----------------------");
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    private int partition(int[] arr, int l, int r) {

        int p = (int) (Math.random() * (r - l + 1)) + l;
        swap(arr, p, l);
        int j = l;
        int pivot = arr[l];
        for (int i = l + 1; i <= r; i++) {
            if (pivot > arr[i]) {
                j++;
                swap(arr, i, j);
            }

        }
        swap(arr, l, j);
      // printArray(arr, pivot);
        return j;

    }

    public void printArray(int[] arr, int p) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == p)
                System.out.print(" [" + p + "] ");
            else
                System.out.print(arr[i] + " ");


        }
        System.out.println();

        return;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = new Random().nextInt(100);
        }
        QuickSortVisual quickSortVisual = new QuickSortVisual();
        quickSortVisual.printArray(arr, -9999);
        quickSortVisual.quickSort(arr, 0, arr.length - 1);

    }
}