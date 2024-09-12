package com.echo.modules.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.echo.modules.ums.mapper.UmsUserMapper;
import com.echo.modules.ums.model.UmsUser;
import com.echo.modules.ums.service.LianXiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LianXiServiceImpl implements LianXiService {

    @Autowired
    private UmsUserMapper userMapper;

    @Override
    public List<UmsUser> findAllUsers() {
//        QueryWrapper queryWrapper = new QueryWrapper();
//        List<UmsUser> umsUserList = userMapper.selectList(queryWrapper);
        List<UmsUser> umsUserList = userMapper.selectList(new QueryWrapper<>());
        return umsUserList;
    }

//    public static void main(String[] args) {
//        List<String> umsUserList = new ArrayList<>();
//        umsUserList.add("1");
//        umsUserList.add("2");
//        umsUserList.add("3");
//        umsUserList.add("4");
//        umsUserList.add("5");
//
//        // JAVA 高级特性 泛型
//        // <String> 就是个泛型
//        umsUserList.add("1");
//        umsUserList.add(1);
//
//        List arrayList = new ArrayList<>();
//        arrayList.add("1");
//        arrayList.add(1);
//
//
//
//
//        System.out.println(umsUserList);
//    }


    @Override
    public String saveUser(UmsUser umsUser) {
        int insert = userMapper.insert(umsUser);
        if(insert == 1) {
            return "新增成功";
        } else {
            return "新增失败";
        }

    }

    @Override
    public String updateUser(UmsUser umsUser) {
        int update = userMapper.updateById(umsUser);
        // 逻辑判断  数据去重 排序
        if(update == 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    @Override
    public String delUser(int userId) {
        int delete = userMapper.deleteById(userId);
        // 逻辑判断  数据去重 排序
        if(delete == 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @Override
    public UmsUser selectUserByName(String userName) {
        // select * from ums_user where user_name = 't'
        QueryWrapper<UmsUser> queryWrapper = new QueryWrapper<UmsUser>().eq("user_name", userName);
        UmsUser umsUser = userMapper.selectOne(queryWrapper);
        return umsUser;
        // jdk8 jdk1.8  新特性 stream 流
//      UmsUser umsUser1 = userMapper.selectOne(new LambdaQueryWrapper<UmsUser>().eq(UmsUser::getUserName, userName));


    }

    @Override
    public UmsUser selectUserByNote(String note) {
//        QueryWrapper<UmsUser> queryWrapper = new QueryWrapper<UmsUser>().eq("note",note);
//        UmsUser umsUser = userMapper.selectOne(queryWrapper);
        UmsUser umsUser = userMapper.selectUserByNote(note);
        return umsUser;
    }

    @Override
    public List<UmsUser> selectAllUsersSorted() {
        List<UmsUser> umsUserList = userMapper.selectList(new QueryWrapper<>());
        // 数据做处理
        // 字符串处理一下
        for(UmsUser umsUser:umsUserList) {
            if(umsUser.getUserName().startsWith("whr")){
                String replaced = umsUser.getUserName().replace("whr", "");
                umsUser.setUserName(replaced);
            }
        }
        //  排序
        umsUserList = umsUserList.stream().sorted(Comparator.comparing(UmsUser::getId).reversed()).collect(Collectors.toList());
        return umsUserList;
    }

}
