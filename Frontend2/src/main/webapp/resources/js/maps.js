/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
         /*<![CDATA[*/ 
	Highcharts.setOptions({
	lang: {
		drillUpText: '< Back to Superior'
	}
});
var map = null,
	geochina = 'https://data.jianshukeji.com/jsonp?filename=geochina/';
combines = [{
		name: '西南',
		id: 'southwest',
		'hc-middle-x': 0.73,
		province: ['西藏', '云南', '重庆', '贵州','四川']
	},{
		name: '华中',
		id: 'center',
		'hc-middle-y': 0.6,
		province: ['湖北', '湖南', '河南']
	},{
		name: '华南',
		id: 'south',
		'hc-middle-y': 0.4,
		province: ['广东', '广西', '海南']
	},{
		name: '华东',
		id: 'east',
		'hc-middle-x': 0.6,
		province: ['上海','江苏', '浙江', '安徽',  '福建', '江西', '山东']
	},{
		name: '华北',
		id: 'north',
		'hc-middle-y': 0.7,
		'hc-middle-x': 0.6,
		province: ['内蒙古', '山西', '河北','北京', '天津']
	},{
		name: '东北',
		id: 'northeast',
		province: ['辽宁', '吉林', '黑龙江']
	},{
		name: '西北',
		id: 'northwest',
		'hc-middle-x': 0.6,
		'hc-middle-y': 0.7,
		province: ['新疆', '青海', '甘肃', '宁夏','陕西']
	}];
$.getJSON(geochina + 'china.json&callback=?', function(mapdata) {
	var data = [];
  
	// 随机数据
        var newFeatues  = [],
		filterName = ['香港', '澳门', '南海诸岛'];
	Highcharts.each(mapdata.features, function(md, index) {
		var tmp = {
			name: md.properties.name,
			value: Math.floor((Math.random() * 100) + 1) // 生成 1 ~ 100 随机值
		};
		if(md.visible !== false && filterName.indexOf(tmp.name) === -1) {
                  if(md.properties.drilldown) {
			tmp.drilldown = md.properties.drilldown;
                        
		}
			 data.push(tmp);
			newFeatues.push(md);
		}
                
	});
        mapdata.features = newFeatues;
	console.log( mapdata)
	map = new Highcharts.Map('container', {
		chart: {
			events: {
				drilldown: function(e) {
					this.tooltip.hide();
					console.log(e);
					// 异步下钻
					if (e.point.drilldown) {
						var pointName = e.point.properties.fullname;
						map.showLoading('Loading,please wait...');
						// 获取二级行政地区数据并更新图表
						$.getJSON(geochina +   e.point.drilldown + '.json&callback=?', function(data) {
							data = Highcharts.geojson(data);
							Highcharts.each(data, function(d) {
								if(d.properties.drilldown) {
									d.drilldown = d.properties.drilldown;
								}
								d.value = Math.floor((Math.random() * 100) + 1); // 生成 1 ~ 100 随机值
							});
							map.hideLoading();
							map.addSeriesAsDrilldown(e.point, {
								name: e.point.name,
								data: data,
								dataLabels: {
									enabled: true,
									format: '{point.name}'
								}
							});
							map.setTitle({
								text: pointName
							});
						});
					}
				},
                          
       
				drillup: function() {
					map.setTitle({
						text: 'China'
					});
				}
			}
		},
		title: {
			text: ''
		},
                	
		mapNavigation: {
			enabled: true,
			buttonOptions: {
				verticalAlign: 'bottom'
			}
		},
		tooltip: {
			useHTML: true,
			headerFormat: '<table><tr><td>{point.name}</td></tr>',
			pointFormat: '<tr><td>Name:</td><td>{point.properties.fullname}</td></tr>' +
			'<tr><td>Administrative Number:</td><td>{point.properties.areacode}</td></tr>' +
			'<tr><td>Superior:</td><td>{point.properties.parent}</td></tr>' +
			'<tr><td>Longtitude and Latitude:</td><td>{point.properties.longitude},{point.properties.latitude}</td></tr>' ,
			footerFormat: '</table>'
		},
		// colorAxis: {
		//     min: 0,
		//     minColor: '#fff',
		//     maxColor: '#006cee',
		//     labels:{
		//         style:{
		//             "color":"red","fontWeight":"bold"
		//         }
		//     }
		// },
		series: [{
			// colors: ['#f9ad93', '#fbc4b0', '#fbc4b0', '#ffe6df', '#f59572', '#f69f81','#fab9a3'],
			colorByPoint: true,
			data: data,
			mapData: mapdata,
			dataLabels: {
				enabled: true,
				format: '{point.name}'
			},
			states: {
				hover: {
					borderWidth: 0,
					borderColor: 'rgba(0,0,0,0)'
				}
			},
			joinBy: 'name',
			name: '中国地图',
			borderWidth: 0,
			borderColor: 'rgba(0,0,0,0)'
		}]
	});
});
/*]]>*/

