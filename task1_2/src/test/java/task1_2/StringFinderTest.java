package task1_2;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class StringFinderTest {
    @Test
    public void Test1() throws IOException {
            var sample = "ab";
            var file = File.createTempFile("inFile", ".tmp");
            var fileWriter = new FileWriter(file);

            for (int i = 0; i < 16; i++) {
                fileWriter.write("ab");
            }

            fileWriter.close();

            var fis = new FileInputStream(file);
            var res = StringFinder.Find(sample, fis);

            for(var elem : res){
                System.out.println(elem);
            }
    }

}