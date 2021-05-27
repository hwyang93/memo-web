<template>
  <div class="wrapper-admin">
    <side-menu> </side-menu>
    <!-- user -->
    <div class="main-area">
      <div class="main-content">
        <admin-top-menu :send="this.pageName"></admin-top-menu>
        <div class="row dash">
          <div class="col-lg-4">
            <div class="card-dash shadow-wrap-1">
              <div class="card-content">
                <div class="c-box title">Users</div>
                <div class="c-box icon"><img src="../../images/dashboard/user.png" id="dashIcon" /></div>
                <div class="c-box data"><user-chart></user-chart></div>
                <div class="c-box total">Total : {{ this.userTotal }}</div>
              </div>
            </div>
          </div>
          <div class="col-lg-4">
            <div class="card-dash shadow-wrap-1">
              <div class="card-content">
                <div class="c-box title">Board</div>
                <div class="c-box icon"><img src="../../images/dashboard/post.png" id="dashIcon" /></div>
                <div class="c-box data"><board-chart></board-chart></div>
                <div class="c-box total">Total : {{ this.postTotal }}</div>
              </div>
            </div>
          </div>
          <div class="col-lg-4">
            <div class="card-dash shadow-wrap-1">
              <div class="card-content">
                <div class="c-box title">Group</div>
                <div class="c-box icon"><img src="../../images/dashboard/group.png" id="dashIcon" /></div>
                <div class="c-box data"><group-chart style="height: 230px; width: 230px; margin: auto"></group-chart></div>
                <div class="c-box total">Total : {{ this.groupTotal }}</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import AdminTopMenu from '../../components/adminTopMenu.vue';
import SideMenu from '../../components/sideMenu.vue';
import UserChart from '@/components/UserChart.vue';
import BoardChart from '@/components/BoardChart.vue';
import EventBus from '@/eventBus.js';
import GroupChart from '@/components/GroupChart.vue';
export default {
  name: 'Dashboard',
  components: {
    SideMenu,
    AdminTopMenu,
    UserChart,
    BoardChart,
    GroupChart
  },
  data() {
    GroupChart;
    return {
      pageName: 'Dashboard',
      userTotal: 0,
      postTotal: 0,
      groupTotal: 0
    };
  },
  methods: {},
  created() {
    EventBus.$on('postTotal', payload => {
      this.postTotal = payload;
    });
    EventBus.$on('userTotal', payload => {
      this.userTotal = payload;
    });
    EventBus.$on('groupTotal', payload => {
      this.groupTotal = payload;
    });
  }
};
</script>
<style lang="css">
@import '../../css/adminStyle.css';
</style>
