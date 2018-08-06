package com.ofonesie.ofonesie.models.data;

import com.ofonesie.ofonesie.models.Listing;
import com.ofonesie.ofonesie.models.UserInfo;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserInfoDAO implements IUserInfoDAO{

    @PersistenceContext

    private EntityManager entityManager;

    public UserInfo getActiveUser(String userName){
        UserInfo activeUserInfo = new UserInfo();

        short enabled = 1;
        List<?> list = entityManager.createQuery("SELECT u FROM UserInfo u WHERE userName=? and enabled=?")
                .setParameter(1, userName).setParameter(2, enabled).getResultList();

        if(!list.isEmpty()){

            activeUserInfo = (UserInfo)list.get(0);

        }
        return activeUserInfo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Listing> getAllUserListings(){
        String hql = "FROM Listing as listing ORDER BY listing.listingId";
        return (List<Listing>) entityManager.createQuery(hql).getResultList();


    }
}
