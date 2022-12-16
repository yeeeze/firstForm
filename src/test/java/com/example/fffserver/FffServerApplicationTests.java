package com.example.fffserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "scheduler.enable=false")
class FffServerApplicationTests {

	@Test
	void contextLoads() {
	}

}
