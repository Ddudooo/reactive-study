package study.reactortemperature.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import study.reactortemperature.model.Temperature;

import java.time.Duration;
import java.util.Random;

@Service
public class TemperatureSensor {

    private final Random rnd = new Random();

    private final Flux<Temperature> dataStream =
        Flux
            .range(0, Integer.MAX_VALUE)
            .concatMap(tick -> Flux.just(tick)
                .delayElements(Duration.ofMillis(rnd.nextInt(5000)))
                .map(tickvalue -> this.probe())
            );

    private Temperature probe() {
        return new Temperature(16 + rnd.nextGaussian() * 10);
    }

    public Flux<Temperature> temperatureFlux() {
        return dataStream;
    }
}