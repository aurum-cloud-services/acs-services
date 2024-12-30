package com.aurum.acs_services.user.infrastructure.repositories;

import com.aurum.acs_services.user.application.abstractions.IUserMapper;
import com.aurum.acs_services.user.domain.aggregates.UserAggregate;
import com.aurum.acs_services.user.infrastructure.entities.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserMapper implements IUserMapper {
    @Override
    public UserEntity toPersistance(UserAggregate user) {
        return UserEntity.builder()
                .cpf(user.getCpf())
                .id(user.getId())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .name(user.getName())
                .password(user.getPassword())
                .build();
    }

    @Override
    public Optional<UserAggregate> toDomain(UserEntity user) {
        try {
            var aggregate = UserAggregate.build(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getCpf(),
                    user.getPassword(),
                    user.getCreatedAt());

            return Optional.of(aggregate);
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
}
