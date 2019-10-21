<%@page import="kr.co.sunnyside.cmn.StringUtil"%>
<%@page import="kr.co.sunnyside.cmn.SearchVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<c:set var="context" value="${pageContext.request.contextPath }" />
<%
	String userId = request.getParameter("userId"); 
	String productId = request.getParameter("productId"); 

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!-- <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
---- Include the above in your HEAD tag --------
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" /> --> 
<link href="../resources/css/store_main.css" rel="stylesheet" type="text/css">
<title>상품상세</title>
</head>
<body>
<h3 class="h4"  style="margin-left: 50px; margin-top: 2em; font-weight: bold; font-size:25">상품 상세정보</h3>
<table style="margin-top: 150px; margin-bottom: 50px;" >

	<tr>
		<td style="display: none;" id="productId" class="productId">${vo.productId }</td>
		<td style="display: none;" id="userId" class="userId">${user.userId }</td>
		<td style="display: none;" id="category" class="category" >${vo.category }</td>
		<td style="display: none;" id="orgFileNm" class="orgFileNm" >${vo.orgFileNm }</td>
		<td style="display: none;" id="saveFileNm" class="saveFileNm" >${vo.saveFileNm }</td>		
		<td>
			<img width="340" height="300" src="${vo.saveFileNm }">
		</td>
	<td>
		<table border="1" style="height: 300px; width: 400px;">
			<tr align="center">
				<td>상품명</td>
				<td><input id="productNm" name="productNm" value="${vo.productNm }"/></td>
			</tr>
			<tr align="center">
				<td>가격</td>
				<td><input id="productCost" name="productCost" value="<fmt:formatNumber value="${vo.productCost }" pattern="#,###,###"/>"/>원</td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<form action="${context}/cart/do_save.do" name="selectProductForm" id="selectProductForm" method="post">
						<input type="hidden" value="${vo.productId }" name="productId" id="productId">
						<input type="hidden" value="${user.userId }" name="userId" id="userId">
						수량: <select name="count" id="count">
								<c:forEach begin="1" end="10" var="i">
                        			<option value="${i}">${i}</option>
                    			</c:forEach>
						</select> &nbsp; 개		
					</form>				
						<input style="margin-top: 30px; margin-right: 30px" id="goCart" name="goCart" type="submit" value="장바구니에 담기" >					
						<input id="pay" name="pay" type="button" value="바로 결제하기" >
				</td>
			</tr>			
		</table>
	</td>
	</tr>
</table>
<hr>
<table style="margin-top: 20px; margin-left: 50px;">
	<tr>
		<td >상품소개</td><tr>
		<td>${vo.productInfo}</td>
	</tr>
</table>
<hr>
<div style="margin-left: 900px; display: none;" id="btns" class="btns" >
	<input type="submit" value="수정" id="updatebtn" name="updatebtn">
	<input type="button" value="삭제" id="delbtn" name="delbtn">
</div>
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<%-- <script src="${context}/resources/js/jquery-1.12.4.js"></script>		 --%>
<script type="text/javascript">
	//장바구니에 담기
	$("#goCart").on("click",function(){
		//alert("goCart");
		
		//로그인 시 이동가능 
		if("${user.userId}"!= ""){ //로그인 되어있으면 
			if(false==confirm('상품을 담으시겠습니까?')) return;
			 addToCart(); //카트에 더하기 함수 
			
	     }else{// 로그인이 안되어있으면
			alert("회원만 사용가능한 서비스입니다.\n로그인을 해주세요.");
	    	location.href ="${context}/login/login_view.do";
	     }		
	});

	 function addToCart(){
		 console.log("productId="+$("#productId").text());
		 console.log("userId="+$("#userId").text());
		 console.log("count="+$("#count option:selected").val());
		 
			//ajax
		     $.ajax({
		        type:"POST",
		        url:"${context}/cart/do_save.do",
		        dataType:"html",
		           data:{
		           "productId":$("#productId").text(),
		           "userId":$("#userId").text(),
		           "count":$("#count option:selected").val()                       
		       
		          },   
		      success: function(data){ 
				  if(null != data && data.msgId=="1"){
					  alert("추가되었습니다.");
					  var userId = $("#userId").text();
					  location.href="${context}/cart/do_retrieve.do?userId="+userId;
					
				  }else{
					alert("추가되었습니다.");	
					  var userId = $("#userId").text();
					  location.href="${context}/cart/do_retrieve.do?userId="+userId;
				  }
		     },
		     complete:function(data){
		  	   
		     },
		     error:function(xhr,status,error){
		         alert("error:"+error);
		     }
		    }); //--ajax  
		 }
	 
	 

	//수정
	$("#updatebtn").on("click",function(){
		//alert("updatebtn");
		if(false==confirm('상품을 수정 하시겠습니까?')) return;
		productId = $("#productId").text();
		//alert("productId=="+productId);
		//location.href ="${context}/store/do_selectOneToUpdate.do?productId="+productId;
		popup();
	
	});
	
	function popup(){
        var url = "${context}/noTileStore/do_selectOneToUpdate.do?productId="+productId;
        var name = "상품수정";
        var option = "width = 700, height = 700, top = 100, left = 400, location = no"
        window.open(url, name, option);
    }
	
	
	//삭제
	$("#delbtn").on("click",function(e){
		if(confirm("삭제하시겠습니까?")==false) return;
		console.log("productId"+$("#productId").text());
		e.preventDefault();//현재 이벤트가 상위로 전파되지 않도록 중단한다
		
		doDeleteFile();		
		});

	 function doDeleteFile(){
			 
		//ajax
	     $.ajax({
	        type:"POST",
	        url:"${context}/store/do_delete.do",		        
	        dataType:"html",
	           data:{
	           "productId":$("#productId").text(),
	           "orgFileNm":$("#orgFileNm").text(),
	           "saveFileNm":$("#saveFileNm").text()                    
	       
	          },   
	      success: function(data){ 
			  if(null != data && data.msgId=="1"){
				  alert("삭제되었습니다.");		
				  location.href="${context}/store/do_main.do";
				
			  }else{
				alert("삭제되었습니다.");	
				location.href="${context}/store/do_main.do";
			  }
	     },
	     complete:function(data){
	  	   
	     },
	     error:function(xhr,status,error){
	         alert("error:"+error);
	     }
	    }); //--ajax  
	 }


	$(document).ready(function(){
		//admin일때만 작성가능
		if("${user.userId}"=="admin"){
		        $("#btns").css("display", "inline ");//보이게하기
		     }
	});

</script>
</body>
</html>