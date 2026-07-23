import java.util.Scanner;

public class Bai3_SapXepMang {
    // Sap xep noi bot (Bubble Sort) tang dan
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j]     = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void inMang(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so phan tu cua mang: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("Nhap " + n + " phan tu:");
        for (int i = 0; i < n; i++) {
            System.out.print("  arr[" + i + "] = ");
            arr[i] = sc.nextInt();
        }

        System.out.print("Mang truoc khi sap xep: ");
        inMang(arr);

        bubbleSort(arr);

        System.out.print("Mang sau khi sap xep  : ");
        inMang(arr);

        sc.close();
    }
}
