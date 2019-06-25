package com.pinyougou.sellergoods.service;

import com.pinyougou.entity.PageResult;
import com.pinyougou.pojo.TbBrand;

import java.util.List;

public interface BrandService {
    /**
     * 查找全部品牌
     * @return
     */
    public List<TbBrand> findAll();
    /**
     * 按照分页查找全部品牌
     *
     * @return
     */
    public PageResult findByPage(int page, int rows);

    /**
     * 增加一个品牌
     * @param brand
     */
    void add(TbBrand brand);

    /**
     * 查找一个
     * @param id
     * @return
     */
    TbBrand findOne(Long id);

    /**
     * 修改品牌
     * @param brand
     */
    void update(TbBrand brand);

    /**
     * 删除选中
     * @param ads
     */
    void delete(Long[] ads);

    PageResult findByPage(TbBrand brand, int page, int size);
}
