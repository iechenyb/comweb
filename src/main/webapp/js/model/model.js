var app = angular.module('app', []);
app.controller('controller', main);
var page;
var path;
function main($scope, $q, $http) {
	page = $scope;
	path = $('#path').val();
	page.openEditView = openEditView;
	page.submit = submit;
	page.del = delmodel;
	resetList();
}
function resetList() {
	page.list=[];
	for(i=1;i<=21;i++){
		var model={'id':i,'col1':'xx1'+i,'col2':'xx2'+i,'col3':'xx3'+i,'col4':'xx4'+i,'col5':'xx5'+i,'col6':'xx6'+i};
	    page.list.push(model);
	}
	initPageSpilt(page.list,page);
	var url = path + "model/list.do?x=y";
	console.log(ajaxGet(url));
}
function submit(model) {
	delete model['$$hashKey'];
	url = path + "model/add.do";
	if (page.cmd == 'mod') {
		url = path + "model/update.do";
	}
	var data = ajaxPostJson(url,page.model);
	if(isLawFullRet(data)){
		page.list.push(data.t);
		$('#eidtView').modal("close");
		page.model = {};
	}
}
var pubObj;
function delmodel(record) {
	pubObj = record;
	$('#tip').modal({
		relatedTarget : this,
		onConfirm : function(options) {
			var url = path + "model/del.do?id=" + pubObj.id;
			var data = ajaxGet(url);
			if(isLawFullRet(data)){
				page.list.splice(page.list.indexOf(pubObj), 1);
				page.$apply();
				initPageSpilt(page.list,page);
				toPage(page);
			}
		},
		onCancel : function() {
		}
	});
}
function openEditView(cmd, record) {
	page.cmd = cmd;
	if (cmd != 'add') {
		page.model = record;
	} else {
	}
	$('#eidtView').modal({
		onConfirm : function() {
			if (window.confirm('你确定要取消编辑吗？')) {
				resetList();
				$('#eidtView').modal("close");
				return true;
			} else {
				return false;
			}
		},
		closeOnConfirm : true,
		closeViaDimmer : false,
		width : $(window).width() * 0.8,
		height : Math.min($(window).height() * 0.8, 800),
	});
}

function orderEvent() {
	var up = page.direct;
	page.list.forEach(function(item) {
		return;
	});
	if (up == "0") {
		page.list.sort(function(a, b) {
			return a[page.order] < b[page.order] ? 1 : -1;
		});
	} else {
		page.list.sort(function(a, b) {
			return a[page.order] > b[page.order] ? 1 : -1;
		});
	}
}
