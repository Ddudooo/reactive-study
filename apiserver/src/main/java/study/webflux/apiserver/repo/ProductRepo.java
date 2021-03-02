package study.webflux.apiserver.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import study.webflux.apiserver.model.Product;
import study.webflux.apiserver.model.dto.SimpleProductDto;

public interface ProductRepo extends ReactiveMongoRepository<Product, String> {

    @Query(value = "{}", fields = "{_id: 1, name: 1}")
    Flux<SimpleProductDto> findAllWithSimpleDto(Pageable page);
}
