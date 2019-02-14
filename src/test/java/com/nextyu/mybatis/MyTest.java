package com.nextyu.mybatis;

import org.apache.ibatis.parsing.PropertyParser;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author zhouyu
 */
public class MyTest {

    @Test
    public void propertyParser() {
        Properties props = new Properties();
        props.setProperty("hehe", "哈哈");

        String parse = PropertyParser.parse("${hehe}${hehe}${hehe}", props);
        System.out.println(parse);
    }

    @Test
    public void computeIfAbsent() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "rose");

        map.computeIfAbsent("name", key -> "jack");
        map.computeIfAbsent("age", key -> "18");

        System.out.println(map.get("name"));
        System.out.println(map.get("age"));

    }
}
