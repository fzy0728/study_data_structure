package study;

import java.util.Arrays;
class Sort{

	private int []arr;
	public Sort() {}

	public void quicklySort() {
		quicklySort(arr,0,arr.length-1);
	}
	public void swap(int a,int b) {
		int temp = a;
		a = b;
		b = temp;
	}
	/**
	 * 快速排序
	 * @param arr
	 * @param start
	 * @param end
	 */
	public void quicklySort(int []arr,int start,int end) {
		if(start>end){
			return;
		}
		int i = start;
		int j = end;
		int key = arr[start];
		while(i!=j){
			while(arr[i]<key && i<j){
				i++;
			}
			arr[i] = arr[j];
			while(arr[j]>=key && i<j){
				j--;
			}
			arr[j] = arr[i];
		}
		arr[i] = key;
		quicklySort(arr,i+1,end);
		quicklySort(arr,start,i-1);
	}

	public int[] getArr() {
		return arr;
	}

	public void setArr(int[] arr) {
		this.arr = arr;
	}
	public void printResult(){
		System.out.println(Arrays.toString(arr));
	}
	public static void headSort(int []arr) {



	}
}

public class HeapSort {

	public static void main(String[] args) {
		int []arr = {9,8,7,6,5,4,3,2,1,0};
		Sort sort = new Sort();
		sort.setArr(arr);
		sort.printResult();
		sort.quicklySort  ();
		sort.printResult();
		System.out.println(Arrays.toString(arr));
	}
}
