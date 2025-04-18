package io.andresgois.webflux.controller;


import io.andresgois.webflux.controller.domain.request.UserRequest;
import io.andresgois.webflux.controller.domain.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserController {

	@PostMapping
	ResponseEntity<Mono<Void>> save(@Valid @RequestBody UserRequest request);

	@GetMapping(value = "/{id}")
	ResponseEntity<Mono<UserResponse>> find(@PathVariable("id") String id);

	@GetMapping
	ResponseEntity<Flux<UserResponse>> findall();

	@PatchMapping(value = "/{id}")
	ResponseEntity<Mono<UserResponse>> update(@PathVariable String id,
											  @RequestBody UserRequest request);

	@DeleteMapping(value = "/{id}")
	ResponseEntity<Mono<Void>> delete(@PathVariable("id") String id);
}
