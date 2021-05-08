package com.psx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.psx.server.pojo.TUserRole;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author psx
 * @since 2021-04-14
 */
public interface ITUserRoleService extends IService<TUserRole> {

    boolean deleteById(int id);

    boolean deleteByIds(Integer[] ids);

    String getRole(Integer userid);
}
