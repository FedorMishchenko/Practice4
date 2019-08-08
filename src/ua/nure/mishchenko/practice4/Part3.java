package ua.nure.mishchenko.practice4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    private static final String ENCODING = "cp1251";
    private static final String PATH = "part3.txt";
    private static final String INT = "int";
    private static final String CHAR = "char";
    private static final String DOUBLE = "double";
    private static final String STRING = "String";
    private static final String QUIT = "stop";
    private static final String REGEX_STRING = "(\\p{L}{2,})";
    private static final String REGEX_CHAR = "((?U)(?<![\\w\\d])([\\w])(?=\\s))";
    private static final String REGEX_INT = "\\s([\\d]+)\\s";
    private static final String REGEX_DOUBLE = "(([-+]?[0-9]*\\.\\d+)|([-+]?[0-9]+\\.))";

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put(STRING, REGEX_STRING);
        map.put(INT, REGEX_INT);
        map.put(CHAR, REGEX_CHAR);
        map.put(DOUBLE, REGEX_DOUBLE);
    }

    public static void main(String[] args) {
        execute(Util.readFile(PATH, ENCODING));
    }

    public static void execute(String input) {
        Scanner scan = new Scanner(System.in, ENCODING);
        StringBuilder sb = new StringBuilder();
        while (true) {
            sb.append(scan.nextLine());
            if (sb.toString().trim().equals(QUIT)) {
                break;
            }
            if (map.get(sb.toString()) == null) {
                System.out.println("Incorrect input");
            } else {
                if ("".equals(getTypes(map.get(sb.toString()), input))) {
                    System.out.println("No such values");
                } else {
                    System.out.println(getTypes(map.get(sb.toString()), input));
                }
            }
            sb.setLength(0);
        }
    }

    private static String getTypes(String regex, String input) {
        Matcher matcher = Pattern.compile(regex,Pattern.MULTILINE).matcher(input);
        StringBuilder res = new StringBuilder();
        while (matcher.find()) {
            res.append(matcher.group(1)).append(" ");
        }
        return res.toString().trim();
    }
}
