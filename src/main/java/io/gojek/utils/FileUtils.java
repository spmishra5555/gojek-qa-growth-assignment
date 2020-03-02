package io.gojek.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

public class FileUtils {

    private FileUtils() {
    }

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().findAndRegisterModules()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

    public static List<String> readLines(File file) {
        try {
            return Files.readLines(file, Charset.defaultCharset());
        } catch (IOException exp) {
            System.err.println(exp.getMessage());
            return Collections.emptyList();
        }
    }

    public static <S> S jsonToPojo(String json, Class<S> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
