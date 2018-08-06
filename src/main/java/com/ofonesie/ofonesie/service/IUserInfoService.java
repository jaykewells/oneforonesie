package com.ofonesie.ofonesie.service;

import com.ofonesie.ofonesie.models.Listing;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface IUserInfoService {

    @Secured({"ROLE_ADMIN"})
    List<Listing> getAllUserListings();
}
