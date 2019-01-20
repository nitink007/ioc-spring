package com.gauge.bdd.action;

import org.springframework.stereotype.Component;

@Component
public class ButtonAction {

    public String getActionName() {
        System.out.println("Button Action");

        return "ButtonAction";
    }

}
