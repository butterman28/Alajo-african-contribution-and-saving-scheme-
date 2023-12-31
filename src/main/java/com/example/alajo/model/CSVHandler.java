package com.example.alajo.model;

import com.opencsv.CSVWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {

// helps you read csv obviously
    public static List<String[]> readCSV(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();

        try (FileReader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord record : csvParser) {
                String[] values = new String[record.size()];
                for (int i = 0; i < record.size(); i++) {
                    values[i] = record.get(i);
                }
                data.add(values);
            }
        }

        return data;
    }

// helps you update the csv
    public static void writeCSV(String filePath, List<String[]> data) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, false))) {
            writer.writeAll(data);
        }
    }
// helps you add a new guy to the csv
public static void appendtoCSV(String filePath, List<String> data) throws IOException {
    try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, true))) {
        String[] dataArray = data.toArray(new String[0]);

        // Write the string array to the CSV file
        writer.writeNext(dataArray);
    }
}
}