
	/*链接*/
	function linkedToUrl(url){
		location.href=url ;
	}
	
	/*显示影藏的div*/
	function showDiv(divId){
		document.getElementById(divId).style.display="block" ;
	}
	
	/*隐藏div*/
	function hiddenDiv(divId){
		document.getElementById(divId).style.display="none" ;
	}
	
	/*显示第一个div，隐藏第二个div*/
	function shDiv(div1,div2){
		document.getElementById(div1).style.display="block" ;
		document.getElementById(div2).style.display="none" ;
		
	}
	
	
	
	
	/*弹出一个窗口，固定了大小（为了统一窗口大小）*/
	function openNewWindow(url){
		win=window.open(url,"parentPage","dependent,fullscreen=no,menubar=yes,toolbar=yes,directories=yes,location=no,status=no,scrollbars=yes,resizable=yes"); 
 			//window.close;
	   	win.focus()
	}
	
	/*为了需要弹出一个大殿的窗口*/
	function openNewBigWindow(url){
		win=window.open(url,"parentPage","height=525,width=800,top=150,left=100,dependent,fullscreen=no,menubar=no,toolbar=no,directories=no,location=no,status=no,scrollbars=no,resizable=no"); 
 			//window.close;
	   	win.focus()
	}
	
	
	//"height="+hight+",width="+width+",top=150
	/*弹出一个窗口，已大小所谓参数*/
	function openAWindow(url){
		win=window.open(url,"parentPage","height=400,width=500,top=150,left=300,dependent,fullscreen=no,menubar=no,toolbar=no,directories=no,location=no,status=no,scrollbars=no,resizable=no"); 
 			//window.close;
	   	win.focus()
	}
	
	
	/*用于弹出窗口的返回*/
	function returnParentWindow(){
	 	//刷新父窗口
	 	window.opener.location.reload() ;
		window.close() ;
	}
	
	
	/*用于弹出窗口的返回,不刷新父页*/
	function closeWindow(){
		window.close() ;
	}
	
	
	/*对链接进行编码，主要是为了解决参数中出现中文的时候出现乱码*/
	function locationURL(url){
		var strUrl = encodeURI(url) ;
		location.href= strUrl ;
	}

	/*form 提交时，只提交一次*/
	function subForm(thiz){
		thiz.form.disabled=true ;
		thiz.form.submit() ;
	}

	
	//去掉空格
	function trim(str){
	   return str.replace(/^\s*|\s*$/g,"");
	}

	//验证数字
	function checkNumber(sDouble){
	  var re = /^\d+(?=\.{0,1}\d+$|$)/
	  return re.test(sDouble)
	}

	function ajaxF(action,postStr,div){
		var myajax=new Ajax.Updater(div,action,{method:'post',parameters:postStr});
		
	}

function updatePrice(action,formName,div){
	var obj = document.forms[formName] ;
	var postStr = Form.serialize(obj)
	ajaxF(action,postStr,div);
}	

function ajaxForm(action,formName,div){
	var obj = document.forms[formName] ;
	var postStr = Form.serialize(obj)
	ajaxF(action,postStr,div);
}	

	
	
	/*比较两个时间的前后
		如果date1在date2的之前，则返回false
	*/
	function checkSTimeAndETime(date1,date2){
		//var arr1=date1.split("-"); 
		//var d1=new Date(arr1[0],arr1[1],arr1[2]); 
		//var arr2= date2.split("-") ;
		//var d2= new Date(arr2[0],arr2[1],arr2[2])
		//if(d1>d2){
		//	return false ;
		//}
		//return true ;
		/**
		 * 上面的方法有些问题，如2012-01-31与2012-02-01就不能正确判断
		 */
		var arr1=date1.split("-");
		var y1=parseInt(arr1[0]);
		var m1=parseInt(arr1[1]);
		var d1=parseInt(arr1[2]);
		
		var arr2= date2.split("-") ;
		var y2=parseInt(arr2[0]);
		var m2=parseInt(arr2[1]);
		var d2=parseInt(arr2[2]);
		
		if(y1>y2)
			return false;
		else if(y1<y2)
			return true;

		if(m1>m2)
			return false;
		else if(m1<m2)
			return true;

		if(d1>d2)
			 return false;
		else
			return true;
	}

	/*点击按钮时将button的class改变*/
	function changeTheButtonStyle(id){
		var objs = document.getElementsByTagName('input');
		for(var i=0;i<objs.length;i++){
			if(objs[i].type=="button"){
				objs[i].className ='inputbutton';
			}
		}
		document.getElementById(id).className ='inputbutton3'
	}
	
