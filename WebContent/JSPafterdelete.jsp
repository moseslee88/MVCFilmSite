  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of Films</title>
</head>
<body>
<div id=deleted>


STUFF was deleted here form database!!

   <c:choose>
  <c:when test="${filmlist != null}">
   <ul>
  <c:forEach var="fil" items="${filmlist }">
    <li>FilmId = <c:out value="${fil.id}"/></li>
    <li><c:out value="${fil.title}"/></li>
    <li><c:out value="${fil.description}"/></li>
    <li><c:out value="${fil.releaseyear}"/></li>
    <li><c:out value="${fil.rentalduration}"/></li>
    <li><c:out value="${fil.rentalrate}"/></li>
    <li><c:out value="${fil.length}"/></li>
    <li><c:out value="${fil.replacementcost}"/></li>
    <li><c:out value="${fil.rating}"/></li>
    <li><c:out value="${fil.specialfeatures}"/></li><br><br>
    </c:forEach>
    </ul>
    </c:when>
    </c:choose>


    <!--  <form action="getTitle.do" method="get">
<input type="text" name="filmId">
<input type="submit" value="Look Up Film by id">

</form>


<c:choose>  
  <c:when test="${filmTitle != null}">
    <h3>${filmTitle}</h3>
  </c:when>
</c:choose> -->

</div>
</body>
</html>