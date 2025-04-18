package io.andresgois.webflux.controller.domain.response;

public record UserResponse(
		String id,
		String name,
		String email
) {
}