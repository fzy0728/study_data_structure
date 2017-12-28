package study;

public class QuickySort {
	public static void swap(int a,int b) {
			int temp = a;
			a = b;
			b = temp;
	}
	public static void quicklySort(int a[],int start,int end){
		if (start>end){
			return ;
		}
		int i = start;	// i 找小的
		int j = end;	//	j	找大的
		int key = a[start];
		while(i<j){
			while (a[j]>key && i<j){
				j--;
			}
			a[i] = a[j] ;
			while (a[i]<=key && i<j){
				i++;
			}
			a[j] = a[i];
		}

		a[i] = key;
		quicklySort(a,start,i-1);
		quicklySort(a,i+1,end);
	}
	public static void main(String[] args){
		int a[] = {3,2,3,6,5,65,76,6,4,23,2,5,6,65,7,3,23,234,2};
		quicklySort(a,0,a.length-1);
		System.out.println(a.toString());
	}
}
