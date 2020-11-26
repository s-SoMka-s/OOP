package task3_2;

import java.util.HashMap;

public class Semester {
    private HashMap<String, Double> credits = null;

    public Semester(String subject, Double credit){
        if (credits == null) {
            credits = new HashMap<String, Double>(1);
            credits.put(subject, credit);
        }
    }

    public Semester(){
        if (credits == null) {
            credits = new HashMap<String, Double>(1);
        }
    }

    protected Double getCredit(String subject) {
        if (credits.containsKey(subject)) {
            return credits.get(subject);
        }

        return null;
    }

    protected void putCredit(String subject, Double credit) {
        if (credits.containsKey(subject)) {
            credits.replace(subject, credit);
        }

        credits.put(subject, credit);
    }

    protected Double getAverage(){
        if(credits.isEmpty()){
            return 0d;
        }

        var sum = credits.values().stream().mapToDouble(x->x).sum();
        var num = (double)credits.keySet().size();

        return sum / num;
    }
}
