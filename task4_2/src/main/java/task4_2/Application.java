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
    private String lastPathstr;

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
            this.lastPathstr = this.scanner.nextLine();
            var path = Path.of(this.lastPathstr);

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
                try {
                    this.serializer(this.notebook, this.lastPathstr);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                return;
            case "-serialize":
                this.serializeHandler(args);
                return;
            default:
                return;
        }
    }

    private void serializeHandler(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("To few args!");
        }

        try {
            this.serializer(this.notebook, args[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showHandler(String[] args) throws ParseException {
        if (args.length == 2) {
            var records = this.notebook.getAllRecordsOrdered();
            this.printRecords(records);
            return;
        }

        this.showOrderedBetweenHandler(args);
    }

    private void showOrderedBetweenHandler(String[] args) throws ParseException {
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
        if (records.isEmpty()) {
            System.out.println("You don't have any records yet!");
        }

        var sb = new StringBuilder();
        for (var record : records) {
            sb = sb.append("CreatedAt: ").append(record.getCreatedAtDate())
                    .append("Title: ").append(record.getTitle()).append("\n")
                    .append("Content: ").append(record.getContent()).append("\n");
        }

        System.out.println(sb);
    }

    private void serializer(MyNotebook source, String dstPath) throws IOException {
        this.lastPathstr = dstPath;
        var gson = new GsonBuilder().setPrettyPrinting().create();
        var jsonStr = gson.toJson(source);

        var file = new File(dstPath);
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        }

        var fw = new FileWriter(file);
        fw.write(jsonStr);
        fw.close();
    }

    private MyNotebook deserializer(Path path) throws IOException {
        var jsonString = new String(Files.readAllBytes(path));

        var gson = new Gson();
        var res = gson.fromJson(jsonString, MyNotebook.class);

        return res;
    }
}
