<script src=" https://cdn.jsdelivr.net/npm/echarts@5.4.1/dist/echarts.min.js "></script>
<div class="col-6">
			<div class="card text-dark bg-light mb-3">
				<div class="card-header">Today</div>
				<div id="_today_chart" style="width: 100%;height:400px;">
				</div>
			</div>
		</div>

<script type="text/javascript">
      // Initialize the echarts instance based on the prepared dom
      var myChart = echarts.init(document.getElementById('_today_chart'));

      // Specify the configuration items and data for the chart
      option = {
        tooltip: {
          trigger: 'item'
        },
        legend: {},
        series: [
          {
            type: 'pie',
            radius: '50%',
            data: [
              { value: 1048, name: 'Male' },
              { value: 735, name: 'Female' },
              { value: 580, name: 'Other' }
            ]
          }
        ]
      };

      // Display the chart using the configuration items and data just specified.
      myChart.setOption(option);
    </script>
