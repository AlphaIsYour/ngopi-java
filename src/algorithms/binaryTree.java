package algorithms;

public class binaryTree {
    static class Node {
        int code;
        String name;
        int quantity;
        double price;
        Node left, right;
        
        public Node(int code, String name, int quantity, double price) {
            this.code = code;
            this.name = name;
            this.quantity = quantity;
            this.price = price;
            left = right = null;
        }
    }
    
    static class BinaryTree {
        Node root;
        
        BinaryTree() {
            root = null;
        }
        Node search(int code) {
            return searchRec(root, code);
        }

        Node searchRec(Node root, int code) {
            if (root == null || root.code == code) {
                return root;
            }
            return code < root.code ? searchRec(root.left, code) : searchRec(root.right, code);
        }
        
        void insert(int code, String name, int quantity, double price) {
            root = insertRec(root, code, name, quantity, price);
            System.out.println("Barang " + name + " dengan kode " + code + " berhasil ditambahkan.");
        }
        
        Node insertRec(Node root, int code, String name, int quantity, double price) {
            if (root == null) {
                return new Node(code, name, quantity, price);
            }
            if (code < root.code) {
                root.left = insertRec(root.left, code, name, quantity, price);
            } else if (code > root.code) {
                root.right = insertRec(root.right, code, name, quantity, price);
            } else {
                System.out.println("Kode barang " + code + " sudah ada. Informasi diperbarui.");
                root.name = name;
                root.quantity = quantity;
                root.price = price;
            }
            return root;
        }
        
        void inorderTraversal() {
            if (root == null) {
                System.out.println("Inventaris kosong.");
                return;
            }
            System.out.println("\n=================================");
            System.out.println("Daftar Inventaris:");
            System.out.println("-------------------------------------------");
            System.out.println("Kode\t| Nama Barang\t| Jumlah\t| Harga");
            System.out.println("-------------------------------------------");
            inorderRec(root);
            System.out.println("=================================");
        }
        
        void inorderRec(Node root) {
            if (root != null) {
                inorderRec(root.left);
                System.out.printf("%d\t| %-15s | %d\t| Rp%.2f%n", root.code, root.name, root.quantity, root.price);
                inorderRec(root.right);
            }
        }
        
        void delete(int code) {
            if (search(code) == null) {
                System.out.println("Barang dengan kode " + code + " tidak ditemukan.");
                return;
            }
            root = deleteRec(root, code);
            System.out.println("Barang dengan kode " + code + " berhasil dihapus.");
        }
        
        Node deleteRec(Node root, int code) {
            if (root == null) return root;
            if (code < root.code) {
                root.left = deleteRec(root.left, code);
            } else if (code > root.code) {
                root.right = deleteRec(root.right, code);
            } else {
                if (root.left == null) return root.right;
                if (root.right == null) return root.left;
                Node temp = findMinNode(root.right);
                root.code = temp.code;
                root.name = temp.name;
                root.quantity = temp.quantity;
                root.price = temp.price;
                root.right = deleteRec(root.right, temp.code);
            }
            return root;
        }
        
        Node findMinNode(Node node) {
            while (node.left != null) node = node.left;
            return node;
        }
    }
    
    static class InventoryManagement {
        public static void main(String[] args) {
            BinaryTree inventory = new BinaryTree();
            System.out.println("=== SISTEM INVENTARIS TOKO ===");
            inventory.insert(50, "Laptop", 10, 12000000);
            inventory.insert(30, "Keyboard", 25, 350000);
            inventory.insert(70, "Monitor", 15, 2500000);
            inventory.inorderTraversal();
            inventory.delete(30);
            inventory.inorderTraversal();
        }
    }
    
    public static void main(String[] args) {
        InventoryManagement.main(args);
    }
}

