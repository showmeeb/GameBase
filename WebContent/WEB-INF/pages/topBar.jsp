<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>GameBase遊戲基地</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            text-decoration: none;
        }

        .topBar {
            position: fixed;
            width: 100%;
            height: 7%;
            padding: 5px;
            background-color: burlywood;
        }

        .logo {
            float: left;
            width: 10%;
            height: 100%;
            margin-right: 2%;
            background-color: chartreuse;
        }

        .searchBar {
            float: left;
            width: 30%;
            height: 100%;
            background-color: chartreuse;
            margin-right: 2%;
        }

        .searchForm {
            margin: 2px;
            box-sizing: border-box;
            -moz-box-sizing: border-box;
        }

        .searchInput {
            width: 70%;
            height: 100%;
            box-sizing: border-box;
            -moz-box-sizing: border-box;
            font-size: 30px;
            padding: 10px;
            font-family: 微軟正黑體;
        }

        .searchButton {
            width: 10%;
            height: 100%;
        }

        .signpost {
            float: left;
            font-size: 50px;
            background-color: chartreuse;
            width: 20%;
            height: 100%;
        }

        .loginBar {
            float: right;
            margin-right: 50px;
            font-size: 50px;

            background-color: chartreuse;
            width: 20%;
            height: 100%;
        }
    </style>
</head>

<body>
    <nav class="topBar">
        <div class="logo">LOGO</div>
        <div class="searchBar">
            <form action="XXSearchController" class="searchForm">
                <select name="looking">
                    <option value="forProduct">找商品</option>
                    <option value="foForumr">找論壇</option>
                </select>
                <input class="searchInput" type="text" name="keyword">
                <input type="submit" value="搜尋" class="searchButton">
            </form>
        </div>
        <div class="signpost"><a href="aa">論壇</a>|<a href="aa">商城</a></div>
        <div class="loginBar">
            <div><a href="yahoo">註冊</a> | <a href="yahoo">登入</a></div>
        </div>
    </nav>

</body>
</html>