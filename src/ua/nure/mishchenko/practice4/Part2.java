package ua.nure.mishchenko.practice4;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {
    private static final String SPACE = "\\s";
    private static final String ENCODING = "cp1251";
    private static int size = 10;
    private static final int MAX_NUMBER = 50;
    private static final int HUNDRED = 100;
    private static final String FILENAME_UNSORTED = "part2.txt";
    private static final String FILENAME = "part2_sorted.txt";

    public static void main(String[] args) {
        String unsorted = generate();
        write(unsorted,FILENAME_UNSORTED);
        String sorted = sort(unsorted);
        write(sorted, FILENAME);
        System.out.printf("%s%s%s%n", "input ", " ==> ", unsorted.trim());
        System.out.printf("%s%s%s", "output", " ==> ", sorted.trim());
    }

    private static String sort(String in) {
        return sort(Stream.of(in.split(SPACE))
                .map(Integer::parseInt)
                .collect(Collectors.toList()))
                .toString()
                .replaceAll(",", "")
                .replaceAll("\\]", "")
                .replaceAll("\\[", "").trim();
    }

    private static <T extends Comparable<T>> List<T> sort(List<T> list) {
        for (int index = 0; index < list.size() - 1; index++) {
            boolean swapped = false;
            for (int barrier = 0; barrier < list.size() - index - 1; barrier++) {
                if (list.get(barrier).compareTo(list.get(barrier + 1)) > 0) {
                    T tmp = list.get(barrier);
                    list.set(barrier, list.get(barrier + 1));
                    list.set((barrier + 1), tmp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return list;
    }

    private static String generate() {
        return Stream.generate(new SecureRandom()::nextInt)
                .map(x -> x / HUNDRED)
                .filter(x -> x > 0 && x < MAX_NUMBER)
                .distinct()
                .limit(size)
                .map(String::valueOf)
                .collect(Collectors.joining(" ")).trim();
    }

    private static void write(String nums, String path) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(path), ENCODING))) {
            bw.write(nums
                    .replaceAll("[\\[\\]]", "")
                    .replaceAll(",\\s", " "));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
