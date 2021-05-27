<template>
  <div @close-modal="detailFlag = false">
    <div class="modal modal-overlay">
      <div class="d-flex flex-column modal-window post">
        <div class="d-flex justify-content-between align-items-center">
          <div class="header">
            <h3>POST 작성</h3>
          </div>
          <div class="close_icon" @click="closeModal">
            <span><i class="fas fa-times"></i></span>
          </div>
        </div>
        <div class="content">
          <b-form-textarea v-model="contents" placeholder="Enter something..." rows="1" max-rows="10" no-resize></b-form-textarea>
          <b-carousel ref="preview" indicators controls img-width="1024" img-height="480" :interval="0">
            <b-carousel-slide v-for="(item, index) in previewImgs" :img-src="item" :key="index"></b-carousel-slide>
          </b-carousel>
        </div>
        <div>
          <b-checkbox v-model="openFlag">공개여부</b-checkbox>
        </div>
        <div style="text-align: right">
          <b-form-file v-model="files" multiple style="display: none"></b-form-file>
          <b-form-file ref="file" v-model="file" style="display: none"></b-form-file>
          <span @click="selectFile">
            <i class="far fa-images fa-2x"></i>
          </span>
        </div>
        <hr />
        <div style="text-align: right">
          <b-button @click="onSave" variant="primary">저장</b-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axiosUtil from '@/utils/axios-util';

export default {
  name: 'post',
  props: {
    idx: Number
  },
  data() {
    return {
      contents: '',
      openFlag: false,
      files: [],
      file: null,
      previewImgs: []
    };
  },
  watch: {
    file: function () {
      this.addFile();
    }
  },
  methods: {
    test() {
      alert(123);
    },
    closeModal() {
      this.$emit('closeModal');
    },
    selectFile() {
      this.$refs.file.$el.querySelector('input[type=file]').click();
    },
    addFile() {
      this.files.push(this.file);
      const imageUrl = URL.createObjectURL(this.file);
      this.previewImgs.push(imageUrl);
      debugger;
      const slide = this.previewImgs.length - 1;
      this.$refs.preview.setSlide(slide);
    },
    onSave() {
      let files = [];
      let file = {};
      const formData = new FormData();
      formData.append('contents', this.contents);
      this.files.forEach(item => {
        formData.append(item.name, item);
        file.fileOrgName = item.name;
        files.push(file);
      });

      axiosUtil.multipartPost('/api/post/post/userMemo/' + this.idx, formData, () => {
        alert('포스트가 저장되었습니다.');
        this.$emit('closeModal');
      });
    }
  },
  beforeMount() {}
};
</script>

<style scoped>
.content image {
  object-fit: scale-down;
}
</style>
