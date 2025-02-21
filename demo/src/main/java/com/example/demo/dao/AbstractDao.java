package com.example.demo.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AbstractDao {

    @Autowired
    private EntityManager entityManager;
    public JPAQueryFactory getFactory()
    {
        return new JPAQueryFactory(entityManager);
    }


}
