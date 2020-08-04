package com.github.firelcw.util;

import java.util.stream.Stream;

/**
 * 类型工具类
 * @author liaochongwei
 * @date 2020/7/31 11:34
 */
public class TypeUtils {
    public static final String[] SIMPLE_TYPES =
            new String[] {"integer","short", "byte","long","char",
                    "float", "double","boolean","string", "void"};

    private TypeUtils() {
    }


    /**
     * 获取简单类型
     * @param typeName
     * @return type
     */
    public static String getType(String typeName) {
        String typeLower = typeName.toLowerCase();
        return Stream.of(SIMPLE_TYPES).filter(typeLower::contains).findFirst().orElse(null);
    }
    /**
     * 是否为简单参数
     * @param typeName
     * @return isSimple
     */
    public static boolean isSimple(String typeName) {
        String typeLower = typeName.toLowerCase();
        return Stream.of(SIMPLE_TYPES).anyMatch(typeLower::contains);
    }

    /**
     * 获取所有简单类型
     * @return allTypes
     */
    public static String[] getTypes() {
        return SIMPLE_TYPES;
    }

    public static String getIntType() {
        return SIMPLE_TYPES[0];
    }
    public static String getShortType() {
        return SIMPLE_TYPES[1];
    }
    public static String getByteType() {
        return SIMPLE_TYPES[2];
    }
    public static String getLongType() {
        return SIMPLE_TYPES[3];
    }
    public static String getCharType() {
        return SIMPLE_TYPES[4];
    }
    public static String getFloatType() {
        return SIMPLE_TYPES[5];
    }
    public static String getDoubleType() {
        return SIMPLE_TYPES[6];
    }
    public static String getBoolType() {
        return SIMPLE_TYPES[7];
    }
    public static String getStringType() {
        return SIMPLE_TYPES[8];
    }
    public static String getVoidType() {
        return SIMPLE_TYPES[9];
    }
}