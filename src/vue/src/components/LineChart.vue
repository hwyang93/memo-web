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
      datacollection: {
        //Data to be represented on x-axis
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
        datasets: [
          {
            label: 'Data One',
            backgroundColor: '#f87979',
            pointBackgroundColor: 'white',
            borderWidth: 1,
            pointBorderColor: '#249EBF',
            //Data to be represented on y-axis
            data: [40, 20, 30, 50, 90, 10, 20, 40, 50, 70, 90, 100]
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
      list: [],
      date: {},
      cnt: {}
    };
  },
  methods: {
    getMonthData() {
      axiosUtil.get('/api/admin/getMonthData', {}, result => {
        this.list = result.data.list;
        for (d in list) {
          this.date.put(d.regDate);
        }
        for (c in list) {
          this.cnt.put(d.cnt);
        }
        console.log(this.date);
        console.log(this.cnt);
      });
    }
  },
  mounted() {
    this.getMonthData();
    //renderChart function renders the chart with the datacollection and options object.
    this.renderChart(this.datacollection, this.options);
  }
};
</script>