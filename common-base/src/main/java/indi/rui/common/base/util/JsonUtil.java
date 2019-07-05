package indi.rui.common.base.util;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.IOException;
import java.io.StringWriter;

@Slf4j
public final class JsonUtil {
    private static JsonFactory jsonFactory = new JsonFactory();

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 只序列化非空字段
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.INTERN_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.CANONICALIZE_FIELD_NAMES, true);
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String toJson(Object object){
        StringWriter stringWriter = new StringWriter();
        try {
            JsonGenerator jsonGenerator = getJsonFactory().createJsonGenerator(stringWriter);
            objectMapper.writeValue(jsonGenerator, object);
            jsonGenerator.close();
        } catch (IOException e) {
            throw new RuntimeException(String.format("Convert bean '%s' to json String failed, caused by %s", object.getClass().getName(), e.getMessage()));
        }
        return stringWriter.toString();
    }

    public static String encode(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Convert bean '%s' to json String failed, caused by %s", object.getClass().getName(), e.getMessage()));
        }
    }

    public static <T> T toObject(String jsonStr, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Convert json String '%s' to bean '%s' failed, caused by %s", jsonStr, clazz, e.getMessage()));
        }
    }

    public static JsonFactory getJsonFactory() {
        return jsonFactory;
    }

    public static void setJsonFactory(JsonFactory jsonFactory) {
        JsonUtil.jsonFactory = jsonFactory;
    }
}
