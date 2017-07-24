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

<div id="title">
HERE's what was added or updated!!
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
<input type="submit" value="Add new Film!">   

</form> 
  
Film Added:<br> 
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
    
                       <div id="tab">
                   <form action="FilmUpdated.do" method="POST">
                      Film ID  <input type="hidden" name="filmId" value="${fil.id}">${fil.id}<br>
                         	  <input type="text" name="title" placeholder="Enter edited title here!">${fil.title}<br>
                               <input type="text" name="description" placeholder="Enter new description">${fil.description}<br>
                               <input type="number" name="releaseyear" placeholder="Enter new year OF release">${fil.releaseyear}<br>
                               Rental Duration: <select name="rentalduration" id="slectboxid" >
												<option >1</option>
												<option >2</option>
												<option >3</option>
												<option >4</option>
												<option >5</option>
												<option >6</option>
												<option >7</option>
												</select>${fil.rentalduration } <br>
							<input type="text" name="rentalrate" placeholder="Enter rental rate in **.** format!!" style="width: 28%">${fil.rentalrate}<br>
							<input type="number" name="length" min="1" max="190" placeholder="Enter length of film" style="width: 30%">${fil.length}<br>
							<input type="number" name="replacementcost" min="0.00" max="100.00" placeholder="Enter replacement cost" style="width: 30%">${fil.replacementcost}<br>
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
                             <input type="submit" value="UpdateFilm" class="submit"/><br><br>
                                     </form>
						                     <form action="FilmDeleted.do" method="POST">
						                     <input type="hidden" name="filmId">
						                     <input type="submit" value="REMOVE Film">

                                      </form><br><br>
                                      
    
    </div>
    </c:forEach>
    </ul>
    
    </c:when>
    </c:choose>
  




</div>
</body>
</html>