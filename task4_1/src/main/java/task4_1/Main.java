package task4_1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        var sc = new Scanner(System.in);
        var exprInf = sc.nextLine();
        var mc = new MyCalculator(exprInf);
    }
}
