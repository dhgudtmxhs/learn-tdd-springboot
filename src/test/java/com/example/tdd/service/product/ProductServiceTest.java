package com.example.tdd.service.product;

import com.example.tdd.domain.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(null);
    }

    @Test
    @DisplayName("기본 가격 계산 테스트")
    void testCalculateDefaultPrice() {
        // given
        Product product = new Product("Laptop", "전자기기", 10, 1000.0, 0.0);

        // when
        double price = productService.calculateDefaultPrice(product);

        // then
        assertEquals(1000.0, price, "기본 가격은 원래 가격과 동일해야 한다.");
    }

    @Test
    @DisplayName("카테고리 할인율 적용 테스트")
    void testCalculateCategoryDiscountedPrice() {
        // given
        Product product = new Product("Laptop", "전자기기", 10, 1000.0, 0.0);

        // when
        double discountedPrice = productService.calculateCategoryDiscountedPrice(product);

        // then
        assertEquals(900.0, discountedPrice, "카테고리 할인 적용 후 가격은 900.0이어야 한다.");
    }

    @Test
    @DisplayName("사용자 할인율 적용 테스트")
    void testCalculateUserDiscountedPrice() {
        // given
        Product product = new Product("Laptop", "전자기기", 10, 1000.0, 0.0);
        double userDiscountRate = 0.2;

        // when
        double discountedPrice = productService.calculateUserDiscountedPrice(product, userDiscountRate);

        // then
        assertEquals(700.0, discountedPrice, "카테고리 10% + 사용자 20% 할인 후 가격은 700.0이어야 한다.");
    }

    @Test
    @DisplayName("총 할인율 계산 테스트")
    void testCalculateTotalDiscountRate() {
        // given
        Product product = new Product("Laptop", "전자기기", 10, 1000.0, 0.0);
        double userDiscountRate = 0.2;

        // when
        double totalDiscountRate = productService.calculateTotalDiscountRate(product, userDiscountRate);

        // then
        double epsilon = 1e-9; // 허용 오차
        assertEquals(0.3, totalDiscountRate, epsilon, "카테고리 10% + 사용자 20% 할인율 합계는 30%이어야 한다.");
    }

    @Test
    @DisplayName("재고 감소 테스트")
    void testReduceStock() {
        // given
        Product product = new Product("Laptop", "전자기기", 10, 1000.0, 0.0);
        int quantityToReduce = 5;

        // when
        Product updatedProduct = productService.reduceStock(product, quantityToReduce);

        // then
        assertEquals(5, updatedProduct.getQuantity(), "재고 감소 후 남은 재고는 5개 여야 한다.");
    }

    @Test
    @DisplayName("재고 부족 예외 테스트")
    void testReduceStockInsufficient() {
        // given
        Product product = new Product("Laptop", "전자기기", 3, 1000.0, 0.0);
        int quantityToReduce = 5;

        // when & then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.reduceStock(product, quantityToReduce);
        });

        assertEquals("재고가 부족합니다.", exception.getMessage(), "재고 부족 시 예외 메시지를 반환해야 한다.");
    }
}
