package com.example.fffserver;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
class StreamTest {

    @Nested
    @DisplayName("stream 처리 순서 확인 테스트")
    class OrderTest {
        @Test
        @DisplayName("stream")
        void streamOrderTest() {
            List<Integer> list = List.of(1, 2, 3, 4, 5);

            log.info("streamTest 시작");
            list.forEach(integer -> {
                log.info(integer + " 시작");
                log.info(integer.toString());
                log.info(integer + " 끝");
            });
        }

        @Test
        @DisplayName("parallelStream")
        void parallelStreamOrderTest() {
            List<Integer> list = List.of(1, 2, 3, 4, 5);

            log.info("parallelStreamTest 시작");
            list.parallelStream().forEach(integer -> {
                log.info(integer + " 시작");
                log.info(integer.toString());
                log.info(integer + " 끝");
            });
        }
    }
}
