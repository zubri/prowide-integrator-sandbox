package com.prowidesoftware.sandbox;

import com.prowidesoftware.swift.model.AbstractSwiftMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<AbstractSwiftMessage, Long> {
}
