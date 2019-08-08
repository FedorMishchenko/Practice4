package ua.nure.mishchenko.practice4;

import java.io.ByteArrayInputStream;

public class Demo {

    public static void main(String[] args) {

        Part1.main(args);
        Part2.main(args);
        System.out.println();
        System.setIn(new ByteArrayInputStream(
                "double^char^String^double^int^stop".replace("^", System.lineSeparator()).getBytes()));
        Part3.main(args);
        System.setIn(System.in);
        Part4.main(args);
        System.setIn(new ByteArrayInputStream(
                "apple fr^apple en^asdf^table ru^stop"
                        .replace("^", System.lineSeparator()).getBytes()));
        Part5.main(args);
        System.setIn(System.in);
        System.setIn(new ByteArrayInputStream(
                "asdf^Latn^Cyrl^latn^stop"
                        .replace("^", System.lineSeparator()).getBytes()));
        Part6.main(args);
        System.setIn(System.in);

    }
}
