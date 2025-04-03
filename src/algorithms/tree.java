package algorithms;

public class tree {
    static class Node {
        long phoneNumber;
        String name;
        Node left, right;
        
        public Node(long phoneNumber, String name) {
            this.phoneNumber = phoneNumber;
            this.name = name;
            left = right = null;
        }
    }
    
    static class BinarySearchTree {
        Node root;
        
        BinarySearchTree() {
            root = null;
        }
        
        void insert(long phoneNumber, String name) {
            root = insertRec(root, phoneNumber, name);
            System.out.println("Kontak " + name + " dengan nomor " + phoneNumber + " berhasil ditambahkan.");
        }
        
        Node insertRec(Node root, long phoneNumber, String name) {
            if (root == null) {
                return new Node(phoneNumber, name);
            }
            if (phoneNumber < root.phoneNumber) {
                root.left = insertRec(root.left, phoneNumber, name);
            } else if (phoneNumber > root.phoneNumber) {
                root.right = insertRec(root.right, phoneNumber, name);
            } else {
                System.out.println("Nomor telepon " + phoneNumber + " sudah ada. Nama diperbarui dari " + root.name + " menjadi " + name);
                root.name = name;
            }
            return root;
        }
        
        Node search(long phoneNumber) {
            return searchRec(root, phoneNumber);
        }
        
        Node searchRec(Node root, long phoneNumber) {
            if (root == null || root.phoneNumber == phoneNumber) {
                return root;
            }
            return phoneNumber < root.phoneNumber ? searchRec(root.left, phoneNumber) : searchRec(root.right, phoneNumber);
        }
        
        void delete(long phoneNumber) {
            if (search(phoneNumber) == null) {
                System.out.println("Kontak dengan nomor " + phoneNumber + " tidak ditemukan.");
                return;
            }
            root = deleteRec(root, phoneNumber);
            System.out.println("Kontak dengan nomor " + phoneNumber + " berhasil dihapus.");
        }
        
        Node deleteRec(Node root, long phoneNumber) {
            if (root == null) return root;
            if (phoneNumber < root.phoneNumber) {
                root.left = deleteRec(root.left, phoneNumber);
            } else if (phoneNumber > root.phoneNumber) {
                root.right = deleteRec(root.right, phoneNumber);
            } else {
                if (root.left == null) return root.right;
                if (root.right == null) return root.left;
                root.phoneNumber = minValue(root.right);
                root.right = deleteRec(root.right, root.phoneNumber);
            }
            return root;
        }
        
        long minValue(Node root) {
            while (root.left != null) root = root.left;
            return root.phoneNumber;
        }
        
        void inorder() {
            if (root == null) {
                System.out.println("Buku telepon kosong.");
                return;
            }
            System.out.println("\n=================================");
            System.out.println("Daftar Kontak (terurut berdasarkan nomor telepon):");
            System.out.println("-------------------------------------------");
            System.out.println("No. Telepon\t| Nama");
            System.out.println("-------------------------------------------");
            inorderRec(root);
            System.out.println("=================================");
        }
        
        void inorderRec(Node root) {
            if (root != null) {
                inorderRec(root.left);
                System.out.println(root.phoneNumber + "\t| " + root.name);
                inorderRec(root.right);
            }
        }
    }
    
    static class PhoneBook {
        public static void main(String[] args) {
            BinarySearchTree phoneBook = new BinarySearchTree();
            System.out.println("=== APLIKASI BUKU TELEPON ===");
            phoneBook.insert(987654321, "Alice");
            phoneBook.insert(123456789, "Bob");
            phoneBook.insert(567890123, "Charlie");
            phoneBook.insert(234567890, "Eve");
            phoneBook.inorder();
            phoneBook.delete(567890123);
            phoneBook.insert(123456789, "Bobby");
            phoneBook.inorder();
        }
    }
    
    public static void main(String[] args) {
        PhoneBook.main(args);
    }
}
