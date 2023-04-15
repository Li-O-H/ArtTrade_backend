package com.itmo.ArtTrade.security.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    @PreAuthorize("authentication.principal.id eq #userId")
    public void invokerEqualsUserCheck(Long userId) {}

    @PreAuthorize("authentication.principal.id ne #userId")
    public void invokerNotEqualsUserCheck(Long userId) {}
}
