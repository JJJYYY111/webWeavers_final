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
		addressDTO.setMemberID((String) session.getAttribute("sessionMid"));
		return gson.toJson(addressService.selectAll(addressDTO));
	}
	
	// 결제시 배송지에 사용할 특정주소를 출력하는 메서드
	@PostMapping("/async/addressSelectOne")
	public @ResponseBody String addressSelectOne(AddressDTO addressDTO, Gson gson) {
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
		return String.valueOf(addressService.update(addressDTO));
	}

	// 주소를 삭제하기위한 메서드
	@PostMapping("/async/addressDelete")
	public @ResponseBody String addressDelete(AddressDTO addressDTO) {
		return String.valueOf(addressService.delete(addressDTO));
	}
}
