package com.psx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.psx.server.mapper.TRoleMapper;
import com.psx.server.mapper.TUserRoleMapper;
import com.psx.server.pojo.RespBean;
import com.psx.server.pojo.TRole;
import com.psx.server.pojo.TUserRole;
import com.psx.server.service.ITUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author psx
 * @since 2021-04-14
 */
@Service
public class TUserRoleServiceImpl extends ServiceImpl<TUserRoleMapper, TUserRole> implements ITUserRoleService {

    @Autowired
    private TUserRoleMapper userRoleMapper;
    @Autowired
    private ITUserRoleService userRoleService;
    @Autowired
    private TRoleMapper roleMapper;
    @Override
    public boolean deleteById(int id) {
        int uid=(userRoleMapper.selectOne(new QueryWrapper<TUserRole>().eq("userid",id))).getId();
        if(userRoleService.removeById(uid)){
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean deleteByIds(Integer[] ids) {
        for(int i=0;i<ids.length;i++){
            int uid=(userRoleMapper.selectOne(new QueryWrapper<TUserRole>().eq("userid",ids[i]))).getId();
            if(!userRoleService.removeById(uid)){
                return false;
            }
        }
            return true;
    }

    @Override
    public String getRole(Integer userid) {
        Integer rid=userRoleMapper.selectOne(new QueryWrapper<TUserRole>().eq("userid",userid)).getRid();
        return roleMapper.selectOne(new QueryWrapper<TRole>().eq("id",rid)).getRole();
    }

    @Override
    public RespBean upRole(Integer rid, Integer userid) {
        if(userRoleMapper.upRole(rid,userid)){
            return RespBean.success("更新成功");
        }else{
            return RespBean.error("更新失败");
        }

    }
}
