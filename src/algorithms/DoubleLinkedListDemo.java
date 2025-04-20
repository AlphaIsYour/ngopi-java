package algorithms;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        StudentList.demoStudentList();
    }
}

class Node {
    String data; 
    Node next; 
    Node prev;  
    
    public Node(String data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class DoubleLinkedList {
    private Node head; 
    private Node tail; 
    
    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
    }
    
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
    
    public boolean removeByName(String name) {
        if (head == null) {
            System.out.println("List kosong, tidak ada siswa yang bisa dihapus.");
            return false;
        }
        
        if (head.data.equals(name)) {
            if (head == tail) { 
                head = tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
            System.out.println("Siswa '" + name + "' berhasil dihapus dari awal list.");
            return true;
        }
        
        if (tail.data.equals(name)) {
            tail = tail.prev;
            tail.next = null;
            System.out.println("Siswa '" + name + "' berhasil dihapus dari akhir list.");
            return true;
        }
        
        Node current = head.next;
        while (current != null && !current.data.equals(name)) {
            current = current.next;
        }
        
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

class StudentList {
    public static void demoStudentList() {
        DoubleLinkedList studentList = new DoubleLinkedList();
        
        studentList.addLast("Ahmad");
        studentList.addLast("Budi");
        studentList.addLast("Chika");
        studentList.addLast("Deni");
        studentList.addLast("Eka");
        
        System.out.println("Daftar Siswa (depan ke belakang):");
        studentList.printForward();
        
        System.out.println("\nDaftar Siswa (belakang ke depan):");
        studentList.printBackward();
        
        System.out.println("\nMenghapus siswa 'Budi'...");
        studentList.removeByName("Budi");
        
        System.out.println("\nDaftar Siswa setelah penghapusan (depan ke belakang):");
        studentList.printForward();
        
        System.out.println("\nDaftar Siswa setelah penghapusan (belakang ke depan):");
        studentList.printBackward();
        
        System.out.println("\nMenambahkan siswa 'Zainal' di awal list...");
        studentList.addFirst("Zainal");
        
        System.out.println("\nDaftar Siswa setelah penambahan di awal (depan ke belakang):");
        studentList.printForward();
        
        System.out.println("\nMenghapus siswa pertama 'Zainal'...");
        studentList.removeByName("Zainal");
        
        System.out.println("\nMenghapus siswa terakhir 'Eka'...");
        studentList.removeByName("Eka");
        
        System.out.println("\nDaftar Siswa final (depan ke belakang):");
        studentList.printForward();
    }
}