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
          <b-form-textarea placeholder="Enter something..." rows="1" max-rows="10" no-resize></b-form-textarea>
          <b-carousel ref="preview" controls img-width="1024" img-height="480" :interval="0">
            <b-carousel-slide v-for="(item, index) in previewImgs" :img-src="item" :key="index"></b-carousel-slide>
          </b-carousel>
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
          <b-button variant="primary">저장</b-button>
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
      files: [],
      file: [],
      previewImgs: []
    };
  },
  watch: {
    file: function () {
      this.addFile();
    }
  },
  methods: {
    closeModal() {
      this.$emit('closeModal');
    },
    selectFile() {
      this.$refs.file.$el.querySelector('input[type=file]').click();
    },
    addFile() {
      console.log(this.file);
      this.files.push(this.file);
      const imageUrl = URL.createObjectURL(this.file);
      this.previewImgs.push(imageUrl);
      console.log(this.previewImgs.length);
      this.$refs.preview.setSlide(this.previewImgs.length - 1);
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
