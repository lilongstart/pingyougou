package com.pinyougou.manager.controller;

import com.pinyougou.entity.PageResult;
import com.pinyougou.entity.Result;
import com.pinyougou.sellergoods.service.BrandService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/brand")
public class BrandController {
    @Reference()
    private BrandService brandService;

    /**
     * 查找全部品牌
     * @return
     */
    @RequestMapping("/findAll.do")
    @ResponseBody
    public List<TbBrand> findAll(){
       List<TbBrand> tbBrands = brandService.findAll();
       return tbBrands;
    }

    /**
     * 按分页查询
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findByPage.do")
    @ResponseBody
    public PageResult findPages(int page,int size){
        PageResult pageResult = brandService.findByPage(page, size);
        return pageResult;
    }

    /**
     * 增加品牌
     * @param brand
     * @return
     */
    @RequestMapping("/add.do")
    @ResponseBody
    public Result add(@RequestBody TbBrand brand){

        Result result = new Result();
        try {
            brandService.add(brand);
            result.setSuccess(true);
            result.setMessage("添加成功！");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("用户已存在！");
            e.printStackTrace();
        }
         return result;
    }

    /**
     * 查询一个，用于删除
     * @param id
     * @return
     */
    @RequestMapping("/findOne.do")
    @ResponseBody
    public TbBrand findOne(@RequestParam(name = "id",required = true)Long id){

    TbBrand tbBrand= brandService.findOne(id);
        return tbBrand;
    }

    /**
     * 修改品牌
     * @param brand
     * @return
     */
    @RequestMapping("/update.do")
    @ResponseBody
    public Result update(@RequestBody TbBrand brand){
        Result result = new Result();
        try {
            brandService.update(brand);
            result.setSuccess(true);
            result.setMessage("更改成功！");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更改失败！");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除选中
     * @param ids
     * @return
     */
    @RequestMapping("/delete.do")
    @ResponseBody
    public Result delete(Long [] ids) {

        Result result = new Result();
        try {
            brandService.delete(ids);
            result.setSuccess(true);
            result.setMessage("删除成功！");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除失败！");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 按条件查询，并分页
     * @param brand
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/search.do")
    @ResponseBody
    public PageResult findPages(@RequestBody TbBrand brand,int page,int size){

      PageResult pageResult= brandService.findByPage(brand,page,size);
        return pageResult;
    }
}
