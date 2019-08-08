package ua.nure.mishchenko.practice4;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Part5 {
    private static final String REGEX = " ";
    private static final int INPUT_PARAM_LENGTH = 2;
    private static final String QUIT = "stop";
    private static ResourceBundle bundle;
    private static final String ENCODING = "UTF-8";
    private static String massage = "No such values";

    public static void main(String[] args) {
        execute("resources");
    }

    private static String[] parse(String input) {
        if (input.isEmpty()) {
            return new String[0];
        }
        return Pattern.compile(REGEX)
                .splitAsStream(input)
                .filter(x -> x.length() > 0)
                .limit(INPUT_PARAM_LENGTH)
                .collect(Collectors.toList())
                .toArray(new String[INPUT_PARAM_LENGTH]);
    }


    public static void execute(String baseName) {
        Scanner scan = new Scanner(System.in, ENCODING);
        StringBuilder sb = new StringBuilder();
        StringBuilder resBundle = new StringBuilder();
        while (true) {
            sb.setLength(0);
            sb.append(scan.nextLine());
            String[] result = parse(sb.toString().trim());

            if (Arrays.equals(result, new String[0]) || result.length > INPUT_PARAM_LENGTH) {
                System.out.println(massage);
                continue;
            } else if (QUIT.equals(result[0])) {
                break;
            }
            if (result[0] == null || result[1] == null) {
                System.out.println(massage);
                continue;
            }
            setProperty(baseName, new Locale(result[1]));
            resBundle.append(getValue(result[0]));
            if (resBundle.toString().length() > 0) {
                System.out.println(resBundle);
            } else {
                System.out.println(massage);
            }
            resBundle.setLength(0);
        }
    }

    private static void setProperty(String baseName, Locale locale) {
        bundle = ResourceBundle.getBundle(baseName, locale);
    }

    private static String getValue(String key) {
        if (bundle.containsKey(key)) {
            return bundle.getString(key);
        } else {
            return "";
        }
    }
}
