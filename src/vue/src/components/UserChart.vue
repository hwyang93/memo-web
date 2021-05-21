<script>
//Importing Line class from the vue-chartjs wrapper
import { Line } from 'vue-chartjs';
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);
import axiosUtil from '@/utils/axios-util';
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
      }
    };
  },
  methods: {
    getMonthData() {
      axiosUtil.get('/api/admin/getMonthData', {}, result => {
        this.list = result.data;
        console.log(this.list);
        this.datacollection.labels = Object.keys(this.list);
        console.log(this.datacollection.datasets);
        this.datacollection.datasets[0].data = Object.values(this.list);
        this.renderChart(this.datacollection, this.options);
      });
    }
  },
  beforeMount() {
    this.getMonthData();
  }
};
</script>