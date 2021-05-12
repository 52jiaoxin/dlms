package com.psx.server.controller;

import com.psx.server.pojo.Sta;
import com.psx.server.pojo.Statistic;
import com.psx.server.service.ITBookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author psx
 * @date 2021/5/10 22:43
 */
@RestController
public class SysController {
    @Autowired
    private ITBookService bookService;


    @ApiOperation(value="获得借书排行前十书籍")
    @GetMapping("/sys/statistic")
    public Statistic getNum(){
        Integer total=bookService.getTotal();
        List<Integer> list=bookService.getNum();
        List<String> listname=bookService.getBookName();
        listname.add("其他");
        Statistic statistic=new Statistic();
        statistic.setTotal(total);
        statistic.setData1(listname);
        List<Sta> staList=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Sta sta=new Sta();
            sta.setNum(list.get(i));
            sta.setName(listname.get(i));
            staList.add(sta);
        }
        Sta sta1=new Sta();
        sta1.setNum(total-10);
        sta1.setName("其他");
        staList.add(sta1);
        statistic.setData(staList);
        System.out.println(statistic);
        return statistic;
    }

    @ApiOperation(value="获得借书排行前十读者")
    @GetMapping("/sys/userstatistic")
    public Statistic getUserStatistic(){
        Integer total=bookService.getUserTotal();
        List<Integer> list=bookService.getUserNum();
        List<String> listname=bookService.getUserNickName();
        listname.add("其他");
        Statistic statistic=new Statistic();
        statistic.setTotal(total);
        statistic.setData1(listname);
        List<Sta> staList=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Sta sta=new Sta();
            sta.setNum(list.get(i));
            sta.setName(listname.get(i));
            staList.add(sta);
        }
        Sta sta1=new Sta();
        if (total-10<0)
            sta1.setNum(0);
        else
            sta1.setNum(total-10);
        sta1.setName("其他");
        staList.add(sta1);
        statistic.setData(staList);
        System.out.println(statistic);
        return statistic;
    }
}
