<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
<meta charset="UTF-8">
<title>/users/signup_form.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
<script src="${pageContext.request.contextPath}/resources/js/angular.min.js"></script>
<script>
	//myApp 이라는 모듈 만들기
	var myApp = angular.module("myApp", []);
	//formCtrl 이라는 컨트롤러 만들기
	myApp.controller("formCtrl", function($scope, $http){
		//angularjs 가 초기화 될 때 최초 한번만 호출된다.
		$scope.canUseId=false; //입력한 아이디 사용가능 여부
		//아이디가 입력이되면 입력창이 바뀌(change)므로 바뀔 때 호출될 함수 등록.
		$scope.idChanged=function(){
			$http({
				url:"checkid.do",
				method:"get",
				params:{inputId:$scope.id}
			}).success(function(data){
				//data => {isExist:true} or {isExist:false} 인 object 이다.
				//입력한 아이디가 DB에 존재하지 않을 경우에 사용할 수 있도록 한다.
				$scope.canUseId=!data.isExist;
				/*
					이런 방법도 있다.
					밑에 코드 중 canUseId를 사용하는 부분({'is-invalid': (myForm.id.$invalid || !canUseId)&& myForm.id.$dirty, 'is-valid': myForm.id.$valid && canUseId})을
					{'is-invalid': myForm.id.$invalid && myForm.id.$dirty, 'is-valid': myForm.id.$valid} 로 바꿀 수 있다.
					
					//위에 $scope.canUseId=!data.isExist; 문장을 대체한다
					$scope.myForm.id.$valid = !data.isExist;
					$scope.myForm.id.$invalid = data.isExist;
				*/
			});
		};
		//비밀번호 입력란을 입력했을 때 호출되는 함수
		$scope.isPwdEqual = true; //처음에는 아무것도 입력하지 않았기 때문에 같다는 의미로 true 지정.
		$scope.pwdChanged=function(){
			//비밀번호를 같게 입력했는지 여부를 모델로 관리한다.
			$scope.isPwdEqual = $scope.pwd==$scope.pwd2;
		};
	});
</script>
</head>
<body>
<div class="container" ng-controller="formCtrl">
	<h1>회원 가입 폼 입니다.</h1>
	<!-- 웹브라우저의 기본 유효성 검증을 막겠다(개발자가 직접 관리하겠다)의 의미로  novalidate -->
	<p>아이디 사용가능 여부: {{canUseId}}</p>
	<form action="signup.do" method="post" name="myForm" novalidate>
		<div class="form-group">
			<label for="id">아이디</label>
			<!-- 
				한 글자 이상 반드시 입력해야하므로 ng-required="true" 조건을 넣어준다.
				아이디가 입력이되면 입력창이 바뀌(change)므로 idChanged() 함수를 이용.(ng-input이 없기 때문에 ng-change 활용)
			 -->
			<input class="form-control" type="text" name="id" id="id"
				ng-model="id"
				ng-required="true"
				ng-pattern="/^[a-z].{4,9}$/"
				ng-class="{'is-invalid': (myForm.id.$invalid || !canUseId)&& myForm.id.$dirty, 'is-valid': myForm.id.$valid && canUseId}"
				ng-change="idChanged()"/>
			<small class="form-text text-muted">영문자 소문자로 시작하고 최소 5글자~10글자 이내로 입력 하세요.</small>
			<div class="invalid-feedback">사용할 수 없는 아이디 입니다.</div>
			<!-- ng-show 속성을 붙이면 언제 띄울 지 조건부로 나타낼 수 있다(지금 여기서는 아이디가 사용불가할 때(!canUseId) 띄운다) -->
			<div class="invalid-feedback" ng-show="!canUseId">이미 존재하는 아이디 입니다.</div>
			<span id="checkResult"></span>
		</div>
		<div class="form-group">
			<label for="pwd">비밀번호</label>
			<!-- 'is-invalid'의 싱글따옴표('')로 감싸주는 이유는 중간에 -(하이푼) 있는데 싱글따옴표로 감싸지 않으면 빼기 연산(-)으로 인식하기 때문. -->
			<input class="form-control" type="password" name="pwd" id="pwd" 
				ng-model="pwd"
				ng-required="true"
				ng-minlength="5"
				ng-maxlength="10"
				ng-class="{'is-invalid': (myForm.pwd.$invalid || !isPwdEqual) && myForm.pwd.$dirty, 'is-valid': myForm.pwd.$valid && isPwdEqual}"
				ng-change="pwdChanged()"/>
			<small class="form-text text-muted">최소 5글자~10글자 이내로 입력 하세요.</small>
			<div class="invalid-feedback">비밀번호를 확인 하세요.</div>
		</div>
		<div class="form-group">
			<label for="pwd2">비밀번호 확인</label>
			<input class="form-control" type="password" name="pwd2" id="pwd2" 
				ng-model="pwd2"
				ng-change="pwdChanged()"/>
		</div>
		<div class="form-group">
			<label for="email">이메일</label>
			<input class="form-control" type="text" name="email" id="email" 
				ng-model="email"
				ng-required="true"
				ng-pattern="/@/"
				ng-class="{'is-invalid': myForm.email.$invalid && myForm.email.$dirty, 'is-valid': myForm.email.$valid}"/>
			<div class="invalid-feedback">이메일 형식에 맞게 입력해 주세요.</div>
		</div>
		<!-- 
			폼 전체 유효성이 유효하지 않을 때(myForm.$invalid), 아이디를 사용할 수 없을 때(!canUseId),
			비밀번호 입력란과 비밀번호 확인란이 같지 않을 때(!isPwdEqual) 버튼 비활성화(ng-disabled) 한다.
		 -->
		<button ng-disabled="myForm.$invalid || !canUseId || !isPwdEqual" class="btn btn-primary" type="submit">가입</button>
		<button class="btn btn-danger" type="reset">다시 시도</button>
	</form>
</div>
<script>
	/*
	//아이디를 검증할 정규 표현식
	var reg_id = /^[a-z].{4,9}$/;
	//비밀번호를 검증할 정규 표현식
	var reg_pwd = /^.{5,10}$/;
	//이메일을 검증할 정규 표현식
	var reg_email = /@/; // @가 있기만 하면 true 반환.

	//각각의 input 요소의 유효성 여부
	var isIdValid = false;
	var isPwdValid = false;
	var isEmailValid = false;
	//폼 전체의 유효성 여부
	var isFormValid = false;
	
	//폼이 제출되는 조건 정하기.
	$("#myForm").on("submit", function(){
		//폼 유효성 여부를 얻어낸다.
		isFormValid = isIdValid && isPwdValid && isEmailValid;
		if(!isFormValid){//만일 폼이 유효하지 않다면
			return false; //폼 제출 막기.
		}
	});
	
	//아이디를 입력했을 때 실행할 함수 등록
	$("#id").on("input", function(){
		//입력한 아이디를 읽어온다.
		var inputId = $("#id").val();
		//아이디 형식(정규표현식)에 맞게 입력했는지 여부
		var result = reg_id.test(inputId);
		
		//일단 두개의 클래스를 제거하고
		$(this).removeClass("is-valid is-invalid");
		
		if(result){//형식에 맞게 입력했다면
			//ajax 를 이용해서 서버에 보낸 후 결과를 응답 받는다.
			$.ajax({
				method:"GET",
				url:"checkid.do",
				data:"inputId="+inputId,
				success:function(data){
					$(this).removeClass("is-valid is-invalid");
					//data => {isExist:true} or {isExist:false} 인 object 이다.
					if(data.isExist){//이미 존재하는 아이디임으로 사용불가
						//아이디 형식에 맞게 입력했으나 이미 존재하는 아이디 이므로 사용불가(is-invalid)를 표시
						isIdValid = false;
						$("#id").addClass("is-invalid");
					}else{//사용가능
						//아이디 형식에 맞게 입력했고 기존에 존재하는 아이디도 없으므로 사용가능(is-valid)를 표시
						isIdValid = true;
						$("#id").addClass("is-valid");
					}
				}
			});
		}else{//형식에 맞게 입력하지 않았다면
			//아이디 형식에 맞지 않으므로 사용불가(is-invalid)를 표시.
			isIdValid = false;
			$("#id").addClass("is-invalid");
		}
	});
	
	
	//비밀번호 입력란 혹은 비밀번호 확인란을 입력했을 때 실행할 함수 등록(input 이벤트 처리)
	$("#pwd, #pwd2").on("input", function(){
		//입력한 비밀번호를 읽어온다.
		var inputPwd = $("#pwd").val();
		var inputPwd2 = $("#pwd2").val();
		//형식에 맞게 입력했는 지 여부(정규표현식 검사)
		var result = reg_pwd.test(inputPwd);
		if(result){//형식에 맞게 입력 했다면
			if(inputPwd == inputPwd2){//비밀번호 확인란과 동일하게 입력했다면
				//비밀번호 형식에 맞게 입력했고(정규표현식 검증 통과) 비밀번호 입력란과 비밀번호 확인란이 동일하게 입력했다면
				$("#pwd").removeClass("is-invalid");
				isPwdValid = true;
				$("#pwd").addClass("is-valid");
				
			}else{//비밀번호 확인란과 다르게 입력했다면
				//비밀번호 형식에 맞게 입력했지만(정규표현식 검증 통과) 비밀번호 입력란과 비밀번호 확인란을 다르게 입력했다면
				isPwdValid = false;
				$("#pwd").addClass("is-invalid");
			}
		}else{//형식에 맞게 입력하지 않았다면
			isPwdValid = false;
			$("#pwd").addClass("is-invalid");
		}
	});
	
	
	$("#email").on("input", function(){
		var inputEmail = $("#email").val();
		//이메일 유효성 여부를 얻어낸다.
		isEmailValid = reg_email.test(inputEmail);
		$(this).removeClass("is-valid is-invalid");
		if(isEmailValid){
			$(this).addClass("is-valid");
		}else{
			$(this).addClass("is-invalid");
		}
	});
	*/
</script>
</body>
</html>