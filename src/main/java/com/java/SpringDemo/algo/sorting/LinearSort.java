package com.java.SpringDemo.algo.sorting;

public class LinearSort {
        public static void main(String[] args) {
            //Linear Sort
            int[] nums = {92,53,24,74,46};
            linearSort(nums);
            System.out.println("==Linear Sorted Array==");
            printArr(nums);
        }

    public static void printArr(int[] nums) {
        for(int i: nums){
            System.out.print(i+",");
        }
    }

    public static void swap(int i,int j,int[] arr){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }



        public static void linearSort(int[] nums){
            int m= nums.length-2;
            int e = 0;
            while(e<=m) {
                    int f = e + 1;
                    while (f <= nums.length-1) {
                        //printing series of indices
                        System.out.println(e + "," + f);
                        if(nums[f]<nums[e]) swap(e,f,nums);
                        f++;
                    }
                    e++;
                }

        }
}
