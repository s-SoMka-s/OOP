package task4_2;

import java.text.ParseException;

public class Main {

    public static void main(String args[]) {
        try {
            var app = new Application();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}

