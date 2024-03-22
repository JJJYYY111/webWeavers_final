package com.webWeavers.weaveGlow.controller.member;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webWeavers.weaveGlow.biz.address.AddressDTO;
import com.webWeavers.weaveGlow.biz.address.AddressService;
import com.webWeavers.weaveGlow.biz.member.MemberDTO;
import com.webWeavers.weaveGlow.biz.member.MemberService;
import com.webWeavers.weaveGlow.biz.product.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	@Autowired
	ProductService productService;
	@Autowired
	AddressService addressService;

	@RequestMapping("/async/idCheck")
	public @ResponseBody String idCheck(MemberDTO memberDTO) {
		System.out.println("아이디중복검사진입");
		System.out.println(memberDTO);
		memberDTO.setSearchCondition("idCheck"); // memberDTO에 검색조건 저장

		if (memberService.selectOne(memberDTO) == null) { // memberDTO가 null인 경우(중복x)
			return "1"; // 1 응답
		} else { // mDTO가 null이 아닌 경우(중복o),
			return "0"; // 0 응답
		}

	}

	@RequestMapping("/async/nickNameCheck")
	public @ResponseBody String nickNameCheck(MemberDTO memberDTO, HttpSession session) {
		System.out.println("닉네임중복검사진입");
		System.out.println(memberDTO);
		memberDTO.setSearchCondition("memberNickNameCheck"); // mDTO에 검색조건 저장

		memberDTO = memberService.selectOne(memberDTO); // selectOne()을 통해 리턴값(객체) 저장

		if (memberDTO == null || memberDTO.getMemberID().equals(session.getAttribute("sessionMid"))) { // mDTO가 null인
																										// 경우(중복x),
																										// 개인정보수정에서 기존
																										// 닉네임을 사용하고 싶은
																										// 경우
			return "1"; // 1 응답
		} else { // mDTO가 null이 아닌 경우(중복o),
			return "0"; // 0 응답
		}
	}

	@RequestMapping("/login")
	public String login() {
		return "user/login";

	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionMid");
		session.removeAttribute("grade");
		return "redirect:/main";
	}

	@RequestMapping("/memberSelectOne")
	public String memberSelectOne(MemberDTO memberDTO, HttpSession session, Model model) {
		memberDTO.setSearchCondition("login");
		System.out.println(memberDTO);
		memberDTO = memberService.selectOne(memberDTO);

		// 확인된 사용자 정보가 비어있거나 사용자의 등급이 5등급 인 경우
		if (memberDTO == null || memberDTO.getGradePK() == 5) {
			model.addAttribute("msg", "잘못입력하셨거나 없는 회원입니다.");
			return "user/login";
		}
		// 확인된 사용자 정보가 있거나 사용자의 등급이 5등급 아닌 경우
		else {
			session.setAttribute("sessionMid", memberDTO.getMemberID());
			session.setAttribute("grade", memberDTO.getGradePK());
 			return "redirect:/main";
		}

	}

	@RequestMapping("/memberUpdate")
	public String memberUpdate(MemberDTO memberDTO, HttpSession session) {
		memberDTO.setSearchCondition("updateInfo");
		memberDTO.setMemberID((String) session.getAttribute("sessionMid"));
		System.out.println("회원정보수정진입");
		System.out.println(memberDTO);
		if (!memberService.update(memberDTO)) { // 성공했으면 mypage로 이동
			return "redirect:/error";
		}
		return "redirect:/mypage";
	}

	@RequestMapping("/mypage")
	public String myPage(MemberDTO memberDTO, HttpSession session, Model model) {
		// 서버에서 입력 받은 사용자정보의 사용방식을 구분하기위해 작성
		memberDTO.setSearchCondition("memberInfo");

		// 서버로부터 사용자 ID 정보를 받아와 MemberDTO에 저장
		memberDTO.setMemberID((String) session.getAttribute("sessionMid"));
		memberDTO = memberService.selectOne(memberDTO);
		// 확인된 사용자 정보가 비어있는 경우
		if (memberDTO == null) {
			return "redirect:/error";
		}
		// 확인된 사용자 정보가 있는 경우
		else {
			model.addAttribute("data", memberDTO);
			return "user/mypage";
		}
	}

	@RequestMapping("/profileChangePasswordCheck")
	public String profileChangePasswordCheck() {
		return "user/profileChangePasswordCheck";
	}

	@RequestMapping("/unregisterPasswordCheck")
	public String unregisterPasswordCheck() {
		return "user/unregisterPasswordCheck";
	}

	@RequestMapping("/profileChange")
	public String profileChange(MemberDTO memberDTO, HttpSession session, Model model) {
		memberDTO.setMemberID((String) session.getAttribute("sessionMid"));

		memberDTO.setSearchCondition("login");
		System.out.println(memberDTO);
		memberDTO = memberService.selectOne(memberDTO);

		if (memberDTO == null) {
			return "redirect:/passwordCheckProfileChange";
		}
		// 확인한 사용자 정보가 있는 경우
		model.addAttribute("memberPassword", memberDTO.getMemberPassword());
		model.addAttribute("memberName", memberDTO.getMemberName());
		model.addAttribute("memberBirth", memberDTO.getMemberBirth());
		model.addAttribute("memberPhone", memberDTO.getMemberPhone());
		model.addAttribute("memberNickname", memberDTO.getMemberNickname());
		model.addAttribute("memberEmail", memberDTO.getMemberEmail());
		model.addAttribute("marketing", memberDTO.getMemberMarketing());
		return "user/profileChange";
	}

	@RequestMapping("/register")
	public String register() {
		return "user/register";
	}

	@RequestMapping("/registerSuccess")
	public String registerSuccess(MemberDTO memberDTO, AddressDTO addressDTO) {
////		 Date 객체를 선언하고 초기화
////		 "yyyy-MM-dd" 형식의 날짜 문자열을 처리하기 위한 formatter 생성
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			// request.getParameter("birth")로부터 전달된 생년월일 문자열을 Date 객체로 변환
//		memberDTO.setMemberBirth(formatter.parse(memberBirthDate));	
//		} catch (Exception e ) {
//			// 예외가 발생한 경우
//			e.printStackTrace();
//		}
		
		System.out.println("회원가입기능진입");
		System.out.println(memberDTO);
		
		if (memberDTO.getMemberMarketing() != null) {
			memberDTO.setMemberMarketing("Y");
		}
		System.out.println(memberDTO);
		// 입력받은 정보의 추가가 된 경우
		if (!memberService.insert(memberDTO)) {
			return "redirect:/register";
		}
		addressDTO.setAddressName("기본배송지");
		// 상세주소를 입력하지 않은 경우
		if (addressDTO.getAddressDetail() == null) {
			addressDTO.setAddressDetail("");
		}

		if (!addressService.insert(addressDTO)) {
			return "redirect:/register";
		}
		return "user/registerSuccess";

	}

	@RequestMapping("/unregister")
	public String unregister(MemberDTO memberDTO, HttpSession session) {
		memberDTO.setMemberID((String) session.getAttribute("sessionMid"));
		memberDTO.setSearchCondition("login");
		if (memberService.selectOne(memberDTO) == null) { // 해당 유저가 존재하지 않는다면
			return "redirect:/error";
		}
		return "user/unregister";
	}

	@RequestMapping("/unregisterSuccess")
	public String unregisterSuccess(MemberDTO memberDTO, HttpSession session) {
		// 유저의 정보를 담는 mDTO에 id와 검색조건을 설정
		memberDTO.setMemberID((String) session.getAttribute("sessionMid"));
		memberDTO.setSearchCondition("unregisterUpdateInfo");

		if (!memberService.update(memberDTO)) { // 탈퇴에 성공했다면 세션에서 사용자의 id를 삭제
			return "redirect:/unregisterPasswordCheck";
		}
		session.removeAttribute("sessionMid");
		session.removeAttribute("grade");
		return "user/unregisterSuccess";
	}
}
