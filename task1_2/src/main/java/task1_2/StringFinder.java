package task1_2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;


public class StringFinder {
    public static HashSet<Long> Find(String sample, String file) throws IOException {
        var prefixFunc = prefixFunction(sample);

        final int GIGABYTE = 1024 * 1024 * 1024;

        try (var fileReader = new FileReader(file, StandardCharsets.UTF_8))
        {
            var buffSize = Math.min(sample.length() * 4, GIGABYTE);
            var buffer = new char[buffSize];
            var buffOffset = 0;
            var buffCapacity = buffer.length;

            var result = new HashSet<Long>();

            int runCounter = 0;

            // считывание текста в буффер
            while (fileReader.read(buffer, buffOffset, buffCapacity) != -1) {
                var sourceStr = String.valueOf(buffer);

                KMPSearch(sourceStr, sample, runCounter * (long)buffOffset, prefixFunc)
                        .forEach(x -> result.add(x));

                buffOffset = buffer.length / 2;
                buffCapacity = buffOffset;

                // выполняем сдвиг элементов буффера влево, начиная с середины
                // далее будем читать с середины буффера
                // чтобы не потерять вхождения
                for(int i = 0; i < buffCapacity; i++){
                    buffer[i] = buffer[i + buffOffset];
                    buffer[i + buffOffset] = '\0';
                }

                // счетчик чтений из файла
                runCounter++;
            }

            return result;
        }
    }

    private static int[] prefixFunction(String sample) {
        var values = new int[sample.length()];

        for (int i = 1; i < sample.length(); i++) {
            int j = 0;
            while ((i + j < sample.length()) && (sample.charAt(j) == sample.charAt(i + j))) {
                values[i + j] = Math.max(values[i + j], j + 1);
                j++;
            }
        }

        return values;
    }

    private static ArrayList<Long> KMPSearch(String text, String sample, long inFileOffset, int[] prefixFunc) {
        var result = new ArrayList<Long>();

        int i = 0; // позиция внутри текста
        int j = 0; // позиция внутри образца

        while (i < text.length()) {
            // прикладываем образец к тексту
            if (sample.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }
            // образец полностью совпал с какой-то частью текста
            if (j == sample.length()) {
                result.add(inFileOffset + (long)(i - j));
                j = prefixFunc[j - 1];
            }// образец не совпал
            else if ((i < text.length()) && (sample.charAt(j) != text.charAt(i))) {
                if (j != 0) {
                    j = prefixFunc[j - 1];
                } else {
                    i = i + 1;
                }
            }
        }

        return result;
    }
}
