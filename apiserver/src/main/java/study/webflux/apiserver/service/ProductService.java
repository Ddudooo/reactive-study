package study.webflux.apiserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import study.webflux.apiserver.model.Product;
import study.webflux.apiserver.model.dto.CreateProductDto;
import study.webflux.apiserver.model.dto.DetailProductDto;
import study.webflux.apiserver.model.dto.SimpleProductDto;
import study.webflux.apiserver.model.dto.UpdateProductDto;
import study.webflux.apiserver.repo.ProductRepo;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public Flux<SimpleProductDto> findAll(Pageable pageable) {
        return productRepo.findAllWithSimpleDto(pageable);
    }

    public Mono<DetailProductDto> findById(String id) {
        return productRepo.findById(id)
            .switchIfEmpty(Mono.empty())
            .flatMap(e -> Mono.just(new DetailProductDto(e)))
            .log();
    }

    @Transactional
    public Mono<String> create(Mono<CreateProductDto> monoDto) {
        return monoDto.flatMap(dto ->
            productRepo.save(
                new Product(
                    dto.getName(),
                    dto.getPrice(),
                    dto.getQuantity(),
                    dto.getDesc()
                ))
                .flatMap(p -> Mono.just(p.getId())));
    }

    @Transactional
    public Mono<String> update(String id, Mono<UpdateProductDto> monoDto) {
        return productRepo.findById(id)
            .flatMap(e ->
                monoDto.flatMap(dto -> {
                    e.changeName(dto.getName());
                    e.changePrice(dto.getPrice());
                    e.changeQuantity(dto.getQuantity());
                    e.changeDesc(dto.getDesc());
                    return productRepo.save(e);
                })
            )
            .flatMap(p -> Mono.just(p).switchIfEmpty(Mono.empty()))
            .flatMap(p -> Mono.just(p.getId()));
    }

    @Transactional
    public Mono<Void> delete(String id) {
        return productRepo.deleteById(id);
    }
}