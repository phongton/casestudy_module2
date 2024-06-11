package common;

import repository.Repo;

import java.util.Scanner;


public class RegexHandler {
    private static final String REGEX_POINT1 = "^\\D*$";
    private static final String REGEX_POINT2 = "^\\d+$";
    private static Scanner scanner = new Scanner(System.in);
    static Repo repo = new Repo();

    public static String checkRegexName() {
        String value = scanner.nextLine();
        while (!value.matches(REGEX_POINT1)) {
            System.out.print("Sai định dạng vui lòng nhập lại : ");
            value = scanner.nextLine();
        }
        return value;
    }

    public static String checkRegexPrice() {
        String value = scanner.nextLine();
        while (!value.matches(REGEX_POINT2)) {
            System.out.print("Sai định dạng vui lòng nhập lại : ");
            value = scanner.nextLine();
        }
        return value;
    }
    public static String checkRegexId() {
        String value = scanner.nextLine();
        while (!value.matches(REGEX_POINT2)) {
            System.out.print("Sai định dạng vui lòng nhập lại : ");
            value = scanner.nextLine();
        }
        while (repo.isIdExists(value)) {
            System.out.println("Id đã tồn tại vui lòng nhập id khác :");
            value = scanner.nextLine();
        }
        return value;
    }
}
