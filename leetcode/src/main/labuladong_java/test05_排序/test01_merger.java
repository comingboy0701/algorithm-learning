package test05_排序;

public class test01_merger {

    static public void mergeSort(int[] arr) {
        int[] tmp = new int[arr.length];
        Sort(arr, 0, arr.length - 1, tmp);

    }

    public static void Sort(int[] arr, int left, int right, int[] tmp) {
        if (left < right) {
            int mid = (left + right) / 2;
            // 左边递归
            Sort(arr, left, mid, tmp);
            //右边递归
            Sort(arr, mid + 1, right, tmp);
            merge(arr, left, mid, right, tmp);
        }

    }

    public static void merge(int[] arr, int left, int mid, int right, int[] tmp) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                tmp[t] = arr[i];
                i++;
                t++;
            } else {
                tmp[t] = arr[j];
                j++;
                t++;
            }
        }
        while (i <= mid) {
            tmp[t] = arr[i];
            i++;
            t++;
        }
        while (j <= right) {
            tmp[t] = arr[j];
            j++;
            t++;
        }
        t = 0;
        int Left = left;
        while (Left <= right) {
            arr[Left] = tmp[t];
            t++;
            Left++;
        }
    }

}
