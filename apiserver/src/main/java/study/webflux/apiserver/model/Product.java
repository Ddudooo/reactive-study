package study.webflux.apiserver.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import static org.springframework.util.ObjectUtils.isEmpty;
import static org.springframework.util.StringUtils.hasText;

@Getter
@Document(collection = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    private String id;
    @Indexed
    private String name;
    @Indexed
    private int price;
    @Indexed
    private int quantity;
    private String desc;

    public Product(String name, int price, int quantity, String desc) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.desc = desc;
    }

    public void changeName(String name) {
        if (hasText(name)) {
            this.name = name;
        }
    }

    public void changePrice(Integer price) {
        if (!isEmpty(price)) {
            this.price = price;
        }
    }

    public void changeQuantity(Integer quantity) {
        if (!isEmpty(quantity)) {
            this.quantity = quantity;
        }
    }

    public void changeDesc(String desc) {
        if (hasText(desc)) {
            this.desc = desc;
        }
    }
}