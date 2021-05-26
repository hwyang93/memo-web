<template>
  <div class="wrapper-admin">
    <side-menu> </side-menu>
    <div class="main-content">
      <admin-top-menu :send="this.pageName"></admin-top-menu>
      <div class="container home-banner-container space-type-1">
        <div class="row">
          <div class="col-lg-4">
            <div class="home-banner-row left shadow-wrap-1">
              <img src="../../images/dashboard/post.png" id="title-icon" alt="postIcon" />
              <h4 class="title-left">Board List</h4>
              <div class="left-listBox listBox">
                <table class="listTbl">
                  <colgroup>
                    <col width="20%" />
                    <col width="80%" />
                  </colgroup>
                  <thead>
                    <tr>
                      <th>No</th>
                      <th scope="col">Title</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="(post, index) in postList" :key="index">
                      <th>{{ index + 1 }}</th>
                      <th scope="col" @click="detail(post.postIdx)">{{ post.userMemo.title }}</th>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <div class="col-lg-8">
            <div class="home-banner-row right shadow-wrap-1">
              <div class="title-back">
                <img src="../../images/dashboard/post.png" id="title-icon" alt="postIcon" />
                <h3 class="title-name">게시물 관리</h3>
                <div class="infoBox">
                  <table class="infoTbl">
                    <colgroup>
                      <col width="20%" />
                      <col width="30%" />
                      <col width="20%" />
                      <col width="30%" />
                    </colgroup>
                    <tbody>
                      <tr scope="row">
                        <th scope="col">Writer</th>
                        <td>{{ this.userMemo.userId }}</td>
                        <th scope="col">Register Date</th>
                        <td>{{ $moment(this.userMemo.regDate).format('YYYY-MM-DD') }}</td>
                      </tr>
                      <tr scope="row" v-if="this.postFiles != null">
                        <th scope="col">File</th>
                        <td colspan="3">
                          <ul class="fileList">
                            <li class="fileItem" v-for="(f, index) in this.postFiles" :key="index">
                              {{ f.fileSaveName }} <span class="delBtn file" @click="delFile(this.postDetail.postIdx)">X</span>
                            </li>
                          </ul>
                        </td>
                      </tr>
                      <tr scope="row" v-else>
                        <th scope="col">File</th>
                        <td colspan="3">첨부된 파일이 없습니다.</td>
                      </tr>
                      <tr scope="row">
                        <th scope="col">Place</th>
                        <td colspan="3">{{ this.userMemo.memoPlace }}</td>
                      </tr>
                      <tr scope="row">
                        <th scope="col">Title</th>
                        <td colspan="3">{{ this.userMemo.title }}</td>
                      </tr>
                      <tr scope="row">
                        <th scope="col">Content</th>
                        <td colspan="3"><textarea id="postCon" v-model="this.postDetail.contents" readonly></textarea></td>
                      </tr>
                    </tbody>
                  </table>
                  <table class="userInfoTbl">
                    <colgroup>
                      <col width="20%" />
                      <col width="30%" />
                    </colgroup>
                    <tbody></tbody>
                  </table>
                  <div>
                    <span class="delBtn" @click="delPost(this.postDetail.postIdx)">Delete</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axiosUtil from '@/utils/axios-util';
import SideMenu from '../../components/sideMenu.vue';
import AdminTopMenu from '../../components/adminTopMenu.vue';
export default {
  name: 'Board',
  components: {
    SideMenu,
    AdminTopMenu
  },
  data() {
    return {
      pageName: 'Post',
      postList: [],
      postDetail: {},
      postFiles: [],
      userMemo: {}
    };
  },
  methods: {
    detail(idx) {
      axiosUtil.get('/api/admin/postDetail/' + idx, {}, result => {
        this.postDetail = result.data.postDetail;
        this.userMemo = result.data.postDetail.userMemo;
        this.postFiles = result.data.postDetail.postFiles;
      });
    },
    delPost(idx) {
      axiosUtil.get('/api/admin/deletePost/' + idx, {}, result => {
        if (result.data.result == 1) {
          alert('삭제되었습니다.');
          location.reload();
        } else {
          alert('오류');
        }
      });
    }
  },
  created() {
    axiosUtil.get('/api/admin/postList', {}, result => {
      this.postList = result.data.postList;
      axiosUtil.get('/api/admin/postDetail/' + this.postList[0].postIdx, {}, result => {
        this.postDetail = result.data.postDetail;
        this.userMemo = result.data.postDetail.userMemo;
        this.postFiles = result.data.postDetail.postFiles;
      });
    });
  }
};
</script>

<style lang="css">
@import '../../css/adminStyle.css';
</style>
