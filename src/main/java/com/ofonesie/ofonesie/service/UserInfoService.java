package com.ofonesie.ofonesie.service;

import com.ofonesie.ofonesie.models.Listing;
import com.ofonesie.ofonesie.models.data.UserInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserInfoService implements IUserInfoService{

    @Autowired
    private UserInfoDAO userInfoDao;

    /*@Override
    public List<Listing> getAllUserListings(){
        return userInfoDao.getAllUserListings();
    }*/
}
