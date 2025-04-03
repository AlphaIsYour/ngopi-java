package algorithms;

 public class arraycuy {
    public static void main(String[] args) {
        // 1. Deklarasi Array: Pertama, deklarasikan sebuah array yang bisa menyimpan beberapa angka integer.
        int[] numbers = new int[7];

        // 2. Isi Array: Kemudian, isi array tersebut dengan beberapa angka yang kamu pilih sendiri.
        numbers[0] = 11;
        numbers[1] = 22;
        numbers[2] = 33;
        numbers[3] = 44;
        numbers[4] = 55;
        numbers[5] = 66;
        numbers[6] = 77;

        // 3. Cetak Array: Akhirnya, gunakan loop (perulangan) untuk mencetak semua angka yang ada dalam array tersebut ke konsol satu per satu.
        System.out.println("Isi array:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
    }
}
