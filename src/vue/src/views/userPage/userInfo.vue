<template>
  <div class="card card-user shadow-wrap-1">
    <div class="image">
      <img src="https://cdn.pixabay.com/photo/2015/05/07/11/02/guitar-756326_960_720.jpg" alt="">
    </div>
    <div class="card-content">
      <div class="author">
        <img src="../../images/friends/user-sample.jpg" alt="" class="avatar border-white">
        <h4 class="title">Song Bong<br>
          <a href="#">
            <small>@charm_bbong</small>
          </a>
        </h4>
      </div>
      <p class="description text-center">
        "all thing must end<br>
        good-bye my friend<br>
        think of me when you see the sun of feel the wind"
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: "userInfo"
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

</style>