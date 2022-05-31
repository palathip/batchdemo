package com.aycap.kbb.batchdemo;

import com.aycap.kbb.batchdemo.model.Person;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope("singleton")
public class MyListReader {
    private final Map<Long, List<Person>> map = new HashMap<>();

    public void setPersons(Long key, Integer epoch) {
        ArrayList<Person> arr = new ArrayList<>();
        for (int i = 0; i < epoch; i++) {
            arr.add(new Person("0001","Jill", "Doe"));
            arr.add(new Person("0002","Joe", "Doe"));
            arr.add(new Person("0003","Justin", "Doe"));
            arr.add(new Person("0004","Jane", "Doe"));
            arr.add(new Person("0005","John", "Doe"));
        }
        map.put(key, arr);
    }

    public ListItemReader<Person> getPersons(Long batchKey) {
        return new ListItemReader<>(map.get(batchKey));
    }
}
