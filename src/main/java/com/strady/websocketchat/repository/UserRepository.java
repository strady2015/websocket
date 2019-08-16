package com.strady.websocketchat.repository;

import com.strady.websocketchat.core.BaseRepository;
import com.strady.websocketchat.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: strady
 * @Date: 2019-08-15
 * @Time: 19:23:18
 * @Description:
 */
@Repository
public interface UserRepository extends BaseRepository<User, String> {

    List<User> findByIsDeletedIsFalse();

    User findFirstByUserNameAndIsDeletedFalse(String userName);

}