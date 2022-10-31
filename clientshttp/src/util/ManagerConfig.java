package util;

import model.ConfigFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ManagerConfig {
    
    public static ConfigFile getConfigFromFile(File file){
        ConfigFile configFile = new ConfigFile();
        try (BufferedReader br = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String firstline = br.readLine();               //Первая строка, без изменений
            String line = br.readLine();
            String[] arrayList = line.split(";");
            configFile.setrCount(Integer.valueOf(arrayList[0]));
            configFile.setwCount(Integer.valueOf(arrayList[1]));
            String[] lineWithIds = arrayList[2].split(",");
            List<Integer> listOfIds = new ArrayList<>();
            for (int i = 0; i < lineWithIds.length; i++) {
                listOfIds.add(Integer.valueOf(lineWithIds[i]));
            }
            configFile.setIdList(listOfIds);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configFile;
    }


    public static void saveParam(ConfigFile configFile, String fileStr) {
        File file = new File(fileStr);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            bw.write("rCount;wCount;idList\n");
            bw.write(configFile.toString());
        } catch (IOException exception) {
            throw new RuntimeException();
        }
    }

}
