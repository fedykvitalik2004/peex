package org.vitalii.fedyk.peex;

import java.util.*;


class Exercise {

    public Cache useCache() {
        Cache cache = new Cache();

        cache.search("www.facebook.com");
        cache.search("www.google.com");
        cache.search("www.amazon.com");
        cache.search("www.google.com");
        cache.search("www.facebook.com");
        cache.search("www.twitter.com");
        cache.search("www.globalsoftwaresuppot.com");
        cache.search("www.bbc.com");
        cache.search("www.cnn.com");
        cache.search("www.google.com");
        cache.search("www.amazon.com");
        cache.search("www.google.com");
        cache.search("www.facebook.com");

        // the cache remembers the recently visited URLs (in this case: facebook, google, amazon, cnn, bbc - 5 because the capacity of the cache is 5)


        cache.showCache();

        // I need it to be able to test wether your implmenetation works fine
        return cache;
    }
}

class Pair {
    private String Url;
    private String content;

    public Pair() {
    }

    public Pair(String Url, String content) {
        this.Url = Url;
        this.content = content;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String URL) {
        this.Url = URL;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Pair pair = (Pair) object;
        return Objects.equals(Url, pair.Url) && Objects.equals(content, pair.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Url, content);
    }
}

class Cache {

    // we can store up to 5 items in the cache
    private static final int CAPACITY = 5;
    // let's use a list to store the items (URLs)
    private List<Pair> cache = new LinkedList<>();

    public void search(String url) {
        Pair foundPair = null;
        for (Pair pair: cache) {
            if (pair.getUrl().equals(url)) {
                foundPair = pair;
                return;
            }
        }

        if (foundPair != null) {
            cache.remove(foundPair);
            cache.add(0, foundPair);
        } else {
            final Pair newPair = new Pair(url,"Random data for a given URL...");
            if(cache.size()>=CAPACITY) cache.remove(CAPACITY-1);
            cache.add(0, newPair);
        }
    }

    // I need it to test your implementation
    public List<Pair> getCache() {
        return this.cache;
    }

    public void showCache() {
        for(Pair pair : cache)
            System.out.println(pair);
    }
}