package com.ofonesie.ofonesie.models.data;

import com.ofonesie.ofonesie.models.Listing;
import com.ofonesie.ofonesie.models.UserInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserInfoDAO extends CrudRepository<UserInfo, Integer>{

    UserInfo findByUsername(String username);

    //List<Listing> getAllUserListings();
}
