package com.airwallex.calculator;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CalculatorApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CalculatorApplication.class)
                .logStartupInfo(false).bannerMode(Banner.Mode.OFF)
                .run(args);
    }

}
