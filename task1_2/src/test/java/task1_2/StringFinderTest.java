package task1_2;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class StringFinderTest {
    @Test
    public void Test1(){
        try {
            var sample = "ab";
            var file = "inFile.txt";
            var fileWriter = new FileWriter(file);
            for (int i = 0; i < 16; i++) {
                fileWriter.write("111");
            }
            fileWriter.close();

            var res = StringFinder.Find(sample, file);
            for(var elem : res){
                System.out.println(elem);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

}