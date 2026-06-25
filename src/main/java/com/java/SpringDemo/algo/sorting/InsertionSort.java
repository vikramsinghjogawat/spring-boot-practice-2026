package com.java.SpringDemo.algo.sorting;

public class InsertionSort {
    public static void main(String[] args) {
        //Inseation Sort
        int[] nums = {92,53,24,74,46};
        insertionSort(nums);
        System.out.println("==Insertion Sorted Array==");
        printArr(nums);
    }

    public static void insertionSort(int[] nums) {
        int e=0;
        while(e<= nums.length-1) {
            int stored = nums[e];
            //inner loop starts from one less than the current element
            int f = e - 1;
            //we shift elements down until we find its right place or reached -1
            while (f >= 0 && nums[f] > stored){
                nums[f+1]= nums[f];
                f--;
            }
            //we have stopped at right spot now copy the stored element to next place
            nums[f+1]=stored;
        e++;
        }
    }

    public static void printArr(int[] nums) {
        for(int i: nums) System.out.print(i+",");

    }

}
