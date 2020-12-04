package task4_2;

import java.util.Date;
import java.util.regex.Pattern;

public class Record {
    private String title;
    private String content;
    private Date createdAt;
    private Date updatedAt;

    /**
     * Конструктор класса Record
     * @param title - заголовок записи
     * @param content - содержимое записи
     */
    public Record(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdAt = new Date();
        updatedAt = (Date) this.createdAt.clone();
    }

    /**
     * @return заголовок текущей записи
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @return содержимое текущей записи
     */
    public String getContent() {
        return this.content;
    }

    /**
     * @return дата создания текущей записи
     */
    public Date getCreatedAtDate() {
        return this.createdAt;
    }

    /**
     * Проверяет, содержит ли заголовок текущей записи ключевые слова
     * @param keyWords - массив ключевых слов
     * @return true/false
     */
    public boolean containsKeyWords(String[] keyWords) {
        var title = this.title.toLowerCase();
        for (var keyWord : keyWords) {
            keyWord = keyWord.toLowerCase();
            if (Pattern.matches(new StringBuilder().append("*").append(keyWord).append("*").toString(), title)){
                return true;
            }
        }

        return false;
    }

}
