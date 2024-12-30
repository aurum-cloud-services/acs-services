package com.aurum.acs_services.user.application.abstractions;

import com.aurum.acs_services.user.infrastructure.dtos.LoginDTO;
import com.aurum.acs_services.user.infrastructure.dtos.LoginOutputDTO;

public interface IUserLoginUseCase {
    LoginOutputDTO run(LoginDTO dto);
}
