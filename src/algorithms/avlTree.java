package algorithms;

public class avlTree {
    static class Student {
        int nim;
        String name;
        
        Student(int nim, String name) {
            this.nim = nim;
            this.name = name;
        }
        
        @Override
        public String toString() {
            return "NIM: " + nim + ", Nama: " + name;
        }
    }
    
    static class AVLNode {
        Student student;
        int height;
        AVLNode left, right;
        
        AVLNode(Student student) {
            this.student = student;
            this.height = 1;
        }
    }
    
    static class AVLTree {
        AVLNode root;
        
        int height(AVLNode N) {
            return (N == null) ? 0 : N.height;
        }
        
        int getBalance(AVLNode N) {
            return (N == null) ? 0 : height(N.left) - height(N.right);
        }
        
        AVLNode rightRotate(AVLNode y) {
            AVLNode x = y.left;
            y.left = x.right;
            x.right = y;
            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;
            return x;
        }
        
        AVLNode leftRotate(AVLNode x) {
            AVLNode y = x.right;
            x.right = y.left;
            y.left = x;
            x.height = Math.max(height(x.left), height(x.right)) + 1;
            y.height = Math.max(height(y.left), height(y.right)) + 1;
            return y;
        }
        
        AVLNode insert(AVLNode node, Student student) {
            if (node == null)
                return new AVLNode(student);
            if (student.nim < node.student.nim)
                node.left = insert(node.left, student);
            else if (student.nim > node.student.nim)
                node.right = insert(node.right, student);
            else
                return node;
            
            node.height = 1 + Math.max(height(node.left), height(node.right));
            int balance = getBalance(node);
            
            if (balance > 1 && student.nim < node.left.student.nim)
                return rightRotate(node);
            if (balance < -1 && student.nim > node.right.student.nim)
                return leftRotate(node);
            if (balance > 1 && student.nim > node.left.student.nim) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            if (balance < -1 && student.nim < node.right.student.nim) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
            return node;
        }
        
        Student search(AVLNode node, int nim) {
            if (node == null) return null;
            if (nim == node.student.nim) return node.student;
            return (nim < node.student.nim) ? search(node.left, nim) : search(node.right, nim);
        }
        
        void inorderTraversal(AVLNode node) {
            if (node != null) {
                inorderTraversal(node.left);
                System.out.println(node.student);
                inorderTraversal(node.right);
            }
        }
        
        void addStudent(int nim, String name) {
            Student student = new Student(nim, name);
            root = insert(root, student);
        }
        
        Student findStudent(int nim) {
            return search(root, nim);
        }
        
        void displayAllStudents() {
            System.out.println("\n=================================");
            if (root == null) {
                System.out.println("Tidak ada data mahasiswa dalam sistem.");
                System.out.println("=================================");
                return;
            }
            System.out.println("Daftar Mahasiswa (Terurut berdasarkan NIM):");
            inorderTraversal(root);
            System.out.println("=================================");
        }
    }
    
    public static void main(String[] args) {
        AVLTree studentDatabase = new AVLTree();
        
        System.out.println("=== SISTEM DATABASE MAHASISWA ===");
        studentDatabase.addStudent(20210001, "Ahmad Fadli");
        studentDatabase.addStudent(20210004, "Budi Santoso");
        studentDatabase.addStudent(20210015, "Citra Dewi");
        studentDatabase.addStudent(20210010, "Dian Purnama");
        studentDatabase.addStudent(20210008, "Eko Prasetyo");
        studentDatabase.displayAllStudents();
        
        int searchNim = 20210010;
        Student foundStudent = studentDatabase.findStudent(searchNim);
        if (foundStudent != null) {
            System.out.println("Mahasiswa dengan NIM " + searchNim + " ditemukan:");
            System.out.println(foundStudent);
        } else {
            System.out.println("Mahasiswa dengan NIM " + searchNim + " tidak ditemukan.");
        }
    }
}
