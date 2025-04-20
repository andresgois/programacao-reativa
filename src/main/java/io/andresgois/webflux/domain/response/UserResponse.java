package io.andresgois.webflux.domain.response;

public record UserResponse(
		String id,
		String name,
		String email,
		String password
) {
}