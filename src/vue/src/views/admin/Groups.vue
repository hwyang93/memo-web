<template>
  <div class="wrapper-admin">
    <side-menu> </side-menu>
    <div class="main-content">
      <admin-top-menu :send="this.pageName"></admin-top-menu>
      <div class="row groups">
        <div class="col-lg-3">
          <div class="card card-group shadow-wrap-1">
            <div class="card-content">
              <img src="../../images/dashboard/group.png" id="title-icon" alt="groupIcon" />
              <h4 class="title-left">Group State</h4>
              <div class="chartArea"><group-chart></group-chart></div>
            </div>
          </div>
        </div>
        <div class="col-lg-4">
          <div class="card card-group h-80 shadow-wrap-1">
            <div class="card-content">
              <img src="../../images/dashboard/group.png" id="title-icon" alt="groupIcon" />
              <h4 class="title-left">Group List</h4>
              <div class="left-listBox listBox">
                <table class="listTbl" v-if="this.groupList != null">
                  <colgroup>
                    <col width="20%" />
                    <col width="80%" />
                  </colgroup>
                  <thead>
                    <tr>
                      <th>No</th>
                      <th scope="col">Group Title</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="(g, index) in this.groupList" :key="index">
                      <th>{{ index + 1 }}</th>
                      <th scope="col" @click="detail(g.groupIdx)">{{ g.groupTitle }}</th>
                    </tr>
                  </tbody>
                </table>
                <table class="listTbl" v-else>
                  <colgroup>
                    <col width="100%" />
                  </colgroup>
                  <thead>
                    <tr>
                      <th scope="col">Data</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <th scope="col">그룹이 존재하지 않습니다.</th>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-5">
          <div class="card card-group h-80 shadow-wrap-1">
            <div class="card-content">
              <img src="../../images/dashboard/group.png" id="title-icon" alt="groupIcon" />
              <h3 class="title-left">그룹 관리</h3>
              <div class="c-box">
                <div class="infoBox">
                  <table class="infoTbl">
                    <colgroup>
                      <col width="30%" />
                      <col width="90%" />
                    </colgroup>
                    <tbody>
                      <tr scope="row">
                        <th scope="col">Group Title</th>
                        <td colspan="2">{{ this.groupDetail.groupTitle }}</td>
                      </tr>
                      <tr scope="row">
                        <th scope="col">Schedule</th>
                        <td colspan="2">0 개</td>
                      </tr>
                      <tr scope="row">
                        <th scope="col">Info</th>
                        <td colspan="2" v-if="this.groupDetail.groupComment != null"><textarea class="groupInfo" readonly v-model="this.groupDetail.groupComment"></textarea></td>
                        <td colspan="2" v-else><textarea class="groupInfo isEmpty" readonly>그룹 소개가 없습니다.</textarea></td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="groupMemberBox">
                  <div class="groupMember-Title">
                    <h4 class="title-left gm">Group Member</h4>
                    <h6 class="groupMemberCnt">{{ this.groupMembers.length }} 명</h6>
                  </div>
                  <div class="mList">
                    <ul>
                      <li class="m-item" v-for="(gm, index) in this.groupMembers" :key="index"><img src="#" class="mProfile" />{{ gm.groupUser }}</li>
                    </ul>
                  </div>
                </div>
                <div>
                  <span class="delBtn" @click="delGroup()">Delete</span>
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
import GroupChart from '../../components/GroupChart.vue';
export default {
  name: 'Groups',
  components: {
    SideMenu,
    AdminTopMenu,
    GroupChart,
    GroupChart
  },
  data() {
    return {
      pageName: 'Groups',
      groupList: [],
      groupDetail: [],
      groupMembers: []
    };
  },
  methods: {
    getGroupList() {
      axiosUtil.get('/api/admin/groupList', {}, result => {
        this.groupList = result.data.groupList;
        if (this.groupList != null) {
          axiosUtil.get('/api/admin/groupDetail/' + this.groupList[0].groupIdx, {}, result => {
            this.groupDetail = result.data.groupDetail;
            this.groupMembers = result.data.groupDetail.groupMembers;
          });
        }
      });
    },
    detail(idx) {
      axiosUtil.get('/api/admin/groupDetail/' + idx, {}, result => {
        this.groupDetail = result.data.groupDetail;
        this.groupMembers = result.data.groupDetail.groupMembers;
      });
    },
    delGroup() {
      axiosUtil.get('/api/admin/deleteGroup/' + this.groupDetail.groupIdx, {}, result => {
        if (result.data.result == 1) {
          alert('삭제되었습니다.');
          location.reload();
        } else {
          alert('오류');
        }
      });
    }
  },
  beforeMount() {
    this.getGroupList();
  }
};
</script>

<style lang="css">
@import '../../css/adminStyle.css';
</style>