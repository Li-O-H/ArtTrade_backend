package com.itmo.ArtTrade.security.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    @PreAuthorize("authentication.principal.id.equals(#creatorId)")
    public void invokerEqualsOwnerCheck(Long creatorId) {}
}
