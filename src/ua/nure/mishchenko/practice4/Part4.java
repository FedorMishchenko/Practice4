package ua.nure.mishchenko.practice4;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 implements Iterable<String> {

    private static final String FILE_PATH = "part4.txt";
    private static final String ENCODING = "cp1251";
    private static String input;
    private static Pattern pattern = Pattern.compile("(\\p{javaUpperCase}.+?\\.)\\s?", Pattern.MULTILINE);

    public Part4() {
        readFile();
    }

    private static void readFile() {
        File file = new File(FILE_PATH);
        StringBuilder sb = new StringBuilder();
        try (Scanner sc = new Scanner(file, ENCODING)){
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        input = sb.toString();
    }

    private static class Iter implements Iterator<String> {
        private Matcher matcher = pattern.matcher(input);
        private boolean condition;

        @Override
        public boolean hasNext() {
            condition = true;
            return matcher.find();
        }

        @Override
        public String next() {
            if(!condition){
                throw new NoSuchElementException();
            }
            condition = false;
            return matcher.group(1).replaceAll("\\s$", "");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<String> iterator() {
        return new Iter();
    }

    public void output() {
        for (String str : this) {
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        new Part4().output();

    }
}