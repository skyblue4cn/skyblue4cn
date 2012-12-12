	var n =0 ;
	
	/*保存保单，不更改状态的保存，只保存信息*/
	function saveApplyBill(actionName){
		var obj = document.forms['form1']
		obj.action = actionName ;
		obj.submit() ;	
	}
	/*同一个部门、同一个团队号、同一个时间 三者同时满足两个则告诉申请人有同样的保单存在，不能申请保单*/
	function checkApplyBillIsExist(){
		/*暂时先不实现，具体需求有待确认！*/			
	}
	
	/*取得保单的价格*/
	function getBillPrice(){
		var obj = document.forms['form1'] ;
		/*验证人数是否是数字*/
		if(!checkNumber(obj.neibin.value)){
			alert('内宾人数填写不正确，请填写数字！') ;
			return  ;
		}
		if(!checkNumber(obj.waibin.value)){
			alert('外宾人数填写不正确，请填写数字！') ;
			return ;
		}
		/*验证是否填写了起始时间*/
		var stime = trim(obj.startTime.value) ;
		var etime = trim(obj.endTime.value) ;
		if(stime== ''){
			return  ;
		}
		if( etime== ''){
			return  ;
		}
		/*判断开始日期是否在结束日期之前*/
		if(!checkSTimeAndETime(stime,etime)){
			alert("起保开始时间和结束时间填写不正确，请更正！") ;
			return  ;
		}
		var postStr = Form.serialize(obj)
		var action = "c_getBillPrice.action" ;
		ajaxF(action,postStr,'feeDiv')
	}

	/*导入游客*/
	function loadTravel(billId){
		if(document.getElementById('travelerRadio2').checked == false){
			alert('请先确认选择添加游客明细！')
			return ;
		}
		saveApplyBill('saveApplyBill.action');
		openAWindow('toLoadTraveler.action?intBillId='+billId) ;
	}
	
	/*点击添加游客时添加格子*/
	function addTr(){
		var obj = document.getElementById('travelerTable')
		var trIds = obj.getElementsByTagName('tr')
		n = trIds.length;
		var trObj = document.getElementById('travelerTable').insertRow()
		trObj.id = 'tr'+ n ;
		var td1 = trObj.insertCell();
		var td2 = trObj.insertCell();
		var td3 = trObj.insertCell();
		var td4 = trObj.insertCell();
		var td5 = trObj.insertCell();
		var td6 = trObj.insertCell();
		var td7 = trObj.insertCell();
		td1.insertAdjacentText("afterBegin",n);
		td2.insertAdjacentHTML("afterBegin",'<input type="text" name="travelerName" class="inputbox2"/>');
		td3.insertAdjacentHTML("afterBegin",'<input type="text" name="travelerSex" class="inputbox2"/>');
		td4.insertAdjacentHTML("afterBegin",'<input type="text" name="travelerBirthday" class="inputbox2"/>');
		td5.insertAdjacentHTML("afterBegin",'<input type="text" name="travelerCountry" class="inputbox2"/>');
		td6.insertAdjacentHTML("afterBegin",'<input type="text" name="travelerIndentity" class="inputbox3"/>');
		td7.insertAdjacentHTML("afterBegin",'<a href="javascript:deleteTr('+n+')">删除</a>');
	}
	
	/*删除格子*/
	function deleteTr(id){
		var obj = document.getElementById('travelerTable')
		var trIds = obj.getElementsByTagName('tr')
		var bl = false ; 
		for(var i=0;i<trIds.length ; i++ ){
			if(trIds[i].id == ('tr'+id)){
				obj.deleteRow(i) ;
				bl = true ;
				i-- ;
				continue ;
			}
			if(bl){
				var tds = trIds[i].getElementsByTagName('td') ;
				tds[0].innerHTML = parseInt(tds[0].innerHTML)-parseInt(1) ;
			}
		}
		if(bl){
			n--;
		}
	}
	
	/*上传旅客附件*/
	function uploadTravelFile(billId){
		if(document.getElementById('travelerRadio2').checked == false){
			alert('请先确认选择添加游客明细！')
			return ;
		}
		saveApplyBill('saveApplyBill.action');
		openAWindow('toUploadTravelerFile.action?intBillId=' + billId) ;
	}
	
	/*对已经有附件的保单进行想修改时，重新上传附件*/
	function reuploadFile(cancel){
		var obj1 = document.getElementById('fileupload1')
		var obj2 = document.getElementById('fileupload2')
		var obj3 = document.getElementById('fileupload3')
		var obj4 = document.getElementById('fileupload4')
		var obj5 = document.getElementById('fileupload5')
		if(cancel==null || cancel==''){
			if(window.confirm('重新上传附件会替换掉已上传的附件，您确定要重新上传附件吗？')){
				obj1.style.display='none' ;
				obj3.style.display='none' ;
				obj2.style.display='block' ;
				obj4.style.display='block' ;
				obj5.style.display='block';
			}
		}else if(cancel=='cancel'){
			/*将选择的文件去掉*/
			//document.getElementById('travelerFile').select();
			//document.selection.clear();
			document.getElementById('travelerFile').outerHTML=document.getElementById('travelerFile').outerHTML;
			obj1.style.display='block' ;
			obj3.style.display='block' ;
			obj2.style.display='none' ;
			obj4.style.display='none' ;
			obj5.style.display='none';
		}
		
	}
	
	
	/*选择不添加旅客明细时给出提示信息*/
	function sendNotTravelerMessage(){
		var obj1 = document.getElementById('travelerRadio1') ;
		var obj2 = document.getElementById('travelerRadio2') ;
		var timeObj = document.getElementById('startTime') ;
		if(timeObj.value == ''){
				alert('请填写起止时间！') ;
				obj1.checked = false ;
				return ;
			}
	
		//var message = "重要说明：请务必在" + timeObj.value + "下午3：00前添加所有旅客明细，否则保单无效！" ;
		var message = "重要说明：请务必在旅游起始时间当天下午3:00点前添加所有旅客明细，否则保单无效！" 
		var messageObj = document.getElementById('messageDiv') ;
		messageObj.innerHTML = message ;
		
	}
	
	
	//根据人数产生添加旅客名单的格子
	function crateTextToAddTraveler(){
		var obj = document.forms['form1'] ;
		var postStr = "" ;
	}
	
	
	/*保单提交前对保单进行检查*/
	function checkForm(date , type){
		var formObj = document.forms['form1'] ;
		/*验证是否填写了团队号*/
		if(trim(formObj.team.value) == ''){
			alert('请填写团队号！')
			return ;
		}
		
		if(trim(formObj.phone.value) == ''){
			alert('请填写电话号码！') ;
			return ;
		}
		
		/*验证是否填写了路线*/
		if(trim(formObj.rold.value) == ''){
			alert('请填写旅游路线！')
			return  ;
		}
		/*验证是否填写了起始时间*/
		var stime = trim(formObj.startTime.value) ;
		var etime = trim(formObj.endTime.value) ;
		if(stime== ''){
			alert('请填写旅游起始时间！')
			return  ;
		}
		if( stime== ''){
			alert('请填写旅游结束时间！')
			return  ;
		}
		/*判断开始日期是否在结束日期之前*/
		if(!checkSTimeAndETime(stime,etime)){
			alert("起保开始时间和结束时间填写不正确，请更正！") ;
			return  ;
		}
		/*和当前时间比较*/
		if(!checkSTimeAndETime(date,stime)){
			alert("起保开始时间不能在当前时间之前，请更正！") ;
			return  ;
		}
		/*验证人数是否是数字*/
		if(!checkNumber(formObj.neibin.value)){
			alert('内宾人数填写不正确，请填写数字！') ;
			return  ;
		}
		if(!checkNumber(formObj.waibin.value)){
			alert('外宾人数填写不正确，请填写数字！') ;
			return ;
		}
		if((parseInt(formObj.neibin.value) + parseInt(formObj.waibin.value))<=0){
			alert('没有填写内宾和外宾人数，请检查！') ;
			return ;
		}
		/*验证旅客明细里游客姓名和国籍不能为空*/
		var trIds = document.getElementById('travelerTable').getElementsByTagName('tr')
		for(var i =1 ; i<trIds.length ; i++){
			var tds = trIds[i].getElementsByTagName('td') ;
			if(trim(tds[1].getElementsByTagName('input')[0].value) == ''){
				alert('游客姓名不能为空！请填写游客姓名')
				return ;
			}
			/*
			if(trim(tds[2].getElementsByTagName('input')[0].value) == ''){
				alert('游客国籍不能为空!请填写游客国籍')
				return ;
			}
			if(trim(tds[3].getElementsByTagName('input')[0].value) == '' && trim(tds[4].getElementsByTagName('input')[0].value) == ''){
				alert('身份证号和护照号不能同时为空！每个游客请至少填写一项！')
				return ;
			}
			*/
		}
		
		
		/*验证人数是否符合要求*/
		var sum = (parseInt(formObj.neibin.value) + parseInt(formObj.waibin.value)) ;
		if( parseInt(sum) < parseInt(trIds.length-1)){
			alert('您添加的游客超出了您填写的游客人数！请更正！') ;
			return ;
		}
		if( parseInt(sum) > parseInt(trIds.length-1) && trim(formObj.travelerFile.value) == ''){
			if(window.confirm('游客人数没有填写足够，您确认提交保单吗？')){
				formObj.action= 'c_sureApplyBill.action' ;
				formObj.submit() ;
				return ;
			}else{
				return ;
			}
		}
		
		if(window.confirm('确认提交吗？')){
			formObj.action= 'c_sureApplyBill.action' ;
			formObj.submit() ;
		}
	}
	
	/*保单更新*/
	function updateBill(date){
		var formObj = document.forms['form1'] ;
		/*验证是否填写了团队号*/
		if(trim(formObj.team.value) == ''){
			alert('请填写团队号！')
			return ;
		}
		
		if(trim(formObj.phone.value) == ''){
			alert('请填写电话号码！') ;
			return ;
		}
		
		/*验证是否填写了路线*/
		if(trim(formObj.rold.value) == ''){
			alert('请填写旅游路线！')
			return  ;
		}
		/*验证是否填写了起始时间*/
		var stime = trim(formObj.startTime.value) ;
		var etime = trim(formObj.endTime.value) ;
		if(stime== ''){
			alert('请填写旅游起始时间！')
			return  ;
		}
		if( stime== ''){
			alert('请填写旅游结束时间！')
			return  ;
		}
		/*判断开始日期是否在结束日期之前*/
		if(!checkSTimeAndETime(stime,etime)){
			alert("起保开始时间和结束时间填写不正确，请更正！") ;
			return  ;
		}
		/*和当前时间比较*/
		if(!checkSTimeAndETime(date,stime)){
			alert("起保开始时间不能在当前时间之前，请更正！") ;
			return  ;
		}
		/*验证人数是否是数字*/
		if(!checkNumber(formObj.neibin.value)){
			alert('内宾人数填写不正确，请填写数字！') ;
			return  ;
		}
		if(!checkNumber(formObj.waibin.value)){
			alert('外宾人数填写不正确，请填写数字！') ;
			return ;
		}
		if((parseInt(formObj.neibin.value) + parseInt(formObj.waibin.value))<=0){
			alert('没有填写内宾和外宾人数，请检查！') ;
			return ;
		}
		/*验证旅客明细里游客姓名和国籍不能为空*/
		var trIds = document.getElementById('travelerTable').getElementsByTagName('tr')
		for(var i =1 ; i<trIds.length ; i++){
			var tds = trIds[i].getElementsByTagName('td') ;
			if(trim(tds[1].getElementsByTagName('input')[0].value) == ''){
				alert('游客姓名不能为空！请填写游客姓名')
				return ;
			}
			/*if(trim(tds[2].getElementsByTagName('input')[0].value) == ''){
				alert('游客国籍不能为空!请填写游客国籍')
				return ;
			}
			if(trim(tds[3].getElementsByTagName('input')[0].value) == '' && trim(tds[4].getElementsByTagName('input')[0].value) == ''){
				alert('身份证号和护照号不能同时为空！每个游客请至少填写一项！')
				return ;
			}
			*/
		}
		/*验证人数是否符合要求*/
		var sum = (parseInt(formObj.neibin.value) + parseInt(formObj.waibin.value)) ;
		if( parseInt(sum) < parseInt(trIds.length-1)){
			alert('您添加的游客超出了您填写的游客人数！请更正！') ;
			return ;
		}
		if( parseInt(sum) > parseInt(trIds.length-1) && trim(formObj.travelerFile.value) == ''){
			if(window.confirm('游客人数没有填写足够，您确认提交保单吗？')){
				formObj.action= 'c_updateBillByUser.action' ;
				formObj.submit() ;
				return ;
			}else{
				return ;
			}
		}
		
		if(window.confirm('确认提交吗？')){
			formObj.action= 'c_updateBillByUser.action';
			formObj.submit() ;
		}
	}
	
	/*备案保单更新*/
	function updateBeiAnBill(){
		var formObj = document.forms['form1'] ;
		/*验证人数是否是数字*/
		if(!checkNumber(formObj.neibin.value)){
			alert('内宾人数填写不正确，请填写数字！') ;
			return  ;
		}
		if(!checkNumber(formObj.waibin.value)){
			alert('外宾人数填写不正确，请填写数字！') ;
			return ;
		}		
		
			/*验证旅客明细里游客姓名和国籍不能为空*/
		var trIds = document.getElementById('travelerTable').getElementsByTagName('tr')
		for(var i =1 ; i<trIds.length ; i++){
			var tds = trIds[i].getElementsByTagName('td') ;
			if(trim(tds[1].getElementsByTagName('input')[0].value) == ''){
				alert('游客姓名不能为空！请填写游客姓名')
				return ;
			}
			/*
			if(trim(tds[2].getElementsByTagName('input')[0].value) == ''){
				alert('游客国籍不能为空!请填写游客国籍')
				return ;
			}
			if(trim(tds[3].getElementsByTagName('input')[0].value) == '' && trim(tds[4].getElementsByTagName('input')[0].value) == ''){
				alert('身份证号和护照号不能同时为空！每个游客请至少填写一项！')
				return ;
			}
			*/
		}
		
		
		/*验证人数是否符合要求*/
		var sum = (parseInt(formObj.neibin.value) + parseInt(formObj.waibin.value)) ;
		if( parseInt(sum) < parseInt(trIds.length-1)){
			alert('您添加的游客超出了您填写的游客人数！请更正！') ;
			return ;
		}
		if( parseInt(sum) > parseInt(trIds.length-1) && trim(formObj.travelerFile.value) == ''){
			if(window.confirm('游客人数没有填写足够，您确认提交保单吗？')){
				formObj.action= 'c_updateBeiAnBillByUser.action' ;
				formObj.submit() ;
				return ;
			}else{
				return ;
			}
		}
		
		if(window.confirm('确认提交吗？')){
			formObj.action= 'c_updateBeiAnBillByUser.action';
			formObj.submit() ;
		}
	}
	
	/*保险公司人员更新保单*/
	function updateBillByBx(){
		var formObj = document.forms['form1'] ;
		/*验证人数是否是数字*/
		if(!checkNumber(formObj.neibin.value)){
			alert('内宾人数填写不正确，请填写数字！') ;
			return  ;
		}
		if(!checkNumber(formObj.waibin.value)){
			alert('外宾人数填写不正确，请填写数字！') ;
			return ;
		}	
		/*验证是否填写了起始时间*/
		var stime = trim(formObj.startTime.value) ;
		var etime = trim(formObj.endTime.value) ;
		if(stime== ''){
			alert('请填写旅游起始时间！')
			return  ;
		}
		if( stime== ''){
			alert('请填写旅游结束时间！')
			return  ;
		}	
		if(window.confirm('确认提交吗？')){
			formObj.action= 'updateBillByBx.action';
			formObj.submit() ;
		}
	}