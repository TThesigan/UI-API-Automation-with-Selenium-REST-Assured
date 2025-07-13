package utils;


import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReadTestData {
    public String boardName;
    public List<String> listNames;

    public static ReadTestData read(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, ReadTestData.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
