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
import java.util.regex.Matcher;

public class Application {
    private Scanner scanner;
    private MyNotebook notebook;

    public Application() throws ParseException {
        this.scanner = new Scanner(System.in);
        System.out.println("What's you name?");
        var owner = this.scanner.nextLine();
        System.out.println("Hello, " + owner + "!");

        System.out.println("Do you want to continue working with an existing book or create new?");
        var choice = this.scanner.nextLine();
        if (choice.toLowerCase().matches("new")) {
            this.notebook = new MyNotebook(owner);
        } else {
            System.out.println("Choose json file that represents you notebook.");
            var pathStr = this.scanner.nextLine();
            var path = Path.of(pathStr);

            try {
                this.notebook = this.deserializer(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.readCommands();
    }

    private void readCommands() {
        while (true) {
            System.out.println("Write what you want to do with you notebook");
            var command = scanner.nextLine().split(" ");
            if (command[1].equals("-exit")) {
                System.out.println("Goodbye, " + this.notebook.getOwner());
                return;
            }

            try {
                this.parser(command);
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
                this.showHandler(args);
            default:
                return;
        }
    }

    private void showHandler(String[] args) throws ParseException {
        if (args.length == 2) {
            var records = this.notebook.getAllRecordsOrdered();
            this.printRecords(records);
        }

        var formatter = new SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.ENGLISH);

        var start = formatter.parse(args[2]);
        var end = formatter.parse(args[3]);

        var list = new ArrayList<String>(Arrays.asList(args));
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);

        var keyWords = (String[]) list.toArray();

        var records = this.notebook.getAllRecordsOrderedBetween(start, end, keyWords);
        this.printRecords(records);
    }

    private void printRecords(ArrayList<Record> records) {
        if (records.isEmpty()){
            System.out.println("You don't have any records yet!");
        }

        var sb = new StringBuilder();
        for (var record : records) {
            sb = sb.append(record.getTitle()).append("\n")
                    .append(record.getContent()).append("\n")
                    .append(record.getCreatedAtDate());
        }

        System.out.println(sb);
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

    private MyNotebook deserializer(Path path) throws IOException {
        var jsonString = new String(Files.readAllBytes(path));

        var gson = new Gson();
        var res = gson.fromJson(jsonString, MyNotebook.class);

        return res;
    }
}
