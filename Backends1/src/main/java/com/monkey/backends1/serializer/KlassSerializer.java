/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.backends1.serializer;

import com.alibaba.fastjson.JSONObject;
import com.monkey.backends1.Album;
import com.monkey.backends1.Klass;
import com.monkey.backends1.Student;
import javax.persistence.EntityManager;

/**
 *
 * @author 李阿俊
 * 
 */
public class KlassSerializer {
    public static Klass unserialize(JSONObject data, EntityManager em) {
        Klass klass = new Klass();
        klass.setGrade(data.getString("grade"));
        klass.setNum(data.getString("num"));
        return klass;
    }
}
