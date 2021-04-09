package com.psx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.psx.server.pojo.TUser;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author psx
 * @since 2021-03-24
 */
@Component
public interface TUserMapper extends BaseMapper<TUser> {
    public int insertAdmin(TUser TUser);
}