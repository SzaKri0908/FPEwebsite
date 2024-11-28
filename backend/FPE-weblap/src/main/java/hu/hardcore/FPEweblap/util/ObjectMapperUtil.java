package hu.hardcore.FPEweblap.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.io.InputStream;

public final class ObjectMapperUtil {

    private static final ObjectMapper mapper = new ObjectMapper();


    public static ObjectNode objectNode(){
        return mapper.createObjectNode();
    }

    public static  <T> T readValue(InputStream src, Class<T> valueType) throws IOException {
        return mapper.readValue(src, valueType);
    }

    public static  <T> T readValue(String src, TypeReference<T> valueType) throws IOException {
        return mapper.readValue(src, valueType);
    }

    public static String writeValueAsString(Object pojo) throws JsonProcessingException {
        return mapper.writeValueAsString(pojo);
    }

}
