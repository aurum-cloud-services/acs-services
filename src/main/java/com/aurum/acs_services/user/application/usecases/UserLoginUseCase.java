package com.aurum.acs_services.user.application.usecases;

import com.aurum.acs_services.user.application.abstractions.IJwtHandler;
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
    private final IJwtHandler jwtHandler;

    public UserLoginUseCase(IUserRepository userRepository, IJwtHandler jwtHandler) {
        this.userRepository = userRepository;
        this.jwtHandler = jwtHandler;
    }

    @Override
    public ResponseEntity<LoginOutputDTO> run(LoginDTO dto) {
        var user = userRepository.login(dto.identifier(), dto.password());

        if (user == null) {
            throw new NotFoundException("user");
        }

        String token = jwtHandler.encode(user);

        return ResponseEntity.ok(new LoginOutputDTO("User found", token, 200));
    }
}
