<template>

  <section class="container space-type-1 blog_page">
    <h1 class="title-type-1">My Profile</h1>

    <div class="row">
      <div class="col-lg-4 col-md-5">
        <!--userInfo-->
        <user-info></user-info>
      </div>
      <div class="col-lg-8 col-md-7">
          <user-post-list></user-post-list>
      </div>


    </div>

  </section>

</template>

<script>
import userInfo from './userInfo';
import userPostList from './userPostList';

export default {
  name: "userComponent",
  components: {
    userInfo,
    userPostList
  }
}
</script>

<style scoped>
h4.title {
  margin: 30px 0 15px;
}
h4 small {
  color: #9a9a9a;
}
.card {
  margin-bottom: 20px;
}
.card .card-content {
  padding: 15px 15px 10px;
}

.card-user .image {
  position: relative;
  height: 150px;
  border-radius: 8px 8px 0 0;
  overflow: hidden;
}
.card-user .image img {
  width: 100%;
  height: 150px;
}

.card-user .card-content {
  min-height: 250px;
}

.card-user .author {
  margin-top: -65px;
  text-align: center;
  text-transform: none;
}
.card-user .avatar {
  position: relative;
  width: 100px;
  height: 100px;
  margin-bottom: 15px;
  border-radius: 50%;
}
.card-user .avatar.border-white {
  border: 5px solid #fff;
}

.card .description {
  font-size: 16px;
  color: #66615b;
}

.btn-update {
  color: #fff;
  background-color: #68b3c8;
}
.btn-update:hover {
  background-color: #429cb6;
}
</style>