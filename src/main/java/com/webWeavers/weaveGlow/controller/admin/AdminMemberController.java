package com.webWeavers.weaveGlow.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.webWeavers.weaveGlow.biz.member.MemberDTO;
import com.webWeavers.weaveGlow.biz.member.MemberService;

@Controller
public class AdminMemberController {
	
	@Autowired
	MemberService memberService;

	// 관리자페이지 - 회원목록으로 이동하는 메서드
	@GetMapping("/adminMemberStatus")
	public String adminMemberStatus(MemberDTO memberDTO, Model model) {
		// 회원목록을 조회하여 Model객체에 추가하고 페이지이동
		memberDTO.setSearchCondition("allMemberInfo");
		model.addAttribute("memberDatas", memberService.selectAll(memberDTO));
		return "admin/adminMemberStatus";
	}

	// 관리자페이지 - 해당 회원의 정보수정페이지로 이동하는 메서드
	@GetMapping("/adminMemberStatusChange")
	public String adminMemberStatusChange(MemberDTO memberDTO, Model model) {
		// 페이지에 출력할 정보를 검색조건을 통해 조회한뒤 해당 값을 Model에 저장하고 페이지이동
		memberDTO.setSearchCondition("idCheck");
		memberDTO = memberService.selectOne(memberDTO);
		model.addAttribute("memberID", memberDTO.getMemberID());
		model.addAttribute("memberPassword", memberDTO.getMemberPassword());
		model.addAttribute("memberName", memberDTO.getMemberName());
		model.addAttribute("memberBirth", memberDTO.getMemberBirth());
		model.addAttribute("memberPhone", memberDTO.getMemberPhone());
		model.addAttribute("memberNickname", memberDTO.getMemberNickname());
		model.addAttribute("memberEmail", memberDTO.getMemberEmail());
		model.addAttribute("gradePK", memberDTO.getGradePK());
		return "admin/adminMemberStatusChange";
	}

	// 관리자페이지 - 회원정보수정을 수행하는 메서드
	@PostMapping("/adminMemberUpdate")
	public String adminMemberUpdate(MemberDTO memberDTO) {
		// 파라미터값을 바인딩한 커맨드객체에 검색조건을 추가하여 회원정보를 수정한뒤 페이지이동
		memberDTO.setSearchCondition("adminUpdateMember");
		System.out.println(memberDTO);
		if (!memberService.update(memberDTO)) {
			return "redirect:/error";
		}
		return "redirect:/adminMemberStatus";
	}

	// 관리자페이지 - 회원검색기능의 메서드
	@PostMapping("/async/adminMemberSearch")
	public @ResponseBody String adminMemberSearch(MemberDTO memberDTO, Gson gson) {
		// 파라미터값을 바인딩한 커맨드객체에 검색조건을 추가하여 회원정보를 조회한뒤 Json객체로 변환한값을 리턴
		memberDTO.setSearchCondition("userSearch");
		System.out.println(memberDTO);
		return gson.toJson(memberService.selectAll(memberDTO));
	}
}
