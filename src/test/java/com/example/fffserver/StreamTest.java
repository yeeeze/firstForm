package com.example.fffserver;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

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

    @Nested
    @DisplayName("SubmissionService publish()와 비슷한 환경에서 병렬테스트")
    class Parallel {

        @Test
        @DisplayName("user 로직은 순차 처리")
        void parallelStreamTest1() {
            Map<String, Integer> map = Map.of("1", 1, "2", 2);
            Map<Integer, Set<String>> setMap = Map.of(1, Set.of("11", "12"), 2, Set.of("22", "21"));

            map.entrySet().parallelStream().forEach(entry -> {
                Integer value = entry.getValue();

                Set<String> strings = setMap.get(value);
                for (String s : strings) {
                    log.info("이벤트: {}, 유저: {}", value, s);
                }
            });
        }

        @Test
        @DisplayName("user 로직도 병렬 처리")
        void parallelStreamTest2() {
            Map<String, Integer> map = Map.of("1", 1, "2", 2);
            Map<Integer, Set<String>> setMap = Map.of(1, Set.of("11", "12"), 2, Set.of("22", "21"));

            map.entrySet().parallelStream().forEach(entry -> {
                Integer value = entry.getValue();

                Set<String> strings = setMap.get(value);
                strings.parallelStream().forEach((s) -> {
                    log.info("이벤트: {}, 유저: {}", value, s);
                });
            });
        }
    }

}
