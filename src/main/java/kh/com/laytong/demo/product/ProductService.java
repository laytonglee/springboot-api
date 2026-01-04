package kh.com.laytong.demo.product;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {
    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public Product createProduct(CreateProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setId(nextId.getAndIncrement());
        products.put(product.getId(), product);

        return product;
    }

    public List<Product> findAll(String category){
        return products.values().stream().filter(
                p -> p.getCategory().equalsIgnoreCase(category)
        ).toList();
    }

    public Optional<Product> findById(Long id){
        return Optional.ofNullable(products.get(id));
    }

    public Optional<Product> update(Long id, UpdateProductRequest request){
        Product product = products.get(id);
        if (product == null){
            return Optional.empty();
        }
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());

        products.put(id, product);
        return Optional.of(product);
    }

    public boolean delete(Long id){
        return products.remove(id) != null;
    }
}
