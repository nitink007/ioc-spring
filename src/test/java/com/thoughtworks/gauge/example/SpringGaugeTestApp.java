package com.thoughtworks.gauge.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Component scan finds all components/beans/... below this package.
 */
@Configuration
@ComponentScan(basePackages = "com.thoughtworks.gauge")
public class SpringGaugeTestApp {
}
