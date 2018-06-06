<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${category.getName()}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>

<style>

    .thumbnail {
        position: relative;
        width: 40px;
        height: 40px;
        overflow: hidden;
    }

    .thumbnail-adv {
        position: relative;
        width: 150px;
        height: 150px;
        overflow: hidden;
    }

    .thumbnail-adv img {
        position: absolute;
        left: 50%;
        top: 50%;
        height: 100%;
        width: auto;
        -webkit-transform: translate(-50%, -50%);
        -ms-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
    }

    .thumbnail img {
        position: absolute;
        left: 50%;
        top: 50%;
        height: 100%;
        width: auto;
        -webkit-transform: translate(-50%, -50%);
        -ms-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
    }

    .thumbnail-adv img.portrait {
        width: 100%;
        height: auto;
    }

    .thumbnail img.portrait {
        width: 100%;
        height: auto;
    }
</style>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="/">Ads</a>

    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="/">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/add.jsp">Add advertisement</a>
        </li>
    </ul>
</nav>
<div class="container">
    <h2 class="text-primary" style="text-align:center"><c:out value="${category.getName()}"></c:out></h2>
    <br>
    <c:forEach items="${advertisements}" var="advertisement">
        <table class="table">
            <tbody>
            <tr>
                <td rowspan="5" width="20%">
                    <div class="thumbnail-adv">
                        <img src="${advertisement.getImage().getPath()}${advertisement.getImage().getName()}"
                             alt="Image"/>
                    </div>
                <td>Title: <a href="adv?id=${advertisement.getId()}"><c:out
                        value="${advertisement.getTitle()}"></c:out></a>
                </td>
                <td rowspan="5" width="20%">
                    Cost: <c:out value="${advertisement.getCost()}"></c:out>
                </td>
            </tr>
            <tr>
                <td>Category: <a href="category?id=${advertisement.getCategory().getId()}"><c:out
                        value="${advertisement.getCategory().getName()}"></c:out></a>
                </td>
            </tr>

            <tr>
                <td>Author: <a href="view?author=${advertisement.getAuthor().getName()}"><c:out
                        value="${advertisement.getAuthor().getName()}"></c:out></a>
                </td>
            </tr>
            <tr>
                <td>
                    Date: <fmt:formatDate value="${advertisement.getCreateDate()}" pattern="dd MMM"/>
                </td>
            </tr>
            <tr>
                <td>
                    <c:out value="${advertisement.getShortText()}"></c:out>
                </td>
            </tr>
            </tbody>
        </table>
    </c:forEach>
    <a href="javascript:history.back()">
        <button type="button" class="btn btn-primary ">Go back</button>
    </a><br><br>
    <c:if test="${total_pages gt 0}">
        <hr>
        <div class="row align-items-center">
            <table style="border-spacing: 10px 5px">
                <tr>
                    <c:forEach begin="1" end="${total_pages}" varStatus="loop">
                        <td>
                            <a href="/category?id=${category.getId()}&page=${loop.index}"><h5>${loop.index}</h5></a>
                        </td>
                    </c:forEach>
                </tr>
                </tbody>
            </table>
        </div>
    </c:if>
</div>
</body>
</html>

