package study.webflux.apiserver.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.webflux.apiserver.model.Product;

@Getter
@Setter
@JsonInclude(Include.NON_EMPTY)
@NoArgsConstructor
public class DetailProductDto {

    private String id;
    private String name;
    private Integer price;
    private Integer quantity;
    private String desc;

    public DetailProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.desc = product.getDesc();
    }
}