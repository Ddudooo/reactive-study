package study.reactortemperature.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import study.reactortemperature.model.Temperature;
import study.reactortemperature.service.TemperatureSensor;

@RestController
@RequiredArgsConstructor
public class TemperatureApi {

    private final TemperatureSensor temperatureSensor;

    @GetMapping(value = "/api/temperature", produces = "application/stream+json")
    public Flux<Temperature> getTemperature() {
        return temperatureSensor.temperatureFlux();
    }
}