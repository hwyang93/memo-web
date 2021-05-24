<script>
//Importing Line class from the vue-chartjs wrapper
import { Bar } from 'vue-chartjs';
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);
import axiosUtil from '@/utils/axios-util';
import EventBus from '@/eventBus.js';
//Exporting this so it can be used in other components
export default {
  extends: Bar,
  data() {
    return {
      datacollection: {
        type: 'bar',
        //Data to be represented on x-axis
        labels: ['post', 'Memo', 'Schedule', 'GroupSchedule'],
        datasets: [
          {
            label: 'Post',
            borderWidth: 1,
            pointBorderColor: '#249EBF',
            //Data to be represented on y-axis
            data: [],
            backgroundColor: ['skyblue', 'lightgray', 'lightgreen', 'lightpink']
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
      post: 0,
      memo: 0,
      schedule: 0,
      gSchedule: 0,
      total: 0
    };
  },
  methods: {
    getPostData() {
      axiosUtil.get('/api/admin/getPostData', {}, result => {
        this.post = result.data.post;
        this.memo = result.data.memo;
        this.schedule = result.data.schedule;
        this.gSchedule = result.data.gSchedule;
        this.datacollection.datasets[0].data = [this.post, this.memo, this.schedule, this.gSchedule];
        this.total = this.post + this.memo + this.schedule + this.gSchedule;
        EventBus.$emit('postTotal', this.total);
        this.renderChart(this.datacollection, this.options);
      });
    }
  },
  mounted() {
    this.getPostData();
  }
};
</script>