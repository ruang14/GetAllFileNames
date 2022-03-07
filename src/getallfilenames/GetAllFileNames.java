/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getallfilenames;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author ruang
 */
public class GetAllFileNames {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GetAllFileNames gafn = new GetAllFileNames();
        String directory = "";

        try {
            gafn.listAllFileNames(directory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listAllFileNames(String directory) {
        try {
            File folder = new File(directory);
            File[] listOfDirectories = folder.listFiles();
            File[] listOfFiles = {};
            List<String> fileNames = new ArrayList<String>();
            String prefix = "";
            String fileName = "";

            for (int i = 0; i < listOfDirectories.length; i++) {
                if (listOfDirectories[i].isDirectory()) {
                    listOfFiles = listOfDirectories[i].listFiles();

                    for (int j = 0; j < listOfFiles.length; j++) {
                        if (listOfFiles[j].isFile()) {
                            System.out.println(listOfFiles[j].getName());
                        }
                    }
                } else if (listOfDirectories[i].isFile()) {
                    fileName = listOfDirectories[i].getName().substring(prefix.length(), listOfDirectories[i].getName().indexOf(".") - 2);
                    if (fileName.contains("_")) {
                        fileName = fileName.substring(0, fileName.indexOf("_"));
                    }
                    System.out.println(fileName);
                    fileNames.add(fileName);
                }
            }

            writeToFile(fileNames, directory);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(List<String> string, String directory) {
        BufferedWriter bw = null;

        try {

            File file = new File(directory + "\\GetAllFileNames.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);

            for (String s : string) {
                bw.append("'" + s + "',");
                bw.newLine();
            }

            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
