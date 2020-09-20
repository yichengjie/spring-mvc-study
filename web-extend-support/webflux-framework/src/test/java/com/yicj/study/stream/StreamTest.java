package com.yicj.study.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    @Test
    public void test1(){
        List<String> names = new ArrayList<>() ;
        names.add("Guangshan") ;
        names.add("July") ;
        names.add("Jackson") ;
        List<String> collect = names.stream().map(n -> "Hello " + n).collect(Collectors.toList());
        System.out.println(collect);
    }
}
