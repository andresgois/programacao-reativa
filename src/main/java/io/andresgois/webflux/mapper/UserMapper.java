package io.andresgois.webflux.mapper;

import io.andresgois.webflux.domain.request.UserRequest;
import io.andresgois.webflux.domain.User;
import io.andresgois.webflux.domain.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
		componentModel = "spring",
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface UserMapper {
	@Mapping(target = "id", ignore = true)
	User toEntity(final UserRequest user);

	UserResponse toResponse(final User user);

	@Mapping(target = "id", ignore = true)
	User toEntity(final UserRequest user, @MappingTarget User entity);
}
