package task4_1;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        var sc = new Scanner(System.in);
        var exprInf = sc.nextLine();

        var defClassPath = "task4_1.Implementations.Definer";
        var appClassPath = "task4_1.Implementations.Applier";
        try {
            var mc = new MyCalculator(exprInf, defClassPath, appClassPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
