package task4_2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;


public class MyNotebook {
    @SerializedName("Owner")
    private String owner;

    @SerializedName("Records")
    private ArrayList<Record> records;

    @SerializedName("Created-at")
    private final Date createdAt;

    @SerializedName("Updated-at")
    private Date updatedAt;

    /**
     * Конструктор класса Notebook
     * @param owner - владелец записной книжки
     */
    public MyNotebook(String owner) {
        this.owner = owner;
        records = new ArrayList<>();
        createdAt = new Date();
        updatedAt = (Date) this.createdAt.clone();
    }

    /**
     * Метод позволяет добавить новую запиьс в книжку
     * @param record - новая запись
     * @throws IllegalStateException если такая запись уже существует
     */
    public void addRecord(Record record) {
        if (records.contains(record)) {
            throw new IllegalStateException("This record is already exists!");
        }

        records.add(record);
        this.updatedAt = new Date();
    }

    /**
     * Метод позволяет удалить запись по её заголовку
     * @param title - заголовок записи
     * @throws IllegalStateException если такой записи не существует
     */
    public void removeRecord(String title) {
        var exists = records.stream().filter(r -> r.getTitle().equals(title)).findFirst().get();
        if (exists == null) {
            throw new IllegalStateException("This record doesn't exists!");
        }

        records.remove(exists);
        this.updatedAt = new Date();
    }

    /**
     * Метод позволяет получить все записи из книжки, отсортированные по дате добавления
     * @return список всех записей
     */
    public ArrayList<Record> getAllRecordsOrdered() {
        records.sort(new Comparator<Record>() {
            @Override
            public int compare(Record r1, Record r2) {
                return r1.getCreatedAtDate().compareTo(r2.getCreatedAtDate());
            }
        });

        return this.records;
    }

    /**
     * Метод позволяет получить все записи, отсортированные по дате добавления,
     * между startDate и endDate, которые содержат ключевые слова
     * @param startDate - начальная дата
     * @param endDate - конечная дата
     * @param keyWords - ключевые слова, по которым нужно искать
     * @return
     */
    public ArrayList<Record> getAllRecordsOrderedBetween(Date startDate, Date endDate, String[] keyWords) {
        var ordered = this.getAllRecordsOrdered();
        var orderedBetween = ordered.stream()
                .filter(r -> r.getCreatedAtDate().after(startDate) && r.getCreatedAtDate().before(endDate))
                .filter(r -> r.containsKeyWords(keyWords))
                .toArray();

        var res = new ArrayList<Record>();
        for (var obj : orderedBetween){
            res.add((Record)obj);
        }

        return res;
    }

    /**
     * @return владелец записной книжки
     */
    public String getOwner(){
        return owner;
    }

}
