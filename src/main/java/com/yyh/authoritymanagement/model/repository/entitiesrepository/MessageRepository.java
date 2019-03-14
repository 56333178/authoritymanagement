package com.yyh.authoritymanagement.model.repository.entitiesrepository;

import com.yyh.authoritymanagement.model.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}