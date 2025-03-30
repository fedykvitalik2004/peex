package org.vitalii.fedyk.peex.testing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Toy {
    private String name = "Toy";

    public Toy get(final String name) {
        return new Toy(name);
    }

    public void say(final String word) {
        System.out.println(name + " is saying '" + word + "'");
    }
}
