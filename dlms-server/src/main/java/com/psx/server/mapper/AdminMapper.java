package com.psx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.psx.server.pojo.Admin;
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
public interface AdminMapper extends BaseMapper<Admin> {
    public int insertAdmin(Admin admin);
}
