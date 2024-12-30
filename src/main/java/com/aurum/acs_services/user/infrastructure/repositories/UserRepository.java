package com.aurum.acs_services.user.infrastructure.repositories;

import com.aurum.acs_services.user.application.abstractions.IUserMapper;
import com.aurum.acs_services.user.application.abstractions.IUserRepository;
import com.aurum.acs_services.user.domain.aggregates.UserAggregate;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepository {
    private final EntityManager entityManager;
    private final IUserMapper userMapper;

    public UserRepository(EntityManager entityManager, IUserMapper userMapper) {
        this.entityManager = entityManager;
        this.userMapper = userMapper;
    }

    @Override
    public UserAggregate save(UserAggregate user) {
        var entity = userMapper.toPersistance(user);

        entityManager.persist(entity);

        var savedEntity = entityManager.find(entity.getClass(), entity.getCpf());

        var aggregate = userMapper.toDomain(savedEntity);

        return aggregate.orElse(null);
    }
}
