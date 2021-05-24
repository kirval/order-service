package orderservice.dto;

import lombok.Data;
import orderservice.validation.group.OnCreate;
import orderservice.validation.group.OnUpdate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class CustomerDto {

    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Integer coordinateX;

    @NotNull
    private Integer coordinateY;

}