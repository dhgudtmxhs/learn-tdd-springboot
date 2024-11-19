package com.example.tdd.service.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceTest {

    @Test
    void testAddition() { // 하나의 단위테스트
    // TDD의 기본 사이클(Red -> Green -> Refactor) 순서를 지킨다.
    // Given-When-Then 패턴으로 TDD에서 테스트를 구조화한다.

        // Given - 테스트 환경 설정
        CalculatorService calcService = new CalculatorService();

        // When - 테스트 동작
        int result = calcService.add(2,3);
        // 메서드가 없거나 메서드 값이 잘못구현 -> Red
        // 메서드가 테스트 통과하도록 구현됨 -> Green
        // Green일 때, 코드 개선 작업 -> Refector


        // Then - 테스트 성공/실패 여부 판단
        assertEquals(5, result);

    }

}
