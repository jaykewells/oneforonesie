package com.ofonesie.ofonesie.models.data;

import com.ofonesie.ofonesie.models.Listing;
import com.ofonesie.ofonesie.models.UserInfo;

import java.util.List;

public interface IUserInfoDAO {

    UserInfo getActiveUser(String userName);

    List<Listing> getAllUserListings();
}
