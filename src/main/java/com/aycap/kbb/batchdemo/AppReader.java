package com.aycap.kbb.batchdemo;


import com.aycap.kbb.batchdemo.model.ApplicationModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Scope("singleton")
@Slf4j
public class AppReader {
    private final Map<Long, List<ApplicationModel>> applicaitionMap = new HashMap<>();
    private final Map<Long, List<ApplicationModel>> map = new HashMap<>();
    private final Map<Long, List<Object>> mapResult = new HashMap<>();

    public void setApplicationMap(Long key,List<ApplicationModel> application) {
        applicaitionMap.put(key,application);
    }

    public ListItemReader<ApplicationModel> getApplicationMap(Long key){
        if(applicaitionMap.containsKey(key)){
            return new ListItemReader<>(applicaitionMap.get(key));
        }
        return new ListItemReader<>(Collections.emptyList());
    }

    public void setResult(Long key,List<Object> ex){
        mapResult.put(key,ex);
    }

    public List<Object> getResult(Long key){
        map.remove(key);
        return mapResult.remove(key);
    }
}
