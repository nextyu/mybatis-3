package com.nextyu.mybatis;

import org.apache.ibatis.parsing.PropertyParser;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

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

    @Test
    public void propertyTokenizer() {
        String str = "orders[0].items[0].name";
        PropertyTokenizer propertyTokenizer = new PropertyTokenizer(str);
    }

    @Test
    public void tee() {
        Integer maxNumber = Stream.of(1)
                .max(Comparator.comparing(Integer::valueOf)).orElse(2);
        System.out.println(maxNumber);
    }
}
