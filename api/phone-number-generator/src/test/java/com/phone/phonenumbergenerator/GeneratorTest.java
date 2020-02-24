package com.phone.phonenumbergenerator;

import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class GeneratorTest {

    @Test
    public void test(){
        Generator generator = new Generator("0123456");
        Map<Integer, Set<String>> res = generator.getValidCombination();
        assertThat(res).isNotEqualTo(null);
        assertThat(res.values().iterator().next()).doesNotContain("0123456");
        System.out.println("1");
    }

    @Test
    public void test2(){
        Generator generator = new Generator("0123456789");
        Map<Integer, Set<String>> res = generator.getValidCombination();
        assertThat(res).isNotEqualTo(null);
        assertThat(res.values().iterator().next()).doesNotContain("0123456789");
        assertThat(res.values().iterator().next()).doesNotContain("1023456789");
        assertThat(res.values().iterator().next()).doesNotContain("2340156789");
        System.out.println("1");
    }
        
}