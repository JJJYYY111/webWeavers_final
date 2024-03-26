package com.webWeavers.weaveGlow.biz.crawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webWeavers.weaveGlow.biz.categorization.CategorizationService;
import com.webWeavers.weaveGlow.biz.product.ProductDTO;
import com.webWeavers.weaveGlow.biz.product.ProductService;
import com.webWeavers.weaveGlow.biz.subcategory.SubCategoryDTO;
import com.webWeavers.weaveGlow.biz.subcategory.SubCategoryService;

@Service
public class CrawlingService {
	
	@Autowired
	ProductService productService;
	@Autowired
	CategorizationService categorizationService;
	@Autowired
	SubCategoryService subCategoryService;
	
	public @ResponseBody String crawlingProduct(SubCategoryDTO subCategoryDTO) {
		List<SubCategoryDTO> subCategoryDatas = subCategoryService.selectAll(subCategoryDTO);
		
		List<ProductDTO> datas = new ArrayList<ProductDTO>();
		String weburl = "https://tonymoly.com/ctgr/subcategory_product_list.do?i_sCategorycd1=L01&i_sCategorynm1=%EA%B8%B0%EC%B4%88&i_sCategorycd2=L01M01&i_sCategorynm2=%EC%8A%A4%ED%82%A8%EC%BC%80%EC%96%B4&i_sCategorycd3=L01M01S01&i_sCategorynm3=%EC%8A%A4%ED%82%A8";
		Document doc = null;
		try {
			doc = Jsoup.connect(weburl).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements elems = doc.select("li.prd_list");
		Iterator<Element> itr = elems.iterator();
//		int count = 0; // 추가된 객체 개수를 세기 위한 변수
		while (itr.hasNext() /* && count < 5 */) { // 5개까지만 추가
			ProductDTO productDTO = new ProductDTO();
			Element el = itr.next();
			String name = el.select("span.prod-name").text();
			String price1 = el.select("div.selling-price-wrap > em.price-after").text().replace(",", "").replace("원",
					"");
			String img = el.select("img.over").attr("src");
			String detailImg = "https://tonymoly.com" + el.select("a.link.thumb").attr("href");
			int price = Integer.parseInt(price1);
//			System.out.println(name + " / " + price + " / " + img + " / " + detailImg );
			productDTO.setProductName(name);
			productDTO.setProductPrice(price);
			productDTO.setProductImg(img);
			productDTO.setProductDetailImg(detailImg);
			productDTO.setProductQuantity(50);
			productDTO.setProductStatus(1);
			datas.add(productDTO);
		}

		for (int i = 0; i < datas.size(); i++) {
			System.out.println(datas.get(i).getProductDetailImg());
			try {
				doc = Jsoup.connect(datas.get(i).getProductDetailImg()).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
			elems = doc.select("div#section_detail_view img:first-child");
			String detailImg = elems.attr("src");
			datas.get(i).setProductDetailImg(detailImg);
			if (!productService.insert(datas.get(i))) {
				return "redirect:/error";
			}
		}
		return "redirect:/main";
	}
}
