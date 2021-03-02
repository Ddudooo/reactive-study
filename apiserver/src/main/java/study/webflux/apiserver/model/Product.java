package study.webflux.apiserver.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
        this.name = name;
    }

    public void changePrice(int price) {
        this.price = price;
    }

    public void changeQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void changeDesc(String desc) {
        this.desc = desc;
    }
}