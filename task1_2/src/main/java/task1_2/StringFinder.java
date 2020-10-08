package task1_2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class StringFinder {
    public static ArrayList<Long> Find(String sample, InputStream inputStream) throws IOException {
        var prefixFunc = prefixFunction(sample);

        var buffSize = sample.length() * 4;
        var buffer = new char[buffSize];
        var buffOffset = 0;
        var buffCapacity = buffer.length;

        var result = new ArrayList<Long>();

        var runCounter = 0;
        var fileReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        // считывание текста в буффер
        long readed = 0;
        while ((readed = fileReader.read(buffer, buffOffset, buffCapacity)) != -1) {
            var inFileOffset = runCounter * (long) buffCapacity;
            var textLength = readed + buffOffset;

            var tmp = KMPSearch(buffer, textLength, sample, inFileOffset, prefixFunc);

            // проверяем на дубликаты
            if(result.size()>0 && (tmp.get(0) == result.get(result.size()-1))){
                tmp.remove(0);
            }

            result.addAll(tmp);

            buffOffset = buffer.length / 4;
            buffCapacity = buffSize - buffOffset;

            // выполняем сдвиг элементов буффера влево, начиная с середины
            // далее будем читать с 1/4 буффера
            // чтобы не потерять вхождения
            for (int i = 0; i < buffOffset; i++) {
                buffer[i] = buffer[buffSize - buffCapacity + i];
            }

            for (int i = buffOffset; i < buffSize; i++) {
                buffer[i] = '\0';
            }

            // счетчик чтений из файла
            runCounter++;
        }

        return result;
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

    private static ArrayList<Long> KMPSearch(char[] textBuff, long textLength, String sample, long inFileOffset, int[] prefixFunc) {
        var result = new ArrayList<Long>();

        int i = 0; // позиция внутри текста
        int j = 0; // позиция внутри образца

        while (i < textLength) {
            // прикладываем образец к тексту
            if (sample.charAt(j) == textBuff[i]) {
                j++;
                i++;
            }
            // образец полностью совпал с какой-то частью текста
            if (j == sample.length()) {
                result.add(inFileOffset + (long) (i - j));
                j = prefixFunc[j - 1];
            }// образец не совпал
            else if ((i < textLength) && (sample.charAt(j) != textBuff[i])) {
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
