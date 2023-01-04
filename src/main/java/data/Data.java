package data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.weather.Weather;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Data {
//    private static List<RegionName> regions;
//    private static List<Region> regionsInfo = new ArrayList<>();
//    private static long timeOfUpdate;
//
//    public static void getData(){
//        File file = new File("output.txt");
//        Scanner scanner = null;
//        regions = Arrays.asList(RegionName.values());
//        ObjectMapper objectMapper = new ObjectMapper();
//            try {
//                scanner = new Scanner(file);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            while (scanner.hasNextLine()){
//                String str = scanner.nextLine();
//                for(RegionName region : regions){
//                    if(region.getName().equals(str)) {
//                        try {
//                            region.setWeather(objectMapper.readValue(scanner.nextLine(), Weather.class));
//                        } catch (JsonProcessingException e) {
//                            e.printStackTrace();
//                        }
//                        break;
//                    }
//                }
//            }
//
//    }

//    public static void getData(){
//        long start = System.currentTimeMillis();
//        regions = Arrays.asList(RegionName.values());
//        for(RegionName region : regions){
//            try {
//                URL url = new URL(region.getRequest());
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("GET");
//                connection.setConnectTimeout(5000);
//                connection.setReadTimeout(5000);
//                BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                StringBuffer stringBuffer = new StringBuffer();
//                String inputLine;
//                while ((inputLine = input.readLine()) != null) {
//                    stringBuffer.append(inputLine);
//                }
//                connection.disconnect();
//                ObjectMapper objectMapper = new ObjectMapper();
//
//                region.setWeather(objectMapper.readValue(stringBuffer.toString(), Weather.class));
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println("Data was updated!");
//        System.out.println("Time to get response from server: " + (System.currentTimeMillis() - start));
//        timeOfUpdate = System.currentTimeMillis();
//        writeJsonToFile();
//    }


//    public static void writeJsonToFile(){
//        File file = new File("output.txt");
//        try {
//            FileWriter fw = new FileWriter(file, true);
//            ObjectMapper om = new ObjectMapper();
//            for(RegionName region : Arrays.asList(RegionName.values())){
//                fw.write(region.getName() + "\n");
//                fw.write(om.writeValueAsString(region.getWeather()));
//                fw.write("\n");
//
//            }
//            fw.close();
//            System.out.println("File created!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
