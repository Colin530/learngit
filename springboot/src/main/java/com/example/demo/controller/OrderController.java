package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Order;
import com.example.demo.mapper.OrderMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Vector;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderMapper orderMapper;

    @PostMapping
    public Result<?> save(@RequestBody Order order) {
        orderMapper.insert(order);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Order order) {
        orderMapper.updateById(order);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        orderMapper.deleteById(id);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids) {
        orderMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Integer id) {
        return Result.success(orderMapper.selectById(id));
    }

    /**
     * 注意：这个方法使用的是Mybatis sql的方式做的多表联合查询，大家可以点开，参考下怎么写多表查询
     * @param userId
     * @return
     */
//    @GetMapping("/{userId}")
//    public Result<?> getByUserId(@PathVariable Integer userId) {
//        return Result.success(orderMapper.findByUserId(userId));
//    }
//    test

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Order> wrapper = Wrappers.<Order>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Order::getName, search);
        }
        Page<Order> BookPage = orderMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(BookPage);
    }
}
