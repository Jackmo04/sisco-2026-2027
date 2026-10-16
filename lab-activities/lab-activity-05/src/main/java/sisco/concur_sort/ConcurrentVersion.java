package sisco.concur_sort;

import java.util.*;

public class ConcurrentVersion {

	static final int VECTOR_SIZE = 400_000_000;
	
	public static void main(String[] args) {
	
		log("Num elements to sort: " + VECTOR_SIZE);
		log("Generating array.");
		var v = genArray(VECTOR_SIZE);
		
		log("Array generated.");
		log("Sorting.");
	
		long t0 = System.nanoTime();		
		mergeSort(v, 0, v.length);
		long t1 = System.nanoTime();
		log("Done. Time elapsed: " + ((t1 - t0) / 1000000) + " ms");
		
		// dumpArray(v);
	}

	private static void merge(int arr[], int l, int m, int r){
        
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // Merge the temp arrays

        // Initial indices of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    private static void mergeSort(int arr[], int l, int r){
        
        if (l < r) {

            int m = l + (r - l) / 2;

			var a = new Thread(() -> mergeSort(arr, l, m));
			var b = new Thread(() -> mergeSort(arr, m + 1, r));
			a.start();
			b.start();
			try {
				a.join();
				b.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

            merge(arr, l, m, r);
        }
    }

	private static int[] genArray(int n) {
		Random gen = new Random(System.currentTimeMillis());
		var v = new int[n];
		for (int i = 0; i < v.length; i++) {
			v[i] = gen.nextInt();
		}
		return v;
	}

	private static void dumpArray(int[] v) {
		for (var l:  v) {
			System.out.print(l + " ");
		}
		System.out.println();
	}

	private static void log(String msg) {
		System.out.println(msg);
	}
}
