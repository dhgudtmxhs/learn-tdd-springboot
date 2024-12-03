package com.example.tdd.service.product;

import com.example.tdd.domain.product.Product;
import com.example.tdd.repository.product.ProductRepository;
import com.example.tdd.service.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll(); // 테스트 실행 전 데이터 정리
    }

    @Test
    @DisplayName("재고 감소 후 저장 통합 테스트")
    void testReduceStockAndSaveIntegration() throws Exception {
        // given
        Product product = Product.builder()
                .name("Laptop")
                .category("전자기기")
                .quantity(10)
                .price(1000.0)
                .discountRate(0.0)
                .build();
        productRepository.save(product);

        // when
        Product updatedProduct = productService.reduceStockAndSave(product, 4);

        // then
        assertNotNull(updatedProduct, "재고 감소 후 저장된 제품이 null이 아니어야 한다.");
        assertEquals(6, updatedProduct.getQuantity(), "재고가 4 감소한 후에는 6이어야 한다.");
    }

    @Test
    @DisplayName("카테고리 할인 적용 후 저장 통합 테스트")
    void testCalculateCategoryDiscountedPriceAndSaveIntegration() throws Exception {
        // given
        Product product = Product.builder()
                .name("Laptop")
                .category("전자기기")
                .quantity(10)
                .price(1000.0)
                .discountRate(0.0)
                .build();
        productRepository.save(product);

        // when
        double discountedPrice = productService.calculateCategoryDiscountedPrice(product);
        Product updatedProduct = Product.builder()
                .name(product.getName())
                .category(product.getCategory())
                .quantity(product.getQuantity())
                .price(discountedPrice)
                .discountRate(product.getDiscountRate())
                .build();
        updatedProduct = productRepository.save(updatedProduct);

        // then
        assertEquals(900.0, discountedPrice, "카테고리 할인 적용 후 가격은 900.0이어야 한다.");
        assertEquals(900.0, updatedProduct.getPrice(), "저장된 제품의 가격이 900.0이어야 한다.");
    }

    @Test
    @DisplayName("사용자 할인율 적용 후 저장 통합 테스트")
    void testCalculateUserDiscountedPriceAndSaveIntegration() throws Exception {
        // given
        Product product = Product.builder()
                .name("Laptop")
                .category("전자기기")
                .quantity(10)
                .price(1000.0)
                .discountRate(0.0)
                .build();
        productRepository.save(product);

        // when
        double discountedPrice = productService.calculateUserDiscountedPrice(product, 0.2);
        Product updatedProduct = Product.builder()
                .name(product.getName())
                .category(product.getCategory())
                .quantity(product.getQuantity())
                .price(discountedPrice)
                .discountRate(product.getDiscountRate())
                .build();
        updatedProduct = productRepository.save(updatedProduct);

        // then
        assertEquals(700.0, discountedPrice, "카테고리 10% + 사용자 20% 할인 후 가격은 700.0이어야 한다.");
        assertEquals(700.0, updatedProduct.getPrice(), "저장된 제품의 가격이 700.0이어야 한다.");
    }
}
