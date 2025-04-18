package io.andresgois.webflux.repository;

import io.andresgois.webflux.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class UserRepository {

	private final ReactiveMongoTemplate mongoTemplate;

	public Mono<User> save(User user) {
		return mongoTemplate.save(user);
	}

}
