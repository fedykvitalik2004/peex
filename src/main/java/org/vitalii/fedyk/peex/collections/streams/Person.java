package org.vitalii.fedyk.peex.collections.streams;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@Getter
public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int personId;
}
