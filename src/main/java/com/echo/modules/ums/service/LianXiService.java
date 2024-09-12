package com.echo.modules.ums.service;

import com.echo.modules.ums.model.UmsUser;

import java.util.List;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2024/6/17 22:53
 * 项目名称: {pro-cli}
 * 文件名称: LianXiService
 * 文件描述: [ TODO ]
 * version：1.0
 * 给这个service 标注解
 *
 ********************************************************/

public interface LianXiService {

    /**
     * 查询所有用户
     * @return
     */
    public List<UmsUser> findAllUsers();


    /**
     * 新增用户
     * @param umsUser
     * @return
     */
    String saveUser(UmsUser umsUser);


    /**
     * 修改用户
     * @param umsUser
     * @return
     */
    String updateUser(UmsUser umsUser);


    /**
     * 删除用户
     * @param userId
     * @return
     */
    String delUser(int userId);

    /**
     * 根据姓名查询用户
     * @param userName
     * @return
     */
    UmsUser selectUserByName(String userName);

    /**
     * 根据note查询用户
     * @param note
     * @return
     */
    UmsUser selectUserByNote(String note);


    /**
     * 查询所有用户-按照用户id倒序返回
     * @return
     */
    List<UmsUser> selectAllUsersSorted();

}
