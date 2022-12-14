package com.example.fffserver.domain.answer.domain;

import com.example.fffserver.domain.answer.domain.entity.Answer;
import com.example.fffserver.domain.form.domain.FormRepository;
import com.example.fffserver.domain.form.domain.entity.Form;
import com.example.fffserver.domain.form.domain.vo.Event;
import com.example.fffserver.domain.form.domain.vo.EventFactory;
import com.example.fffserver.domain.question.domain.entity.Question;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SubmissionServiceTest {

    @Autowired
    private SubmissionService submissionService;
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    FormRepository formRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        formRepository.deleteAll();
    }

    @Nested
    @DisplayName("이벤트가 1개일 때")
    class oneEvent {

        Form form;

        @Test
        @DisplayName("선착순 30명 이벤트에 100명이 접속하면 70명의 대기인원이 남는다.")
        void event() throws InterruptedException {
            final int user = 100;
            final int winner = 30;
            final CountDownLatch countDownLatch = new CountDownLatch(user);

            form = new Form(LocalDateTime.now(), LocalDateTime.of(2022, Month.DECEMBER, 30, 23, 59), winner, List.of(new Question("유저 이름", "TEXT")));
            formRepository.insert(form);

            Event event = EventFactory.createfromForm(form);

            List<Thread> workers = Stream.generate(() -> new Thread(new Woker(countDownLatch, event)))
                    .limit(user)
                    .collect(Collectors.toList());
            workers.forEach(Thread::start);
            countDownLatch.await();

            Awaitility.await().atMost(Duration.ofSeconds(10))
                    .untilAsserted(() -> {
                        Long waitingQueueSize = submissionService.getWaitingQueueSize(event);
                        assertThat(waitingQueueSize).isEqualTo(user - winner);
                    });
        }

        @Test
        @DisplayName("redis tearDown")
        void tearDownRedisData() {
            redisTemplate.opsForZSet().remove(form.getId().toString(), 0, -1);
        }
    }

    @Nested
    @DisplayName("다수의 이벤트가 동시에 진행될 때")
    class manyEvent {

        Form formFirst;
        Form formSecond;

        @Test
        @DisplayName("각각의 이벤트는 충돌되지 않고, 병렬 처리되어야한다.")
        void event() throws InterruptedException {
            final int user = 100;
            final int winner = 30;
            final CountDownLatch countDownLatch1 = new CountDownLatch(user);
            final CountDownLatch countDownLatch2 = new CountDownLatch(user);

            formFirst = new Form(LocalDateTime.now(), LocalDateTime.of(2022, Month.DECEMBER, 30, 23, 59), winner, List.of(new Question("유저 이름", "TEXT")));
            formRepository.insert(formFirst);

            formSecond = new Form(LocalDateTime.now(), LocalDateTime.of(2022, Month.DECEMBER, 30, 23, 59), winner, List.of(new Question("유저 이름", "TEXT")));
            formRepository.insert(formSecond);

            Event event1 = EventFactory.createfromForm(formFirst);
            Event event2 = EventFactory.createfromForm(formSecond);

            List<Thread> workers1 = Stream.generate(() -> new Thread(new Woker(countDownLatch1, event1)))
                    .limit(user)
                    .collect(Collectors.toList());
            List<Thread> workers2 = Stream.generate(() -> new Thread(new Woker(countDownLatch2, event2)))
                    .limit(user)
                    .collect(Collectors.toList());
            workers1.forEach(Thread::start);
            workers2.forEach(Thread::start);
            countDownLatch1.await();
            countDownLatch2.await();

            Awaitility.await().atMost(Duration.ofSeconds(10))
                    .untilAsserted(() -> {
                        Long waitingQueueSize1 = submissionService.getWaitingQueueSize(event1);
                        Long waitingQueueSize2 = submissionService.getWaitingQueueSize(event2);

                        assertThat(waitingQueueSize1).isEqualTo(user - winner);
                        assertThat(waitingQueueSize2).isEqualTo(user - winner);
                    });
        }

        @Test
        @DisplayName("redis tearDown")
        void tearDownRedisData() {
            redisTemplate.opsForZSet().remove(formFirst.getId().toString(), 0, -1);
            redisTemplate.opsForZSet().remove(formSecond.getId().toString(), 0, -1);
        }
    }

    private class Woker implements Runnable {

        private CountDownLatch countDownLatch;
        private Event event;

        public Woker(CountDownLatch countDownLatch, Event event) {
            this.countDownLatch = countDownLatch;
            this.event = event;
        }

        @Override
        public void run() {
            String userName = UUID.randomUUID().toString();
            submissionService.addQueue(userName, event, List.of(new Answer(userName, event.getFormId())));
            countDownLatch.countDown();
        }
    }
}