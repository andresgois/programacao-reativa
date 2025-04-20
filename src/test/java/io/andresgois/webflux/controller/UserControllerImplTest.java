package io.andresgois.webflux.controller;

import com.mongodb.reactivestreams.client.MongoClient;
import io.andresgois.webflux.domain.User;
import io.andresgois.webflux.domain.request.UserRequest;
import io.andresgois.webflux.mapper.UserMapper;
import io.andresgois.webflux.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static reactor.core.publisher.Mono.just;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebTestClient
class UserControllerImplTest {

	private static final String ID = "123456";
	private static final String NAME = "Andre";
	private static final String EMAIL = "andre@mail.com";
	private static final String PASSWORD = "123";
	private static final String BASE_URI = "/users";

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private UserService service;

	@MockBean
	private UserMapper mapper;

	@MockBean
	private MongoClient mongoClient;


	@Test
	@DisplayName("Test endpoint save with success")
	void testSaveWithSuccess() {
		final var request = new UserRequest(NAME, EMAIL, PASSWORD);

		when(service.save(any(UserRequest.class))).thenReturn(just(User.builder().build()));

		webTestClient.post().uri(BASE_URI)
				.contentType(APPLICATION_JSON)
				.body(fromValue(request))
				.exchange()
				.expectStatus().isCreated();

		verify(service).save(any(UserRequest.class));
	}

	@Test
	void findById() {
	}

	@Test
	void findall() {
	}

	@Test
	void update() {
	}

	@Test
	void delete() {
	}
}