package com.psx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.psx.server.pojo.TUserRole;
import org.springframework.stereotype.Component;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author psx
 * @since 2021-04-14
 */
@Component
public interface TUserRoleMapper extends BaseMapper<TUserRole> {

    boolean upRole(Integer rid, Integer userid);
}
