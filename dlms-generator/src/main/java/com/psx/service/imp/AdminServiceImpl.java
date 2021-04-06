package com.psx.service.imp;

import com.psx.pojo.Admin;
import com.psx.mapper.AdminMapper;
import com.psx.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author psx
 * @since 2021-03-28
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
