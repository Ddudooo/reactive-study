package study.webflux.apiserver.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class UpdateProductDto {

    private String id;
    private String name;
    private Integer price;
    private Integer quantity;
    private String desc;
}