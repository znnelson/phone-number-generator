package com.phone.phonenumbergenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class util {

    public static boolean exists(String number) {
        String directory = Paths.get("").toAbsolutePath().toString() + "/tempData";
        File dir = new File(directory + '/' + number + ".csv");
        if (dir.exists()) {
            return true;
        }
        return false;
    }

    public static String writeToFile(Map<Integer, Set<String>> values, String number) {
        String fileName = number;
        String directory = Paths.get("").toAbsolutePath().toString() + "/tempData";
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        List<String[]> aList = new ArrayList<>();
        aList.add(new String[] { String.valueOf(values.keySet().iterator().next()) });
        Iterator<String> iterator = values.values().iterator().next().iterator();
        while (iterator.hasNext()) {
            aList.add(new String[] { iterator.next() });
        }
        try {
            csvWriterAll(aList, directory + '/' + fileName + ".csv");
        } catch (Exception e) {
            fileName = "";
        }
            return fileName;
        

    }

    public static void csvWriterAll(List<String[]> stringArray, String path) throws Exception {

        CSVWriter writer = new CSVWriter(new FileWriter(path));
        writer.writeAll(stringArray);
        writer.close();

    }

    public static int getTotal(String number) throws IOException {
        String directory = Paths.get("").toAbsolutePath().toString()+"/tempData";
        File dir = new File(directory+'/'+number+".csv");
        Reader reader = Files.newBufferedReader(dir.toPath());
        CSVReader csvReader = new CSVReader(reader);
        String[] line;
        line = csvReader.readNext();
        reader.close();
        csvReader.close();
        return Integer.parseInt(line[0]);
   }

   public static void getData(String number, int pageSize, int pageNumber, List<PhoneNumberEntity> list)
           throws IOException {
    String directory = Paths.get("").toAbsolutePath().toString()+"/tempData";
    File dir = new File(directory+'/'+number+".csv");
    Reader reader = Files.newBufferedReader(Paths.get(dir.getAbsolutePath()));
    CSVReader csvReader = new CSVReader(reader);
    String[] line;
    int i=0;
    while(i<=(pageSize*(pageNumber-1))){
        csvReader.readNext();
        i++;
    }
    int j = 0;
    while((line = csvReader.readNext())!=null && j< pageSize){
        PhoneNumberEntity p = new PhoneNumberEntity();
        p.phoneNumber=line[0];
        list.add(p);
        j++;
    }
    reader.close();
    csvReader.close();
   }
   
   
}