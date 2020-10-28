package com.alisonnet.medicalsystem.controller;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class HibTestRepo  {


//    @PersistenceContext
//    EntityManager entityManager;
//
//    public EntityManager getEntityManager() {
//        return entityManager;
//    }
//
//    public Book findById(int id){
//        return entityManager.find(Book.class, id);
//    }
//
//    public void updateInsert(Book book){
//        entityManager.merge(book);
//    }
}
