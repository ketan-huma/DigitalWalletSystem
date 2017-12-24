<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html ng-app="wallet">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="lib/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    </head>

    <body ng-cloak>

        <%
            HttpSession httpSession = request.getSession();
            String userId = (String) httpSession.getAttribute("userId");
        %>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Digital Wallet System</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#/home">Home </a>
                </li>
                <li class="nav-item" ng-if="${userId == null || userId == ""}">
                    <a class="nav-link" href="#/register">Register</a>
                </li>
                <li class="nav-item" ng-if="${userId == null || userId == ""}">
                    <a class="nav-link" href="#/login">Login</a>
                </li>
                <li class="nav-item" ng-if="${!(userId == null || userId == "")}">
                    <a class="nav-link" href="#/cardDetails">My Wallet</a>
                </li>
                <li class="nav-item" ng-if="${!(userId == null || userId == "")}" ng-controller="loginController">
                    <a class="nav-link" ng-click="logout();" style="cursor: pointer">Logout</a>
                </li>
            </ul>
            <span ng-if="${!(userId == null || userId == "")}">Welcome ${userId}</span>
        </div>
    </nav>

    <ng-view></ng-view>

    <script src="lib/angular.js" type="text/javascript"></script>
    <script src="lib/angular-route.js" type="text/javascript"></script>
    <script src="app/walletApp.js" type="text/javascript"></script>
    <script src="lib/jquery-3.2.1.slim.min.js" type="text/javascript"></script>
    <script src="lib/popper.min.js" type="text/javascript"></script>
    <script src="lib/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
