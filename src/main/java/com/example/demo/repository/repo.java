package com.example.demo.repository;

import com.example.demo.entity.abobus;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class repo   {
    private final static String fileName = "C:\\proga\\demo\\src\\main\\resources\\DataContext.json";
    private Gson gson;


    private Comparator<abobus> idComparator = new Comparator<abobus>() {
        @Override
        public int compare(abobus o1, abobus o2) {
            return o1.getId().compareTo(o2.getId());
        }
    };

    public repo(Gson gson) {
        this.gson = gson;
    }
    @Async
    private List<abobus> loadData() {
        var list = new ArrayList<abobus>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            list = gson.fromJson(bufferedReader, new TypeToken<List<abobus>>() {}.getType());
            bufferedReader.close();
            System.out.println("Lighting objects have been read from " + fileName + " file.");
            list.sort(idComparator);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Async
    private void writeData(List<abobus> abobuses) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            gson.toJson(abobuses, fileWriter);
            fileWriter.close();
            System.out.println("Lighting objects have been saved to " + fileName + " file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Async
    public abobus getByID(Long id) {
        List<abobus> abbob = loadData();
        var buff = abbob.stream().filter(x -> x.getId() == Integer.parseInt(id.toString())).findFirst().get();
        return buff;
    }
    @Async
    public void delete(Long myClassId) {
        List<abobus> myClassList = loadData();
        myClassList.removeIf(x -> myClassId - 1 >= 0 && x.getId() == myClassId);
        writeData(myClassList);
    }
    @Async
    public void save(abobus x) {
        List<abobus> myClassList = loadData();
        if (myClassList.isEmpty()) {
            x.setId(Long.valueOf(1));
        } else {
            x.setId(Long.valueOf(myClassList.get(myClassList.size() - 1).getId() + 1));
        }
        myClassList.add(x);
        writeData(myClassList);
    }
    @Async
    public List<abobus> findAll() {
        List<abobus> myClassList = loadData();
        return myClassList;
    }
    @Async
    public abobus update(abobus abob) {
        List<abobus> abobus = loadData();
        if (!abobus.isEmpty() && abob != null) {
            var id = 0;
            for (var item : abobus) {
                if (item.getId() == abob.getId()) {
                    break;
                }
                id = id + 1;
            }
            abobus.set(
                    id,
                    abob);
        }
        writeData(abobus);
        abobus = loadData();
        return abobus.stream().filter(x -> (x.getId()) == abob.getId()).toList().get(0);
    }
}