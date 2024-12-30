package com.aurum.acs_services.user.infrastructure.repositories;

import com.aurum.acs_services.user.application.abstractions.IUserMapper;
import com.aurum.acs_services.user.application.abstractions.IUserRepository;
import com.aurum.acs_services.user.domain.aggregates.UserAggregate;
import com.aurum.acs_services.user.infrastructure.entities.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
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
    @Transactional
    public UserAggregate save(UserAggregate user) {
        var entity = userMapper.toPersistance(user);

        entityManager.persist(entity);

        var savedEntity = entityManager.find(entity.getClass(), entity.getCpf());

        var aggregate = userMapper.toDomain(savedEntity);

        return aggregate.orElse(null);
    }

    @Override
    public UserAggregate login(String identifier, String password) {
        try {
            var query = entityManager.createQuery(
                    "SELECT u FROM UserEntity u WHERE (u.email = :email OR u.cpf = :cpf) AND u.password = :password",
                    UserEntity.class
            );

            query.setParameter("email", identifier);
            query.setParameter("cpf", identifier);
            query.setParameter("password", password);

            var userEntity = query.getSingleResult();
            var aggregate = userMapper.toDomain(userEntity);

            return aggregate.orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}
