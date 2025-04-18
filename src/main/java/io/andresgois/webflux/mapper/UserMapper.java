package io.andresgois.webflux.mapper;

import io.andresgois.webflux.controller.domain.request.UserRequest;
import io.andresgois.webflux.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
}
