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
    private Date createdAt;

    @SerializedName("Updated-at")
    private Date updatedAt;

    public MyNotebook(String owner) {
        this.owner = owner;
        records = new ArrayList<>();
        createdAt = new Date();
        updatedAt = (Date) this.createdAt.clone();
    }


    public void addRecord(Record record) {
        if (records.contains(record)) {
            throw new IllegalStateException("This record is already exists!");
        }

        records.add(record);

    }

    public void removeRecord(String title) {
        var exists = records.stream().filter(r -> r.getTitle().equals(title)).findFirst().get();
        if (exists == null) {
            throw new IllegalStateException("This record doesn't exists!");
        }

        records.remove(exists);
    }

    public ArrayList<Record> getAllRecordsOrdered() {
        records.sort(new Comparator<Record>() {
            @Override
            public int compare(Record r1, Record r2) {
                return r1.getCreatedAtDate().compareTo(r2.getCreatedAtDate());
            }
        });

        return this.records;
    }

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

}
