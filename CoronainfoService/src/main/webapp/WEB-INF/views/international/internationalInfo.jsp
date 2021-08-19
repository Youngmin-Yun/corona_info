<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/assets/css/internationalInfo.css">
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="/assets/js/internationalInfo.js"></script>
    <title>국제 코로나 상황</title>
</head>

<body>
    <div class="container">
        <%@include file="/WEB-INF/views/includes/menu.jsp"%>
        <div class="right_area">
            <div class="content_head">
                <select id="continent_select">
                    <option value="아시아">아시아</option>
                    <option value="중동">중동</option>
                    <option value="유럽">유럽</option>
                    <option value="아메리카">아메리카</option>
                    <option value="오세아니아">오세아니아</option>
                    <option value="아프리카">아프리카</option>
                    <option value="기타">기타</option>
                </select>
            </div>
            <table class="country_table">
                <thead>
                    <tr>
                        <td>국가명</td>
                        <td>누적 확진자 수</td>
                        <td>누적 사망자 수</td>
                        <td>기준일</td>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
</body>

</html>