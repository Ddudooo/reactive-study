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
import study.webflux.apiserver.model.dto.SimpleProductDto;
import study.webflux.apiserver.model.dto.UpdateProductDto;
import study.webflux.apiserver.repo.ProductRepo;

import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public Flux<SimpleProductDto> findAll(Pageable pageable) {
        return productRepo.findAllWithSimpleDto(pageable);
    }

    public Mono<Product> findById(String id) {
        return productRepo.findById(id);
    }

    @Transactional
    public Mono<Product> create(CreateProductDto dto) {
        Product product = new Product(dto.getName(), dto.getPrice(), dto.getQuantity(),
            dto.getDesc());
        return productRepo.save(product);
    }

    @Transactional
    public Mono<Product> update(UpdateProductDto dto) {
        Mono<Product> byId = productRepo.findById(dto.getId());
        Optional<Product> product = byId.blockOptional();
        Product foundProduct = product.orElseThrow();
        if (hasText(dto.getName())) {
            foundProduct.changeName(dto.getName());
        }
        if (hasText(dto.getDesc())) {
            foundProduct.changeDesc(dto.getDesc());
        }
        if (dto.getPrice() != null) {
            foundProduct.changePrice(dto.getPrice());
        }
        if (dto.getQuantity() != null) {
            foundProduct.changeQuantity(dto.getPrice());
        }
        return productRepo.save(foundProduct);
    }

    @Transactional
    public Mono<Void> delete(String id) {
        return productRepo.deleteById(id);
    }
}