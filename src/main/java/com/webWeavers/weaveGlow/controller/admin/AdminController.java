package com.webWeavers.weaveGlow.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
	@RequestMapping("/adminDashboard")
	public String adminDashboard() {
		return "admin/adminDashboard";
	}
	
	
	@RequestMapping("/adminMemberStatus")
	public String adminMemberStatus() {
		return "admin/adminMemberStatus";
	}
	
	
	@RequestMapping("/adminProductStatus")
	public String adminProductStatus() {
		return "admin/adminProductStatus";
	}
	
	
	@RequestMapping("/adminProductRegistration")
	public String adminProductRegistration() {
		return "admin/adminProductRegistration";
	}
	
	
	@RequestMapping("/adminOrderStatus")
	public String adminOrderStatus() {
		return "admin/adminOrderStatus";
	}
	
	
	@RequestMapping("/salesStatus")
	public String salesStatus() {
		return "admin/salesStatus";
	}
	
	@RequestMapping("/daliySalesStatus")
	public String daliySalesStatus() {
		return "admin/daliySalesStatus";
	}
	
	@RequestMapping("/monthlySalesStatus")
	public String monthlySalesStatus() {
		return "admin/monthlySalesStatus";
	}
	
	
	
}
