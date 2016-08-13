package com.spring.web.demo.logic.mock;

import com.spring.web.demo.persistent.entity.Product;
import com.spring.web.demo.persistent.repository.ProductRepository;

import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class MockBuilder {

    private final ProductRepository repository;

    private MockBuilder(ProductRepository repository) {
        this.repository = repository;
    }

    public static void build(ProductRepository repository) {
        final MockBuilder builder = new MockBuilder(repository);
        builder.init();
    }

    private void init() {
        when(repository.save(any(Product.class))).thenReturn(mockExistingProduct());
        when(repository.findById(anyLong())).thenReturn(Optional.of(mockExistingProduct()));
        doNothing().when(repository).delete(anyLong());
        when(repository.findAll()).thenReturn(mockAllProducts());
        when(repository.findByParameters(any(Product.class))).thenReturn(Collections.singletonList(mockExistingProduct()));
    }

    private Product mockExistingProduct() {
        final Product product = new Product();
        product.setId(1L);
        product.setTitle("iPhone 5s");
        product.setSku("111-234-567-8901");
        product.setDescription("The iPhone 5S (marketed with a stylized lowercase 's' as iPhone 5s) is a smartphone " +
                "designed and marketed by Apple Inc. Part of the iPhone series, the device was unveiled on September 10," +
                " 2013 at Apple's Cupertino headquarters. It was released on September 20, 2013 along with its mid-range" +
                " counterpart, the iPhone 5C.[12]");

        return product;
    }

    private List<Product> mockAllProducts() {
        final Product first = mockExistingProduct();

        final Product second = new Product();
        second.setId(2L);
        second.setTitle("iPhone 6");
        second.setSku("111-234-567-8902");
        second.setDescription("The iPhone 6 and iPhone 6 Plus are smartphones designed and marketed by Apple Inc. " +
                "The devices are part of the iPhone series and were announced on September 9, 2014, and released on " +
                "September 19, 2014.[16] The iPhone 6 and iPhone 6 Plus jointly serve as successors " +
                "to the iPhone 5c and iPhone 5s.");

        return new ArrayList<Product>() {{
            add(first);
            add(second);
        }};
    }
}
