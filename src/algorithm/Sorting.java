package algorithm;

import java.util.*;

import static java.util.Arrays.binarySearch;

public class Sorting {
    public static void main(String[] args) {
//        int[] array = new int[]{2, 3, 12, 6, 32, 52, 5, 23, 74, 24, 64, 49};
//        int[] array2 = new int[]{2, 3, 3, 12, 12, 12, 6, 6, 6, 6, 32, 52, 5, 23, 74, 24, 64, 49};
//        int[] arr1 = {1,4,6};
//        int[] arr2 = {2,3,5};
//
//        int[] result = mergeSort(array);
//        for(int i : result){
//            System.out.print(i + " ");
//        }


    }

    public static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // O(n^2)
    public static void bubbleSort(int[] arr){
        if(arr.length == 0) return;

        for (int i = 0; i < arr.length; i++) {    // 外层控制有序区长度
            // 无序区在前，j 只要小于 x-1 就可以了，x是无序区长度
            for (int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j] > arr[j + 1]){
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    // 优化1：当数组已经有序
    public static void bubbleSortOpt1(int[] arr){
        if(arr.length == 0) return;

        for (int i = 0; i < arr.length; i++) {
            boolean isSorted = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j] > arr[j + 1]){
                    swap(arr, j, j + 1);
                    isSorted = false;
                }
            }
            // 如果这一轮没有发生交换，数组已经有序
            if(isSorted) return;
        }
    }

    // 优化2：数组局部有序，从某个位置之后的部分数组有序
    public static void bubbleSortOpt2(int[] arr){
        if(arr.length == 0) return;

        int lastPos = arr.length - 1;
        while (true){
            int thisTurnPos = lastPos;
            for (int j = 0; j < lastPos; j++) {  // < lastPos j小于上次交换位置就可以了
                if(arr[j] > arr[j + 1]){
                    swap(arr, j, j + 1);
                    thisTurnPos = j;     // 外层的 i 不用每次递增 1，直接定位到最后一次交换的位置
                }
            }

            if(thisTurnPos == lastPos) return;
        }
    }


    public static void selectionSort(int[] arr){
        if(arr.length == 0) return;

        for (int i = 0; i < arr.length; i++) {  // 外层控制有序区长度，i是无序区第一个元素下标
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    // 优化1：双向选择排序
    public static void selectionSortOpt1(int[] arr){
        if(arr.length == 0) return;

        int left = 0;
        int right = arr.length - 1;
        while(left < right){
            // 在一次迭代中同时记录最大值和最小值
            int minIndex = left;
            int maxIndex = right;
            for (int j = left; j <= right; j++) {
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
                if(arr[j] > arr[maxIndex]){
                    maxIndex = j;
                }
            }
            swap(arr, left, minIndex);
            // 因为先swap最小值的位置，所以得考虑最大值（arr[max]）在最小位置（left）的情况
            if(maxIndex == left){
                maxIndex = minIndex;    // left已经和minIndex交换了
            }
            swap(arr, right, maxIndex);
            left++;
            right--;
        }
    }


    public static void insertionSort(int[] arr){
        if(arr.length < 2) return;

        for (int i = 1; i < arr.length - 1; i++) {  // 外层控制有序区长度，i是无序区第一个元素下标，默认第一个元素属于有序区
            int currVal = arr[i];
            int preIndex = i - 1;   // preIndex是有序区最后一个元素下标
            while(preIndex >= 0 && arr[preIndex] > currVal){    // 只有前面的元素小于currVal才交换，这一实现确保插入排序是稳定的
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            // 注意循环退出的时候,preIndex是-1或者是第一个不大于currVal的元素
            arr[preIndex + 1] = currVal;
        }
    }

    // 优化1：在有序区寻找合适位置时使用二分搜索
    public static void binaryIndexInsertionSort(int[] arr){
        if(arr.length < 2) return;

        for (int i = 1; i < arr.length - 1; i++) {
            int currVal = arr[i];
            int preIndex = i - 1;

            int index = Searching.binarySearchIndexAfter(arr, 0, i, currVal);
            index = -index - 1; // 得到插入点

            if(index != i + 1){ // 如果插入点刚好就是currVal的位置，不必交换
                while(preIndex >= index){   // 退出循环时preIndex = index - 1
                    arr[preIndex + 1] = arr[preIndex];
                    preIndex--;
                }
            }
            arr[index] = currVal;
        }
    }

    // 优化2：希尔排序。先按步长x1分组进行插入排序，再减小步长至x2 < x1，继续分组插入排序，直至步长减小为1，即整个数组都分在同一个组中进行插入排序。
    public static void shellSort(int[] arr){
        if(arr.length < 2) return;

        int len = arr.length;
        int step = len / 2;
        if(len > 1){
            while (step >= 1){
                shellSub(arr, step);
                // 步长减半
                step = step / 2;
            }
        }
    }

    private static void shellSub(int[] arr, int step){
        if(arr.length == 0) return;

        for (int i = step; i < arr.length; i++) {  // i是无序区第一个元素    // i++则是把所有分组都排序了 如果是i+=step的话则只排序了其中一个分组
            int currVal = arr[i];
            int preIndex = i - step;
            while(preIndex >= 0 && arr[preIndex] > currVal){
                arr[preIndex + step] = arr[preIndex];
                preIndex = preIndex - step;
            }
            // 退出循环时preIndex < 0或者等于不大于currVal的最接近的下标
            arr[preIndex + step] = currVal;
        }
    }


    // O(nlogn)
    public static int[] mergeSort(int[] arr){
        if(arr.length < 2) return arr;

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    private static int[] merge(int[] left, int[] right){
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, index = 0;
        while (index < result.length && i < left.length && j < right.length){
            if(left[i] <= right[j]){
                result[index++] = left[i++];
            }else{
                result[index++] = right[j++];
            }
        }
        if(i == left.length){
            System.arraycopy(right, j, result, index, right.length - j);
        }
        if(j == right.length){
            System.arraycopy(left, i, result, index, left.length - i);
        }
        return result;
    }


}
