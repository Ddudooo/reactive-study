package study.webflux.apiserver.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import study.webflux.apiserver.model.dto.SimpleProductDto;
import study.webflux.apiserver.service.ProductService;

@Component
@RequiredArgsConstructor
public class ProductHandler {

    private final ProductService productService;
    static Mono<ServerResponse> NOT_FOUND = ServerResponse.notFound().build();

    public Mono<ServerResponse> getAllProducts(ServerRequest request) {
        PageRequest page = PageRequest.of(Integer.parseInt(request.pathVariable("page")),
            Integer.parseInt(request.pathVariable("size")));
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(productService.findAll(page), SimpleProductDto.class);
    }
    

}