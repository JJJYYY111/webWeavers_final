package com.webWeavers.weaveGlow.controller.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
public class UploadController {
	
	@Autowired
	private PhotoUtil photoUtil;

	@PostMapping("/upload")
    public ModelAndView upload(MultipartHttpServletRequest request) {
        ModelAndView mav = new ModelAndView("jsonView");

        String uploadPath = photoUtil.ckUpload(request);
        
        mav.addObject("uploaded", true);
        mav.addObject("url", uploadPath);
        return mav;
    }
}
