package com.webWeavers.weaveGlow.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webWeavers.weaveGlow.biz.address.AddressDTO;
import com.webWeavers.weaveGlow.biz.address.AddressService;
import com.webWeavers.weaveGlow.biz.member.MemberDTO;
import com.webWeavers.weaveGlow.biz.member.MemberService;
import com.webWeavers.weaveGlow.biz.product.ProductService;
import com.webWeavers.weaveGlow.biz.sms.SmsService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	@Autowired
	ProductService productService;
	@Autowired
	AddressService addressService;
	@Autowired
	SmsService smsService;

	// id중복검사를 수행하는 메서드
	@PostMapping("/async/idCheck")
	public @ResponseBody String idCheck(MemberDTO memberDTO) {
		System.out.println("아이디중복검사진입");
		memberDTO.setSearchCondition("idCheck"); // memberDTO에 검색조건 저장

		if (memberService.selectOne(memberDTO) == null) { // memberDTO가 null인 경우(중복x)
			return "1"; // 1 응답
		} else { // mDTO가 null이 아닌 경우(중복o),
			return "0"; // 0 응답
		}
	}

	// 닉네임중복검사를 수행하는 메서드
	@PostMapping("/async/nickNameCheck")
	public @ResponseBody String nickNameCheck(MemberDTO memberDTO, HttpSession session) {
		System.out.println("닉네임중복검사진입");
		memberDTO.setSearchCondition("memberNickNameCheck"); // mDTO에 검색조건 저장

		memberDTO = memberService.selectOne(memberDTO); // selectOne()을 통해 리턴값(객체) 저장

		if (memberDTO == null || memberDTO.getMemberID().equals(session.getAttribute("sessionMid"))) { // mDTO가 null인
			return "1"; // 1 응답
		} else { // mDTO가 null이 아닌 경우(중복o),
			return "0"; // 0 응답
		}
	}

	// sms본인인증확인을 진행하는 메서드
	@PostMapping("/async/smsCertification")
	public @ResponseBody int smsCertification(MemberDTO memberDTO) {
		System.out.println("[로그] 본인인증번호발송서비스진입");

		// memberDTO 검색조건 저장
		memberDTO.setSearchCondition("certification");
		// 문자인증 서비스 sendMessage()의 리턴값 변수에 저장
		int resultNum = smsService.sendMessage(memberDTO);

		if (resultNum > 0) {
			return resultNum; // 메시지발송 성공 : 인증번호 * 7777 응답
		} else {
			return -1; // 메시지발송 실패 : -1 응답
		}
	}

	// 로그인 페이지로 이동하는 메서드
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

	// 로그아웃을 수행하는 메서드
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionMid");
		session.removeAttribute("grade");
		return "redirect:/main";
	}

	// 로그인을 수행하는 메서드
	@PostMapping("/memberSelectOne")
	public String memberSelectOne(MemberDTO memberDTO, HttpSession session, Model model) {
		memberDTO.setSearchCondition("login");
		memberDTO = memberService.selectOne(memberDTO);

		// 확인된 사용자 정보가 비어있거나 사용자의 등급이 5등급 인 경우
		if (memberDTO == null || memberDTO.getGradePK() == 5) {
			model.addAttribute("msg", "잘못입력하셨거나 없는 회원입니다.");
			return "user/login";
		}
		// 확인된 사용자 정보가 있거나 사용자의 등급이 5등급 아닌 경우
		session.setAttribute("sessionMid", memberDTO.getMemberID());
		session.setAttribute("grade", memberDTO.getGradePK());
		return "redirect:/main";
	}

	// 회원정보수정을 수행하는 메서드
	@PostMapping("/memberUpdate")
	public String memberUpdate(MemberDTO memberDTO, HttpSession session) {
		System.out.println("회원정보수정진입");
		memberDTO.setSearchCondition("updateInfo");
		memberDTO.setMemberID((String) session.getAttribute("sessionMid"));
		if (!memberService.update(memberDTO)) { // 성공했으면 mypage로 이동
			return "redirect:/error";
		}
		return "redirect:/mypage";
	}

	// 마이페이지로 이동하는 메서드
	@GetMapping("/mypage")
	public String myPage(MemberDTO memberDTO, HttpSession session, Model model) {
		memberDTO.setSearchCondition("memberInfo");

		memberDTO.setMemberID((String) session.getAttribute("sessionMid"));
		memberDTO = memberService.selectOne(memberDTO);
		if (memberDTO == null) {
			return "redirect:/error";
		}
		model.addAttribute("data", memberDTO);
		return "user/mypage";
	}

	// 비밀번호확인페이지로 이동하는 메서드 - 회원상태변경 진입시
	@GetMapping("/profileChangePasswordCheck")
	public String profileChangePasswordCheck() {
		return "user/profileChangePasswordCheck";
	}
	
	// 비밀번호확인페이지로 이동하는 메서드 - 회원탈퇴시
	@GetMapping("/unregisterPasswordCheck")
	public String unregisterPasswordCheck() {
		return "user/unregisterPasswordCheck";
	}
	
	// 회원정보수정페이지로 이동하는 메서드
	@PostMapping("/profileChange")
	public String profileChange(MemberDTO memberDTO, HttpSession session, Model model) {
		memberDTO.setMemberID((String) session.getAttribute("sessionMid"));

		memberDTO.setSearchCondition("login");
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
	
	// 회원가입페이지로 이동하는 메서드
	@GetMapping("/register")
	public String register() {
		return "user/register";
	}
	
	// 회원등록완료페이지로 이동하는 메서드
	@PostMapping("/registerSuccess") // 얘도 트랜잭션?
	public String registerSuccess(MemberDTO memberDTO, AddressDTO addressDTO) {

		if (memberDTO.getMemberMarketing() != null) {
			memberDTO.setMemberMarketing("Y");
		} else {
			memberDTO.setMemberMarketing("N");
		}
		
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
	
	// 회원탈퇴페이지로 이동하는 메서드
	@PostMapping("/unregister")
	public String unregister(MemberDTO memberDTO, HttpSession session) {
		memberDTO.setMemberID((String) session.getAttribute("sessionMid"));
		memberDTO.setSearchCondition("login");
		if (memberService.selectOne(memberDTO) == null) { // 해당 유저가 존재하지 않는다면
			return "redirect:/error";
		}
		return "user/unregister";
	}
	
	// 회원탈퇴완료페이지로 이동하는 메서드
	@PostMapping("/unregisterSuccess")
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
