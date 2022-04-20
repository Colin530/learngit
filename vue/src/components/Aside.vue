<template>
  <div>
<!--    左侧导航栏-->
    <el-menu
        style="width: 200px; min-height: calc(100vh - 50px)"
        default-active="home"
        router
        class="el-menu-vertical-demo">
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>系统管理</span>
        </template>
      <el-submenu>
        <el-menu-item index="user">用户管理</el-menu-item>
        <el-menu-item index="order">订单管理</el-menu-item>
      </el-submenu>
      </el-submenu>

    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Aside"
}
</script>

<style>

</style>