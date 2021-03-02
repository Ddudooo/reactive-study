package study.webflux.apiserver.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleProductDto {

    private String id;
    private String name;

    public SimpleProductDto(String id, String name) {
        this.id = id;
        this.name = name;
    }
}