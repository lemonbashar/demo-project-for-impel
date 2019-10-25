package com.demoforimpel;

import com.demoforimpel.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class DemoForImpelApplicationTests {
	@MockBean
	private UserService userService;

	@Test
	void contextLoads() {
		userService.createUser("lemon","lemon");
	}

}
