package com.suriya.learning.ds.sort;

class QuickSort {
	
	public static void main(String[] args) {
		
		long startTime = System.nanoTime();
		
		int[] array = {10, 6, 8, 2, 4, 9, 4};
		
		System.out.println("Before:");
		for(int i=0;i < array.length; i++) {
			System.out.println(array[i]);
		}
		
		
		System.out.println("After:");
		for(int i=0;i < array.length; i++) {
			System.out.println(array[i]);
		}
		
		System.out.println( System.nanoTime() - startTime );
	}
	
	public static void swap(int[] array, int iOne, int iTwo) {
			int temp = array[iTwo];
			array[iTwo] = array[iOne];
			array[iOne] = temp;
	}
	
	public static void sort(int[] array, int iLow, int iHigh) {
		
		int pivot = array[array.length - 1];
		int iPartition = iHigh;
		for(int i=0; i<iHigh-1; i++) {
			if(array[i] > pivot) {
			//	swap(array, )
			}
		}
		
		
		
		
		
	}
}