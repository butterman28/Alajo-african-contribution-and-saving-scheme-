package com.example.alajo.model;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


public class writers {
    public static String updaterow(List<String[]> args,String condition,String replacement, String targetid,String filePath) {
        int tar_id= Integer.parseInt(targetid);
        for (int j = 0; j < args.size(); j++) {
            String[] row = args.get(j);
            for (int i = 0; i < row.length; i++) {
                String value = row[i];
                if (value.equals(condition)) {
                    row[tar_id] = String.valueOf(replacement);
                    args.set(j,row);// Return the index when acctname is found
                }
            }
        }try {
            CSVHandler.writeCSV(filePath,args);
            System.out.println("Data written to CSV file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately (e.g., show an error message)
        }

        return null;
    }

    public static String createUniqueIndex() throws IOException{
        String uniqueId = UUID.randomUUID().toString();
        return uniqueId;
    }

    public static void appendrow(List<String> args,String filePath) {
        try {
            CSVHandler.appendtoCSV(filePath,args);
            System.out.println("Data written to CSV file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately (e.g., show an error message)
        }
    }


}
