<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>WeaveGlow - Unregister</title>
<common:head />
</head>
<body>
	<common:header />

	<common:banner bannerText='회원탈퇴' />
   
   <!--================ 내용 =================-->
   <section class="login_box_area section-margin">
      <div class="container">
         <div class="row">
            <form class="col-lg-12 row login_form" action="/unregisterSuccess" method="POST" id="unregister_form" name="unregisterForm">
               <div class="col-lg-12">
                  <div class="login_form_inner register_form_inner">
                     <h3>약관 동의</h3>
                     <textarea class="col-lg-10" rows="4" style="resize: none;" readonly>
Mill Oil is an innovative oil filled radiator with the most modern technology. If you are looking for something that can make your interior look awesome, and at the same time give you the pleasant warm feeling during the winter.
Mill Oil is an innovative oil filled radiator with the most modern technology. If you are looking for something that can make your interior look awesome, and at the same time give you the pleasant warm feeling during the winter.
                     </textarea>
                     <div class="col-md-12 form-group">
                        <div class="creat_account" style="text-align: center;">
                           <input type="checkbox" id="f-option1" name="selector" >
                           <label for="f-option1">[필수] 개인 정보 삭제 동의</label>
                        </div>
                     </div>
                     <textarea class="col-lg-10" rows="4" style="resize: none;" readonly>
Mill Oil is an innovative oil filled radiator with the most modern technology. If you are looking for something that can make your interior look awesome, and at the same time give you the pleasant warm feeling during the winter.
Mill Oil is an innovative oil filled radiator with the most modern technology. If you are looking for something that can make your interior look awesome, and at the same time give you the pleasant warm feeling during the winter.
                     </textarea>
                     <div class="col-md-12 form-group">
                        <div class="creat_account" style="text-align: center;">
                           <input type="checkbox" id="f-option2" name="selector" >
                           <label for="f-option2">[필수] 서비스 이용 혜택 소멸 동의</label>
                        </div>
                     </div>

                     <textarea class="col-lg-10" rows="4" style="resize: none;" readonly>
Mill Oil is an innovative oil filled radiator with the most modern technology. If you are looking for something that can make your interior look awesome, and at the same time give you the pleasant warm feeling during the winter.
Mill Oil is an innovative oil filled radiator with the most modern technology. If you are looking for something that can make your interior look awesome, and at the same time give you the pleasant warm feeling during the winter.
                     </textarea>
                     <div class="col-md-12 form-group">
                        <div class="creat_account" style="text-align: center;">
                           <input type="checkbox" id="f-option3" name="selector3">
                           <label for="f-option3">[선택] 개인 정보 활용 동의</label>
                        </div>
                     </div>
                     
                     <div class="col-md-12 form-group">
                        <button type="submit" value="submit" class="button button-register w-100">회원탈퇴</button>
                     </div>   
                     
                  </div>
               </div>
            </form>
         </div>
      </div>
   </section>
   <!--================ /내용 =================-->
      
	<common:footer />
	<script src="js/register.js"></script>
</body>
</html>