package algorithms;

import java.util.*;
public class hashing {
    static class HashTable {
        private ArrayList<LinkedList<Integer>> bucketArray;
        private int numBuckets;
        private int size;

        public HashTable() {
            bucketArray = new ArrayList<>();
            numBuckets = 10; // Inisial kapasitas
            size = 0;
            for (int i = 0; i < numBuckets; i++) {
                bucketArray.add(new LinkedList<>());
            }
        }

        private int getBucketIndex(Integer key) {
            int hashCode = key.hashCode();
            int index = hashCode % numBuckets;
            return index < 0 ? index * -1 : index;
        }

        public void add(Integer key) {
            int bucketIndex = getBucketIndex(key);
            LinkedList<Integer> chain = bucketArray.get(bucketIndex);
            if (!chain.contains(key)) {
                chain.add(key);
                size++;
            }
        }

        public boolean remove(Integer key) {
            int bucketIndex = getBucketIndex(key);
            LinkedList<Integer> chain = bucketArray.get(bucketIndex);
            boolean result = chain.remove(key);
            if (result) size--;
            return result;
        }

        public boolean contains(Integer key) {
            int bucketIndex = getBucketIndex(key);
            LinkedList<Integer> chain = bucketArray.get(bucketIndex);
            return chain.contains(key);
        }

        public int size() {
            return size;
        }

        public void printAllItems() {
            System.out.println("Isi Inventaris:");
            for (int i = 0; i < numBuckets; i++) {
                if (!bucketArray.get(i).isEmpty()) {
                    System.out.println("Bucket " + i + ": " + bucketArray.get(i));
                }
            }
        }
    }

    static class InventoryManagement {
        public static void main(String[] args) {
            HashTable inventory = new HashTable();
            
            System.out.println("=== SISTEM INVENTARIS TOKO ===");
            System.out.println("Menambahkan item...");
            inventory.add(1001);
            inventory.add(1002);
            inventory.add(1003);
            
            System.out.println("\n=================================");
            System.out.println("Apakah ID 1002 tersedia? " + inventory.contains(1002));
            inventory.remove(1002);
            System.out.println("Apakah ID 1002 masih tersedia? " + inventory.contains(1002));
            
            System.out.println("Jumlah item dalam inventaris: " + inventory.size());
            
            System.out.println("\nMenampilkan semua item dalam HashTable:");
            inventory.printAllItems();
            
            System.out.println("\n=================================");
            System.out.println("Pengujian dengan ID yang tidak ada:");
            System.out.println("Apakah ID 9999 tersedia? " + inventory.contains(9999));
        }
    }
}



