package com.curso.nivel1_llm_directo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"llm.provider-gemini",
		"gemini.api.key-test-key-placeholder"
})
class Nivel1LlmDirectoApplicationTests {

	@Test
	void contextLoads() {
	}

}
