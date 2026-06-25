package com.java.SpringDemo.algo.sorting;

public class SelectionSort {
    public static void main(String[] args) {
        //Selection sort
        //Idea
        //for first iteration -> for 0 , find the least element index from 1-9 and then swap with 0.
        //Continue for 0th, 1st .....till nums.length-2
        int[] nums = {92, 53, 24, 74, 46};
        selectionSort(nums);
        System.out.println("==Selection Sorted Array==");
        printArr(nums);

    }

    private static void selectionSort(int[] nums) {
        int e = 0;
        while(e< nums.length-2) {
            //starting from next element
            int f = e + 1;
            //assuming the first index a smallest
            int smallestIndex = f;
            //finding the index with smallest element in below loop
            while (f < nums.length) {
                //System.out.println(e + ", " + f);
                if (nums[f] < nums[smallestIndex]) {
                    smallestIndex = f;
                }
                f++;
            }
            swap(e, smallestIndex, nums);
            e++;
        }

    }
    public static void swap(int i,int j,int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
        private static void printArr(int[] nums) {
        for (int i : nums) {
            System.out.print(i + ",");
        }
    }
}
