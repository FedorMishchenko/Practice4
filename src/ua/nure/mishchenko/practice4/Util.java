package ua.nure.mishchenko.practice4;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Util {

    private Util(){
        throw new IllegalStateException("Utility class");
    }

    public static String readFile(String path, String encoding) {
        List<String> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(path), Charset.forName(encoding))) {
            lines = stream.collect(Collectors.toList());
        } catch (IOException ex) {ex.printStackTrace();}
        return lines.toString();
    }

    public static void writeFile(String path,List lines, String encoding){
        Path file = Paths.get(path);
        try {
            Files.write(file, lines, Charset.forName(encoding));
        } catch (IOException ex) { ex.printStackTrace(); }
    }


}
