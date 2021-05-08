package com.psx.server.controller;


import com.psx.server.pojo.TBook;
import com.psx.server.service.ITRecommendService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author psx
 * @since 2021-04-20
 */
@RestController
public class TRecommendController {

    @Autowired
    private ITRecommendService recommendService;

    @ApiOperation(value ="获取推荐图书")
    @GetMapping("/recommend")
    public List<TBook> getRecommend(){
        return recommendService.getRecommend();
    }
}
