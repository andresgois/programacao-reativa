package io.andresgois.webflux.domain.request;

public record UserRequest(
        String name,
        String email,
        String password
) {
}
