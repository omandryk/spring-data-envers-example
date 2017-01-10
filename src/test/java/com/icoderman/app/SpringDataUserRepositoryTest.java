package com.icoderman.app;

import com.icoderman.app.dao.UserRepository;
import com.icoderman.app.model.User;
import com.icoderman.config.SpringDataJPATestConfig;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringDataJPATestConfig.class)
public class SpringDataUserRepositoryTest {

	private static User[] USERS = new User[6];

	@BeforeClass
	public static void before() {
		USERS[0] = new User(1L, "habuma", "password", "Craig Walls", "craig@habuma.com");
		USERS[1] = new User(2L, "mwalls", "password", "Michael Walls", "mwalls@habuma.com");
		USERS[2] = new User(3L, "chuck", "password", "Chuck Wagon", "chuck@habuma.com");
		USERS[3] = new User(4L, "artnames", "password", "Art Names", "art@habuma.com");
		USERS[4] = new User(5L, "newbee", "letmein", "New Bee", "newbee@habuma.com");
		USERS[5] = new User(4L, "arthur", "letmein", "Arthur Names", "arthur@habuma.com");
	}

	@Autowired
	private UserRepository userRepository;

	@Test
	@Transactional
	public void count() {
		assertEquals(4, userRepository.count());
	}

	@Test
	@Transactional
	public void findAll() {
		List<User> users = userRepository.findAll();
		assertEquals(4, users.size());
		assertUser(0, users.get(0));
		assertUser(1, users.get(1));
		assertUser(2, users.get(2));
		assertUser(3, users.get(3));
	}

	@Test
	@Transactional
	public void findByUsername() {
		assertUser(0, userRepository.findByUsername("habuma"));
		assertUser(1, userRepository.findByUsername("mwalls"));
		assertUser(2, userRepository.findByUsername("chuck"));
		assertUser(3, userRepository.findByUsername("artnames"));
	}

	@Test
	@Transactional
	public void findOne() {
		assertUser(0, userRepository.findOne(1L));
		assertUser(1, userRepository.findOne(2L));
		assertUser(2, userRepository.findOne(3L));
		assertUser(3, userRepository.findOne(4L));
	}

	@Test
	@Transactional
	public void save_newUser() {
		assertEquals(4, userRepository.count());
		User user = new User(null, "newbee", "letmein", "New Bee", "newbee@habuma.com");
		User saved = userRepository.save(user);
		assertEquals(5, userRepository.count());
		assertUser(4, saved);
		assertUser(4, userRepository.findOne(5L));
	}

	private static void assertUser(int expectedUserIndex, User actual) {
		assertUser(expectedUserIndex, actual, "Newbie");
	}

	private static void assertUser(int expectedUserIndex, User actual,
	                               String expectedStatus) {
		User expected = USERS[expectedUserIndex];
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getUsername(), actual.getUsername());
		assertEquals(expected.getPassword(), actual.getPassword());
		assertEquals(expected.getFullName(), actual.getFullName());
		assertEquals(expected.getEmail(), actual.getEmail());
	}

}
