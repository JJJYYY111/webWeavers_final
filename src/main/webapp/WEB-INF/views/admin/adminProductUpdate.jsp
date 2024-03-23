<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1" , viewport-fit=cover" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <script src="https://cdn.ckeditor.com/ckeditor5/29.1.0/classic/ckeditor.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicon.png" />
    <title>상품수정</title>
    <!-- This page plugin CSS -->
    <link href="assets/extra-libs/datatables.net-bs4/css/dataTables.bootstrap4.css" rel="stylesheet" />
    <!-- Custom CSS -->
    <link href="dist/css/style.min.css" rel="stylesheet" />
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
    </style>

</head>

<body>
    <!-- ============================================================== -->
    <!-- Preloader - style you can find in spinners.css -->
    <!-- ============================================================== -->
    <div class="preloader">
        <div class="lds-ripple">
            <div class="lds-pos"></div>
            <div class="lds-pos"></div>
        </div>
    </div>
    <!-- ============================================================== -->
    <!-- Main wrapper - style you can find in pages.scss -->
    <!-- ============================================================== -->
    <div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
        data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
        <!-- ============================================================== -->
        <!-- Topbar header - style you can find in pages.scss -->
        <!-- ============================================================== -->
        <header class="topbar" data-navbarbg="skin6">
            <nav class="navbar top-navbar navbar-expand-md">
                <div class="navbar-header" data-logobg="skin6">
                    <!-- This is for the sidebar toggle which is visible on mobile only -->
                    <a class="nav-toggler waves-effect waves-light d-block d-md-none" href="javascript:void(0)"><i
                            class="ti-menu ti-close"></i></a>
                    <!-- ============================================================== -->
                    <!-- Logo -->
                    <!-- ============================================================== -->
                    <div class="navbar-brand">
                        <!-- Logo icon -->
                        <a href="index.html">
                            <b class="logo-icon">
                                <!-- Dark Logo icon -->
                                <img src="assets/images/logo_small.png" width=40; alt="homepage" class="dark-logo" />
                                <!-- Light Logo icon -->
                                <img src="assets/imageslogo_small.png" alt="homepage" class="light-logo" />
                            </b>
                            <!--End Logo icon -->
                            <!-- Logo text -->
                            <span class="logo-text">
                                <!-- dark Logo text -->
                                <img src="assets/images/weaveGlow_logo1.png" width=165; alt="homepage"
                                    class="dark-logo" />
                                <!-- Light Logo text -->
                                <img src="assets/images/light-logo 1.png" class="light-logo" alt="homepage" />
                            </span>
                        </a>
                    </div>
                    <!-- ============================================================== -->
                    <!-- End Logo -->
                    <!-- ============================================================== -->
                    <!-- ============================================================== -->
                    <!-- Toggle which is visible on mobile only -->
                    <!-- ============================================================== -->
                    <a class="topbartoggler d-block d-md-none waves-effect waves-light" href="javascript:void(0)"
                        data-toggle="collapse" data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><i
                            class="ti-more"></i></a>
                </div>
                <!-- ============================================================== -->
                <!-- End Logo -->
                <!-- ============================================================== -->
                <div class="navbar-collapse collapse" id="navbarSupportedContent">
                    <!-- ============================================================== -->
                    <!-- toggle and nav items -->
                    <!-- ============================================================== -->
                    <ul class="navbar-nav float-left mr-auto ml-3 pl-1">
                        <!-- Notification -->
                        <li class="nav-item dropdown">
                            <div class="dropdown-menu dropdown-menu-left mailbox animated bounceInDown">
                                <ul class="list-style-none">
                                    <li>
                                        <a class="nav-link pt-3 text-center text-dark" href="javascript:void(0);">
                                            <strong>Check all notifications</strong>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                    <!-- ============================================================== -->
                    <!-- Right side toggle and nav items -->
                    <!-- ============================================================== -->
                    <ul class="navbar-nav float-right">
                        <!-- ============================================================== -->
                        <!-- User profile and search -->
                        <!-- ============================================================== -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="javascript:void(0)" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">
                                <img src="assets/images/weaveglow_logo_small1.jpg" alt="user" class="rounded-circle"
                                    width="40">
                                <span class="ml-2 d-none d-lg-inline-block"><span style="color:black;">Web</span> <span
                                        class="text-dark">weavers</span> <i data-feather="chevron-down" class="svg-icon"
                                        style="color: black;"></i></span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-sm-right ">
                                <div class="p-2"> <a href="javascript:void(0)" class="btn btn-sm btn-info"
                                        style="width: 100%;"> Logout
                                    </a> </div>
                            </div>
                        </li>
                        <!-- ============================================================== -->
                        <!-- User profile and search -->
                        <!-- ============================================================== -->
                    </ul>
                </div>
            </nav>
        </header>
        <!-- ============================================================== -->
        <!-- End Topbar header -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
      <common:adminSideBar />
        <!-- ============================================================== -->
        <!-- End Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Page wrapper  -->
        <!-- ============================================================== -->
        <div class="page-wrapper">
            <!-- ============================================================== -->
            <!-- Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-7 align-self-center">
                        <!-- <h1 class="page-title text-truncate text-dark font-weight-medium mb-1" >
                상품 목록
              </h1> -->
                    </div>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- End Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Container fluid  -->
            <!-- ============================================================== -->
            <div class="container-fluid">
                <!-- ============================================================== -->
                <!-- Start Page Content -->
                <!-- ============================================================== -->
                <!-- basic table -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <h1 class="card-title">상품 수정</h1>
                                <div class="row">
                                    <div class="col-12">
                                        <div class="card">
                                            <div class="card-body">
                                                <div class="table-responsive">
                                                    <form>
                                                        <div class='cur-row' style="color:#000000">
                                                            <div>상품코드</div>
                                                            <div class="col-md-5">
                                                                <input type="text" class="form-control"
                                                                    placeholder="값 받아오기" readonly>
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <div class='cur-row' style="color:#000000">
                                                            <div>등록일</div>
                                                            <div class="col-md-5" style="margin-left: 15px;">
                                                                <input type="text" class="form-control"
                                                                    placeholder="값 받아오기" readonly>
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <div class='cur-row' style="color:#000000; display: flex; ">
                                                            <div style="width: 7%;">카테고리</div>
                                                            <div style="margin-right: 1%; width: 100%;">
                                                                <input type='radio' class='category' name='category'
                                                                    value='skin'>스킨/로션</input>
                                                                <input type='radio' class='category' name='category'
                                                                    value='cleanging'
                                                                    style="margin-left: 1%;">클렌징</input>
                                                                <input type='radio' class='category' name='category'
                                                                    value='mask' style="margin-left: 1%;">마스크/팩</input>
                                                            </div>
                                                        </div>

                                                        <br>
                                                        <div>
                                                            <div class='cur-row' style="color:#000000; display: flex; ">
                                                                <div style="width: 8%;">세부카테고리
                                                                </div>
                                                                <div class="sub-category"></div>
                                                                <!-- <div style="margin-left: 1%; width: 100%;">
                                                                    <input type='radio' name='subCategory' value='skin'
                                                                        checked>스킨</input>
                                                                    <input type='radio' name='subCategory'
                                                                        value='cleanging'
                                                                        style="margin-left: 1%;">로션/에멀젼</input>
                                                                    <input type='radio' name='subCategory' value='mask'
                                                                        style="margin-left: 1%;">에센스/세럼/앰플</input>
                                                                    <input type='radio' name='subCategory' value='mask'
                                                                        style="margin-left: 1%;">크림</input>
                                                                    <input type='radio' name='subCategory' value='mask'
                                                                        style="margin-left: 1%;">아이케어</input>
                                                                    <input type='radio' name='subCategory' value='mask'
                                                                        style="margin-left: 1%;">미스트/부스터</input>
                                                                    <input type='radio' name='subCategory' value='mask'
                                                                        style="margin-left: 1%;">에센스/세럼/앰플</input>
                                                                    <input type='radio' name='subCategory' value='mask'
                                                                        style="margin-left: 1%;">세트</input>
                                                                </div> -->

                                                                <!--  <div style="margin-left: 1%; width: 100%;">
                                                                    <input type='radio' name='subCategory' value='skin'
                                                                        checked>클렌징폼</input>
                                                                    <input type='radio' name='subCategory'
                                                                        value='cleanging'
                                                                        style="margin-left: 1%;">클렌징크림/로션/워터</input>
                                                                    <input type='radio' name='subCategory' value='mask'
                                                                        style="margin-left: 1%;">클렌징젤/오일/티슈</input>
                                                                    <input type='radio' name='subCategory' value='mask'
                                                                        style="margin-left: 1%;">립,아이 리무버</input>
                                                                </div> -->
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <div class='cur-row' style="color:#000000; display: flex;">
                                                            <div>상품명</div>
                                                            <div class="col-md-5" style="margin-left: 15px;">
                                                                <input type="text" class="form-control"
                                                                    placeholder="ex) 촉촉한 스킨">
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <div class='cur-row' style="color:#000000; display: flex;">
                                                            <div>판매가</div>
                                                            <div class="col-md-5" style="margin-left: 15px;">
                                                                <input type="text" class="form-control"
                                                                    placeholder="ex) 25,000">
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <div class='cur-row' style="color:#000000">
                                                            <div>등록일</div>
                                                            <div class="col-md-5" style="margin-left: 15px;">
                                                                <input type="text" class="form-control"
                                                                    placeholder="값 받아오기" readonly>
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <div class='cur-row' style="color:#000000; display: flex;">
                                                            <div>판매상태</div>
                                                            <div class="col-md-5">
                                                                <select class="custom-select mr-sm-2"
                                                                    id="registration-salestatus" style="color: black">
                                                                    <option value="1">판매중</option>
                                                                    <option value="2">판매완료</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <div class='cur-row' style="color:#000000; display: flex;">
                                                            <div>재고수량</div>
                                                            <div class="col-md-5">
                                                                <input type="text" class="form-control"
                                                                    placeholder="ex) 100">
                                                            </div>
                                                        </div>

                                                        <br>
                                                        <div class='cur-row' style="color:#000000; display: flex;">
                                                            <div>상세설명</div>
                                                        </div>
                                                        <br>
                                                        <div class="table-responsive">
                                                            <div id="classic"></div>
                                                            </td>
                                                            </tr>
                                                            <!-- end table-responsive-->
                                                            <div class="float-right" style="height: 50px;">
                                                                <br>
                                                                <button type="button" class="btn btn-primary"><i
                                                                        class="fas fa-check"></i>
                                                                    완료</button>
                                                                <button type="button" class="btn btn-primary"><i
                                                                        class="fas fa-check"></i>
                                                                    취소</button>
                                                            </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div> <!-- end card-body-->
                            </div>
                            <!--end card-->
                        </div>
                        <!--end col-->
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
    </div>
    <!--footer -->
    <!--==============================================================-->
    <footer class="footer text-center text-muted">
        Web Wevers <a href="http://koreaitsecurity.net/">WebWevers</a>
    </footer>
    <!-- ============================================================== -->
    <!--End footer-->
    <!--==============================================================-->
    </div>
    <!--==============================================================-->
    <!--End Page wrapper-->
    <!--==============================================================-->
    </div>
    <!--==============================================================-->
    <!--End Wrapper-->
    <!--==============================================================-->
    <!--End Wrapper-->
    <!--==============================================================-->
    <!--All Jquery-->
    <!--==============================================================-->
    <script src="assets/libs/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- apps -->
    <!-- apps -->
    <script src="dist/js/app-style-switcher.js"></script>
    <script src="dist/js/feather.min.js"></script>
    <!-- slimscrollbar scrollbar JavaScript -->
    <script src="assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
    <script src="assets/extra-libs/sparkline/sparkline.js"></script>
    <!--Wave Effects -->
    <!-- themejs -->
    <!--Menu sidebar -->
    <script src="dist/js/sidebarmenu.js"></script>
    <!--Custom JavaScript -->
    <script src="dist/js/custom.min.js"></script>
    <!--This page plugins -->
    <script src="assets/extra-libs/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="dist/js/pages/datatable/datatable-basic.init.js"></script>

    <script>
        ClassicEditor
            .create(document.querySelector('#classic'))
            .catch(error => {
                console.error(error);
            });
    </script>

    <script>
        //         // 라디오 버튼 클릭 시 서브카테고리 표시/숨김 함수
        //         function showSubCategories(category) {
        //             // 모든 서브카테고리 숨김
        //             /*   var subCategories = document.querySelectorAll('.sub-categories');
        //               subCategories.forEach(function (subCategory) {
        //                   subCategory.style.display = 'none';
        //               });
        //    */
        //             // 선택한 카테고리에 해당하는 서브카테고리 표시
        //             var selectedSubCategory = document.querySelector('.' + category);
        //             if (selectedSubCategory && category === 'skin') {
        //                 selectedSubCategory.style.display = 'block';
        //             }
        //         }

        //         // 페이지 로드 시 초기화
        //         document.addEventListener('DOMContentLoaded', function () {
        //             // 초기로드 시 '스킨' 서브카테고리 표시
        //             showSubCategories('skin');

        //            /*  // 라디오 버튼 클릭 시 해당하는 서브카테고리 표시
        //             var categoryRadios =
        //                 document.querySelectorAll('input[name="category"]');
        //             categoryRadios.forEach(function (radio) {
        //                 radio.addEventListener('click', function () {
        //                     showSubCategories(this.value);
        //                 });
        //             }); */

        //             // 세부 카테고리 라디오 버튼 클릭 시
        //             var subCategoryRadios =
        //                 document.querySelectorAll('input[name="subCategory"]');
        //             subCategoryRadios.forEach(function (radio) {
        //                 radio.addEventListener('click', function () {
        //                     // 여기에서 원하는 동작 추가
        //                     //컨트롤러에서 값 가져오는거 추가하기
        //                 });
        //             });
        //         });
    </script>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            var selectedCategory = "skin"; //컨트롤러에서 받을 값 적기!!!!
            console.log('로그');
            var selectedCategoryRadio = document.querySelector('input[name="category"][value="' + selectedCategory + '"]');
            console.log(selectedCategoryRadio);
            if (selectedCategoryRadio) {

                selectedCategoryRadio.checked = true; // 라디오 버튼을 선택 상태로 설정
                const subCategoryElement = document.querySelector('.sub-category');
                console.log(subCategoryElement);
                subCategoryElement.innerHTML = `
      <div style="width: 120%;">
        <input type='checkbox' name='subCategory' value='subskin' >스킨</input>
        <input type='checkbox' name='subCategory' value='lotion' style="margin-left: 1%;">로션/에멀젼</input>
        <input type='checkbox' name='subCategory' value='essence' style="margin-left: 1%;">에센스/세럼/앰플</input>
        <input type='checkbox' name='subCategory' value='cream' style="margin-left: 1%;">크림</input>
        <input type='checkbox' name='subCategory' value='eyecare' style="margin-left: 1%;">아이케어</input>
        <input type='checkbox' name='subCategory' value='mist' style="margin-left: 1%;">미스트/부스터</input>
        <input type='checkbox' name='subCategory' value='set' style="margin-left: 1%;">세트</input>
      </div>
    `;
                /* value랑 스킨을 값을 받아와야한다. for문 돌려서 출력해주기. */
                // 선택된 카테고리에 해당하는 서브카테고리 선택
                var selectedSubCategory = "subskin"; //컨트롤러에서 받을 값 적기!!!!
                console.log(selectedSubCategory);
                var selectedSubCategoryRadio = document.querySelector('input[name="subCategory"][value="' + selectedSubCategory + '"]');

                console.log('input[name="subCategory"][value="' + selectedSubCategory + '"]');
                if (selectedSubCategoryRadio) {
                    selectedSubCategoryRadio.checked = true; // 서브카테고리 라디오 버튼을 선택 상태로 설정
                }
            }
        });
    </script>
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
        <input type='checkbox' name='subCategory' value='subskin' checked>스킨</input>
        <input type='checkbox' name='subCategory' value='lotion' style="margin-left: 1%;">로션/에멀젼</input>
        <input type='checkbox' name='subCategory' value='essence' style="margin-left: 1%;">에센스/세럼/앰플</input>
        <input type='checkbox' name='subCategory' value='cream' style="margin-left: 1%;">크림</input>
        <input type='checkbox' name='subCategory' value='eyecare' style="margin-left: 1%;">아이케어</input>
        <input type='checkbox' name='subCategory' value='mist' style="margin-left: 1%;">미스트/부스터</input>
        <input type='checkbox' name='subCategory' value='set' style="margin-left: 1%;">세트</input>
      </div>
    `;
                    } else if (radio.value === 'cleanging') {
                        subCategoryElement.innerHTML = `
      <div style="width: 120%;">
        <input type='checkbox' name='subCategory' value='cleanging' >클렌징폼</input>
        <input type='checkbox' name='subCategory' value='cleansingcream' style="margin-left: 1%;">클렌징크림/로션/워터</input>
        <input type='checkbox' name='subCategory' value='cleansinggel' style="margin-left: 1%;">클렌징젤/오일/티슈</input>
        <input type='checkbox' name='subCategory' value='lib' style="margin-left: 1%;">립,아이 리무버</input>
      </div>
    `;
                    } else if (radio.value === 'mask') {
                        subCategoryElement.innerHTML = `
      <div style="width: 120%;">
        <input type='checkbox' name='subCategory' value='mask' >팩/마스크</input>
        <input type='checkbox' name='subCategory' value='massage' style="margin-left: 1%;">마사지/워시오프 팩</input>
        <input type='checkbox' name='subCategory' value='scrub' style="margin-left: 1%;">필링/스크럽</input>
        <input type='checkbox' name='subCategory' value='sheetmask' style="margin-left: 1%;">시트마스크</input>
        <input type='checkbox' name='subCategory' value='nosepack' style="margin-left: 1%;">코팩</input>
      </div>
    `;
                    }
                });
            });
        });
    </script>

</body>

</html>