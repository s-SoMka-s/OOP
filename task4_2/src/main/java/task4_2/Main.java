package task4_2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String args[]){


    }


    private void serializer(MyNotebook source, String dstPath) throws IOException {
        var gson = new GsonBuilder().setPrettyPrinting().create();
        var jsonStr = gson.toJson(source);

        var file = new File(dstPath);
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        }

        var fw = new FileWriter(file);
        fw.write(jsonStr);
    }

    private MyNotebook deserializer() throws IOException {
        var jsonString = new String(Files.readAllBytes(Path.of("notebook.json")));

        var gson = new Gson();
        var res = gson.fromJson(jsonString, MyNotebook.class);

        return res;
    }
}
