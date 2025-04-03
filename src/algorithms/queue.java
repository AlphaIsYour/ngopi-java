package algorithms;

public class queue {
    static class Queue {
        private int maxSize;
        private int[] queueArray;
        private int front;
        private int rear;
        private int nItems;

        public Queue(int size) {
            maxSize = size;
            queueArray = new int[maxSize];
            front = 0;
            rear = -1;
            nItems = 0;
        }

        public void enqueue(int value) {
            if (!isFull()) {
                if (rear == maxSize - 1) {
                    rear = -1;
                }
                queueArray[++rear] = value;
                nItems++;
                System.out.println("Pesanan " + value + " ditambahkan ke antrian");
            } else {
                System.out.println("Queue sudah penuh (maksimum " + maxSize + " pesanan), pesanan " + value + " tidak dapat ditambahkan");
            }
        }

        public int dequeue() {
            if (!isEmpty()) {
                int temp = queueArray[front++];
                if (front == maxSize) {
                    front = 0;
                }
                nItems--;
                return temp;
            } else {
                System.out.println("Queue kosong, tidak ada pesanan untuk diproses");
                return -1;
            }
        }

        public boolean isEmpty() {
            return (nItems == 0);
        }

        public boolean isFull() {
            return (nItems == maxSize);
        }

        public int peek() {
            if (!isEmpty()) {
                return queueArray[front];
            } else {
                System.out.println("Queue kosong, tidak ada pesanan untuk dilihat");
                return -1;
            }
        }

        public void displayQueue() {
            if (isEmpty()) {
                System.out.println("Queue kosong, tidak ada pesanan");
                return;
            }
            
            System.out.println("\n=================================");
            System.out.println("Daftar Pesanan dalam Antrian:");
            int count = 0;
            int i = front;
            while (count < nItems) {
                System.out.println((count + 1) + ". Pesanan ID: " + queueArray[i]);
                i++;
                if (i == maxSize) {
                    i = 0;
                }
                count++;
            }
            System.out.println("=================================");
        }
    }

    static class FastFoodOrderQueue {
        public static void main(String[] args) {
            Queue orderQueue = new Queue(5);
            
            System.out.println("=== SISTEM ANTRIAN PESANAN RESTORAN CEPAT SAJI ===");
            
            orderQueue.enqueue(101);
            orderQueue.enqueue(102);
            orderQueue.enqueue(103);
            
            System.out.println("\n=== Melihat Pesanan Pertama (Peek) ===");
            System.out.println("Pesanan pertama dalam antrian: " + orderQueue.peek());
            
            System.out.println("\n=== Memproses Pesanan ===");
            System.out.println("Pesanan pertama yang akan dilayani: " + orderQueue.dequeue());
            System.out.println("Pesanan berikutnya yang akan dilayani: " + orderQueue.dequeue());
            
            orderQueue.enqueue(104);
            orderQueue.enqueue(105);
            orderQueue.enqueue(106);
            
            System.out.println("\n=== Menampilkan Semua Pesanan ===");
            orderQueue.displayQueue();
            
            System.out.println("\n=== Memproses Semua Pesanan ===");
            while (!orderQueue.isEmpty()) {
                System.out.println("Memproses pesanan: " + orderQueue.dequeue());
            }
            
            System.out.println("\n=== Status Antrian Saat Ini ===");
            orderQueue.displayQueue();
            
            System.out.println("\n=== Mencoba Memproses Pesanan dari Antrian Kosong ===");
            orderQueue.dequeue();
        }
    }

    public static void main(String[] args) {
        FastFoodOrderQueue.main(args);
    }
}
