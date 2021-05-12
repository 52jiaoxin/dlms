package com.psx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.psx.server.pojo.TBook;
import com.psx.server.pojo.TUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

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

    int insertAdmin(TUser user);
    List<TUser> getEmpList(String nickname);

    List<TUser> getReaderList(String username);

    int getUserByUsername(String username);

    IPage<TBook> getUserByPage(Page<TBook> page, @Param("user")TUser user);


    List<TUser> getUserList(String keywords, Integer id,String role);

    IPage<TBook> getReaderByPage(Page<TBook> page, TUser user);
}
