package algorithms;

public class singleLinkedList {
    
        public static void main(String[] args) {
            SingleLinkedList booklist = new SingleLinkedList();
            booklist.addLast("Harry Potter");
            booklist.addLast("Lord of The Rings");
            booklist.addLast("Game of Thrones");
            booklist.addLast("Attack On Titan");
            System.out.println("Daftar buku : ");
            booklist.printList();
            
            System.out.println("Hapus Buku : ");
            booklist.removeElemen("Lord of The Rings");
            booklist.printList();
        }
        
        public static class Node {
            String data;
            Node next;
    
            public Node(String data) {
                this.data = data;
                this.next = null;
            }
        }
        
        public static class SingleLinkedList {
            private Node head;
            
            public void addLast(String data) {
                Node newNode = new Node(data);
                if (head == null) {
                    head = newNode;
                } else {
                    Node temp = head;
                    while (temp.next != null) {
                        temp = temp.next;
                    }
                    temp.next = newNode;
                }
            }
            
            // Menampilkan seluruh isi linked list
            public void printList() {
                Node temp = head;
                while (temp != null) {
                    System.out.print(temp.data + " -> ");
                    temp = temp.next;
                }
                System.out.println("null");
            }
            
            public void removeElemen(String judul){
                if ( head == null ){
                    System.out.println("Buku Kosong!");
                    return;
                }
                
                if (head.data.equals(judul)) {
                    head = head.next;
                    return;
                }
                
                Node temp = head;
                
                while ( temp.next != null && !temp.next.data.equals(judul) ){
                    temp = temp.next;
                }
                
                if ( temp.next == null ) {
                    System.out.println("Buku dengan judul = " + judul + " Tidak ditemukan!");
                    return;
                }
                
                temp.next = temp.next.next;
            }
        }
    
}
