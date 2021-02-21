package task2_1;

import java.util.ArrayList;

public class PrimeFinder {
    /**
     * Последовательно проверяет все числа, полученного на вход списка целых чисел, на простоту
     * @param numbers - список целых чисел
     * @return true - если список содержит хотя-бы одно простое число
     * false - в противном случае
     */
    public boolean hasPrimesSequent(ArrayList<Integer> numbers) {
        return numbers.stream().anyMatch(n -> this.isPrime(n));
    }

    private boolean isPrime(int x) {
        for (int i = 2; i*i <= x;i++){
            if (x % i == 0) {
                return false;
            }
        }

        return true;
    }
}
