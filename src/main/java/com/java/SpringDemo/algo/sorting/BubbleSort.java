package com.java.SpringDemo.algo.sorting;

public class BubbleSort {
        public static void main(String[] args) {
            //Bubble sort
            //Idea
            // generate series 0-1,1-2,2-3,3-4//0-1,1-2,2-3//0-1,1-2//0-1

            int[] nums = {92,53,24,74,46};
            bubbleSort(nums);
            System.out.println("==Bubble Sorted Array==");
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
        public static void bubbleSort(int[] nums){
            int m=nums.length-2;
            while(m>= 0) {
                int e = 0;
                int f = 1;
                while (e <= m) {
                    //printing series of indices
                    System.out.println(e + "," + f);
                    if(nums[f]<nums[e]){
                        swap(e,f,nums);
                    }
                    e++;
                    f++;
                }
                m--;
            }
        }
}
