/**
 * 
 */
/**
 * 
 */
/**
 * 
 */


function requestFiveData(data) { //ajax통신을 위한 requestData 함수
	$.ajax({
		url: "/Home5",
		type: "POST",
		contentType: "application/json",
		success: function(point) {
			for (var i = 0; i < point.length; i++) {
				//%Y-%m-%d 
				data.push({
					x: Date.parse(point[i].time),
					y: point[i].data
				});
			} //for();
		}, //function();
		cache: false
	}) //ajax
}

$(document).ready(function() { //별도의 호출없이 페이지 로드됐을 때 자동 호출되는 함수



	Highcharts.chart('container2', {
		chart: {
			type: 'spline',
			animation: Highcharts.svg, // don't animate in old IE
			marginRight: 10,
			events: { // chart의 이벤트 리스너로 requestData라는 ajax 함수로 등록돼 있는데 
				load: function () {
				var series = this.series[0];
				setInterval(function() {
						$.ajax({
							url: "/Home1",
							type: "POST",
							contentType: "application/json",
							success: function(point) {
						       let x = Date.parse(point.time), // current time
              					y = point.mfcc;
              				
              				
								series.addPoint([x, y], true, true);
							},
							cache: false
						}) //ajax();		            
					
				},5000) //interval();*/
				}
			} //events();
		}, //chart();

		time: {
			useUTC: false
		},

		title: {
			text: 'Live data'
		},

		xAxis: {
			type: 'datetime',
			tickPixelInterval: 150
		},

		yAxis: {
			title: {
				text: 'mfcc'
			},

			plotLines: [{
				value: 0,
				width: 1,
				color: '#808080'
			}]
		},

		tooltip: {
			headerFormat: '<b>{series.name}</b><br/>',
			pointFormat: '{point.x:%Y-%m-%d %H:%M:%S}<br/>{point.y:.4f}'
		},

		exporting: {
			enabled: false
		},


		series: [{
			name: 'mfcc',
			data: (function() {
				var data = [],
					time = (new Date()).getTime(),
					i;

				for (i = -19; i <= 0; i += 1) {
					data.push({
						x: time + i * 1000,
						y: 0
					});
				}

				return data;
			}())
		}]
	});
});
      //(function () {
        // generate an array of random data
        //var data = [],
          //time = (new Date()).getTime(),
          //i;

        //for (i = -19; i <= 0; i += 1) {
          //data.push({
            //x: time + i * 1000,
            //y: Math.random()
          //});
        //}
       // return;
      //}())
    //}]