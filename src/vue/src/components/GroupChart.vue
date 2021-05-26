<script>
import { Doughnut, mixins } from 'vue-chartjs';
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);
import axiosUtil from '@/utils/axios-util';
export default {
  extends: Doughnut,
  data() {
    return {
      chartOptions: {
        hoverBorderWidth: 3
      },
      chartData: {
        hoverBackgroundColor: 'red',
        hoverBorderWidth: 3,
        labels: ['Active', 'other'],
        datasets: [
          {
            label: 'Data One',
            backgroundColor: ['#41B883', '#FFB937'],
            data: [10, 5]
          }
        ],
        active: 0,
        other: 0
      }
    };
  },
  methods: {
    getGroupActive() {
      axiosUtil.get('/api/admin/getGroupActiveDate', {}, result => {
        this.active = result.data.active;
        this.other = result.data.other;
      });
    }
  },
  mounted() {
    // this.chartData is created in the mixin.
    // If you want to pass options please create a local options object
    this.renderChart(this.chartData, this.chartOptions, {
      borderWidth: '10px',
      hoverBackgroundColor: 'red',
      hoverBorderWidth: '10px'
    });
  }
};
</script>