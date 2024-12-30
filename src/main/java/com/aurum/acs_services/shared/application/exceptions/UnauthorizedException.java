package com.aurum.acs_services.shared.application.exceptions;

import com.aurum.acs_services.shared.domain.contracts.BaseHttpException;

public class UnauthorizedException extends BaseHttpException {
    public UnauthorizedException() {
        super("Unauthorized.", 401);
    }
}
