package com.thoughtworks.gauge.example.java;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.example.core.DriverFactory;
import com.thoughtworks.gauge.example.core.SampleText;
import com.thoughtworks.gauge.main.action.ButtonAction;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DebitCard {

    @Autowired
    private SampleText st;

    @Autowired
    private ButtonAction ba;

    static final int MAX_AMOUNT = 10000;
    private static int balance = MAX_AMOUNT;

    @Step("Set debit card balance as <amount> rupees")
    public void setLimit(String amount) {
        try {
            balance = Integer.parseInt(amount);
            System.out.println(st.getSamplet1());
            ba.getActionName();
        } catch (NumberFormatException e) {
            Assert.fail(String.format("Failed to parse the amount %s", amount));
        }
    }

    public static boolean hasBalance(int amount) {
        return amount <= balance;
    }

    public static void debit(int amount) {
        balance -= amount;
    }
}