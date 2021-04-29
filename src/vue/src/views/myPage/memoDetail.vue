<template>
  <div @close-modal="detailFlag = false">
    <div class="modal modal-overlay">
      <div class="modal-window">
        <div class="md-map md-container">
          <kakao-map ref="kakaoMap" :scheduleList="scheduleList" />
        </div>
        <div class="md-content">
          <div class="md-modify">
            <div class="md-header"><input type="text" class="md-header" v-model="form.title" /></div>
            <div class="md-body">
              <h5 class="md-title">주소</h5>
              <input type="text" class="md-text" v-model="form.memoPlace" />
              <h5 class="md-title">일시</h5>
              시작 : <input type="date" class="md-text" v-model="form.regDate" /><br />
              <h5 class="md-title">메모</h5>
              <textarea class="md-text" style="" v-model="form.memo"></textarea>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <b-button variant="primary">저장</b-button>
          <b-button variant="danger">삭제</b-button>
          <b-button @click="closeModal()" variant="dark">닫기</b-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axiosUtil from '@/utils/axios-util';
import KakaoMap from '../../components/KakaoMap';

export default {
  name: 'memoDetail',
  props: {
    idx: Number
  },
  components: { KakaoMap },
  data() {
    return {
      form: {
        title: '',
        memoPlace: '',
        regDate: '',
        memo: '',
        lon: 0,
        lat: 0
      },
      scheduleList: []
    };
  },
  methods: {
    getMemoInfo() {
      axiosUtil.get('/api/myMemo/myMemo/' + this.idx, {}, result => {
        this.form.title = result.data.result.title;
        this.form.memoPlace = result.data.result.memoPlace;
        this.form.regDate = this.$moment(result.data.result.regDate).format('YYYY-MM-DD');
        this.form.memo = result.data.result.memo;
        this.form.lon = result.data.result.lon;
        this.form.lat = result.data.result.lat;
        this.scheduleList.push(result.data.result);
        this.$refs.kakaoMap.setCenter(this.form.lat, this.form.lon);
      });
    },
    closeModal() {
      this.$emit('closeModal');
    }
  },
  beforeMount() {
    this.getMemoInfo();
  }
};
</script>

<style scoped></style>
