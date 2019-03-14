package com.yyh.authoritymanagement.model.repository.beansrepository;

import com.yyh.authoritymanagement.model.beans.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsersRepository extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users> {

}
