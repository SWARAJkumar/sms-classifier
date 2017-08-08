/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smsClassifier;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadClass {
    public int[] Y= new int[5573];
    public void readCSV() {

        String csvFile = "C:\\Users\\SWARAJ\\Documents\\NetBeansProjects\\SmsClassifier\\src\\smsclassifier\\spam.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            int i=0;
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                if(country[0].equals("ham"))
                    Y[i]=0;
                else
                    Y[i]=1;
                
                System.out.println("Country [code= " + country[0]+"---"+Y[i] + " name=" + country[1] + "]");
                i=i+1;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}