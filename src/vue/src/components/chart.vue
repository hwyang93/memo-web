<template>
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <canvas style="height: 200px" id="myChartOne"></canvas>
      </div>
    </div>
  </div>
</template>
<script>
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);
export default {
  methods: {
    getChart() {
      let myChartOne = document.getElementById('myChartOne');
      let barChart = new Chart(myChartOne, {
        type: 'bar',
        data: {
          labels: ['1월', '2월', '3월', '4월'],
          datasets: [
            {
              label: '회원',
              data: [100, 500, 1000, 1500]
            }
          ],
          backgroundColor: ['red', 'rgba(255,255,0,0.5)', '#ff9000', 'skyblue', 'dodgerblue']
        },
        options: {
          responsive: true,
          lineTension: 1,
          scales: {
            yAxes: [
              {
                ticks: {
                  beginAtZero: true,
                  padding: 25
                }
              }
            ]
          }
        }
      });
    }
  },
  mounted() {
    this.getChart();
  }
};
</script>




