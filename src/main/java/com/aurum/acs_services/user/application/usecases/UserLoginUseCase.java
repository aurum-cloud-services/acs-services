package com.aurum.acs_services.user.application.usecases;

import com.aurum.acs_services.shared.application.exceptions.NotFoundException;
import com.aurum.acs_services.user.application.abstractions.IUserLoginUseCase;
import com.aurum.acs_services.user.application.abstractions.IUserRepository;
import com.aurum.acs_services.user.infrastructure.dtos.LoginDTO;
import com.aurum.acs_services.user.infrastructure.dtos.LoginOutputDTO;

public class UserLoginUseCase implements IUserLoginUseCase {
    private final IUserRepository userRepository;

    public UserLoginUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public LoginOutputDTO run(LoginDTO dto) {
        var user = userRepository.login(dto.identifier(), dto.password());

        if (user == null) {
            throw new NotFoundException("user");
        }

        return new LoginOutputDTO("User found", "", 200);
    }
}
