package com.example.tdd.service.product;

import com.example.tdd.domain.product.Product;
import com.example.tdd.repository.product.ProductRepository;
import com.example.tdd.service.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Mock test : 저장 동작과 호출 여부를 검증
class ProductServiceWithMockTest {

    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class); // Mock 객체 생성
        productService = new ProductService(productRepository);    // 생성자로 주입
    }

    @Test
    @DisplayName("재고 감소 후 저장 테스트")
    void testReduceStockAndSave() {
        // given
        Product product = new Product("Laptop", "전자기기", 10, 1000.0, 0.0);
        Product updatedProduct = new Product("Laptop", "전자기기", 6, 1000.0, 0.0); // 재고 감소 후 제품 기대값

        // when
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);
        Product result = productService.reduceStockAndSave(product, 4);

        // then
        assertEquals(6, result.getQuantity(), "재고 감소 후 남은 수량이 6이어야 한다."); // 실제 저장값 x
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    @DisplayName("카테고리 할인율 적용 후 저장 테스트")
    void testCalculateCategoryDiscountedPriceAndSave() {
        // given
        Product product = new Product("Laptop", "전자기기", 10, 1000.0, 0.0);
        Product discountedProduct = new Product("Laptop", "전자기기", 10, 900.0, 0.0); // 카테고리 할인 적용 후 제품 기대값

        // when
        when(productRepository.save(any(Product.class))).thenReturn(discountedProduct);
        Product result = productService.applyCategoryDiscountAndSave(product);

        // then
        assertEquals(900.0, result.getPrice(), "카테고리 할인 적용 후 가격은 900.0이어야 한다.");
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    @DisplayName("사용자 할인율 적용 후 저장 테스트")
    void testCalculateUserDiscountedPriceAndSave() {
        // given
        Product product = new Product("Laptop", "전자기기", 10, 1000.0, 0.0);
        double userDiscountRate = 0.2;
        Product discountedProduct = new Product("Laptop", "전자기기", 10, 700.0, 0.0); // 할인 적용 후 제품 기대값

        // when
        when(productRepository.save(any(Product.class))).thenReturn(discountedProduct);
        Product result = productService.applyUserDiscountAndSave(product, userDiscountRate);

        // then
        assertEquals(700.0, result.getPrice(), "사용자 할인 적용 후 가격은 700.0이어야 한다.");
        verify(productRepository, times(1)).save(any(Product.class));
    }

}
