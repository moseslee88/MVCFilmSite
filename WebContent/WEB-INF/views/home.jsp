  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
 <!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MVC Film Project</title>
</head>
<body>

 <form action="getTitle.do" method="get">
<input type="text" name="filmId">
<input type="submit" value="Look Up Film by id">

</form>


<div id="titleid">

<c:choose>  
  <c:when test="${filmTitle != null}">
    <h3>${filmTitle}</h3>
  </c:when>
</c:choose> 

<hr>

<form action="getKeyword.do" method="get">
<input type="text" name="filmkey">
<input type="submit" value="Look up Film by any keyword">



<c:set var="list" value="${filmTitlekey }" />
<c:choose>  
   <c:when test="${! empty filmTitlekey  }">  
  <ul>
  <c:forEach var="fil" items="${filmTitlekey }">
    <li><c:out value="${fil.title}"/></li>
    <li><c:out value="${fil.length}"/></li>
    <li><c:out value="${fil.rating}"/></li>
    <li><c:out value="${fil.description}"/></li>
    <li>Cast = <c:out value="${fil.cast}"/></li><br>
    </c:forEach>
    </ul>
   
  </c:when>
 
  <c:otherwise>
  <c:set var="list" value="${filmTitlekey }" />
  <c:if test='${!fn:contains(list, falsetitle)}'>
         <p> There are no results yet. Please try another keyword please. </p>
  
</c:if>
  </c:otherwise>
</c:choose>
</form>
</div>

<hr>

 <form action="JSPafterAddedFilm.jsp" id="title">  
<input type="submit" value="Click here for NEW film you wish to add!"><br>
</form> 




</body>
</html>