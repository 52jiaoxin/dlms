package com.psx.service.imp;

import com.psx.pojo.TBook;
import com.psx.mapper.TBookMapper;
import com.psx.service.ITBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class TBookServiceImpl extends ServiceImpl<TBookMapper, TBook> implements ITBookService {

}
