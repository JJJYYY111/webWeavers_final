<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">

<head>
</head>

<body>
        <!-- ============================================================== -->
        <header class="topbar" data-navbarbg="skin6">
            <nav class="navbar top-navbar navbar-expand-md">
                <div class="navbar-header" data-logobg="skin6">
                    <a class="nav-toggler waves-effect waves-light d-block d-md-none" href="javascript:void(0)"><i
                            class="ti-menu ti-close"></i></a>
                    <div class="navbar-brand">
                        <a href="adminDashboard">
                            <b class="logo-icon">
                                <!-- Dark Logo icon -->
                                <img src="/resources//admin/assets/images/logo_small.png" width=40; alt="homepage" class="dark-logo" />
                                <!-- Light Logo icon -->
                                <img src="/resources//admin/assets/imageslogo_small.png" alt="homepage" class="light-logo" />
                            </b>
                            <!-- Logo text -->
                            <span class="logo-text">
                                <!-- dark Logo text -->
                                <img src="/resources//admin/assets/images/weaveGlow_logo1.png" width=165; alt="homepage"
                                    class="dark-logo" />
                                <!-- Light Logo text -->
                                <img src="/resources//admin/assets/images/light-logo 1.png" class="light-logo" alt="homepage" />
                            </span>
                        </a>
                    </div>
                    <!-- End Logo -->
                    <!-- ============================================================== -->
                    <a class="topbartoggler d-block d-md-none waves-effect waves-light" href="javascript:void(0)"
                        data-toggle="collapse" data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><i
                            class="ti-more"></i></a>
                </div>
                <!-- End Logo -->
                <div class="navbar-collapse collapse" id="navbarSupportedContent">
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
                    <ul class="navbar-nav float-right">
                        <!-- ============================================================== -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="javascript:void(0)" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">
                                <img src="/resources//admin/assets/images/weaveglow_logo_small1.jpg" alt="user" class="rounded-circle"
                                    width="40">
                                <span class="ml-2 d-none d-lg-inline-block"><span style="color:black;">Web</span> <span
                                        class="text-dark">weavers</span> <i data-feather="chevron-down" class="svg-icon"
                                        style="color: black;"></i></span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-sm-right ">
                                <div class="p-2"> <a href="logout" class="btn btn-sm btn-info"
                                        style="width: 100%;"> Logout
                                    </a> </div>
                            </div>
                        </li>
                        <!-- ============================================================== -->
                    </ul>
                </div>
            </nav>
        </header>
        <!-- End Topbar header -->
        
        </body>
        </html>