package com.icoderman.app;

import com.icoderman.app.dao.UserRepository;
import com.icoderman.app.model.User;
import com.icoderman.config.SpringDataJPATestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringDataJPATestConfig.class)
public class SpringDataUserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	//@Transactional
	public void a() {
		User user = new User(null, "myusername", "mypwd", "Oleksandr Mandryk", "test@test.com");
		User saved = userRepository.save(user);
		assertFalse(saved == null);

	User user1 = userRepository.findByUsername("myusername");
			user1.setFullName("Updated Name");
		user1.setPassword("superpwd");
			User saved1 = userRepository.save(user1);
			assertFalse(saved1 == null);
		
	}


}
