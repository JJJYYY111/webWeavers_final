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
		// 파라미터값을 바인딩한 커맨드객체에 검색조건을 추가하고 기존유저들중 해당 ID를 가진 유저가 있는지 조회
		memberDTO.setSearchCondition("idCheck");
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
		// 파라미터값을 바인딩한 커맨드객체에 검색조건을 추가하고 기존유저들중 해당 닉네임을 가진 유저가 있는지 조회
		memberDTO.setSearchCondition("memberNickNameCheck");
		memberDTO = memberService.selectOne(memberDTO); 
		if (memberDTO == null || memberDTO.getMemberID().equals(session.getAttribute("sessionMid"))) { // mDTO가 null인 경우(중복x)
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
		// 세션값 제거
		session.removeAttribute("sessionMid");
		session.removeAttribute("grade");
		return "redirect:/main";
	}

	// 로그인을 수행하는 메서드
	@PostMapping("/memberSelectOne")
	public String memberSelectOne(MemberDTO memberDTO, HttpSession session, Model model) {
		
		// 파라미터값을 바인딩한 커맨드객체에 검색조건을 추가하고 해당 회원이 존재하는지 조회
		memberDTO.setSearchCondition("login");
		memberDTO = memberService.selectOne(memberDTO);

		// 확인된 사용자 정보가 비어있거나 사용자의 등급이 5등급(탈퇴상태) 인 경우
		if (memberDTO == null || memberDTO.getGradePK() == 5) {
			model.addAttribute("msg", "잘못입력하셨거나 없는 회원입니다.");
			return "user/login";
		}
		// 확인된 사용자 정보가 있거나 사용자의 등급이 5등급(탈퇴상태)이 아닌 경우
		session.setAttribute("sessionMid", memberDTO.getMemberID());
		session.setAttribute("grade", memberDTO.getGradePK());
		return "redirect:/main";
	}

	// 회원정보수정을 수행하는 메서드
	@PostMapping("/memberUpdate")
	public String memberUpdate(MemberDTO memberDTO, HttpSession session) {
		System.out.println("회원정보수정진입");
		// 파라미터값을 바인딩한 커맨드객체에 사용자의 ID값과 검색조건을 추가하고 회원정보수정기능을 수행한뒤 페이지 이동
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
		
		// 사용자의ID값과 검색조건을 통해 회원정보를 조회하여 Model에 추가하고 페이지이동
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
		
		// 사용자의 ID와 검색조건을 통해 사용자의 정보를 조회하여 Model에 추가하고 페이지이동
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
	@PostMapping("/registerSuccess")
	public String registerSuccess(MemberDTO memberDTO, AddressDTO addressDTO) {
		
		// 마케팅 수신동의에 체크를 했으면 'Y'를 저장, 그렇지 않을경우 'N'을 저장
		if (memberDTO.getMemberMarketing() != null) {
			memberDTO.setMemberMarketing("Y");
		} else {
			memberDTO.setMemberMarketing("N");
		}
		
		// 파라미터값을 바인딩한 커맨드객체를 통해 회원추가기능을 수행
		if (!memberService.insert(memberDTO)) {
			return "redirect:/register";
		}
		
		addressDTO.setAddressName("기본배송지");
		// 상세주소를 입력하지 않은 경우
		if (addressDTO.getAddressDetail() == null) {
			addressDTO.setAddressDetail("");
		}
		// 파라미터값을 바인딩한 커맨드객체를 통해 주소추가 기능을 수행
		if (!addressService.insert(addressDTO)) {
			return "redirect:/register";
		}
		// 회원가입 완료 페이지로 페이지 이동
		return "user/registerSuccess";
	}
	
	// 회원탈퇴페이지로 이동하는 메서드
	@PostMapping("/unregister")
	public String unregister(MemberDTO memberDTO, HttpSession session) {
		return "user/unregister";
	}
	
	// 회원탈퇴완료페이지로 이동하는 메서드
	@PostMapping("/unregisterSuccess")
	public String unregisterSuccess(MemberDTO memberDTO, HttpSession session) {
		
		// 사용자의 ID값과 검색조건을 통해 회원의 상태를 변경(탈퇴상태로 변경)
		memberDTO.setMemberID((String) session.getAttribute("sessionMid"));
		memberDTO.setSearchCondition("unregisterUpdateInfo");
		if (!memberService.update(memberDTO)) { 
			return "redirect:/unregisterPasswordCheck";
		}
		
		// 탈퇴에 성공했다면 세션에서 사용자의 ID와 GRADE를 삭제하고 페이지 이동
		session.removeAttribute("sessionMid");
		session.removeAttribute("grade");
		return "user/unregisterSuccess";
	}
}
