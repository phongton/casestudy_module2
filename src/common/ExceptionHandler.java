package common;

import repository.Repo;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExceptionHandler {
    private static final Scanner sc = new Scanner(System.in);
    public static Repo repo = new Repo();

    public static int checkParseInteger() {
        int value;
        while (true) {
            try {
                value = Integer.parseInt(sc.nextLine());
                if (value < 0 || value > 6) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Sai định dạng, nhập lại:");
            }
        }
        return value;
    }

}
