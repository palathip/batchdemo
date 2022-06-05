package com.aycap.kbb.batchdemo;


import com.aycap.kbb.batchdemo.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope("singleton")
@Slf4j
public class BodyReader {
    private final Map<Long, List<Person>> map = new HashMap<>();
    private final Map<Long, List<Object>> mapResult = new HashMap<>();

    public void setPersons(Long key,List<HashMap<String,Object>> application) {
        log.info("Form bodyReader : "+application.size());
        ArrayList<Person> arr = new ArrayList<>();
        application.forEach(row ->{
            arr.add(new Person(row.get("application_no").toString(),row.get("first_name").toString(),row.get("last_name").toString()));
        });
        map.put(key,arr);
    }

    public ListItemReader<Person> getPersons(Long batchKey) {
        return new ListItemReader<>(map.get(batchKey));
    }

    public void setResult(Long key,List<Object> ex){
        mapResult.put(key,ex);
    }

    public List<Object> getResult(Long key){
        map.remove(key);
        return mapResult.remove(key);
    }
}
