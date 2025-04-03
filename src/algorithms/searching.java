package algorithms;

import java.util.Arrays;

/**
 * Implementasi Linear Search dan Binary Search untuk mencari nama dalam array
 */
public class searching {
    static class LinearSearch {
        int search(String arr[], String x) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals(x)) {
                    return i;
                }
            }
            return -1; // Elemen tidak ditemukan
        }
    }
    
    static class BinarySearch {
        int search(String arr[], int left, int right, String x) {
            if (right >= left) {
                int mid = left + (right - left) / 2;
                int comparison = arr[mid].compareTo(x);
                
                if (comparison == 0) {
                    return mid;
                }
                if (comparison > 0) {
                    return search(arr, left, mid - 1, x);
                }
                return search(arr, mid + 1, right, x);
            }
            return -1; // Elemen tidak ditemukan
        }
    }
    
    static void measureExecutionTime(Runnable searchAlgorithm, String methodName) {
        long startTime = System.nanoTime();
        searchAlgorithm.run();
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Waktu eksekusi " + methodName + ": " + elapsedTime + " nanodetik");
    }
    
    public static void main(String[] args) {
        String[] names = {"Alice", "Bob", "Charlie", "David", "Eva"};
        String searchName = "David";
        String notFoundName = "Zack";
        
        System.out.println("=== SISTEM PENCARIAN NAMA ===");
        
        LinearSearch linearSearch = new LinearSearch();
        measureExecutionTime(() -> {
            int result = linearSearch.search(names, searchName);
            if (result == -1) {
                System.out.println(searchName + " tidak ditemukan menggunakan Linear Search.");
            } else {
                System.out.println(searchName + " ditemukan pada posisi: " + result + " menggunakan Linear Search.");
            }
        }, "Linear Search");
        
        Arrays.sort(names); // Binary search membutuhkan array terurut
        BinarySearch binarySearch = new BinarySearch();
        measureExecutionTime(() -> {
            int result = binarySearch.search(names, 0, names.length - 1, searchName);
            if (result == -1) {
                System.out.println(searchName + " tidak ditemukan menggunakan Binary Search.");
            } else {
                System.out.println(searchName + " ditemukan pada posisi: " + result + " menggunakan Binary Search.");
            }
        }, "Binary Search");
        
        System.out.println("\n=================================");
        System.out.println("Pengujian dengan nama yang tidak ada dalam array:");
        
        measureExecutionTime(() -> {
            int result = linearSearch.search(names, notFoundName);
            if (result == -1) {
                System.out.println(notFoundName + " tidak ditemukan menggunakan Linear Search.");
            }
        }, "Linear Search");
        
        measureExecutionTime(() -> {
            int result = binarySearch.search(names, 0, names.length - 1, notFoundName);
            if (result == -1) {
                System.out.println(notFoundName + " tidak ditemukan menggunakan Binary Search.");
            }
        }, "Binary Search");
    }
}
