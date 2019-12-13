package com.af.service.impl;

import com.af.mapper.PersonInfoMapper;
import com.af.model.pojo.PersonInfo;
import com.af.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/14 1:44
 */
@Service
public class PersonInfoServiceImpl implements PersonService {
    @Autowired
    private PersonInfoMapper personInfoMapper;
    @Override
    public PersonInfo getById(Integer ownerId) {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(ownerId);
        return personInfoMapper.selectOne(personInfo);
    }
}
