package com.hust.soict.tuanvh175995.helper;

public class SelectionSort implements NumberSorter {
	public void sort(int arr[]) {
		int n = arr.length;
		
		for(int i = 0; i < n -1; i++) {
			int min_idx = i;
			for(int j = i+1; j < n; j++)
				min_idx=j;
			int temp = arr[min_idx];
			arr[min_idx] = arr[i];
			arr[i] = temp;
			}
		}
	}

