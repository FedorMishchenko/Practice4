package ua.nure.mishchenko.practice4;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static final String PATH = "part1.txt";
    public static final String ENCODING = "cp1251";
    private static final String REGEX = "(?U)(?<word>[\\w]{4,})";
    private static final String REG = "[\\[\\]\\s,]";

    public static void main(String[] args) {
        System.out.println(execute(readFile(PATH)));
    }

    public static String execute(String input){
        StringBuffer sb = new StringBuffer();
        Matcher matcher = Pattern.compile(REGEX).matcher(input);
        while (matcher.find()) {
            String str = matcher.group("word");
            matcher.appendReplacement(sb, apply(str.toCharArray()));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String readFile(String path){
        File file = new File(path);
        StringBuilder sb = new StringBuilder();
        try(Scanner sc = new Scanner(file, ENCODING)) {
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    private static String apply(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (Character.isUpperCase(chars[i])) {
                chars[i] = Character.toLowerCase(chars[i]);
            } else {
                chars[i] = Character.toUpperCase(chars[i]);
            }
        }
        return Arrays.toString(chars).replaceAll(REG,"");
    }
}
