package aqua.ideamart.uni.util;

import com.google.gson.Gson;

public class JsonUtil {

    private static final Gson gson = new Gson();

    public static String convertToJson(Object obj){
        return gson.toJson(obj);
    }

    public static <T> T extractObject(String jsonString, Class<T> type) {
        return (T)gson.fromJson(jsonString, type);
    }

}
