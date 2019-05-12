package test.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rmpksoft.aop.config.ServiceConfig;
import com.rmpksoft.aop.service.PostService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ServiceConfig.class })
public class TestPostService {

	@Autowired
	PostService postService;

	@Test
	void testInit() {
		assertNotNull(postService);
	}

}
