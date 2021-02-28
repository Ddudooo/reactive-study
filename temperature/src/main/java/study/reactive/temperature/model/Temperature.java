package study.reactive.temperature.model;

import lombok.Getter;

@Getter
public class Temperature {

    private final double value;

    public Temperature(double temperature) {
        this.value = temperature;
    }
}