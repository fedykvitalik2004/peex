package org.vitalii.fedyk.peex.testing.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.vitalii.fedyk.peex.testing.Toy;

@RestController
@RequestMapping("/api/toys")
@AllArgsConstructor
public class ToyController {
    private Toy toy;

    @PostMapping
    public Toy create(final Toy toy) {
        return toy.get(toy.getName());
    }

    @GetMapping
    public String getName(@RequestParam int page) {
        return toy.getName() + " " + page;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        toy.say(id);
    }
}
