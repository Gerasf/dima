package com.example.demo.repository;

import com.example.demo.entity.Bus;
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
public class BusRepository {
    private final static String fileName = "src/main/resources/DataContext.json";
    private Gson gson;

    private Comparator<Bus> idComparator = new Comparator<Bus>() {
        @Override
        public int compare(Bus o1, Bus o2) {
            return o1.getId().compareTo(o2.getId());
        }
    };

    public BusRepository(Gson gson) {
        this.gson = gson;
    }
    @Async
    private List<Bus> loadData() {
        var list = new ArrayList<Bus>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            list = gson.fromJson(bufferedReader, new TypeToken<List<Bus>>() {}.getType());
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
    private void writeData(List<Bus> buses) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            gson.toJson(buses, fileWriter);
            fileWriter.close();
            System.out.println("Lighting objects have been saved to " + fileName + " file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Async
    public Bus getByID(Long id) {
        List<Bus> abbob = loadData();
        var buff = abbob.stream().filter(x -> x.getId() == Integer.parseInt(id.toString())).findFirst().get();
        return buff;
    }
    @Async
    public void delete(Long myClassId) {
        List<Bus> myClassList = loadData();
        myClassList.removeIf(x -> myClassId - 1 >= 0 && x.getId() == myClassId);
        writeData(myClassList);
    }
    @Async
    public void save(Bus x) {
        List<Bus> myClassList = loadData();
        if (myClassList.isEmpty()) {
            x.setId(Long.valueOf(1));
        } else {
            x.setId(Long.valueOf(myClassList.get(myClassList.size() - 1).getId() + 1));
        }
        myClassList.add(x);
        writeData(myClassList);
    }
    @Async
    public List<Bus> findAll() {
        List<Bus> myClassList = loadData();
        return myClassList;
    }
    @Async
    public Bus update(Bus abob) {
        List<Bus> Bus = loadData();
        if (!Bus.isEmpty() && abob != null) {
            var id = 0;
            for (var item : Bus) {
                if (item.getId() == abob.getId()) {
                    break;
                }
                id = id + 1;
            }
            Bus.set(
                    id,
                    abob);
        }
        writeData(Bus);
        Bus = loadData();
        return Bus.stream().filter(x -> (x.getId()) == abob.getId()).toList().get(0);
    }
}