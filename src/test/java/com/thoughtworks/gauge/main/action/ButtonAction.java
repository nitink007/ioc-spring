package com.thoughtworks.gauge.main.action;

import org.springframework.stereotype.Component;

@Component
public class ButtonAction {

    public String getActionName() {
        System.out.println("Button Action");

        return "ButtonAction";
    }

}
