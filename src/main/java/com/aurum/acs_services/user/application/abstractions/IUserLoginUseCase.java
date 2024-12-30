package com.aurum.acs_services.user.application.abstractions;

import com.aurum.acs_services.user.infrastructure.dtos.LoginDTO;
import com.aurum.acs_services.user.infrastructure.dtos.LoginOutputDTO;
import org.springframework.http.ResponseEntity;

public interface IUserLoginUseCase {
    ResponseEntity<LoginOutputDTO> run(LoginDTO dto);
}
