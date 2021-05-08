package com.psx.server.controller;


import com.psx.server.pojo.RespBean;
import com.psx.server.pojo.RespPageBean;
import com.psx.server.pojo.TBorrowhistory;
import com.psx.server.service.ITBookService;
import com.psx.server.service.ITBorrowhistoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
public class TBorrowhistoryController {

    @Autowired
    private ITBorrowhistoryService borrowhistoryService;
    @Autowired
    private ITBookService bookService;

    @ApiOperation(value = "获取借阅列表（可以搜索）")
    @GetMapping("/borrow/history")
    public RespPageBean BorrowHistory(
                                              @RequestParam(defaultValue = "1") Integer currentPage,
                                              @RequestParam(defaultValue = "10")Integer size,
                                              @RequestParam(defaultValue = "") Integer userid,
                                              @RequestParam(defaultValue = "") Integer bookid,
                                              @RequestParam(defaultValue = "") String username,
                                              @RequestParam(defaultValue = "") String bookname){
        return borrowhistoryService.getBorrowHistory(currentPage,size,userid,bookid,username,bookname);
    }

    @ApiOperation(value = "批量删除借阅记录")
    @DeleteMapping("/borrow/deletemany")
    public RespBean DeleteMany(Integer[] borrowids){
        if(borrowhistoryService.removeByIds(Arrays.asList(borrowids))){
            return RespBean.success("还书成功");
        }
        return RespBean.error("还书失败");

    }
    @ApiOperation(value = "增加借阅记录")
    @PostMapping("/borrow/add")
    public RespBean AddBorrowHistory( @RequestParam(defaultValue = "") Integer userid,
                                      @RequestParam(defaultValue = "") Integer bookid){
        TBorrowhistory borrowhistor=new TBorrowhistory();
        borrowhistor.setBookid(bookid);
        borrowhistor.setUserid(userid);
        borrowhistor.setBorrowdate(LocalDate.now());
        borrowhistor.setReturndate(LocalDate.now().plusDays(90));
        if(borrowhistoryService.save(borrowhistor)){
            return RespBean.success("借书成功");
        }
        return RespBean.error("借书失败");
    }
    @ApiOperation(value = "续借")
    @PostMapping("/borrow/reborrow")
    public RespBean ReBorrow(@RequestParam Integer id){
        TBorrowhistory borrowHistory=borrowhistoryService.getById(id);
        LocalDate localDate=borrowHistory.getReturndate();
        borrowHistory.setReturndate(localDate.plusDays(30));
        if (borrowHistory.getReturndate().isBefore(LocalDate.now())){
            borrowHistory.setIsexpire(true);
        }else{
            borrowHistory.setIsexpire(false);
        }
        return borrowhistoryService.ReBorrow(borrowHistory);
    }


}
