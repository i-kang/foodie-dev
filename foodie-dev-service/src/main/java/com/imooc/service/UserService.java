package com.imooc.service;

import com.imooc.bo.UserBO;
import com.imooc.pojo.Users;

/**
 * @author augenye
 * @date 2019-11-05 22:00
 */
public interface UserService {

    /**
     * 查询用户名是否存在
     * @param username 用户名
     * @return 是否存在
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 创建一个用户
     * @param userBO 接收一个bo对象
     * @return users 返回一个users类型
     */
    Users createUser(UserBO userBO);

    /**
     * 检索用户名和密码是否匹配
     * @param username 用户名
     * @param password 密码
     * @return
     */
    Users queryUSerForLogin(String username, String password);
}
