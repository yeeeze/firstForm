package com.example.fffserver;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Slf4j
class StreamTest {

    @Nested
    @DisplayName("stream 처리 순서 확인 테스트")
    class OrderTest {
        @Test
        @DisplayName("stream")
        void streamOrderTest() {
            Map<String, Integer> map = Map.of("1", 1, "2", 2, "3", 3, "4", 4, "5", 5);

            log.info("streamTest 시작");
            map.entrySet().forEach(integer -> {
                log.info(integer + " 시작");
                log.info(integer.toString());
                log.info(integer + " 끝");
            });
        }

        @Test
        @DisplayName("parallelStream")
        void parallelStreamOrderTest() {
            Map<String, Integer> map = Map.of("1", 1, "2", 2, "3", 3, "4", 4, "5", 5);

            log.info("parallelStreamTest 시작");
            map.entrySet().parallelStream().forEach(integer -> {
                log.info(integer + " 시작");
                log.info(integer.toString());
                log.info(integer + " 끝");
            });
        }
    }
}
