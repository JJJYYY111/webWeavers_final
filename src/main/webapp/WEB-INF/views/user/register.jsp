<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>WeaveGlow - Register</title>
<common:head />
</head>
<body>
	<common:header />

	<common:banner bannerText='회원가입' />

	<!--================ 내용 =================-->
	<section class="login_box_area section-margin">
		<div class="container">
			<div class="row">

				<form name="joinForm" class="col-lg-12 row login_form"
					action="/registerSuccess" method="POST" id="register_form">
					<!-- 약관동의 -->
					<div class="col-lg-6">
						<div class="login_form_inner register_form_inner">
							<h3>약관 동의</h3>
							<textarea class="col-lg-10" rows="6" style="resize: none;"
								readonly>1. 약관의 적용
1.1 본 약관은 [weaveGlow]과 이용자 간의 회원가입 및 서비스 이용과 관련하여 적용됩니다.

2. 서비스 내용
2.1 [weaveGlow]은(는) 다음과 같은 서비스를 제공합니다.

(1) 상품 구매
(2) 회원 전용 서비스 이용
(3) 기타 [weaveGlow]이 제공하는 서비스
3. 회원가입 및 이용계약의 성립
3.1 이용자는 회원가입 페이지에서 요구되는 정보를 제공하고, 본 약관에 동의한 후 회원가입을 완료하여야 합니다.
3.2 [weaveGlow]은 회원가입 신청에 대한 승낙 여부를 회원가입 페이지에서 공지하거나 이메일 등의 방법으로 통지합니다.

							</textarea>
							<div class="col-md-12 form-group">
								<div class="creat_account" style="text-align: center;">
									<input type="checkbox" id="f-option1" name="selector">
									<label for="f-option1">[필수] weaveGlow 이용약관</label>
								</div>
							</div>
							<textarea class="col-lg-10" rows="6" style="resize: none;"
								readonly>4. 개인정보 수집 및 보호
4.1 [weaveGlow]은 개인정보 수집 및 보호에 관한 정책을 별도로 공지하며, 이를 준수합니다.
4.2 이용자의 개인정보는 서비스 제공과 관련된 목적으로만 사용되며, 법령에 따른 경우나 이용자의 동의가 있는 경우를 제외하고는 제3자에게 제공되지 않습니다.

5. 이용자의 의무
5.1 이용자는 서비스 이용 시 다음 사항을 준수해야 합니다.

(1) 본인의 신원정보를 정확히 제공해야 합니다.
(2) 타인의 개인정보를 도용하거나 부정하게 사용해서는 안 됩니다.
(3) 서비스 이용 중에 발생하는 문제에 대해 [weaveGlow]에 적극적으로 협조해야 합니다.
6. 서비스 이용료 및 결제 방법
6.1 [weaveGlow]은 일부 서비스에 대해서는 이용료를 부과할 수 있으며, 이와 관련된 내용은 별도로 안내됩니다.

7. 책임 및 면책 조항
7.1 [weaveGlow]은 서비스 이용과 관련하여 일정한 주의를 기울이겠으나, 다음 사항에 대해서는 책임을 지지 않습니다.

(1) 이용자가 서비스를 부정하게 이용하여 발생하는 손해
(2) 천재지변, 정전, 서비스 설비의 장애 등 불가항력적인 사유로 인한 서비스 제공 중단으로 인한 손해
							</textarea>
							<div class="col-md-12 form-group">
								<div class="creat_account" style="text-align: center;">
									<input type="checkbox" id="f-option2" name="selector">
									<label for="f-option2">[필수] 개인정보 수집 및 이용</label>
								</div>
							</div>
							<textarea class="col-lg-10" rows="6" style="resize: none;"
								readonly>이메일 이용 동의 약관

1. 수집 및 이용목적
1.1 [weaveGlow]은(는) 이메일 주소를 수집 및 보유하는 목적은 다음과 같습니다.

(1) 서비스 제공에 필요한 정보를 제공하거나 문의에 응답하기 위함
(2) 새로운 서비스나 이벤트, 프로모션 정보를 제공하기 위함
(3) 마케팅 및 광고 목적으로의 활용
2. 수집하는 개인정보의 항목
2.1 [weaveGlow]은(는) 다음과 같은 개인정보를 수집합니다.

(1) 이메일 주소
3. 수집 방법
3.1 [weaveGlow]은(는) 다음과 같은 방법으로 개인정보를 수집합니다.

(1) 회원가입, 이벤트 응모, 고객센터 문의 등을 통한 이메일 주소 수집
4. 이용 및 보유기간
4.1 [weaveGlow]은(는) 수집된 개인정보를 다음의 목적을 위해 보유합니다.

(1) 이용자에게 필요한 정보 제공 및 문의에 대한 응답: 이용자가 이메일을 통해 제공한 서비스와 관련된 정보를 제공하고, 문의에 대한 답변을 위함
(2) 마케팅 및 광고 목적: 새로운 서비스나 이벤트, 프로모션 정보를 제공하기 위함
4.2 보유기간은 해당 정보의 수집 목적이 달성된 후에는 지체 없이 파기됩니다.
5. 정보 제공 동의 및 철회
5.1 이용자는 [weaveGlow]의 개인정보 수집 및 이용에 동의하지 않을 권리가 있습니다. 이를 원할 경우 이용자는 동의를 거부할 수 있으며, 이 경우 서비스 이용에 제한될 수 있습니다.
5.2 이용자는 언제든지 이전에 동의한 내용을 철회할 수 있습니다. 이를 원할 경우 이메일 주소를 통해 [weaveGlow]에게 연락하여 동의 철회를 요청할 수 있습니다.
							</textarea>
							<div class="col-md-12 form-group">
								<div class="creat_account" style="text-align: center;">
									<input type="checkbox" id="f-option3" name="memberMarketing">
									<label for="f-option3">[선택] 이메일 수신 동의</label>
								</div>
							</div>

						</div>
					</div>
					<!-- /약관동의 -->
					<!-- 회원가입 -->
					<div class="col-lg-6">
						<div class="login_form_inner register_form_inner">
							<h3>회원가입</h3>
							<div class="col-md-12 form-group">
								<div class="col-md-4 label-text">이름</div>
								<input type="text" class="form-control col-md-8" id="registName"
									name="memberName" placeholder="이름 입력" onfocus="this.placeholder = ''"
									onblur="this.placeholder = '이름 입력'"
									style="display: inline-block;">
							</div>

							<div class="col-md-12 form-group" style="margin: 0;">
								<div class="col-md-4 label-text">아이디</div>
								<input type="text" class="form-control col-md-8" id="registMid"
									name="memberID" placeholder="아이디 입력" onfocus="this.placeholder = ''"
									onblur="this.placeholder = '아이디 입력'"
									style="display: inline-block;">
							</div>
							<div class="col-md-12"
								style="text-align: left; font-size: 13px; padding: 3px">
								<div class="col-md-4 label-text"></div>
								<span class="col-md-8" id="idCheck"> <!-- 유효성 확인 문구 innerText -->
								</span>
							</div>

							<div class="col-md-12 form-group" style="margin: 0;">
								<div class="col-md-4 label-text">비밀번호</div>
								<input type="password" class="form-control col-md-8"
									id="registMpw" name="memberPassword" placeholder="비밀번호 입력"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = '비밀번호 입력'"
									style="display: inline-block;">
							</div>
							<div class="col-md-12"
								style="text-align: left; font-size: 13px; padding: 3px">
								<div class="col-md-4 label-text"></div>
								<span class="col-md-8" id="pwCheck"> <!-- 유효성 확인 문구 innerText -->
								</span>
							</div>

							<div class="col-md-12 form-group" style="margin: 0;">
								<div class="col-md-4 label-text">비밀번호 확인</div>
								<input type="password" class="form-control col-md-8"
									id="confirmMpw" name="confirmMpw" placeholder="비밀번호 확인"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = '비밀번호 확인'"
									style="display: inline-block;">
							</div>
							<div class="col-md-12"
								style="text-align: left; font-size: 13px; padding: 3px">
								<div class="col-md-4 label-text"></div>
								<span class="col-md-8" id="confirmPwCheck"> <!-- 유효성 확인 문구 innerText -->
								</span>
							</div>

							<div class="col-md-12 form-group" style="margin: 0;">
								<div class="col-md-4 label-text">닉네임</div>
								<input type="text" class="form-control col-md-8"
									id="registNickname" name="memberNickname" placeholder="닉네임 입력"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = '닉네임 입력'"
									style="display: inline-block;">
							</div>
							<div class="col-md-12"
								style="text-align: left; font-size: 13px; padding: 3px">
								<div class="col-md-4 label-text"></div>
								<span class="col-md-8" id="nickCheck"> <!-- 유효성 확인 문구 innerText -->
								</span>
							</div>

							<!-- 주소검색  -->
							<div class="col-md-12 form-group">
								<div class="col-md-4 label-text">주소</div>
								<input type="text" class="form-control col-md-7"
									id="sample4_postcode" name="addressZonecode" placeholder="우편번호"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = '우편번호'"
									style="display: inline-block; background-color: #fff" readonly>
								<a class="col-md-1" style="float: right;"
									onclick="sample4_execDaumPostcode()" value="우편번호 찾기"> <i
									class="ti-home"></i>
								</a>
							</div>
							<div class="col-md-12 form-group">
								<div class="col-md-4 label-text"></div>
								<input type="text" class="form-control col-md-8"
									id="sample4_roadAddress" name="addressRoad" placeholder="도로명주소"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = '도로명주소'"
									style="display: inline-block; background-color: #fff" readonly>
							</div>
							<div class="col-md-12 form-group">
								<div class="col-md-4 label-text"></div>
								<input type="text" class="form-control col-md-8"
									id="sample4_jibunAddress" name="addressJibun"
									placeholder="지번주소" onfocus="this.placeholder = ''"
									onblur="this.placeholder = '지번주소'"
									style="display: inline-block; background-color: #fff" readonly>
							</div>
							<div class="col-md-12 form-group">
								<div class="col-md-4 label-text"></div>
								<input type="text" class="form-control col-md-8"
									id="sample4_detailAddress" name="addressDetail"
									placeholder="상세주소" onfocus="this.placeholder = ''"
									onblur="this.placeholder = '상세주소'"
									style="display: inline-block;">
							</div>
							<!-- /주소검색  -->

							<div class="col-md-12 form-group" style="margin: 0;">
								<div class="col-md-4 label-text">전화번호</div>
								<input type="text" class="form-control col-md-8"
									id="registPhone" name="memberPhone" placeholder="전화번호 입력"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = '전화번호 입력'"
									style="display: inline-block;">
							</div>
							<div class="col-md-12"
								style="text-align: left; font-size: 13px; padding: 3px">
								<div class="col-md-4 label-text"></div>
								<span class="col-md-8" id="phoneCheck"> <!-- 유효성 확인 문구 innerText -->
								</span>
							</div>

							<div class="col-md-12 form-group">
								<div class="col-md-4 label-text">생년월일</div>
								<input type="date" class="form-control col-md-8"
									id="registBirth" name="memberBirth" placeholder="생년월일"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = '생년월일'"
									style="display: inline-block;">
							</div>

							<div class="col-md-12 form-group" style="margin: 0;">
								<div class="col-md-4 label-text">이메일</div>
								<input type="text" class="form-control col-md-8"
									id="registEmail" name="memberEmail" placeholder="e-mail"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'e-mail'"
									style="display: inline-block;">
							</div>
							<div class="col-md-12"
								style="text-align: left; font-size: 13px; padding: 3px">
								<div class="col-md-4 label-text"></div>
								<span class="col-md-8" id="emailCheck"> <!-- 유효성 확인 문구 innerText -->
								</span>
							</div>

							<div class="col-md-12 form-group">
								<button type="submit" value="submit"
									class="button button-register w-100">확인</button>
							</div>

						</div>
					</div>
					<!-- 회원가입 -->
				</form>
			</div>
		</div>
	</section>
	<!--================ /내용 =================-->

	<common:footer />
	<script src="js/register.js"></script>
</body>
</html>