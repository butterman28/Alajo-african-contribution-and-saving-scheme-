package com.example.alajo.model;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private static List<String[]> users = new ArrayList<>();
    private static String userfilePath = "src/main/resources/com/example/alajo/csvs/user.csv";

    public static String createuser(String username, String password,String address,String Email,String Phone) throws NoSuchAlgorithmException {
        String goahead = "";

        /*String defaultstatus = "Standard";*/
        String exist = "";
        String userid;
        try {
            userid = writers.createUniqueIndex();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<String> useregList = new ArrayList<>();
        exist = isstringunique(username);
        if(exist.equals("false") && isstringunique(Email).equals("false")&& isstringunique(Phone).equals("false")){
            useregList.add(userid);
            useregList.add(username);
            useregList.add(hashPassword(password));
            useregList.add(Email);
            useregList.add(Phone);
            useregList.add(address);
            try {
                CSVHandler.appendtoCSV(userfilePath, useregList);
                System.out.println("Data written to CSV file successfully.");
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception appropriately (e.g., show an error message)
            }
            goahead ="Registration Successful";
            return goahead;
        }else { goahead = "Username Already Exist";
            return goahead;
        }


    }

    public static List<String> login(String usernameemailphone, String password) {
        List<String> fetchedaccount = new ArrayList<>();
        List<String> needs = new ArrayList<>();
        String message ="";
        String userid = "";
        String hashedpass;
        try {
            hashedpass = hashPassword(password);
            users = CSVHandler.readCSV(userfilePath);
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        for (int j = 0; j < users.size(); j++) {
            String[] row = users.get(j);
            for (int i = 0; i < row.length; i++) {
                String value = row[i];
                if(value.equals(usernameemailphone)){
                    for (String value1 : row) {
                        //System.out.println("Processing value: " + value1);
                        fetchedaccount.add(value1);
                    }
                }
            }
        }
        for (int j = 0; j < fetchedaccount.size(); j++){
            if(fetchedaccount.get(j).equals(hashedpass)){
                message = "Login Successful";
                userid = fetchedaccount.get(0);
                needs.add(message);
                needs.add(userid);
            }else{
                message = "Login Failed Something is Invalid";
                needs.add(message);
            }
        }

        return needs;
    }
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        try {
            // Create MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Add password bytes to digest
            md.update(password.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // Convert byte array to a hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return null;
        }
    }

    public static String isstringunique(String args) {
        String alreadyexist = "";
        try {
            users = CSVHandler.readCSV(userfilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int j = 0; j < users.size(); j++) {
            String[] row = users.get(j);
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
    public static String returnuserid(String userlabel) {
        String userid ="";
        try {
            users = CSVHandler.readCSV(userfilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int j = 0; j < users.size(); j++) {
            String[] row = users.get(j);
            for (int i = 0; i < row.length; i++) {
                String value = row[i];
                if (value.equals(userlabel)) {
                    userid = row[0];
                }
            }
        }
        return userid;
    }
    public static String returnusername(String userid) {
        String username ="";
        try {
            users = CSVHandler.readCSV(userfilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int j = 0; j < users.size(); j++) {
            String[] row = users.get(j);
            for (int i = 0; i < row.length; i++) {
                String value = row[i];
                if (value.equals(userid)) {
                    username = row[1];
                }
            }
        }
        return username;
    }
}
