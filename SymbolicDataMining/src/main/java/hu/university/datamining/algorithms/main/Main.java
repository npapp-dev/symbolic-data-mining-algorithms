/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.university.datamining.algorithms.main;

import hu.university.datamining.algorithms.Algorithm;
import hu.university.datamining.algorithms.Apriori;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nor
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File file = null;
        BufferedWriter bw = null;
        try {
            //Create data.txt
            int columnCount = 10;
            int rowCount = 100;
            char startChar = 'A';
            String delimiter = ",";
            file = new File("data.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < columnCount; i++) {
                bw.write(((char) (startChar + i)) + " ");
            }
            bw.write("\n");
            Random rand = new Random();
            for (int j = 0; j < rowCount; j++) {
                for (int i = 0; i < columnCount; i++) {
                    if (columnCount - 1 != i) {
                        bw.write(rand.nextInt(2) + delimiter);
                    } else {
                        bw.write(rand.nextInt(2) + "");
                    }
                }
                bw.write("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }/*
        System.out.println("Apriori algorithm");
        long startTime = System.currentTimeMillis();
        Algorithm.getApriori().execute(file.getName(), " ", ",", 3);
        long endTime = System.currentTimeMillis();
        System.out.println("Elapsed time:" + (endTime - startTime));

        System.out.println("Eclat algorithm");
        long startTime2 = System.currentTimeMillis();
        Algorithm.getEclat().execute(file.getName(), " ", ",", 3);
        long endTime2 = System.currentTimeMillis();
        System.out.println("Elapsed time:" + (endTime2 - startTime2));

        System.out.println("Charm algorithm");
        long startTime3 = System.currentTimeMillis();
        Algorithm.getCharm().execute(file.getName(), " ", ",", 3);
        long endTime3 = System.currentTimeMillis();
        System.out.println("Elapsed time:" + (endTime3 - startTime3));
        */
        Algorithm apriori=new Apriori();
        List result=apriori.execute("test5.txt", "|", ",", 3);
        System.out.println();
        result.stream().forEach((s)->System.out.println(s));
        System.out.println();
        Algorithm apriori2=new Apriori();
        List result1=apriori2.execute("test.txt", ",", ",", 3);
        System.out.println();
        result1.stream().forEach((s)->System.out.println(s));
    }

}
