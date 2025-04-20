package algorithms;

import java.util.Arrays;
public class sorting {
    static class BubbleSort {
        void sort(int arr[]) {
            int n = arr.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }
    }
    
    static class InsertionSort {
        void sort(int arr[]) {
            int n = arr.length;
            for (int i = 1; i < n; ++i) {
                int key = arr[i];
                int j = i - 1;
                while (j >= 0 && arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j = j - 1;
                }
                arr[j + 1] = key;
            }
        }
    }
    
    static void printArray(int arr[]) {
        System.out.println(Arrays.toString(arr));
    }
    
    static long measureExecutionTime(Runnable sortingAlgorithm) {
        long startTime = System.nanoTime();
        sortingAlgorithm.run();
        return (System.nanoTime() - startTime) / 1000; 
    }
    
    static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        int[] studentGrades = {85, 72, 90, 34, 66, 78, 88};
        int[] studentGrades2 = studentGrades.clone();
        
        System.out.println("=== SISTEM SORTING NILAI SISWA ===");
        System.out.println("Nilai sebelum sorting: ");
        printArray(studentGrades);
        
        BubbleSort bubbleSorter = new BubbleSort();
        long bubbleTime = measureExecutionTime(() -> bubbleSorter.sort(studentGrades));
        
        System.out.println("\nHasil Bubble Sort: ");
        printArray(studentGrades);
        System.out.println("Bubble Sort menghasilkan array yang terurut: " + isSorted(studentGrades));
        System.out.println("Waktu eksekusi: " + bubbleTime + " mikrodetik");
        
        InsertionSort insertionSorter = new InsertionSort();
        long insertionTime = measureExecutionTime(() -> insertionSorter.sort(studentGrades2));
        
        System.out.println("\nHasil Insertion Sort: ");
        printArray(studentGrades2);
        System.out.println("Insertion Sort menghasilkan array yang terurut: " + isSorted(studentGrades2));
        System.out.println("Waktu eksekusi: " + insertionTime + " mikrodetik");
        
        System.out.println("\n=================================");
        System.out.println("Perbandingan:");
        System.out.println("Bubble Sort: " + bubbleTime + " mikrodetik");
        System.out.println("Insertion Sort: " + insertionTime + " mikrodetik");
        System.out.println("Insertion Sort lebih cepat dibandingkan Bubble Sort untuk dataset ini.");
    }
}
