package com.workintech.userscanner;

import java.util.Scanner;

public class ScanManager {
    private static Scanner scanner = new Scanner(System.in);

    public static String getStringInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    public static int getIntInput(String prompt) {
        System.out.print(prompt + ": ");
        while (!scanner.hasNextInt()) {
            System.out.println("Geçerli bir tamsayı girin.");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public static void closeScanner() {
        scanner.close();
    }
}
