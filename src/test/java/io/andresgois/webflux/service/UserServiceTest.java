package io.andresgois.webflux.service;

import io.andresgois.webflux.domain.User;
import io.andresgois.webflux.domain.request.UserRequest;
import io.andresgois.webflux.mapper.UserMapper;
import io.andresgois.webflux.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository repository;
	@InjectMocks
	private UserService userService;
	@Mock
	private UserMapper mapper;

	@Test
	void save() {
		UserRequest userRequest = new UserRequest("Andre","an@email.com","123");
		User user = User.builder().build();

		Mockito.when(mapper.toEntity(Mockito.any(UserRequest.class))).thenReturn(user);
		Mockito.when(repository.save(Mockito.any(User.class))).thenReturn(Mono.just( User.builder().build()));

		Mono<User> result = userService.save(userRequest);

		// verifica o comportamento de um publisher no caso o result
		StepVerifier.create(result)
				.expectNextMatches(u -> u != null)
				.expectComplete()
				.verify();

		Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(User.class));
	}

	@Test
	void findById() {
	}

	@Test
	void findAll() {
	}

	@Test
	void update() {
	}

	@Test
	void deleteById() {
	}
}