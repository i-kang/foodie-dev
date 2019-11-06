package com.imooc.service.impl;

import com.imooc.bo.UserBo;
import com.imooc.enums.Sex;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.service.UserService;
import com.imooc.utils.DateUtil;
import com.imooc.utils.MD5Utils;
import org.apache.catalina.User;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author augenye
 * @date 2019-11-05 22:01
 */
@Service
public class UserServiceImpl implements UserService {

    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";
    private static final String BIRTHDAY = "1900-01-01";

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public boolean queryUsernameIsExist(String username) {
        //建立一个example 方便查询
        Example userExample = new Example(User.class);
        Example.Criteria userCriteria = userExample.createCriteria();

        //等价查询
        userCriteria.andEqualTo("username", username);
        Users result = usersMapper.selectOneByExample(userExample);

        //如果result == null 说明用户名不存在
        return result != null;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Users createUser(UserBo userBo) {
        Users user = new Users();
        user.setUsername(userBo.getUsername());
        try {
            user.setPassword(MD5Utils.getMD5Str(userBo.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setNickname(userBo.getUsername());
        user.setFace(USER_FACE);
        user.setBirthday(DateUtil.stringToDate(BIRTHDAY));
        user.setSex(Sex.secret.type);

        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());


        return null;
    }
}
