<template>
  <div class="blog-sidebar-widgets">
    <h3 class="widgets-title"> Categories </h3>
    <div class="blog-sidebar-widgets-inner">
      <ul>
        <li class="cat-item">
          <a @click="goMenu('/myPage/schedule')"> - my Schedule </a>
        </li>
        <li class="cat-item">
          <a @click="goMenu('/myPage/memo')"> - my Memo </a>
        </li>
        <li class="cat-item">
          <a @click="goMenu('/myPage/group')"> - my Group </a>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "leftMenu",
  methods: {
    goMenu(url) {
      this.$router.push(url);
    }
  }
}
</script>