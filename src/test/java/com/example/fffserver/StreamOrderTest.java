package com.example.fffserver;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Slf4j
class StreamOrderTest {

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
    @DisplayName("SubmissionService와 비슷한 환경에서 병렬테스트")
    class Parallel {

        @Nested
        @DisplayName("이벤트 Map을 ConcurrentHashMap으로 구현하면")
        class ConcurrentHashMapTest {
            Stream<Map.Entry<String, Integer>> getConcurrentHashMapParallelStream() {
                Map<String, Integer> map = new ConcurrentHashMap<>(Map.of("1", 1, "2", 2, "3", 3, "4", 4, "5", 5, "6", 6));

                return map.entrySet().parallelStream();
            }

            @Test
            @DisplayName("모든 event가 1개의 쓰레드에서 처리된다.")
            void parallelStreamTest1() {
                Map<Integer, Set<String>> setMap = Map.of(
                        1, Set.of("11", "12"), 2, Set.of("22", "21"), 3, Set.of("33, 31"),
                        4, Set.of("44", "41"), 5, Set.of("55", "51"), 6, Set.of("66, 61")
                );

                getConcurrentHashMapParallelStream().forEach(entry -> {
                    Integer value = entry.getValue();

                    Set<String> userNames = setMap.get(value);
                    for (String userName : userNames) {
                        log.info("쓰레드명 {}, 이벤트: {}, 유저: {}", Thread.currentThread().getName(), value, userName);
                    }
                });
            }
        }
        @Nested
        @DisplayName("이벤트 Map을 HashMap으로 구현하면")
        class HashMapTest {
            Stream<Map.Entry<String, Integer>> getHashMapParallelStream() {
                Map<String, Integer> map = new HashMap<>(Map.of("1", 1, "2", 2));

                return map.entrySet().parallelStream();
            }

            @Test
            @DisplayName("event 별로 별도의 쓰레드가 생성되어 처리된다.")
            void parallelStreamTest1() {
                Map<Integer, Set<String>> setMap = Map.of(
                        1, Set.of("11", "12", "13", "14", "15", "16"), 2, Set.of("22", "23", "24", "25", "26", "27")
                );

                getHashMapParallelStream().forEach(entry -> {
                    Integer value = entry.getValue();

                    Set<String> userNames = setMap.get(value);
                    for (String userName : userNames) {
                        log.info("{} 시작", userName);
                        log.info("쓰레드명 {}, 이벤트: {}, 유저: {}", Thread.currentThread().getName(), value, userName);
                        log.info("{} 끝", userName);
                    }
                });
            }
        }
    }
}
