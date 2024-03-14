//package com.weaveGlow.final3.model.util;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import model.product.ProductDTO;
//
//
//public class Crawling {
//
//	private static final String INSERT = "INSERT INTO PRODUCT (PPK, PNAME, PRICE, DETAIL, IMG) VALUES ((SELECT NVL(MAX(PPK),0)+1 FROM PRODUCT), ?, ?, '샘플입니다', ?)";
//	
//	public void crawlingStart() {
//		// 크롤링해서 ArrayList<ProductDTO> datas에 저장
//				List<ProductDTO> datas = new ArrayList<ProductDTO>();
//				final String weburl = "https://tonystreet.com/ctgr/subcategory_product_list.do?i_sCategorycd1=L01&i_sCategorynm1=%EA%B8%B0%EC%B4%88&i_sCategorycd2=L01M01&i_sCategorynm2=%EC%8A%A4%ED%82%A8%EC%BC%80%EC%96%B4";
//				Document doc = null;
//				try {
//					doc = Jsoup.connect(weburl).get();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				Elements elems = doc.select("li.prd_list");
//				Iterator<Element> itr = elems.iterator();
//				ProductDTO productDTO = null;
////				int count = 0; // 추가된 객체 개수를 세기 위한 변수
//				while (itr.hasNext() /* && count < 5 */) { // 5개까지만 추가
//					productDTO = new ProductDTO();
//					Element el = itr.next();
//					String name = el.select("span.prod-name").text();
//					String price1 = el.select("div.selling-price-wrap > em.price-after").text().replace(",", "").replace("원", "");
//					String img = el.select("img.over").attr("src");
//					int price = Integer.parseInt(price1);
//					System.out.println(name + " / " + price + " / " + img );
//					productDTO.setProductName(name)
//					productDTO.setProductPrice(price);
//					productDTO.setProductImg(img);
//					datas.add(productDTO);
////					count++;
//				}
//				
//				
//				
//				Connection conn = null;
//				PreparedStatement pstmt = null;
//
//				try {
//					conn = JDBCUtil.connect();
//					for (ProductDTO data : datas) {
//						pstmt = conn.prepareStatement(INSERT);
//						pstmt.setString(1, data.getPname());
//						pstmt.setInt(2, data.getPrice());
//						pstmt.setString(3, data.getImg());
//						int result = pstmt.executeUpdate();
//						if (result <= 0) {
//							System.out.println("SQL 구문 실패");
//						}
//					}
//				} catch (SQLException e) {
//					System.out.println("오류발생");
//					e.printStackTrace();
//				} finally {
//					JDBCUtil.disconnect(pstmt, conn);
//				}
//				System.out.println("크롤링 완료");
//	}
//}
package com.webWeavers.weaveGlow.biz.util;

