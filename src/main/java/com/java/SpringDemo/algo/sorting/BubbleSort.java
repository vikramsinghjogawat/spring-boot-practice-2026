package com.java.SpringDemo.algo.sorting;

public class BubbleSort {
    public static void main(String[] args) {
        //Bubble sort
        int[] nums = {92,53,24,74,46};
        int l=0;
        for (int j = 0; j < nums.length-1; j++) {
            for (int i = 0; i < nums.length -l- 1; i++) {
                System.out.println(nums[i] + "," + nums[i + 1]);
                if(nums[i]>nums[i+1]){
                    swap(i,i+1,nums);
                }
            }
            l++;
            System.out.println("----------------------------------------------------");
        }
        for(int i:nums){
            System.out.println(i);
        }
    }
    public static void swap(int i,int j,int[] arr){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
