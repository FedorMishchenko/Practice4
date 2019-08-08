package ua.nure.mishchenko.practice4;


import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

    private static final String ENCODING = "cp1251";
    private static final String REGEX = "([А-Яа-я[єіёї]]+)";
    private static final String MASSAGE = ": Incorrect input";

    private static String latinOption(String fileName, String option) {
        File file = new File(fileName);
        StringBuilder res = new StringBuilder(option).append(": ");
        try(Scanner sc = new Scanner(file, ENCODING)) {
            while (sc.hasNext("\\w+")) {
                res.append(sc.next("\\w+")).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res.toString().replaceAll("\\s$", "");
    }

    private static String cyrlOption(String fileName, String option) {
        File file = new File(fileName);
        StringBuilder res = new StringBuilder(option).append(": ");
        StringBuilder sb = new StringBuilder();
        try(Scanner sc = new Scanner(file, ENCODING)) {
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine()).append(" ");
            }
            Matcher matcher = Pattern.compile(REGEX).matcher(sb.toString());
            while (matcher.find()) {
                res.append(matcher.group(1)).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res.toString();
    }

    public static void options(String fileName) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        while (sc.hasNextLine()) {
            String option = sc.nextLine();
            switch (option) {
                case "latn":
                case "Latn":
                    System.out.println(latinOption(fileName, option));
                    options(fileName);
                    break;
                case "cyrl":
                case "Cyrl":
                    System.out.println(cyrlOption(fileName, option));
                    options(fileName);
                    break;
                case "stop":
                case "Stop":
                    break;
                default:
                    System.out.println(option + MASSAGE);
                    options(fileName);
            }
        }
    }

    public static void main(String[] args) {
        options("part6.txt");
    }
}
