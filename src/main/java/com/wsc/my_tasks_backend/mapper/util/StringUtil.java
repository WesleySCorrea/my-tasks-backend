package com.wsc.my_tasks_backend.mapper.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtil {

    public static String normalizeName(String name) {
        if (name == null || name.isBlank()) return name;

        String cleaned = name.replaceAll("[^\\p{L}\\s']", " ");

        return Arrays.stream(cleaned.trim().split("\\s+"))
                .map(StringUtil::capitalize)
                .collect(Collectors.joining(" "));
    }

    private static String capitalize(String word) {
        if (word.isEmpty()) return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
}
