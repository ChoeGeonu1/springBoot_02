/**
 * 
 */
 
 
 function openPopUpdate(data){
	document.getElementById('exampleModalLabel').innerText = "주식 수정";
	document.getElementById("new_stock_name").value = data.stock_name; //주식명
	document.getElementById("new_shares").value = data.shares; //수량
	document.getElementById("new_stock_dividends").value = data.stock_dividends; //배당 주기
	document.getElementById("new_maeipkkeum").value = data.maeipkkeum; //매입금(달러)
	document.getElementById("new_dividends").value = data.dividends; //배당금(달러)
	// none 는 숨기기
	// block는 보이기
	document.getElementById("btn-outline-danger").style.display ='none'; // 삭제버튼 숨기기
	document.getElementById("btn-outline-success").style.display ='block'; // 수정버튼 보이기		
	//document.getElementById("sampleDiv").style.display ='block';
	
	$('#testModal').modal("show");
}
function openPopDelete(data){
	//alert();
	document.getElementById('exampleModalLabel').innerText = "주식 삭제";
	document.getElementById("new_stock_name").value = data.stock_name; //주식명
	document.getElementById("new_shares").value = data.shares; //수량
	document.getElementById("new_stock_dividends").value = data.stock_dividends; //배당 주기
	document.getElementById("new_maeipkkeum").value = data.maeipkkeum; //매입금(달러)
	document.getElementById("new_dividends").value = data.dividends; //배당금(달러)
	
	document.getElementById("btn-outline-danger").style.display ='block'; // 삭제버튼 보이기
	document.getElementById("btn-outline-success").style.display ='none'; // 수정버튼 숨기기		
	$('#testModal').modal("show");
}
function stockManageUpdate(){
	$.ajax({
	        url: "/stockManageUpdate.do",
	        type : "post",
	        data : {
	        	stock_name : document.getElementById('new_stock_name').value, //주식명
	        	shares : document.getElementById('new_shares').value, //수량
	        	stock_dividends : document.getElementById('new_stock_dividends').value, //배당주
	        	maeipkkeum : document.getElementById('new_maeipkkeum').value, //매입금(달러)
	        	dividends : document.getElementById('new_dividends').value //배당금(달러)
	        },
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
