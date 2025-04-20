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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository repository;
	@InjectMocks
	private UserService service;
	@Mock
	private UserMapper mapper;

	@Test
	void save() {
		UserRequest userRequest = new UserRequest("Andre","an@email.com","123");
		User user = User.builder().build();

		when(mapper.toEntity(Mockito.any(UserRequest.class))).thenReturn(user);
		when(repository.save(Mockito.any(User.class))).thenReturn(Mono.just( User.builder().build()));

		Mono<User> result = service.save(userRequest);

		// verifica o comportamento de um publisher no caso o result
		StepVerifier.create(result)
				.expectNextMatches(u -> u != null)
				.expectComplete()
				.verify();

		Mockito.verify(repository, times(1)).save(Mockito.any(User.class));
	}

	@Test
	void testFindById() {
		when(repository.findById(anyString())).thenReturn(Mono.just(User.builder().build()));

		Mono<User> result = service.findById("123");

		StepVerifier.create(result)
				.expectNextMatches(user -> user.getClass() == User.class)
				.expectComplete()
				.verify();

		Mockito.verify(repository, times(1)).findById(anyString());
	}

	@Test
	void testFindAll() {
		when(repository.findAll()).thenReturn(Flux.just(User.builder().build()));

		Flux<User> result = service.findAll();

		StepVerifier.create(result)
				.expectNextMatches(user -> user.getClass() == User.class)
				.expectComplete()
				.verify();

		Mockito.verify(repository, times(1)).findAll();
	}

	@Test
	void update() {
	}

	@Test
	void deleteById() {
	}
}