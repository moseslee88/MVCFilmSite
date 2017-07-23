  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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


<c:choose>  
  <c:when test="${filmTitle != null}">
    <h3>${filmTitle}</h3>
  </c:when>
</c:choose> 

<hr>

<form action="getKeyword.do" method="get">
<input type="text" name="filmkey">
<input type="submit" value="Look up Film by any keyword">


<div id="titleid">
<c:choose>  
   <c:when test="${! empty filmTitlekey }">  
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
         <p> There are no results yet. Please try another keyword please. </p>
  </c:otherwise>
</c:choose>
</div>
</form>

 <form action="postTitle.do" method="post" id="title">  
<input type="text" name="title" placeholder="Enter title you wish to add!"><br>
<input type="text" name="description" placeholder="Describe the new film!"><br>
<input type="number" name="releaseyear" min="1900" max="2017" placeholder="Enter release year!" style="width: 20%"><br>
Rental Duration: <select name="rentalduration" id="slectboxid">
<option >1</option>
<option >2</option>
<option >3</option>
<option >4</option>
<option >5</option>
<option >6</option>
<option >7</option>
</select> <br>
<!--  <input type="number" name="rentalduration" min="1" max="8" placeholder="Enter rental duration(quantity 1 to 8)!" style="width: 30%">
<input type="text" name="filmrating" placeholder="Enter film rating dropd box!"> 
<input type="text" name="filmspecialfeatures" placeholder="Enter any special features!">  -->

<input type="text" name="rentalrate" placeholder="Enter rental rate in **.** format!!" style="width: 28%"><br>
<input type="number" name="length" min="1" max="190" placeholder="Enter length of film" style="width: 30%"><br>
<input type="number" name="replacementcost" min="0.00" max="100.00" placeholder="Enter replacement cost" style="width: 30%"><br>
Rating: <select name="rating" id="title">
<option >G</option>
<option >PG</option>
<option >PG13</option>
<option >R</option>
<option >NC17</option>
</select><br>
Special Features: <input type="checkbox" name="specialfeatures" VALUE="Trailers" >Trailers
<input type="checkbox" name="specialfeatures" VALUE="Commentaries" >Commentaries
<input type="checkbox" name="specialfeatures" VALUE="Deleted Scenes" >Deleted Scenes
<input type="checkbox" name="specialfeatures" VALUE="Behind The Scenes" CHECKED>Behind the Scenes
<br>
<input type="submit" value="Add new Film by user input">   

</form>


</body>
</html>