var app = angular.module('app',[]);
app.controller('controller',main);
var scope ;	    
var optionLine ;	 
var optionLine1 ;	
optionKhis = {
	    title : {
	        text: ''
	    },
	    tooltip : {
	        trigger: 'axis',
	        formatter: function (params) {
	            var res = params[0].seriesName + ' ' + params[0].name;
	            res += '<br/>  开盘 : ' + params[0].value[0] + '  最高 : ' + params[0].value[3];
	            res += '<br/>  收盘 : ' + params[0].value[1] + '  最低 : ' + params[0].value[2];
	            return res;
	        }
	    },
	    legend: {
	        data:['日k线']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataZoom : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType: {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    dataZoom : {
	        show : false,
	        realtime: false,
	        start : 0,
	        end : 100
	    },
	    dataZoom : {
			show : true,
			realtime : true,
			start : 0,
			end : 100
		},
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : true,
	            show:true,
	            splitNumber:20,
	            axisTick: {onGap:false},
	            splitLine: {show:true},
	            data : []
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            scale:true,
	            boundaryGap: [0.01, 0.01]
	        }
	    ],
	    series : [
	        {
	            name:'',
	            type:'k', 
	            symbol:'none',
	            itemStyle: {
	                normal: {
	                    color: 'red',           // 阳线填充颜色
	                    color0: 'green',   // 阴线填充颜色
	                }
	            },
	            data:[]
	        }
	    ]
	};
option = {
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient : 'vertical',
	        x : 'left',
	        data:['涨停','跌停','上涨','下跌','停盘']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {
	                show: true, 
	                type: ['pie', 'funnel'],
	                option: {
	                    funnel: {
	                        x: '25%',
	                        width: '50%',
	                        funnelAlign: 'center',
	                        max: 1548
	                    }
	                }
	            },
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    series : [
	        {
	            name:'访问来源',
	            type:'pie',
	            radius : ['50%', '70%'],
	            itemStyle : {
	                normal : {
	                    label : {
	                        show : false
	                    },
	                    labelLine : {
	                        show : false
	                    }
	                },
	                emphasis : {
	                    label : {
	                        show : true,
	                        position : 'center',
	                        textStyle : {
	                            fontSize : '30',
	                            fontWeight : 'bold'
	                        }
	                    }
	                }
	            },
	            data:[
	                {value:335, name:'涨停'},
	                {value:310, name:'跌停'},
	                {value:234, name:'上涨'},
	                {value:135, name:'下跌'},
	                {value:1548, name:'停牌'}
	            ]
	        }
	    ]
	};
var basePath;
function main($scope,$q,$http) {
	basePath = $("#path").val();
	drawChart(ajaxGet(basePath+"restfull/stock/prices/k.json"));
}
function drawChart(data){	
	  var myChart = echarts.init($("#KChart")[0]);
	  optionKhis.series[0].data=data.y;
	  optionKhis.xAxis[0].data=data.x;
	  myChart.setOption(optionKhis);
	  var myChart1 = echarts.init($("#tjChart")[0]);
	  myChart1.setOption(option);
}


