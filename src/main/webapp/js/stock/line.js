var app = angular.module('app',[]);
app.controller('controller',main);
var scope ;	    
var optionLine ;	 
var optionLine1 ;	
optionLine = {
		   title: {
		       text: "上证指数",
		       subtext: '(SH 000001)',
		       x: "center"
		   },
		   dataZoom : {
				show : true,
				realtime : true,
				start : 0,
				end : 100
			},
		   tooltip: {
		       trigger: "item",
		       formatter: "{a} <br/>{b} 价格 {c}"
		   },
		   legend: {
		       x: 'left',
		       data: ["价格"]
		   },
		   xAxis: [
		       {
		           type: "category",
		           name: "时间",
		           splitLine: {show: true},
		           data: []
		       }
		   ],
		   yAxis: [
		       {
		           type: "value",
		           min:'2',
		           max:'31',
		           splitNumber:6,
		           axisLabel : {
		              formatter: function (value){return value.toFixed(4);}
		           },
		           name: "价格"
		       }
		   ],
		    toolbox: {
		       show: true,
		       feature: {
		           mark: {
		               show: true
		           },
		           dataView: {
		               show: true,
		               readOnly: true
		           },
		           restore: {
		               show: true
		           },
		           saveAsImage: {
		               show: true
		           }
		       }
		   },
		   calculable: true,
		   series: [
		       {
		           name: "价格",
		           type: "line",
		           symbol:'none',
		           data: []

		       }/*,
		       {
		           name: "日均线",
		           type: "line",
		           data: [16.5, 16.5, 16.5, 16.5, 16.5, 16.5, 16.5, 16.5, 16.5]

		       }*/
		   ]
		};	

optionLine1 = {
	    title : {
	        text: '上证指数',
	        subtext: '(SH 000001)'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    dataZoom : {
			show : true,
			realtime : true,
			start : 0,
			end : 100
		},
	    legend: {
	        data:['价格','日均值']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            name:'时间',
	            boundaryGap : false,
	            data : []
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
		        splitNumber:6,
		        name:'价格',
	            axisLabel : {
	                formatter: function (value){return value.toFixed(4);}
	            }
	        }
	    ],
	    series : [
	        {
	            name:'价格',
	            type:'line',
	            symbol:'none',
	            data:[]/*,
	            markPoint : {
	                data : [
	                    {type : 'max', name: '最高价'},
	                    {type : 'min', name: '最低价'}
	                ]
	            }*//*,
	            markLine : {
	                data : [
	                    {type : 'average', name: '平均值'}
	                ]
	            }*/
	        }/*,
		    {
		           name: "日均值",
		           type: "line",
		           data: [16.5, 16.5, 16.5, 16.5, 16.5, 16.5, 16.5, 16.5, 16.5]

		       }*/
	    ]
	};
var basePath;
function main($scope,$q,$http) {
	basePath = $("#path").val();
	var lineChart = echarts.init($("#lineChart")[0]);
	drawChart(ajaxGet(basePath+"restfull/stock/prices/line.do?code=sh000001"));
	var data = ajaxGet(basePath+"restfull/stock/codes.do");
	$scope.shs=data.sh;
	$scope.szs=data.sz;
	$scope.setCode=function(vo){
		drawChart(ajaxGet(basePath+"restfull/stock/prices/line.do?code="+vo.code_));
	}
	$scope.load=function(){
		drawChart(ajaxGet(basePath+"restfull/stock/prices/line.do?code=sh000001"));
	}
}

function drawChart(data){	
	  var myChart = echarts.init($("#lineChart")[0]);
	  optionLine.series[0].data=data.y;
	  optionLine.xAxis[0].data=data.x;
	  optionLine.yAxis[0].max = Number(data.max+0.1).toFixed(4);
	  optionLine.yAxis[0].min = Number(data.min-0.1).toFixed(4);
	  var aveLine = {
	    data : [
	      [{name: '昨日收盘价',value: data.close, xAxis: '09:30', yAxis: data.close},{xAxis:'15:00',yAxis: data.close}]
	    ]
	  };
	  optionLine.series[0].markLine = aveLine;
	  
      myChart.setOption(optionLine);
      var myChart1 = echarts.init($("#lineChart1")[0]);
      optionLine1.series[0].data=data.y;
	  optionLine1.xAxis[0].data=data.x;
	  optionLine1.yAxis[0].max = Number(data.max+0.1).toFixed(4);
	  optionLine1.yAxis[0].min = Number(data.min-0.1).toFixed(4);
	  var aveLine1 = {
	    data : [
	      [{name: '昨日收盘价',value: data.close, xAxis: '09:30', yAxis: data.close},{xAxis:'15:00',yAxis: data.close}]
	    ]
	  };
	  optionLine1.series[0].markLine = aveLine1;
      myChart1.setOption(optionLine1);
}


