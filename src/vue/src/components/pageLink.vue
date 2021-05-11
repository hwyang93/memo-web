<template>
  <div>
    <table class="userListTbl">
      <colgroup>
        <col width="20%" />
        <col width="20%" />
        <col width="10%" />
        <col width="22%" />
        <col width="14%" />
        <col width="14%" />
      </colgroup>
      <thead>
        <tr>
          <th scope="col">아이디</th>
          <th scope="col">비밀번호</th>
          <th scope="col">이름</th>
          <th scope="col">이메일</th>
          <th scope="col">가입일</th>
          <th scope="col"></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(user, index) in paginatedData" :key="index">
          <th>{{ user.userId }}</th>
          <th>{{ user.userPassword }}</th>
          <th>{{ user.userName }}</th>
          <th>{{ user.userEmail }}</th>
          <th>{{ $moment(user.regDate).format('YYYY-MM-DD') }}</th>
          <th><span class="delBtn" @click="delUser(user.userId)">Delete</span></th>
        </tr>
      </tbody>
    </table>
    <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">이전</button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">다음</button>
    </div>
  </div>
</template>

<script>
import axiosUtil from '@/utils/axios-util';

export default {
  name: 'pageLink',
  data() {
    return {
      pageNum: 0
    };
  },
  props: {
    listArray: {
      type: Array,
      require: true
    },
    pageSize: {
      type: Number,
      require: true,
      default: 10
    }
  },
  methods: {
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    delUser: function (id) {
      axiosUtil.get('/api/admin/deleteUser/' + id, {}, result => {
        console.log(result);
        if (result.data.result == 1) {
          alert('삭제되었습니다.');
          location.reload();
        } else {
          alert('오류');
        }
      });
    }
  },
  computed: {
    pageCount() {
      let listLeng = this.listArray.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);

      if (listLeng % listSize > 0) {
        page += 1;
      }
      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;

      return this.listArray.slice(start, end);
    }
  }
};
</script>
<style scoped>
.userListTbl {
  width: 100%;
  margin: auto;
}
.userListTbl thead {
  background: rgb(230, 230, 230);
  width: 100%;
  border-top: 1px solid #939597;
  border-bottom: 1px solid #939597;
}
.userListTbl tbody {
  width: 100%;
}
.userListTbl thead tr {
  text-align: center;
  font-weight: 600;
}
.userListTbl tbody tr {
  text-align: center;
  border-bottom: 1px solid #ccc;
}
.userListTbl th {
  padding: 0.4rem;
  font-weight: 200;
}
.delBtn {
  background: #eee;
  padding: 3px 8px;
  border-radius: 8px;
  border: 1px solid #ccc;
  cursor: pointer;
}
.btn-cover {
  margin-top: 1.5rem;
  text-align: center;
}
.btn-cover .page-btn {
  width: 4rem;
  height: 2rem;
  letter-spacing: 0.5px;
  border: 1px solid #ccc;
  border-radius: 10px;
}
.btn-cover .page-count {
  padding: 0 1rem;
}
</style>