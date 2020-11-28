package task4_2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Application {
    private Scanner scanner;
    private MyNotebook notebook;

    public Application() throws ParseException {
        this.scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать! Как вас зовут");
        var owner = this.scanner.nextLine();

        this.notebook = new MyNotebook(owner);

        while (true) {
            var command = scanner.nextLine().split(" ");
            if (command[1].equals("-exit")) {
                return;
            }

            this.parser(command);
        }
    }

    private void parser(String[] args) throws ParseException {
        switch (args[1]) {
            case "-add":
                this.notebook.addRecord(new Record(args[2], args[3]));
                return;
            case "-rm":
                this.notebook.removeRecord(args[2]);
                return;
            case "-show":
                if (args.length == 2) {
                    var records = this.notebook.getAllRecordsOrdered();
                    return;
                }

                var formatter = new SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.ENGLISH);

                var start = formatter.parse(args[2]);
                var end = formatter.parse(args[3]);

                var list = new ArrayList<String>(Arrays.asList(args));
                list.remove(0);
                list.remove(0);
                list.remove(0);
                list.remove(0);

                var keyWords = (String[])list.toArray();

                var records = this.notebook.getAllRecordsOrderedBetween(start, end, keyWords);
            default:
                return;
        }
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
