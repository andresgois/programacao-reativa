package io.andresgois.webflux.controller.impl;


import io.andresgois.webflux.controller.UserController;
import io.andresgois.webflux.controller.domain.request.UserRequest;
import io.andresgois.webflux.controller.domain.response.UserResponse;
import io.andresgois.webflux.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

	private final UserService userService;

	@Override
	public ResponseEntity<Mono<Void>> save(UserRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userService.save(request).then());
	}

	@Override
	public ResponseEntity<Mono<UserResponse>> find(String id) {
		return null;
	}

	@Override
	public ResponseEntity<Flux<UserResponse>> findall() {
		return null;
	}

	@Override
	public ResponseEntity<Mono<UserResponse>> update(String id, UserRequest request) {
		return null;
	}

	@Override
	public ResponseEntity<Mono<Void>> delete(String id) {
		return null;
	}
}

