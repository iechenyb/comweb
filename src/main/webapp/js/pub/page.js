var PAGESIZE=10;//默认的每页记录条数
var realPagesEq1 = false;//总页数为1
var realPagesEq0 = false;//总页数为0
function initPageSplit(data,page){
	initPageSpilt(data,page);
}
function initPageSpilt(data,page){
    realPagesEq1 = false;
    realPagesEq0 = false;
    if(page.pageSize!=null&&page.pageSize!=''&&page.pageSize!='undefined'){
    	PAGESIZE = page.pageSize;
    }else{
        page.pageSize = PAGESIZE;
    }
    page.total = data.length;
	var pages = Math.ceil(data.length /PAGESIZE);
	if(pages<=1){
	    if(pages==0){
	    	realPagesEq0 = true;
	    }else{
			realPagesEq1 = true;
		}
		page.pages=2;//至少有两页才会绘制分页栏目
	}else{
		page.pages = pages;
	}
	$("#page").page({
        pages:page.pages, //页数
        curr: 1, //当前页 
        type: 'default', //主题
        groups: 5, //连续显示分页数
        prev: '<', //若不显示，设置false即可
        next: '>', //若不显示，设置false即可        
        first: "首页",
        last: "最后一页", //false则不显示
        before: function(context, next) { //加载前触发，如果没有执行next()则中断加载
            page.start = (context.option.curr-1)*PAGESIZE+1;
		    page.end = context.option.curr*PAGESIZE;
		    page.cur = context.option.curr;
            next();
        },
        render: function(context, $el, index) { //渲染[context：对this的引用，$el：当前元素，index：当前索引]
            //逻辑处理
            if (index == 'last') { //虽然上面设置了last的文字为尾页，但是经过render处理，结果变为最后一页
               if(realPagesEq1){
              	  $el.find('a').css('display','none'); //只有一页不展示最后一页
               }else{
                  $el.find('a').html('最后一页');
               }
                return $el; //如果有返回值则使用返回值渲染
            }
            if (index == 'first') {
                $el.find('a').html('首页');
                return $el; //如果有返回值则使用返回值渲染
            }
            if(realPagesEq1&&(index==2||index=='next')){//只有一页不展示第二页/只有一页不展示下一页
            	$el.find('a').css('display','none'); 
            	page.pages = 1;//总页数还原成1
            }
            if(realPagesEq0&&(index==1||index==2||index=='next')){
            	$el.find('a').css('display','none');
            	page.pages = 0;//总页数还原成0
            	pages.cur = 0;
            }
            return false; //没有返回值则按默认处理
        },
        after: function(context, next) { //加载完成后触发
            next();
        },
        /*
         * 触发分页后的回调，如果首次加载时后端已处理好分页数据则需要在after中判断终止或在jump中判断first是否为假
         */
        jump: function(context, first) {
        	if(first){return;}
		    //console.log('当前第：' + context.option.curr + "页"+",start="+page.start+",end="+page.end);
		   try{
		    page.$apply(function(){
	    	    page.start = (context.option.curr-1)*PAGESIZE+1;
			    page.end = context.option.curr*PAGESIZE;
			    page.cur = context.option.curr;
		    });
		   }catch(e){console.log("the error need not care add by iechenyb!");}
        }
    });
}
var jump = function(context) {//注意在全局作用域下定义
   console.log('当前第：' + context.option.curr + "页");
}   
function calPages(data,page){
    if(page.pageSize!=null&&page.pageSize!=''&&page.pageSize!='undefined'){
    	PAGESIZE = page.pageSize;
    }else{
        page.pageSize = PAGESIZE;
    }
    page.total = data.length;
	var pages = Math.ceil(data.length /PAGESIZE);
	return pages;
}
/**
 * cur=3 pages=3 
 */
function toPage(page){
	newpages =  calPages(page.list,page);
	console.log("当前页"+page.cur+"当前总页数："+page.pages+",最新总页数："+calPages(page.list,page));
	if(newpages<=page.pages){
		index=newpages;
	}else{
		index=page.cur;
	}
	$("a[data-page="+index+"]").click();
}
/**
 * 设置当前页面
 * @param pageNum
 */
function setCurr(pageNum){
	pager.setCurr(pageNum,
	 function(){
	    console.log("topage"+pageNum);
	 });
 }
