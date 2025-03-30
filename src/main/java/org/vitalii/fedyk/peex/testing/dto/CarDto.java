package org.vitalii.fedyk.peex.testing.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class CarDto {
    private long id;
    private int age;
    private String name;
}
