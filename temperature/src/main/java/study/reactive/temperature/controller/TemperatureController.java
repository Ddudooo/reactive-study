package study.reactive.temperature.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import study.reactive.temperature.config.RxSeeEmitter;
import study.reactive.temperature.service.TemperatureSensor;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class TemperatureController {

    private final TemperatureSensor temperatureSensor;

    @GetMapping("/temperature-stream")
    public SseEmitter events(HttpServletRequest request) {
        RxSeeEmitter emitter = new RxSeeEmitter();
        temperatureSensor.temperatureStream()
            .subscribe(emitter.getSubscriber());
        return emitter;
    }
}