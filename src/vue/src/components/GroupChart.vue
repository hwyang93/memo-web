<script>
import { Doughnut, mixins } from 'vue-chartjs';
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);
import axiosUtil from '@/utils/axios-util';
import EventBus from '@/eventBus.js';
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
        labels: ['Active', 'Other'],
        datasets: [
          {
            label: 'Data One',
            backgroundColor: ['#41B883', '#FFB937'],
            data: []
          }
        ],
        active: 0,
        other: 0,
        total: 0
      }
    };
  },
  methods: {
    getGroupActive() {
      axiosUtil.get('/api/admin/getGroupActiveData', {}, result => {
        this.chartData.datasets[0].data = [result.data.active, result.data.other];
        this.total = result.data.active;
        EventBus.$emit('groupTotal', this.total);
        this.renderChart(this.chartData, this.chartOptions, {
          borderWidth: '5px',
          hoverBackgroundColor: 'red',
          hoverBorderWidth: '5px'
        });
      });
    }
  },
  beforeMount() {
    this.getGroupActive();
  }
};
</script>