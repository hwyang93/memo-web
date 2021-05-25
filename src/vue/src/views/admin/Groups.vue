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
                <table class="listTbl">
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
                    <tr v-for="(group, index) in groupList" :key="index">
                      <th>{{ index + 1 }}</th>
                      <th scope="col" @click="detail(group.groupIdx)">{{ group.groupTitle }}</th>
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
                        <td colspan="2"></td>
                      </tr>
                      <tr scope="row">
                        <th scope="col">Group Title</th>
                        <td colspan="2"></td>
                      </tr>
                      <tr scope="row">
                        <th scope="col">Info</th>
                        <td colspan="2"><textarea id="groupInfo" readonly></textarea></td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="groupMemberBox">
                  <h4 class="title-left">Group Member</h4>
                  <div class="mList">
                    <ul>
                      <li class="m-item" v-for="(gm, index) in this.groupMember" :key="index"><img src="#" class="mProfile" />{{ gm }}</li>
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
      groupDetail: {},
      groupMember: {}
    };
  },
  methods: {
    getGroupList() {
      axiosUtil.get('/api/admin/groupList', {}, result => {
        this.groupList = result.data.groupList;
        console.log(this.groupList);
      });
    },
    detail(idx) {
      axiosUtil.get('/api/admin/groupDetail/' + idx, {}, result => {
        this.groupDetail = result.data.groupDetail;
        this.groupMember = result.data.groupDetail.groupMember;
        console.log(this.groupDetail);
        console.log(this.groupMember);
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