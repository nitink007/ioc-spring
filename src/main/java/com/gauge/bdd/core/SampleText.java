package com.gauge.bdd.core;

import org.springframework.stereotype.Component;

@Component
public class SampleText {

    private String samplet1;

    public String getSamplet1() {
        return this.samplet1 = "this is sample text1";
    }

}
