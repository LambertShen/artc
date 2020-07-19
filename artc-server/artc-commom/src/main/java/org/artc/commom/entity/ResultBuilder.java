package org.artc.commom.entity;

import java.util.HashMap;
import java.util.Map;

public class ResultBuilder {

    private final Map<String, Object> map = new HashMap<>();

    public ResultBuilder put(String key, Object value) {
        map.put(key, value);
        return this;
    }

    public Map<String, Object> build() {
        return map;
    }
}
