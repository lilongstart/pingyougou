package com.pinyougou.sellergoods.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.entity.PageResult;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;
import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrandExample.Criteria;
import com.pinyougou.pojo.TbBrand;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
@Service()
public class BrandServiceImpl implements BrandService{
    @Autowired
    private TbBrandMapper tbBrandMapper;
    /**
     * 查询所有
     * @return
     */
    @Override
    public List<TbBrand> findAll() {

        List<TbBrand>tbBrandList= tbBrandMapper.selectByExample(null);
        return tbBrandList;
    }

    /**
     * 查询后分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageResult findByPage(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<TbBrand> page = (Page<TbBrand>) tbBrandMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加一个品牌
     * @param brand
     */
    @Override
    public void add(TbBrand brand) {

        tbBrandMapper.insert(brand);
    }

    /**
     * 根据id查询一个，用于修改数据
     * @param id
     * @return
     */
    @Override
    public TbBrand findOne(Long id) {

        TbBrand tbBrand = tbBrandMapper.selectByPrimaryKey(id);
        return tbBrand;
    }

    /**
     * 修改数据
     * @param brand
     */
    @Override
    public void update(TbBrand brand) {

        tbBrandMapper.updateByPrimaryKey(brand);
    }

    /**
     * 删除选中
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {

        for (Long id : ids) {
            tbBrandMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageResult findByPage(TbBrand brand, int page, int size) {

        PageHelper.startPage(page,size);
        TbBrandExample example =  new TbBrandExample();
        Criteria criteria = example.createCriteria();
       if(brand!=null) {
           if (brand.getFirstChar()!= null && brand.getFirstChar().length() > 0) {
               criteria.andFirstCharLike("%"+brand.getFirstChar()+"%");
           }
           if (brand.getName()!= null && brand.getName().length() > 0) {
               criteria.andNameLike("%"+brand.getName()+"%");
           }
       }

        Page<TbBrand> page1 = (Page<TbBrand>) tbBrandMapper.selectByExample(example);

        return new PageResult(page1.getTotal(),page1.getResult());
    }


}
