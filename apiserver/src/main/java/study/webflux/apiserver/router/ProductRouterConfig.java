package study.webflux.apiserver.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import study.webflux.apiserver.handler.ProductHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class ProductRouterConfig {

    @Bean
    public RouterFunction<ServerResponse> productRouter(ProductHandler handler) {
        return RouterFunctions
            .route(GET("/api/products").and(accept(APPLICATION_JSON))
                , handler::findAll)
            .andRoute(GET("/api/products/{id}").and(accept(APPLICATION_JSON))
                , handler::findById)
            .andRoute(POST("/api/products").and(accept(APPLICATION_JSON))
                , handler::create)
            .andRoute(PUT("/api/products/{id}").and(accept(APPLICATION_JSON))
                , handler::update)
            .andRoute(DELETE("/api/products/{id}").and(accept(APPLICATION_JSON))
                , handler::delete);
    }
}