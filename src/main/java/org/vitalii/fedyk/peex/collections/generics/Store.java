package org.vitalii.fedyk.peex.collections.generics;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Store <T>{
    private T item;

    public static void main(String[] args) {
        final Store<String> store= new Store<>();
        store.setItem("Hello");
        
        final String item = store.getItem();
    }
}
