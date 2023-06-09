package com.jue.backend.service.impl.user.account;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jue.backend.mapper.UserMapper;
import com.jue.backend.pojo.User;
import com.jue.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public Map<String, String> register(String username, String password, String confirmedPassword) {
        Map <String, String>  map = new HashMap<>();
        // 一、不为空与有效长度非零
        if(username == null){
            map.put("error_message", "用户名不能为空");
            return map;
        }
        if(password == null || confirmedPassword == null){
            map.put("error_message", "密码不能为空");
            return map;
        }
        username = username.trim();
        if(username.length() == 0){
            map.put("error_message", "用户名不能为空");// 用户名可以包含空格
            return map;
        }
        if (password.length() == 0 || confirmedPassword.length() == 0) {
            map.put("error_message", "密码不能为空");
            return map;
        }

        // 二、过长判断
        if (username.length() > 100) {// 过长
            map.put("error_message", "用户名长度不能大于100");
            return map;
        }

        if (password.length() > 100 || confirmedPassword.length() > 100) {
            map.put("error_message", "密码长度不能大于100");
            return map;
        }

        // 三、相等判断
        if (!password.equals(confirmedPassword)) {
            map.put("error_message", "两次输入的密码不一致");
            return map;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<User> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            map.put("error_message", "用户名已存在");
            return map;
        }
        String[] photoItems = {"https://marketplace.canva.cn/9SPE4/MAEn_f9SPE4/1/s2/canva-geometric-shapes-in-different-dimension-MAEn_f9SPE4.jpg",
                            "https://marketplace.canva.cn/9SPE4/MAEn_f9SPE4/1/s2/canva-geometric-shapes-in-different-dimension-MAEn_f9SPE4.jpg",
                            "https://marketplace.canva.cn/9SPE4/MAEn_f9SPE4/1/s2/canva-geometric-shapes-in-different-dimension-MAEn_f9SPE4.jpg",
                            "https://marketplace.canva.cn/9SPE4/MAEn_f9SPE4/1/s2/canva-geometric-shapes-in-different-dimension-MAEn_f9SPE4.jpg"};

        Random rand = new Random();
        // 最后，如果没问题则将密码加密。存到数据库中
        String encodedPassword = passwordEncoder.encode(password);
        String photo = photoItems[rand.nextInt(photoItems.length)];;
        // id自增，设置为null即可
        User user = new User(null, username, encodedPassword, photo,null);
        userMapper.insert(user);

        map.put("error_message", "success");
        return map;

    }
}
