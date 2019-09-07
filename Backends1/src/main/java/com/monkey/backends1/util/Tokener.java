/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.backends1.util;

import com.monkey.backends1.Student;
import com.monkey.backends1.Token;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 *
 * @author 李阿俊
 */
public class Tokener {
    public static boolean debug = false;
    public static Long aDay = 1000 * 3600 * 24L;
    
    public static String generate(Student student, EntityManager em) throws Exception {
        String originToken = System.currentTimeMillis() + student.getBuptId() + new Random().nextLong();
        String encryptedToken = Encrypt.encrypt(originToken);
        Query qtoken = em.createNamedQuery("Token.findByStudent");
        qtoken.setParameter("student", student);
        Token token;
        List<Token> tks = qtoken.getResultList();
        if (tks.size() > 0) {
            token = tks.get(0);
        } else {
            token = new Token();
        }
        token.setTime(new Date(System.currentTimeMillis()));
        token.setReqStudent(student);
        token.setToken(encryptedToken);
        em.persist(token);
        return encryptedToken;
    }

    public static Integer valid(String token, Long time, EntityManager em) {
        Query qtoken = em.createNamedQuery("Token.findByToken");
        qtoken.setParameter("token", token);
        List<Token> tokens = qtoken.getResultList();
        if (tokens.size() > 0) {
            Token tokenInDB = tokens.get(0);
            if (System.currentTimeMillis() - tokenInDB.getTime().getTime() > time) {
                return -1;
            }
            return tokenInDB.getReqStudent().getSId();
        }
        return -1;
    }
}
