package org.vitalii.fedyk.peex.collections.generics;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StoreWithoutGenerics {
    private Object item;

    public static void main(String[] args) {
        final StoreWithoutGenerics storeWithoutGenerics = new StoreWithoutGenerics();
        storeWithoutGenerics.setItem("Hello");

        final String item = (String) storeWithoutGenerics.getItem();
    }
}
