package com.af.service;

import com.af.model.pojo.PersonInfo;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/14 1:44
 */
public interface PersonService {
    /**
     * 根据用户id查找
     * @param ownerId
     * @return
     */
    public PersonInfo getById(Integer ownerId);
}
