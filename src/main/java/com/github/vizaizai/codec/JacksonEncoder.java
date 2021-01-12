package com.github.vizaizai.codec;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.vizaizai.exception.CodecException;
import com.github.vizaizai.util.Utils;
import com.github.vizaizai.util.value.StringNameValues;
import org.apache.commons.beanutils.BeanMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 默认编码
 * @author liaochongwei
 * @date 2020/7/31 9:53
 */
public class JacksonEncoder implements Encoder {
    private final ObjectMapper mapper;

    public JacksonEncoder() {
        this(Collections.<Module>emptyList());
    }

    public JacksonEncoder(Iterable<Module> modules) {
        this(new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .setDefaultPrettyPrinter(new MinimalPrettyPrinter()) // 最小输出
                .registerModules(modules));
    }

    public JacksonEncoder(ObjectMapper mapper) {
        this.mapper = mapper;
    }
    @Override
    public StringNameValues encodeNameValue(Object object) {
        if (object == null) {
            return null;
        }
        Map<?,?> map;
        if (object instanceof  Map) {
            map = (Map<?, ?>) object;
        } else {
            map = new HashMap<>(new BeanMap(object));
            if (map.get("class") != null) {
                map.remove("class");
            }
        }
        return Utils.toNameValues(map);
    }

    @Override
    public String encodeString(Object object) {
        if (object == null) {
            return null;
        }
        try {
           return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
           throw new CodecException(e);
        }
    }
}
