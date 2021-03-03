package study.webflux.apiserver.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import study.webflux.apiserver.model.dto.CreateProductDto;
import study.webflux.apiserver.model.dto.DetailProductDto;
import study.webflux.apiserver.model.dto.SimpleProductDto;
import study.webflux.apiserver.model.dto.UpdateProductDto;
import study.webflux.apiserver.service.ProductService;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class ProductHandler {

    private final ProductService productService;
    static Mono<ServerResponse> NOT_FOUND = ServerResponse.notFound().build();

    public Mono<ServerResponse> findAll(ServerRequest request) {
        PageRequest page
            = PageRequest.of(
            Integer.parseInt(request.queryParam("page").orElse("0")),
            Integer.parseInt(request.queryParam("size").orElse("20"))
        );
        return ServerResponse.ok()
            .contentType(APPLICATION_JSON)
            .body(productService.findAll(page), SimpleProductDto.class)
            .log();
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok()
            .contentType(APPLICATION_JSON)
            .body(productService.findById(id), DetailProductDto.class)
            .switchIfEmpty(NOT_FOUND).log();
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<CreateProductDto> monoDto = request.bodyToMono(CreateProductDto.class);
        return ServerResponse.ok()
            .contentType(APPLICATION_JSON)
            .body(productService.create(monoDto), String.class);
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<UpdateProductDto> monoDto = request.bodyToMono(UpdateProductDto.class);
        return ServerResponse.ok()
            .contentType(APPLICATION_JSON)
            .body(productService.update(id, monoDto), String.class)
            .switchIfEmpty(NOT_FOUND);
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok()
            .contentType(APPLICATION_JSON)
            .body(productService.delete(id), Void.class)
            .onErrorResume(Exception.class, exception -> NOT_FOUND);
    }
}