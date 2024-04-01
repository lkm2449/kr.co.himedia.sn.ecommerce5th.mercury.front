function searchSale(){
	var searchSales = document.getElementById("searchSales").value;
	location.href="/front/buy/salesList.web?searchWord=" + searchSales;
}

function searchList(){
	var searchWord = document.getElementById("searchWord").value;
	location.href="/front/buy/salesList.web?searchWord=" + searchWord;
}

// 쿠키 저장하기 
// setCookie => saveid함수에서 넘겨준 시간이 현재시간과 비교해서 쿠키를 생성하고 지워주는 역할
function setCookie(cookieName, value, exdays) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + exdays);
	var cookieValue = escape(value)
			+ ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
	document.cookie = cookieName + "=" + cookieValue;
}

// 쿠키 삭제
function deleteCookie(cookieName) {
	var expireDate = new Date();
	expireDate.setDate(expireDate.getDate() - 1);
	document.cookie = cookieName + "= " + "; expires="
			+ expireDate.toGMTString();
}

// 쿠키 가져오기
function getCookie(cookieName) {
	cookieName = cookieName + '=';
	var cookieData = document.cookie;
	var start = cookieData.indexOf(cookieName);
	var cookieValue = '';
	if (start != -1) { // 쿠키가 존재하면
		start += cookieName.length;
		var end = cookieData.indexOf(';', start);
		if (end == -1) // 쿠키 값의 마지막 위치 인덱스 번호 설정 
			end = cookieData.length;
			console.log("end위치  : " + end);
		cookieValue = cookieData.substring(start, end);
	}
	return unescape(cookieValue);
}

// 장바구니 쿠키저장
function setCookie2nd(cookieName, value, days) {
	// 설정 일수만큼 현재시간에 만료값으로 지정
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + days);
	
	var cookieValue = escape(value) + "; path=/" + ((days == null) ? '' : '; expires=' + exdate.toUTCString());
	document.cookie = cookieName + '=' + cookieValue;
}

// 장바구니 쿠키 가져오기
function getCookie2nd(cookieName) {
	var x, y;
	var val = document.cookie.split(';');

	for (var i = 0; i < val.length; i++) {
		x = val[i].substr(0, val[i].indexOf('='));
		y = val[i].substr(val[i].indexOf('=') + 1);
		// 앞과 뒤의 공백 제거하기
		x = x.replace(/^\s+|\s+$/g, '');
		
		if (x == cookieName) {
			// unescape로 디코딩 후 값 리턴
			return unescape(y);
		}
	}
}

// 장바구니
function insertBasket(item) {
	
	// 이미 저장된 값을 쿠키에서 가져오기
	var items = getCookie2nd("productBasket");
	
	// 쿠키값을 저장할 기간(일), null일 경우는 브라우저 닫을 시 삭제됨
	var expire = null;
	
	if (items) {
		//var itemArray = items.split(',');
		setCookie2nd("productBasket", items + "," + item, expire);
		//alert(itemArray.length);
	}
	else {
		setCookie2nd("productBasket", item, expire);
	}
	//alert("[저장된 정보]\n" + items);
}

function execDaumPostcode() {
	
	var width = 500; //팝업의 너비
	var height = 600; //팝업의 높이
	
	new daum.Postcode({
		width: width,
		height: height,
		oncomplete: function(data) {
			// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var roadAddr = data.roadAddress;		// 도로명 주소 변수
			var extraRoadAddr = '';					// 참고 항목 변수
			
			// 법정동명이 있을 경우 추가한다. (법정리는 제외)
			// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
				extraRoadAddr += data.bname;
			}
			
			// 건물명이 있고, 공동주택일 경우 추가한다.
			if(data.buildingName !== '' && data.apartment === 'Y'){
				extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
			}
			
			// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
			if(extraRoadAddr !== ''){
				extraRoadAddr = ' (' + extraRoadAddr + ')';
			}
			
			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById("postcode").value		= data.zonecode;
			document.getElementById("roadAddr").value		= roadAddr;
			document.getElementById("addr1").value			= data.jibunAddress;
			
			// 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
			if(roadAddr !== ''){
				document.getElementById("extraAddress").value = extraRoadAddr;
			}
			else {
				document.getElementById("extraAddress").value = '';
			}
			
			var guideTextBox = document.getElementById("guide");
			// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
			if(data.autoRoadAddress) {
				var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
				guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
				guideTextBox.style.display = 'block';
			
			}
			else if(data.autoJibunAddress) {
				var expJibunAddr = data.autoJibunAddress;
				guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
				guideTextBox.style.display = 'block';
			}
			else {
				guideTextBox.innerHTML = '';
				guideTextBox.style.display = 'none';
			}
			}
		}).open({
			left: (window.screen.width / 2) - (width / 2),
			top: (window.screen.height / 2) - (height / 2)
	});

}

function register() {
	// check_pw() 함수를 호출하여 비밀번호 조건을 확인
	
	if(document.getElementById("checkIdResult").value != "false") {
		alert("아이디를 확인해주세요!");
		return;
	}
		
	
	if (!check_pw()) {
		return;
	}
	
	if(document.getElementById("cst_nm").value == ''){
		alert("이름은 필수 입력 사항입니다!");
		return;
	}
	
	if(document.getElementById("phone").value == ''){
		alert("연락처는 필수 입력 사항입니다!");
		return;
	}
	
	if(document.getElementById("postcode").value == '' 
		|| document.getElementById("addr1").value == '' 
		|| document.getElementById("addr3").value == '' ){
		alert("주소는 필수 입력 사항입니다!");
		return;
	}
	
	if(document.getElementById("cst_email").value == ''){
		alert("이메일은 필수 입력 사항입니다!");
		return;
	}

	var frmMain = document.getElementById("form");
	frmMain.action = "/front/join/joinProc.web";
	frmMain.submit();
}


function check_pw() {
	var pw = document.getElementById('passwd').value;
	var SC = ["!", "@", "#", "$", "%"];
	var check_SC = 0;

	if (pw.length < 8 || pw.length > 16) {
		window.alert('비밀번호는 8글자 이상, 16글자 이하만 이용 가능합니다.');
		document.getElementById('passwd').value = '';
		return false; // 비밀번호 길이 조건을 만족하지 않으면 false 반환
	}

	for (var i = 0; i < SC.length; i++) {
		if (pw.indexOf(SC[i]) != -1) {
			check_SC = 1;
		}
	}

	if (check_SC == 0) {
		window.alert('!,@,#,$,% 의 특수문자가 들어가 있지 않습니다.');
		document.getElementById('passwd').value = '';
		return false; // 특수문자 조건을 만족하지 않으면 false 반환
	}

	if (document.getElementById('passwd').value != '' && document.getElementById('passwd_').value != '') {
		if (document.getElementById('passwd').value == document.getElementById('passwd_').value) {
			document.getElementById('check').innerHTML = '비밀번호가 일치합니다.';
			document.getElementById('check').style.color = 'blue';
			return true; // 비밀번호 일치 시 true 반환
		} else {
			document.getElementById('check').innerHTML = '비밀번호가 일치하지 않습니다.';
			document.getElementById('check').style.color = 'red';
			return false; // 비밀번호 불일치 시 false 반환
		}
	}
}

function changePwd() {
	
	if (!check_pw()) {
		return;
	}
	
	var frmMain = document.getElementById("form");
	frmMain.action = "/front/login/changePasswd.web";
	frmMain.submit();
}

function check_re() {
	
	if(confirm("정말로 회원 탈퇴를 하시겠습니까?")){
		
		var frmMain = document.getElementById("form");
		frmMain.action = "/front/customer/withdrawalProc.web";
		frmMain.submit();
		
	}else{
		
		alert("돌아갑니다.");
		
		var frmMain = document.getElementById("form");
		frmMain.action = "/front/customer/main.web";
		frmMain.submit();
	}

}
