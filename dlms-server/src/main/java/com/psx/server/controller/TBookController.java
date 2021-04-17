package com.psx.server.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.psx.server.pojo.RespBean;
import com.psx.server.pojo.RespPageBean;
import com.psx.server.pojo.TBook;
import com.psx.server.service.ITBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * @author psx
 * @date 2021/4/8 9:55
 */
@RestController
@Api(tags="BookController")
public class TBookController {
    @Autowired
    private ITBookService bookService;


    @ApiOperation(value = "获取所有图书")
    @GetMapping("/book/list")
    public List<TBook> getBookList(){
        return bookService.getBookList();
    }

    @ApiOperation(value = "根据类型获取图书")
    @GetMapping("/book/{type}")
    public List<TBook> getBookListByType(@RequestBody  @PathVariable("type") String type){
        return bookService.getBookListByType(type);
    }

    @ApiOperation(value = "更新图书信息")
    @PostMapping("/book/update")
    public RespBean updateBook(@RequestBody TBook tBook){

        if(bookService.updateById(tBook)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除图书")
    @DeleteMapping("/book/deleteone/{id}")
    public RespBean deleteBook(@PathVariable("id") int id){
        if(bookService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除图书")
    @DeleteMapping("/book/deletemany/")
    public RespBean deleteManyBook( Integer[] ids){
        if(bookService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "分页查询图书")
    @GetMapping("/book/page/")
    public RespPageBean getPageBook(@RequestParam(defaultValue = "1") Integer currentPage,
                                        @RequestParam(defaultValue = "10")Integer size,
                                    TBook book){
        return bookService.getBookByPage(currentPage,size,book);

    }




    @ApiOperation(value = "添加图书")
    @PostMapping("/book/insert")
    public RespBean insertBook(@RequestBody TBook tBook){
        if(bookService.save(tBook)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "导出书籍数据")
    @GetMapping(value = "/book/export",produces = "application/octet-stream")
    public void exportBook(HttpServletResponse response) {
        List<TBook> list=bookService.exportBook(null);
//        设置文件名、表名、Excel版本
        ExportParams params=new ExportParams("书籍表","书籍表", ExcelType.HSSF);
//        创建工作簿
        Workbook workbook=ExcelExportUtil.exportExcel(params,TBook.class,list);
       ServletOutputStream outputStream=null;
        try {
//            设置请求头为流形式
            response.setHeader("content-type","application/octet-stream");
//            防止中文乱码
            response.setHeader("content-disposition","attachment;filename="+
                    URLEncoder.encode("书籍表.xls","UTF-8"));
           outputStream= response.getOutputStream();
//           写出数据
           workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream!=null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    @ApiOperation(value = "导入书籍")
    @PostMapping("/book/import")
    public RespBean importBook(@ApiParam(value="文件",required=true)@RequestPart("file")MultipartFile file){
        ImportParams params=new ImportParams();
//        去掉标题行
        params.setTitleRows(1);

        try {
            List<TBook> list=ExcelImportUtil.importExcel(file.getInputStream(),TBook.class,params);

            if (bookService.saveBatch(list)){
                return RespBean.success("导入成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("导入失败");

    }
}
