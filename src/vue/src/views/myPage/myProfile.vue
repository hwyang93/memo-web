<template>

    <section class="container space-type-1 blog_page">
      <h1 class="title-type-1">My Profile</h1>

      <div class="row">
        <div class="col-lg-4 col-md-5">
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
        </div>
        <div class="col-lg-8 col-md-7">
          <div class="card shadow-wrap-1">
            <div class="card-header">
              <h4 class="title">Edit Profile</h4>
            </div>
            <div class="card-content">
              <form action="">
                <div class="row">
                  <div class="col-md-6">
                    <div class="form-group">
                      <label for="">Username</label>
                      <input type="text" class="form-control">
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="form-group">
                      <label for="">Email</label>
                      <input type="text" class="form-control">
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="col-md-6">
                    <div class="form-group">
                      <label for="">Password</label>
                      <input type="password" class="form-control">
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="form-group">
                      <label for="">Password</label>
                      <input type="password" class="form-control">
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="col-md-12">
                    <div class="form-group">
                      <label for="">About Me</label>
                      <textarea rows="5" placeholder="Here can be your description" class="form-control border-input"></textarea>
                    </div>
                  </div>
                </div>
                <div class="text-center">
                  <button type="submit" class="btn btn-update">
                    Update Profile
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>

      </div>

    </section>

</template>

<script>
export default {
  name: "myProfile"
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