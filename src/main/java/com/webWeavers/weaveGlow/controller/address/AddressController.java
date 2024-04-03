package com.webWeavers.weaveGlow.controller.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.webWeavers.weaveGlow.biz.address.AddressDTO;
import com.webWeavers.weaveGlow.biz.address.AddressService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AddressController {

	@Autowired
	AddressService addressService;

	@RequestMapping("/async/addressSelectAll") // ("/async/idcheck" 혹은 "/async/pwcheck")
	public @ResponseBody String addressSelectAll(AddressDTO addressDTO, HttpSession session) {
		// 데이터를 전달할 때 한글이 깨지지 않도록하기 위해 인코딩 설정
//		response.setCharacterEncoding("UTF-8");

		addressDTO.setMemberID((String) session.getAttribute("sessionMid"));
		List<AddressDTO> adatas = addressService.selectAll(addressDTO);
		System.out.println(adatas);
		String json;
		if (adatas == null) { // 만약 주소목록이 비어있다면
			json = "[]"; // 빈 배열을 반환
		}
		Gson gson = new Gson(); // Gson 객체 생성
		json = gson.toJson(adatas);
		return json;
	}

	@RequestMapping("/async/addressSelectOne") // ("/async/idcheck" 혹은 "/async/pwcheck")
	public @ResponseBody String addressSelectOne(AddressDTO addressDTO) {
//		response.setCharacterEncoding("UTF-8");
		System.out.println(addressDTO);
		addressDTO = addressService.selectOne(addressDTO); // aDAO의 검색기능인 R(selectOne)기능을 사용하여 주소 테이블에 해당 주소가 존재하는지 확인
		String json;
		if (addressDTO == null) { // 주소테이블에 해당 주소가 존재하지 않는다면
			json = "[]"; // 빈 배열을 반환
		} else { // 주소테이블에 해당 주소가 존재한다면 else의 실행문을 실행
			if (addressDTO.getAddressJibun() == null) { // 해당 주소의 지번주소가 null일경우
				addressDTO.setAddressJibun(""); // 해당 주소의 지번주소를 빈칸으로 수정
			}
			if (addressDTO.getAddressDetail() == null) { // 해당 주소의 상세주소가 null일 경우
				addressDTO.setAddressDetail(""); // 해당 주소의 상세주소를 빈칸으로 수정
			}
			System.out.println(addressDTO);
			// Gson 라이브러리를 사용하여 객체를 JSON 문자열로 변환
			Gson gson = new Gson(); // Gson객체 생성
			json = gson.toJson(addressDTO); // aDTO객체를 json타입으로 변환하여 문자열로 저장
		}

		return json;
	}

	@RequestMapping("/async/addressInsert") // ("/async/idcheck" 혹은 "/async/pwcheck")
	public @ResponseBody String addressInsert(AddressDTO addressDTO, HttpSession session) {
		// 요청에 대한 응답을 보내기위해 printWriter 객체 생성
		if (addressDTO.getAddressName() == "") { // 만약 배송지명이 비어있다면
			addressDTO.setAddressName("이름없는배송지"); // 배송지명을 '이름없는배송지'로 초기화
		}
		addressDTO.setMemberID((String) session.getAttribute("sessionMid"));
		return String.valueOf(addressService.insert(addressDTO));
	}

	@RequestMapping("/async/addressUpdate") // ("/async/idcheck" 혹은 "/async/pwcheck")
	public @ResponseBody String addressUpdate(AddressDTO addressDTO) {
		// request로 배송지명을 받아와서 변수로 저장 (aname = 배송지명)
		if (addressDTO.getAddressName() == null) { // 만약 배송지명이 없다면
			addressDTO.setAddressName("이름없는 배송지"); // 배송지명을 '이름없는 배송지'로 초기화
		}
		// request로 상세주소 파라미터를 받아와서 변수로 저장 (detail = 상세주소)
		if (addressDTO.getAddressDetail() == null) { // 만약 상세주소가 null이라면
			addressDTO.setAddressDetail(""); // 빈칸으로 초기화
		}
		return String.valueOf(addressService.update(addressDTO));
	}

	@RequestMapping("/async/addressDelete") // ("/async/idcheck" 혹은 "/async/pwcheck")
	public @ResponseBody String addressDelete(AddressDTO addressDTO) {
		return String.valueOf(addressService.delete(addressDTO));
	}
}
