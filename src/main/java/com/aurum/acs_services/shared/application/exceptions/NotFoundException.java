package com.aurum.acs_services.shared.application.exceptions;

import com.aurum.acs_services.shared.domain.contracts.BaseHttpException;

public class NotFoundException extends BaseHttpException {
    public NotFoundException(String notFound) {

        super(String.format("%s not found", notFound), 404);
    }
}
