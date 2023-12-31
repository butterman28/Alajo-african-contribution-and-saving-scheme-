package com.example.alajo.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ajohandler {
    private static String ajofilePath = "src/main/resources/com/example/alajo/csvs/ajo.csv";
    private static String memfilePath = "src/main/resources/com/example/alajo/csvs/members.csv";
    private static String comfilePath = "src/main/resources/com/example/alajo/csvs/commenced.csv";
    private static List<String[]> ajos = new ArrayList<>();
    public static String joinajo(String ajoid, String userid) {
        String joinfaith="";
        int noofmems = Integer.parseInt(returnoofparticipants(ajoid));
        String payturn = "";
        List<String[]> memlist= fetchajomembeership(ajoid);
        List<String> newmemlist = new ArrayList<>();
        if (memlist.size() == 0 ){
            newmemlist.add(userid);
            newmemlist.add(ajoid);
            newmemlist.add("1");
            joinfaith = "Successful you are the first to collect the contributions on commencement ";
        } else if (memlist.size()<noofmems) {
            newmemlist.add(userid);
            newmemlist.add(ajoid);
            int payid = memlist.size() + 1;
            payturn = String.valueOf(payid);
            newmemlist.add(payturn);
            joinfaith = "Successful you are the "+payturn+"to collect the contributions on commencement";
        } else if (memlist.size() == noofmems) {
            joinfaith = "Can not join max participants reached";
        }
        if (memlist.size() == noofmems){
            beginajo(ajoid);
        }
        try {
            CSVHandler.appendtoCSV(memfilePath,newmemlist);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return joinfaith;
    }

    public static List<String[]> fetchajomembeership(String ajoid) {
        List<String[]> mems = new ArrayList<>();
        List<String[]> specificmems = new ArrayList<>();
        try {
            mems = CSVHandler.readCSV(memfilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int j = 0; j < mems.size(); j++) {
            String[] row = mems.get(j);
            for (int i = 0; i < row.length; i++) {
                String value = row[i];
                if (value.equals(ajoid)) {
                    specificmems.add(row);
                }else{
                    return specificmems;
                }
            }
        }

        return specificmems;
    }
    public static List<String> fetchajo(String ajoname) {
        List<String> ajo = new ArrayList<>();
        try {
            ajos = CSVHandler.readCSV(ajofilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int j = 0; j < ajos.size(); j++) {
            String[] row = ajos.get(j);
            for (int i = 0; i < row.length; i++) {
                String value = row[i];
                if (value.equals(ajoname)) {
                    for (String value1 : row) {
                        System.out.println("Processing value: " + value1);
                        ajo.add(value1);
                    }
                }else{ajo.add("Not Found");}
            }
        }

        return ajo;
    }
    public static String isajonameunique(String args) {
        String alreadyexist = "";
        try {
            ajos = CSVHandler.readCSV(ajofilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int j = 0; j < ajos.size(); j++) {
            String[] row = ajos.get(j);
            for (int i = 0; i < row.length; i++) {
                String value = row[i];
                if (value.toLowerCase().equals(args.toLowerCase())) {
                    alreadyexist = "true";

                } else {
                    alreadyexist = "false";
                }
            }
        }
        return alreadyexist;
    }
    public static String returnajoid(String ajoname) {
        String ajoid ="";
        try {
            ajos = CSVHandler.readCSV(ajofilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int j = 0; j < ajos.size(); j++) {
            String[] row = ajos.get(j);
            for (int i = 0; i < row.length; i++) {
                String value = row[i];
                if (value.equals(ajoname)) {
                    ajoid = row[0];
                }
            }
        }
        return ajoid;
    }

    public static String startdate() {
        LocalDate currentDate = LocalDate.now();

        // Format the date as a string without time information
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String timestamp = currentDate.format(formatter);

        return timestamp;
    }
    public static void beginajo(String ajoid) {
        List<String> ajoinprogress = new ArrayList<>();
        String nextcontribdate = "";
        String nextpaydate = "";
        ajoinprogress.add(ajoid);
        ajoinprogress.add(startdate());
        ajoinprogress.add("1");


        try {
            CSVHandler.appendtoCSV(comfilePath, ajoinprogress);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static String returnoofparticipants(String ajoid) {
        String noofparticipants ="";
        try {
            ajos = CSVHandler.readCSV(ajofilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int j = 0; j < ajos.size(); j++) {
            String[] row = ajos.get(j);
            for (int i = 0; i < row.length; i++) {
                String value = row[i];
                if (value.equals(ajoid)) {
                    noofparticipants = row[2];
                }
            }
        }
        return noofparticipants;
    }
}
