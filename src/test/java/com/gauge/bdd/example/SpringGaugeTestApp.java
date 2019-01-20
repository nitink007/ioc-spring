package com.gauge.bdd.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Component scan finds all components/beans/... below this package.
 */
@Configuration
@ComponentScan(basePackages = "com.gauge.bdd")
public class SpringGaugeTestApp {
}
