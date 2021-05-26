<script>
//Importing Line class from the vue-chartjs wrapper
import { Line } from 'vue-chartjs';
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);
import axiosUtil from '@/utils/axios-util';
import EventBus from '@/eventBus.js';
//Exporting this so it can be used in other components
export default {
  extends: Line,
  data() {
    return {
      list: [],
      date: {},
      cnt: {},
      datacollection: {
        //Data to be represented on x-axis
        labels: [],
        datasets: [
          {
            label: 'user',
            backgroundColor: '#f87979',
            pointBackgroundColor: 'white',
            borderWidth: 1,
            pointBorderColor: '#249EBF',
            //Data to be represented on y-axis
            data: []
          }
        ]
      },
      //Chart.js options that controls the appearance of the chart
      options: {
        scales: {
          yAxes: [
            {
              ticks: {
                beginAtZero: true
              },
              gridLines: {
                display: true
              }
            }
          ],
          xAxes: [
            {
              gridLines: {
                display: false
              }
            }
          ]
        },
        legend: {
          display: true
        },
        responsive: true,
        maintainAspectRatio: false
      },
      userTotal: 0
    };
  },
  methods: {
    getMonthData() {
      axiosUtil.get('/api/admin/getMonthData', {}, result => {
        this.list = result.data;
        this.datacollection.labels = Object.keys(this.list);
        this.datacollection.datasets[0].data = Object.values(this.list);
        var dataList = Object.values(this.list);
        this.userTotal = dataList.reduce((stack, el) => {
          return stack + el;
        }, 0);
        EventBus.$emit('userTotal', this.userTotal);
        this.renderChart(this.datacollection, this.options);
      });
    }
  },
  beforeMount() {
    this.getMonthData();
  }
};
</script>