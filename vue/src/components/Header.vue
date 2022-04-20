<template>
<!--  上方导航栏-->
  <div style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc; display: flex">
    <div style="width: 200px; padding-left: 30px; font-weight: bold; color: dodgerblue">后台管理</div>
    <div style="flex: 1"></div>

<!--    退出主页面到登录页面按钮-->
    <div style="width: 100px">
<!--      <el-button style="background-color: cyan" @click="$router.push('/login')">退出</el-button>-->
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-submenu index="2">
<!--          <template slot="title">张三</template>-->
          <el-menu-item index="2-1" @click="$router.push('/person')">个人</el-menu-item>
          <el-menu-item index="2-2" @click="logout">退出</el-menu-item>
        </el-submenu>
      </el-menu>
    </div>

  </div>
</template>

<script>
export default {
  name: "Header",
  props: ['user'],
  data() {
    return {

    }
  },
  methods: {
    logout() {
      sessionStorage.clear()
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>

</style>
