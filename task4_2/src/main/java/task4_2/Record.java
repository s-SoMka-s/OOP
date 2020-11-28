package task4_2;

import java.util.Date;
import java.util.regex.Pattern;

public class Record {
    private String title;
    private String content;
    private Date createdAt;
    private Date updatedAt;

    public Record(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdAt = new Date();
        updatedAt = (Date) this.createdAt.clone();
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public Date getCreatedAtDate() {
        return this.createdAt;
    }

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
