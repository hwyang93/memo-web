<template>
  <div>
    <!-- Page-Title-end -->
    <section class="bg-img-1 space contact_page">
      <div class="container">
        <div class="row cont-cover">
          <div class="col-lg-6 col-md-12">
            <div class="contcat_info">
              <div class="section-heading ml-0">
                <img
                  src="../images/all-img/logo.png"
                  alt="logo_img"
                  class="img-fluid"
                />
              </div>
            </div>
          </div>
          <!--contact-info-->
          <div class="col-lg-6 col-md-12 contactus_form">
            <div class="row">
              <div class="form-group col-md-12">
                <div class="input-group">
                  <input
                    v-model="form.userId"
                    type="text"
                    class="form-control"
                    placeholder="아이디"
                  />
                </div>
              </div>
              <div class="form-group col-md-12">
                <div class="input-group">
                  <input
                    v-model="form.userPassword"
                    type="password"
                    class="form-control"
                    placeholder="비밀번호"
                  />
                </div>
              </div>
            </div>
            <div class="d-flex justify-content-around">
              <button class="btn large-btn" @click="goLogin()">로그인</button>
              <button class="btn large-btn" @click="goSignUp()">
                회원가입
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import axiosUtil from "../utils/axios-util.js";
export default {
  name: "login",
  data() {
    return {
      form: {
        userId: "",
        userPassword: "",
      },
    };
  },
  methods: {
    goLogin: function () {
      axiosUtil.post("/api/main/login.do", this.form, (result) => {
        if (result.data.result == null) {
          alert("일치하는 회원이 없습니다.");
        } else {
          this.$store.commit("LOGIN", result.data.result);
          this.$router.push("/");
        }
      });
    },
    goSignUp: function () {
      this.$router.push("/signUp");
    },
  },
};
</script>

<style></style>
