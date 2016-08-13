package com.spring.web.demo.logic.mock;

import com.spring.web.demo.logic.service.DefaultProductService;
import com.spring.web.demo.persistent.entity.Product;
import com.spring.web.demo.persistent.repository.ProductRepository;
import org.springframework.data.domain.*;

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
        when(repository.findAll(any(PageRequest.class))).thenReturn(mockAllProducts());
        when(repository.findByParameters(any(Product.class), any(PageRequest.class))).thenReturn(mockAllProducts());
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

    private Page<Product> mockAllProducts() {
        final List<Product> result = new ArrayList<>();

        for (long i = 0; i < DefaultProductService.DEFAULT_PAGE_SIZE; i++) {
            final Product product = new Product();
            product.setId(i);
            product.setTitle("iPhone 6");
            product.setSku("111-234-567-890" + i);
            product.setDescription("The iPhone 6 and iPhone 6 Plus are smartphones designed and marketed by Apple Inc. " +
                    "The devices are part of the iPhone series and were announced on September 9, 2014, and released on " +
                    "September 19, 2014.[16] The iPhone 6 and iPhone 6 Plus jointly serve as successors " +
                    "to the iPhone 5c and iPhone 5s.");
            result.add(product);
        }

        return new PageImpl<>(result,
                new PageRequest(1, DefaultProductService.DEFAULT_PAGE_SIZE, Sort.Direction.ASC, "title"),
                result.size());
    }
}
