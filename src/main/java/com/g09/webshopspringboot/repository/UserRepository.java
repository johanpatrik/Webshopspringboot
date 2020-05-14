package com.g09.webshopspringboot.repository;

import com.g09.webshopspringboot.domain.User;
import org.hibernate.hql.spi.id.cte.CteValuesListBulkIdStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);
    Optional<User> findByUserNameAndPassword(String userName,String password);

}
