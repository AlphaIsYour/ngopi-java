package algorithms;
public class circularLinkedList {
    public static void main(String[] args) {
        // Menjalankan demo TaskManager
        TaskManager.demoTaskManager();
    }
}

// Kelas Node untuk menyimpan data tugas
class Node {
    String data;
    Node next;
    
    public Node(String data) {
        this.data = data;
        this.next = null;
    }
}

// Implementasi Circular Linked List
class CircularLinkedList {
    private Node head;
    private Node tail;
    
    public CircularLinkedList() {
        this.head = null;
        this.tail = null;
    }
    
    public void add(String data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
            tail.next = head;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }
    }
    
    public boolean removeTask(String taskName) {
        if (head == null) {
            System.out.println("List kosong, tidak ada tugas yang bisa dihapus.");
            return false;
        }
        
        if (head == tail && head.data.equals(taskName)) {
            head = tail = null;
            return true;
        }
        
        if (head.data.equals(taskName)) {
            head = head.next;
            tail.next = head;
            return true;
        }
        
        Node current = head;
        while (current.next != head && !current.next.data.equals(taskName)) {
            current = current.next;
        }
        
        if (current.next == head) {
            System.out.println("Tugas '" + taskName + "' tidak ditemukan.");
            return false;
        }
        
        if (current.next == tail) {
            tail = current;
        }
        
        current.next = current.next.next;
        return true;
    }
    
    public void printList() {
        System.out.println("\n===============================");
        if (head == null) {
            System.out.println("List kosong.");
            System.out.println("===============================");
            return;
        }
        
        Node current = head;
        do {
            System.out.print(current.data);
            current = current.next;
            if (current != head) {
                System.out.print(" -> ");
            }
        } while (current != head);
        
        System.out.println(" -> (kembali ke awal)");
        System.out.println("===============================");
    }
}

class TaskManager {
    public static void demoTaskManager() {
        CircularLinkedList taskList = new CircularLinkedList();
        
        taskList.add("Kerjakan PR");
        taskList.add("Beli bahan makanan");
        taskList.add("Olahraga pagi");
        taskList.add("Membaca buku");
        taskList.add("Kerjakan proyek");
        
        System.out.println("Daftar Tugas:");
        taskList.printList();
        
        System.out.println("\nMenghapus tugas 'Beli bahan makanan'...");
        taskList.removeTask("Beli bahan makanan");
        taskList.printList();
        
        System.out.println("\nMenghapus tugas 'Kerjakan PR'...");
        taskList.removeTask("Kerjakan PR");
        taskList.printList();
        
        System.out.println("\nMenghapus tugas 'Kerjakan proyek'...");
        taskList.removeTask("Kerjakan proyek");
        taskList.printList();
        
        System.out.println("\nMenambahkan tugas baru 'Bersih-bersih rumah'...");
        taskList.add("Bersih-bersih rumah");
        taskList.printList();
    }
}

