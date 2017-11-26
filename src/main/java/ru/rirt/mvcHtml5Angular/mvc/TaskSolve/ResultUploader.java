package ru.rirt.mvcHtml5Angular.mvc.TaskSolve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.rirt.mvcHtml5Angular.mvc.hibernate.Commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Leonid Malygin on 28.07.2017.
 * lenyamalygin@gmail.com
 */
@Component
public class ResultUploader {
    List<Object> uploaderList;

    @Autowired
    Commands commands;


    public ResultUploader(){
        uploaderList = Collections.synchronizedList(new ArrayList<>());
    }

    @Scheduled(fixedDelay = 5000, initialDelay = 10000)
    public void UploadData(){
        if (uploaderList.size()!=0){
            synchronized (uploaderList){
                commands.addArraytoDb(uploaderList);
                uploaderList.clear();
            }
        }
    }

    public void addResultForUpload(Object object){
        uploaderList.add(object);
    }
}
