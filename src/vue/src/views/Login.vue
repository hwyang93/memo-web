<template>
  <!-- Page-Title-end -->
  <section class="login-area">
    <div class="container">
      <div class="row cont-cover">
        <div class="col-lg-6 col-md-12">
          <div class="contcat_info">
            <div class="section-heading ml-0">
              <img alt="logo_img" class="img-fluid" src="../images/all-img/logo.png" />
            </div>
          </div>
        </div>
        <!--contact-info-->
        <div class="col-lg-6 col-md-12 contactus_form">
          <div class="row">
            <div class="form-group col-md-12">
              <div class="input-group">
                <input v-model="form.userId" class="form-control" placeholder="아이디" type="text" />
              </div>
            </div>
            <div class="form-group col-md-12">
              <div class="input-group">
                <input v-model="form.userPassword" class="form-control" placeholder="비밀번호" type="password" />
              </div>
            </div>
          </div>
          <div class="d-flex justify-content-around">
            <button class="btn large-btn" @click="goLogin()">로그인</button>
            <button class="btn large-btn" @click="goSignUp()">회원가입</button>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import axiosUtil from '../utils/axios-util.js';

export default {
  name: 'login',
  data() {
    return {
      form: {
        userId: 'test',
        userPassword: 'test'
      }
    };
  },
  methods: {
    goLogin: function () {
      axiosUtil.post('/api/main/login.do', this.form, result => {
        if (!result.data) {
          alert('일치하는 회원이 없습니다.');
        } else {
          this.$store.commit('LOGIN', result.data);
          if (result.data.userId == 'admin') {
            this.$router.push('/adminPage/admin');
          } else {
            this.$router.push('/');
          }
        }
      });
    },
    goSignUp: function () {
      this.$router.push('/signUp');
    }
  }
};
</script>

<style scoped>
.login-area {
  display: flex;
  width: 100vw;
  height: 100vh;
  align-items: center;
  justify-content: center;
  background: linear-gradient(4deg, rgba(255, 255, 255, 0.85) 17%, rgba(255, 255, 255, 0.85) 69%), url(/img/bg-img-1.804e211c.png);
  background-size: cover;
  background-position: center;
}
</style>
