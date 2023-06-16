/**
 * 
 */
 
 
/*팝업 띄우기*/
function openPop(dList) {
	//alert(id.category);
	// id 값 설정
	//$('#category').val(id.category);
	// class 값 설정
	//$('.category').attr('value', id.category);
	document.getElementById("yearmonth").value = dList.yearmonth; //년도
	document.getElementById("category").value = dList.category; //항목
	document.getElementById("january").value = dList.january; //1월
	document.getElementById("february").value = dList.february; //2월
	document.getElementById("march").value = dList.march; //3월
	document.getElementById("april").value = dList.april; //4월
	document.getElementById("may").value = dList.may; // 5월
	document.getElementById("june").value = dList.june; //6월
	document.getElementById("july").value = dList.july; //7월
	document.getElementById("august").value = dList.august; //8월
	document.getElementById("september").value = dList.september; //9월
	document.getElementById("october").value = dList.october; //10월
	document.getElementById("november").value = dList.november; //11월
	document.getElementById("december").value = dList.december; //12월
	
	//비활성화
	//const yearmonth_target = document.getElementById('yearmonth');
	//yearmonth_target.disabled = true;
	//const category_target = document.getElementById('category');
	//category_target.disabled = true;
	//document.getElementById("yearmonth").disabled = true;
	document.getElementById('exampleModalLabel').innerText = "배당금 수정";
	document.getElementById('yearmonth').readOnly = true; // readonly 비활성화
	document.getElementById('category').readOnly = true; // readonly 비활성화
	document.getElementById("stockManageUpdate").style.display ='block'; // 수정버튼 보이기
	document.getElementById("stockManageDelete").style.display ='block'; // 삭제버튼 보이기	
	document.getElementById("stockManageInsert").style.display ='none'; // 등록버튼 숨기기	
	
	// 팝업창 열기
	document.getElementById("popup_layer").style.display = "block";
	
	
}

/*팝업 띄우기*/
function openPop2() {
	document.getElementById("yearmonth").value = ""; //년도
	document.getElementById("category").value = ""; //항목
	document.getElementById("january").value = ""; //1월
	document.getElementById("february").value = ""; //2월
	document.getElementById("march").value = ""; //3월
	document.getElementById("april").value =""; //4월
	document.getElementById("may").value = ""; // 5월
	document.getElementById("june").value =""; //6월
	document.getElementById("july").value = ""; //7월
	document.getElementById("august").value = ""; //8월
	document.getElementById("september").value = ""; //9월
	document.getElementById("october").value = ""; //10월
	document.getElementById("november").value = ""; //11월
	document.getElementById("december").value = ""; //12월
	
	document.getElementById('yearmonth').readOnly = false; // readonly 비활성화
	document.getElementById('category').readOnly = false; // readonly 비활성화
	document.getElementById('exampleModalLabel').innerText = "배당금 등록";
	document.getElementById("stockManageUpdate").style.display ='none'; // 수정버튼 숨기기
	document.getElementById("stockManageDelete").style.display ='none'; // 삭제버튼 숨기기	
	document.getElementById("stockManageInsert").style.display ='block'; // 등록버튼 보이기	
	// 팝업창 열기
	document.getElementById("popup_layer").style.display = "block";
}
/*팝업 닫기*/
function closePop() {
	document.getElementById("popup_layer").style.display = "none";
}

//*등록*//
function savePost(){
	const form = document.getElementById('saveForm');
	let yearmonth  = document.getElementById('yearmonth').value;
	let category  = document.getElementById('category').value;
	console.log("yearmonth " + yearmonth +  " 결과 : " + isNullChek(yearmonth)  + " category " + category + " 결과 : " + isNullChek(category));
	if(isNullChek(yearmonth) ){ 
		alert("년도를 입력해주세요.")
		return;
	};
	if(yearmonth.length <= 3){ 
		alert("년도를 4자리 입력해주세요.")
		return;
	};
	if(isNullChek(category)){
		alert("항목을 입력해주세요."); 
		return;
	};
	
	 form.action = "/dividendInsert";
	 form.submit();
}

function openPopInsert(){
	/*$('#testModal1111').modal("show");*/
	document.getElementById("testModal2").style.display = "block";
}

// 등록
function stockManageInsert(){
	let data = {
		yearmonth : document.getElementById('yearmonth').value, //년도
		category : document.getElementById('category').value, //항목
		january : document.getElementById('january').value, //1월
		february : document.getElementById('february').value, //2월
		march : document.getElementById('march').value, //3월
		april : document.getElementById('april').value, //4월
		may : document.getElementById('may').value, //5월
		june : document.getElementById('june').value, //6월
		july : document.getElementById('july').value, //7월
		august : document.getElementById('august').value, //8월
		september : document.getElementById('september').value, //9월
		october : document.getElementById('october').value, //10월
		november : document.getElementById('november').value, //11월
		december : document.getElementById('december').value, //12월
		clsfc_Code : 'I' // 수정
	}
	let url = 'dividendLogInsert.do';
	ajaxPost(data, url);
}
// 수정
function stockManageUpdate(){
	let data = {
		yearmonth : document.getElementById('yearmonth').value, //년도
		category : document.getElementById('category').value, //항목
		january : document.getElementById('january').value, //1월
		february : document.getElementById('february').value, //2월
		march : document.getElementById('march').value, //3월
		april : document.getElementById('april').value, //4월
		may : document.getElementById('may').value, //5월
		june : document.getElementById('june').value, //6월
		july : document.getElementById('july').value, //7월
		august : document.getElementById('august').value, //8월
		september : document.getElementById('september').value, //9월
		october : document.getElementById('october').value, //10월
		november : document.getElementById('november').value, //11월
		december : document.getElementById('december').value, //12월
		clsfc_Code : 'U' // 수정
	}
	let url = 'dividendLogInsert.do';
	ajaxPost(data, url);
}
// 삭제
function stockManageDelete(){
	let data = {
		yearmonth : document.getElementById('yearmonth').value, //년도
		category : document.getElementById('category').value, //항목
		january : document.getElementById('january').value, //1월
		february : document.getElementById('february').value, //2월
		march : document.getElementById('march').value, //3월
		april : document.getElementById('april').value, //4월
		may : document.getElementById('may').value, //5월
		june : document.getElementById('june').value, //6월
		july : document.getElementById('july').value, //7월
		august : document.getElementById('august').value, //8월
		september : document.getElementById('september').value, //9월
		october : document.getElementById('october').value, //10월
		november : document.getElementById('november').value, //11월
		december : document.getElementById('december').value, //12월
		clsfc_Code : 'D' // 수정
	}
	let url = 'dividendLogInsert.do';
	ajaxPost(data, url);
}

function ajaxPost(data, url){
	$.ajax({
	        url: url,
	        type : "post",
	        data : data,
	        success: function(json) {
	             alert(json);
	             location.reload();
	        },
	        error: function() {
	            alert('error');
	             location.reload();
	        }
	})
}