package com.webWeavers.weaveGlow.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.webWeavers.weaveGlow.biz.address.AddressDTO;
import com.webWeavers.weaveGlow.biz.address.AddressService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AddressController {

	@Autowired
	AddressService addressService;
	
	// 결제시 배송지에 사용할 주소목록들을 출력하는 메서드
	@PostMapping("/async/addressSelectAll")
	public @ResponseBody String addressSelectAll(AddressDTO addressDTO, HttpSession session, Gson gson) {
		// ID값으로 해당 사용자의 주소목록을 받아와서 Json객체로 변환한값을 리턴
		addressDTO.setMemberID((String) session.getAttribute("sessionMid"));
		return gson.toJson(addressService.selectAll(addressDTO));
	}
	
	// 결제시 배송지에 사용할 특정주소를 출력하는 메서드
	@PostMapping("/async/addressSelectOne")
	public @ResponseBody String addressSelectOne(AddressDTO addressDTO, Gson gson) {
		// 커맨드객체에 바인딩된 값을 통해 특정를 주소 조회하여 Json객체로 변환한값을 리턴
		addressDTO = addressService.selectOne(addressDTO); 
		if (addressDTO.getAddressJibun() == null) { 
			addressDTO.setAddressJibun(""); 
		}
		if (addressDTO.getAddressDetail() == null) { 
			addressDTO.setAddressDetail(""); 
		}
		return gson.toJson(addressDTO);
	}
	
	// 새로운 주소를 추가하기위한 메서드
	@PostMapping("/async/addressInsert") 
	public @ResponseBody String addressInsert(AddressDTO addressDTO, HttpSession session) {
		if (addressDTO.getAddressName() == "") { 
			addressDTO.setAddressName("이름없는배송지");
		}
		// 파라미터값을 바인딩한 커맨드객체에 ID값을 추가한뒤 주소테이블에 해당사용자의 주소를 추가하고 성공시 'true' 실패시 'false'를 리턴
		addressDTO.setMemberID((String) session.getAttribute("sessionMid"));
		return String.valueOf(addressService.insert(addressDTO));
	}
	
	// 주소를 수정하기위한 메서드
	@PostMapping("/async/addressUpdate") 
	public @ResponseBody String addressUpdate(AddressDTO addressDTO) {
		if (addressDTO.getAddressName() == null) { 
			addressDTO.setAddressName("이름없는 배송지"); 
		}
		if (addressDTO.getAddressDetail() == null) { 
			addressDTO.setAddressDetail("");
		}
		// 파라미터값을 바인딩한 커맨드객체를 주소테이블에서 수정하고 성공시 'true' 실패시 'false'를 리턴
		return String.valueOf(addressService.update(addressDTO));
	}

	// 주소를 삭제하기위한 메서드
	@PostMapping("/async/addressDelete")
	public @ResponseBody String addressDelete(AddressDTO addressDTO) {
		// 파라미터값을 바인딩한 커맨드객체를 주소테이블에서 삭제하고 성공시 'true' 실패시 'false'를 리턴
		return String.valueOf(addressService.delete(addressDTO));
	}
}
