<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib tagdir="/WEB-INF/tags" prefix="common" %>
          <!DOCTYPE html>
          <html dir="ltr" lang="en">

          <head>
            <meta charset="utf-8" />
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <!-- Tell the browser to be responsive to screen width -->
            <meta name="viewport" content="width=device-width, initial-scale=1" , viewport-fit="cover" />
            <meta name="description" content="" />
            <meta name="author" content="" />
            <script src="https://cdn.ckeditor.com/ckeditor5/29.1.0/classic/ckeditor.js"></script>
            <!-- Favicon icon -->
            <link rel="icon" type="/admin/assets/image/png" sizes="16x16" href="/admin/assets/images/favicon.png" />
            <title>상품등록</title>
            <!-- This page plugin CSS -->
            <link href="/admin/assets/extra-libs/datatables.net-bs4/css/dataTables.bootstrap4.css" rel="stylesheet" />
            <!-- Custom CSS -->
            <link href="/admin/dist/css/style.min.css" rel="stylesheet" />

            <script src="https://cdn.ckeditor.com/ckeditor5/12.4.0/classic/ckeditor.js"></script>
            <script src="https://ckeditor.com/apps/ckfinder/3.5.0/ckfinder.js"></script>
            <style>
              .ck.ck-editor {
                max-width: 700px;
              }

              .ck-editor__editable {
                min-height: 300px;
              }
            </style>

            <style>
              .input {
                color: #000000;
                background-color: #ffffff4d;
                /* 원하는 배경색으로 변경 */
                border: 3px solid #ffffff;
              }

              form .cur-row {
                display: flex;
                align-items: center;
                gap: 10px;
              }
            </style>

            <style>
              ::placeholder {
                color: #000000;
                /* Placeholder 색상 변경 */
                font-weight: 100;
              }
            </style>

            <style>
              .table-responsive {
                overflow-x: hidden;
                /* 가로 스크롤 숨김 */
                overflow-y: auto;/
              }

              /* style.css */

              /* label 스타일 조정 */
              .button {
                display: flex;
                justify-content: center;
              }

              label {
                cursor: pointer;
                font-size: 1em;
              }

              #chooseFile {
                visibility: hidden;
              }
            </style>


          </head>

          <body>
            <!-- =======로딩========= -->
            <div class="preloader">
              <div class="lds-ripple">
                <div class="lds-pos"></div>
                <div class="lds-pos"></div>
              </div>
            </div>
            <!-- =========테마======== -->
            <div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6"
              data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed"
              data-boxed-layout="full">
              <!-- ======헤더====== -->
              <common:adminTopBar />
              <!-- ===========사이드바============ -->
              <common:adminSideBar />
              <!-- ============================================================== -->
              <div class="page-wrapper">
                <!-- ============================================================== -->
                <div class="page-breadcrumb">
                  <div class="row">
                    <div class="col-7 align-self-center">
                    </div>
                  </div>
                </div>
                <!-- ============================================================== -->
                <div class="container-fluid" style="margin-top: -50px;">
                  <!-- ============================================================== -->
                  <div class="row">
                    <div class="col-12">
                      <div class="card">
                        <div class="card-body">
                          <h1 class="card-title">상품 등록</h1>
                          <div class="row">
                            <div class="col-12">
                              <div class="card">
                                <div class="card-body">
                                  <div class="table-responsive">

                                    <form id="myForm" action="adminProductInsert" method="POST" enctype="multipart/form-data">
                                      <div class='cur-row' style="color:#000000; display: flex; ">
                                        <div style="width: 7%;">카테고리</div>
                                        <div style="margin-right: 1%; width: 100%;">
                                          <input type='radio' class='category' name='categoryName' value='skin'>스킨/로션
                                          <input type='radio' class='category' name='categoryName' value='cleanging'
                                            style="margin-left: 1%;">클렌징
                                          <input type='radio' class='category' name='categoryName' value='mask'
                                            style="margin-left: 1%;">마스크/팩
                                        </div>
                                      </div>
                                      <br>
                                      <div>
                                        <div class='cur-row' style="color:#000000; display: flex; ">
                                          <div style="width: 7%;">세부카테고리
                                          </div>
                                          <div class="sub-category"></div>
                                          <br>
                                        </div>
                                      </div>
                                      <br>
                                      <div class='cur-row' style="color:#000000; display: flex;">
                                        <div>상품명</div>
                                        <div class="col-md-5" style="margin-left: 15px;">
                                          <input type="text" class="form-control" name="productName"
                                            placeholder="ex) 촉촉한 스킨" required>
                                        </div>
                                      </div>
                                      <br>
                                      <div class='cur-row' style="color:#000000; display: flex;">
                                        <div>판매가</div>
                                        <div class="col-md-5" style="margin-left: 15px;">
                                          <input type="number" min="1" class="form-control" name="productPrice" placeholder="ex) 25,000" required>
                                        </div>
                                      </div>
                                      <br>
                                      <div class='cur-row' style="color:#000000; display: flex;">
                                        <div>판매상태</div>
                                        <div class="col-md-5">
                                          <select class="custom-select mr-sm-2" id="registration-salestatus"
                                            name="productStatus" style="color: black" required>
                                            <option value="1">판매중</option>
                                            <option value="2">판매완료</option>
                                          </select>
                                        </div>
                                      </div>
                                      <br>
                                      <div class='cur-row' style="color:#000000; display: flex;">
                                        <div>재고수량</div>
                                        <div class="col-md-5">
                                          <input type="number" min="0" max="9999" class="form-control" name="productQuantity"
                                            placeholder="ex) 100" required>
                                        </div>
                                      </div>

                                      <br>


                                      <br>
                                      <div class='cur-row' style="color:#000000; display: flex;">
                                        <div>대표이미지</div>
                                      </div>
                                      <div class="button">
                                        <label for="chooseFile"
                                          style="margin-right: auto; color: black; background-color: lightgray;">
                                         [이미지 업로드]
                                        </label>
                                      </div>
                                      <input type="file" id="chooseFile" name="file" accept="image/*"
                                        onchange="loadFile(this)">

                                      <div class="fileInput">
                                        <p>파일명 : </p>
                                        <p id="fileName"></p>
                                      </div>
                                      <div class="buttonContainer">

                                        <div class="image-show" id="image-show"></div>

                                        <!-- 이미지 삭제 버튼 추가 -->
                                        <div class="deleteButton" id="deleteButton" style="color: black;"
                                          onclick="deleteImage()">
                                          <label
                                            style="margin-right: auto; color: black; background-color: lightgray;">[사진등록취소]</label>
                                        </div>
                                        <br>

                                        <div class="float-right" style="height: 50px;">
                                          <br>

                                        </div>
                                      </div>


                                      <br>
                                      <br>
                                      <div class='cur-row' style="color:#000000; display: flex;">
                                        <div>상세설명</div>
                                      </div>
                                      <br>

                                      <div class="table-responsive" style="width: 100%">
                                        <div id="editor"></div>
                                      </div>
                                      <textarea id="editorContent" name="productDetailImg"
                                        style="display: none;"></textarea>

									<!-- ck에디터 사진 등록 -->
                                      <script>
                                        let myEditor;

                                        ClassicEditor
                                          .create(document.querySelector('#editor'), {
                                            ckfinder: {
                                              uploadUrl: '/admin/upload'
                                            }
                                          })
                                          .then(editor => {
                                            myEditor = editor;
                                            console.log('Editor was initialized');
                                          })
                                          .catch(error => {
                                            console.error(error);
                                          });
                                        // 폼 제출 시 CKEditor의 내용을 hidden textarea에 복사
                                        document.getElementById('myForm').addEventListener('submit', function () {
                                          document.getElementById('editorContent').value = myEditor.getData();
                                        });
                                      </script>
                                      <br>
                                      <div style="text-align: right;">
                                        <button type="submit" class="btn btn-primary"><i class="fas fa-check"></i>
                                          완료</button>
                                        <button class="btn btn-primary"><i class="fas fa-check"></i> 취소</button>
                                      </div>




                                    </form>
                                  </div>
                                  
                                  		
									<!-- input 태그 사진 넣는 js  -->
                                  <script>
                                    function loadFile(input) {
                                      var file = input.files[0]; // 선택된 파일 가져오기

                                      // 미리 만들어 놓은 div에 text(파일 이름) 추가
                                      var name = document.getElementById('fileName');
                                      name.textContent = file.name;

                                      // 새로운 이미지 div 추가
                                      var newImage = document.createElement("img");
                                      newImage.setAttribute("class", 'img');
                                      newImage.setAttribute("id", 'uploadedImage'); // 새로운 이미지에 ID 추가

                                      // 이미지 source 가져오기
                                      newImage.src = URL.createObjectURL(file);

                                      newImage.style.width = "70%";
                                      newImage.style.height = "70%";
                                      newImage.style.objectFit = "contain";

                                      // 이미지를 image-show div에 추가
                                      var container = document.getElementById('image-show');
                                      container.innerHTML = ""; // 이미지가 추가되기 전에 이미지를 초기화하여 이전 이미지를 삭제
                                      container.appendChild(newImage);
                                    };

                                    function deleteImage() {
                                      var image = document.getElementById('uploadedImage'); // 추가된 이미지 가져오기
                                      if (image) {
                                        image.remove(); // 이미지 삭제
                                        document.getElementById('fileName').textContent = ""; // 파일 이름 초기화
                                        document.getElementById('chooseFile').value = ""; // 파일 선택 input 초기화
                                      }
                                    }
                                  </script>



                                </div>
                              </div>
                            </div>
                          </div>
                        </div> <!-- card-body-->
                      </div> <!--  card-->
                    </div> <!-- col-->
                  </div>
                </div>
              </div>
            </div>
            <!-- footer -->
            <footer class="footer text-center text-muted">
              Web Wevers <a href="http://koreaitsecurity.net/">WebWevers</a>
            </footer>
            <!-- All Jquery -->
            <script src="/admin/assets/libs/jquery/dist/jquery.min.js"></script>
            <!-- Bootstrap tether Core JavaScript -->
            <script src="/admin/assets/libs/popper.js/dist/umd/popper.min.js"></script>
            <script src="/admin/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
            <!-- apps -->
            <!-- apps -->
            <script src="/admin/dist/js/app-style-switcher.js"></script>
            <script src="/admin/dist/js/feather.min.js"></script>
            <!-- slimscrollbar scrollbar JavaScript -->
            <script src="/admin/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
            <script src="/admin/assets/extra-libs/sparkline/sparkline.js"></script>
            <!--Wave Effects -->
            <!-- themejs -->
            <!--Menu sidebar -->
            <script src="/admin/dist/js/sidebarmenu.js"></script>
            <!--Custom JavaScript -->
            <script src="/admin/dist/js/custom.min.js"></script>
            <!--This page plugins -->
            <script src="/admin/assets/extra-libs/datatables.net/js/jquery.dataTables.min.js"></script>
            <script src="/admin/dist/js/pages/datatable/datatable-basic.init.js"></script>

			<!-- 카테고리 버튼 스크립트  -->
            <script>
              // JavaScript 코드
              document.addEventListener("DOMContentLoaded", function () {


                // 라디오 버튼 요소들을 가져옴
                const categoryRadios = document.querySelectorAll('.category');

                // 라디오 버튼에 대한 이벤트 리스너 추가
                categoryRadios.forEach(function (radio) {
                  radio.addEventListener('click', function () {
                    // 선택된 라디오 버튼의 값에 따라 세부카테고리 내용 변경
                    const subCategoryElement = document.querySelector('.sub-category');
                    if (radio.value === 'skin') {
                      subCategoryElement.innerHTML = `
<div style="width: 120%;">
<input type='checkbox' name='subCategoryName' value='1' >스킨</input>
<input type='checkbox' name='subCategoryName' value='2' style="margin-left: 1%;">로션/에멀젼</input>
<input type='checkbox' name='subCategoryName' value='3' style="margin-left: 1%;">에센스/세럼/앰플</input>
<input type='checkbox' name='subCategoryName' value='4' style="margin-left: 1%;">크림</input>
<input type='checkbox' name='subCategoryName' value='5' style="margin-left: 1%;">아이케어</input>
<input type='checkbox' name='subCategoryName' value='6' style="margin-left: 1%;">미스트/부스터</input>
<input type='checkbox' name='subCategoryName' value='7' style="margin-left: 1%;">세트</input>
</div>
`;
                    } else if (radio.value === 'cleanging') {
                      subCategoryElement.innerHTML = `
<div style="width: 120%;">
<input type='checkbox' name='subCategoryName' value='8' >클렌징폼</input>
<input type='checkbox' name='subCategoryName' value='9' style="margin-left: 1%;">클렌징크림/로션/워터</input>
<input type='checkbox' name='subCategoryName' value='10' style="margin-left: 1%;">클렌징젤/오일/티슈</input>
<input type='checkbox' name='subCategoryName' value='11' style="margin-left: 1%;">립,아이 리무버</input>
</div>
`;
                    } else if (radio.value === 'mask') {
                      subCategoryElement.innerHTML = `
<div style="width: 120%;">
<input type='checkbox' name='subCategoryName' value='12' style="margin-left: 1%;">마사지/워시오프 팩</input>
<input type='checkbox' name='subCategoryName' value='13' style="margin-left: 1%;">필링/스크럽</input>
<input type='checkbox' name='subCategoryName' value='14' style="margin-left: 1%;">시트마스크</input>
<input type='checkbox' name='subCategoryName' value='15' style="margin-left: 1%;">코팩</input>
</div>
`;
                    }
                  });
                });
              });
            </script>



          </body>

          </html>