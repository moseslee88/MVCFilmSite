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


STUFF was deleted/Updated here from database!!

   <c:choose>
  <c:when test="${filmlist != null}">
   <ul>
  <c:forEach var="fil" items="${filmlist }">
    <li>FilmId = <c:out value="${fil.id}"/></li>
    <li><c:out value="${fil.title}"/></li>
    <li>Description: <c:out value="${fil.description}"/></li>
    <li>Year: <c:out value="${fil.releaseyear}"/></li>
    <li>Rental Duration: <c:out value="${fil.rentalduration}"/></li>
    <li>Rental Rate: <c:out value="${fil.rentalrate}"/></li>
    <li>Length of film: <c:out value="${fil.length}"/></li>
    <li>Replacement cost: <c:out value="${fil.replacementcost}"/></li>
    <li><c:out value="${fil.rating}"/> Rating</li>
    <li>Spec. Features: <c:out value="${fil.specialfeatures}"/></li><br><br>
    </c:forEach>
    </ul>
    </c:when>
    </c:choose>
    


    
 <br><br><h5>The film you updated was ... "${film}"</h5>


</div>
</body>
</html>