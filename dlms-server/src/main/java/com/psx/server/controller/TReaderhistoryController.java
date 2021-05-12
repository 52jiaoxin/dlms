package com.psx.server.controller;


import com.psx.server.pojo.RespBean;
import com.psx.server.pojo.RespPageBean;
import com.psx.server.pojo.TBook;
import com.psx.server.pojo.TReaderhistory;
import com.psx.server.service.ITBookService;
import com.psx.server.service.ITReaderhistoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author psx
 * @since 2021-04-09
 */
@RestController
public class TReaderhistoryController {

    @Autowired
    private ITReaderhistoryService readerhistoryService;

    @Autowired
    private ITBookService bookService;

    @ApiOperation(value = "分页查询浏览记录")
    @GetMapping("/browse/history")
    public RespPageBean getReaderHistory( @RequestParam(defaultValue = "1") Integer currentPage,
                                          @RequestParam(defaultValue = "10")Integer size,
                                          @RequestParam(defaultValue = "") Integer userid,
                                          @RequestParam(defaultValue = "") Integer bookid,
                                          @RequestParam(defaultValue = "") String nickname,
                                          @RequestParam(defaultValue = "") String bookname){
        return readerhistoryService.getReaderHistory(currentPage,size,userid,bookid,nickname,bookname);
    }
    @ApiOperation(value = "删除浏览记录")
    @DeleteMapping("/browse/delete")
    public RespBean DeleteMany(Integer[] browseids){
        if(readerhistoryService.removeByIds(Arrays.asList(browseids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");

    }
    @ApiOperation(value = "清空浏览记录")
    @DeleteMapping("/browse/clear")
    public RespBean Clear(){

        if(readerhistoryService.clear()){
            return RespBean.success("清空成功");
        }
        return RespBean.error("清空失败");

    }
    @ApiOperation(value = "添加浏览记录")
    @PostMapping("/browse/add")
    public RespBean Add(@RequestParam(defaultValue = "") Integer userid,
                        @RequestParam(defaultValue = "") Integer bookid){
        TReaderhistory readerhistory=new TReaderhistory();
        readerhistory.setBookid(bookid);
        readerhistory.setUserid(userid);
        readerhistory.setBrowsetime(LocalDateTime.now());
        TBook book=bookService.getById(bookid);
        if(readerhistoryService.save(readerhistory)){
            return RespBean.success("你浏览了"+book.getName());
        }
        return RespBean.error("未知错误");

    }
}
