package ru.gb.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ProductRepository {

    private final Map<Long, Product> prodMap = new ConcurrentHashMap<Long, Product>();

    private final AtomicLong aLong = new AtomicLong(0);
    private final AtomicInteger aInt = new AtomicInteger(0);


    public List<Product> findAll() {
        return new ArrayList<>(prodMap.values());
    }

    public Product findById(long id){return prodMap.get(id); }

    public void insert(Product product){
        long id = aLong.incrementAndGet();
        int cost = aInt.incrementAndGet();
        product.getCost();
        product.setId(id);
        prodMap.put(id, product);
    }

    public void update(Product product){prodMap.put(product.getId(), product);}
    public void del(long id){prodMap.remove(prodMap.remove(id));}
}
