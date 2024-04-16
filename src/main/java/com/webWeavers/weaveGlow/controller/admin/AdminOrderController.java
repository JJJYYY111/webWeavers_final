package com.webWeavers.weaveGlow.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.webWeavers.weaveGlow.biz.serial.SerialDTO;
import com.webWeavers.weaveGlow.biz.serial.SerialService;

@Controller
public class AdminOrderController {
	
	@Autowired
	SerialService serialService;

	// 관리자페이지 - 주문관리페이지로 이동하는 메서드
	@GetMapping("/adminOrderStatus")
	public String adminOrderStatus(SerialDTO serialDTO, Model model) {
		// 검색조건을 통하여 주문번호목록을 조회한뒤 Model에 추가하고 페이지이동
		serialDTO.setSearchCondition("orderList");
		model.addAttribute("serialDatas", serialService.selectAll(serialDTO));
		return "admin/adminOrderStatus";
	}

	// 관리자페이지 - 해당 주문번호의 구매한 상품들을 출력하기위한 메서드
	@PostMapping("/async/adminDetailOrderList")
	public @ResponseBody String adminGetOrderList(SerialDTO serialDTO, Gson gson) {
		// 파라미터값을 바인딩한 커맨드객체에 검색조건을 추가하여 특정주문번호의 구매내역을 조회한뒤 Json객체로 변환한값을 리턴
		serialDTO.setSearchCondition("orderProduct");
		return gson.toJson(serialService.selectAll(serialDTO));
	}

	// 관리자페이지 - 주문번호를 검색하기위한 메서드
	@PostMapping("/async/adminSearchSerial")
	public @ResponseBody String adminSearchSerial(SerialDTO serialDTO, Gson gson) {
		// 파라미터값을 바인딩한 커맨드객체에 검색조건을 추가하여 원하는 주문번호를 조회한뒤 Json객체로 변환한값을 리턴
		serialDTO.setSearchCondition("orderSearch");
		return gson.toJson(serialService.selectAll(serialDTO));
	}

	// 관리자페이지 - 해당주문번호의 주문상태수정기능을 수행하는 메서드
	@PostMapping("/async/adminSerialStatusChange")
	public @ResponseBody String adminSerialStatus(SerialDTO serialDTO, Gson gson) {
		// 파라미터값을 바인딩한 커맨드객체를 통해 주문번호테이블의 주문상태를수정하고 성공시 'true' 실패시 'false'를 리턴
		return String.valueOf(serialService.update(serialDTO));
	}
}
