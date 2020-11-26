package task3_2;

import java.awt.image.CropImageFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CreditBook extends Semester {
    private ArrayList<Semester> credits;
    private Integer qualifiedWorkCredit = null;

    public Double getAverage(){
        var res = 0d;
        for(var semester : credits){
            res += semester.getAverage();
        }

        return res;
    }


    public void putCredit(Integer semesterId, String subject, Double credit){
        if(semesterId < 1){
            throw new IndexOutOfBoundsException();
        }

        if(semesterId > credits.size())
        {
            credits.add(new Semester(subject, credit));
        }

        credits.get(semesterId).putCredit(subject, credit);
    }

    public Double getCredit(Integer semesterId, String subject){
        return credits.get(semesterId).getCredit(subject);
    }
}


