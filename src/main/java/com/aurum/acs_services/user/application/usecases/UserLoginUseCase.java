package com.aurum.acs_services.user.application.usecases;

import com.aurum.acs_services.shared.application.exceptions.NotFoundException;
import com.aurum.acs_services.user.application.abstractions.IUserLoginUseCase;
import com.aurum.acs_services.user.application.abstractions.IUserRepository;
import com.aurum.acs_services.user.infrastructure.dtos.LoginDTO;
import com.aurum.acs_services.user.infrastructure.dtos.LoginOutputDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserLoginUseCase implements IUserLoginUseCase {
    private final IUserRepository userRepository;

    public UserLoginUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<LoginOutputDTO> run(LoginDTO dto) {
        var user = userRepository.login(dto.identifier(), dto.password());

        if (user == null) {
            throw new NotFoundException("user");
        }

        return ResponseEntity.ok(new LoginOutputDTO("User found", "", 200));
    }
}
