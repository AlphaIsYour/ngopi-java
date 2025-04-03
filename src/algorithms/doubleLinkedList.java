package algorithms;

public class doubleLinkedList {
    public static void main(String[] args) {
        // Membuat objek DoubleLinkedList
        DoubleLinkedList studentList = new DoubleLinkedList();
        
        // Menjalankan demo StudentList
        StudentList.demoStudentList();
    }
}

// Kelas Node untuk menyimpan data siswa
class Node {
    String data; // Data yang disimpan di node (nama siswa)
    Node next;   // Referensi ke node berikutnya
    Node prev;   // Referensi ke node sebelumnya
    
    public Node(String data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

// Implementasi Double Linked List
class DoubleLinkedList {
    private Node head; // Kepala list, mengarah ke node pertama
    private Node tail; // Ekor list, mengarah ke node terakhir
    
    // Konstruktor
    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
    }
    
    // Menambahkan node di awal list
    public void addFirst(String data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
    }
    
    // Menambahkan node di akhir list
    public void addLast(String data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }
    
    // Menghapus node berdasarkan nama siswa
    public boolean removeByName(String name) {
        if (head == null) {
            System.out.println("List kosong, tidak ada siswa yang bisa dihapus.");
            return false;
        }
        
        // Jika node yang akan dihapus adalah head
        if (head.data.equals(name)) {
            if (head == tail) { // Hanya ada satu node
                head = tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
            System.out.println("Siswa '" + name + "' berhasil dihapus dari awal list.");
            return true;
        }
        
        // Jika node yang akan dihapus adalah tail
        if (tail.data.equals(name)) {
            tail = tail.prev;
            tail.next = null;
            System.out.println("Siswa '" + name + "' berhasil dihapus dari akhir list.");
            return true;
        }
        
        // Mencari node yang akan dihapus
        Node current = head.next;
        while (current != null && !current.data.equals(name)) {
            current = current.next;
        }
        
        // Jika node ditemukan
        if (current != null) {
            if (current.next != null) {
                current.next.prev = current.prev;
            }
            current.prev.next = current.next;
            System.out.println("Siswa '" + name + "' berhasil dihapus.");
            return true;
        }

        System.out.println("Siswa '" + name + "' tidak ditemukan dalam list.");
        return false;
    }
    
    // Menampilkan semua elemen dari depan ke belakang
    public void printForward() {
        System.out.println("\n===============================");
        if (head == null) {
            System.out.println("List kosong.");
            System.out.println("===============================");
            return;
        }
        
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" <-> ");
            }
            current = current.next;
        }
        System.out.println(" -> null");
        System.out.println("===============================");
    }
    
    // Menampilkan semua elemen dari belakang ke depan
    public void printBackward() {
        System.out.println("\n===============================");
        if (tail == null) {
            System.out.println("List kosong.");
            System.out.println("===============================");
            return;
        }
        
        Node current = tail;
        while (current != null) {
            System.out.print(current.data);
            if (current.prev != null) {
                System.out.print(" <-> ");
            }
            current = current.prev;
        }
        System.out.println(" <- null");
        System.out.println("===============================");
    }
}

// Kelas untuk demo penggunaan DoubleLinkedList
class StudentList {
    public static void demoStudentList() {
        DoubleLinkedList studentList = new DoubleLinkedList();
        
        // Menambahkan beberapa siswa
        studentList.addLast("Ahmad");
        studentList.addLast("Budi");
        studentList.addLast("Chika");
        studentList.addLast("Deni");
        studentList.addLast("Eka");
        
        System.out.println("Daftar Siswa (depan ke belakang):");
        studentList.printForward();
        
        System.out.println("\nDaftar Siswa (belakang ke depan):");
        studentList.printBackward();
        
        // Menghapus siswa "Budi"
        System.out.println("\nMenghapus siswa 'Budi'...");
        studentList.removeByName("Budi");
        
        System.out.println("\nDaftar Siswa setelah penghapusan (depan ke belakang):");
        studentList.printForward();
        
        System.out.println("\nDaftar Siswa setelah penghapusan (belakang ke depan):");
        studentList.printBackward();
        
        // Menambahkan siswa baru di awal list
        System.out.println("\nMenambahkan siswa 'Zainal' di awal list...");
        studentList.addFirst("Zainal");
        
        System.out.println("\nDaftar Siswa setelah penambahan di awal (depan ke belakang):");
        studentList.printForward();
        
        // Menghapus siswa di awal list
        System.out.println("\nMenghapus siswa pertama 'Zainal'...");
        studentList.removeByName("Zainal");
        
        // Menghapus siswa di akhir list
        System.out.println("\nMenghapus siswa terakhir 'Eka'...");
        studentList.removeByName("Eka");
        
        System.out.println("\nDaftar Siswa final (depan ke belakang):");
        studentList.printForward();
    }
}

