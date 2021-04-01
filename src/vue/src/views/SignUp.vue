<template>
  <div>
    <section class="bg-img-1 space contact_page">
      <div class="container">
        <div class="row cont-cover">
          <div class="col-lg-6 col-md-12">
            <div class="contcat_info">
              <div class="section-heading ml-0">
                <img src="../images/all-img/logo.png" alt="logo_img" class="img-fluid" />
              </div>
            </div>
          </div>
          <!--contact-info-->
          <div class="col-lg-6 col-md-12 contactus_form">
            <div class="row">
              <div class="form-group col-md-12">
                <div class="input-group">
                  <input v-model="form.userId" type="text" class="form-control" placeholder="아이디" />
                </div>
              </div>
              <div class="form-group col-md-12">
                <div class="input-group">
                  <input v-model="form.userPassword" type="password" class="form-control" placeholder="비밀번호" />
                </div>
              </div>
              <div class="form-group col-md-12">
                <div class="input-group">
                  <input v-model="form.userPasswordChk" type="password" class="form-control" placeholder="비밀번호 확인" />
                </div>
              </div>
              <div class="form-group col-md-12">
                <div class="input-group">
                  <input v-model="form.userEmail" type="email" class="form-control" placeholder="Email" />
                </div>
              </div>
              <div class="form-group col-md-12">
                <div class="input-group">
                  <input v-model="form.userName" type="text" class="form-control" placeholder="이름" />
                </div>
              </div>
            </div>
            <div class="d-flex justify-end">
              <b-button variant="primary" size="lg" @click="goSignUp()">가입</b-button>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: 'signUp',
  data() {
    return {
      form: {
        userId: '',
        userPassword: '',
        userPasswordChk: '',
        userEmail: '',
        userName: ''
      }
    };
  },
  methods: {
    goSignUp: function () {
      console.log(this.axiosUtil);
      this.axiosUtil.post('/api/main/signUp.do', this.form, result => {
        alert('가입되었습니다.');
        this.$router.push('/login');
      });
    }
  }
};
</script>

<style></style>
