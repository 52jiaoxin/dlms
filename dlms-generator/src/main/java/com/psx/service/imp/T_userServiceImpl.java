package com.psx.service.imp;

import com.psx.pojo.T_user;
import com.psx.mapper.T_userMapper;
import com.psx.service.IT_userService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author psx
 * @since 2021-04-06
 */
@Service
public class T_userServiceImpl extends ServiceImpl<T_userMapper, T_user> implements IT_userService {

}
