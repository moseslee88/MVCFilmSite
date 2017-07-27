
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All Films</title>
</head>
<body>

	<div id="titleid">


		<c:choose>
			<c:when test="${! empty filmTitlekey  }">
				<ul>
					<c:forEach var="fi" items="${filmTitlekey }">
						<li><c:out value="${fi.id}" /></li>
						<li><c:out value="${fi.title}" /></li>
						<li>Brief Description: <c:out value="${fi.description}" /></li>
						<li>Year: <c:out value="${fi.releaseyear}" /></li>
						<li>Rental duration: <c:out value="${fi.rentalduration}"/></li>
                         <li>Rental Cost: <c:out value="${fi.rentalrate}"/></li>
						<li><c:out value="${fi.rating}" /> Rating</li>
						<li>Length of film: <c:out value="${fi.length}" /> minutes</li>
						<li>Replacement Cost of film: <c:out value="${fi.replacementcost}"/></li>
						<li><c:out value="${fi.specialfeatures}" /></li>
						<li>Cast = <c:out value="${fi.cast}" /></li>
						<br>
						
						

						<form action="FilmUpdated.do" method="POST">
							Film ID <input type="hidden" name="id" value="${fi.id}">${fi.id}<br>
							<input type="text" name="title" value="${fi.title}">${fi.title}<br>
							<input type="text" name="description"
								value="${fi.description}">${fi.description}<br>
							<input type="number" name="releaseyear"
								value="${fi.releaseyear}">${fi.releaseyear}<br>
							Rental Duration: <select name="rentalduration" id="slectboxid" value="${fi.rentalduration}">
								<option>1</option>
								<option>2</option>
								<option>3</option>
								<option>4</option>
								<option>5</option>
								<option>6</option>
								<option>7</option>
							</select>${fi.rentalduration } <br> 
						Rental Rate:	<input type="text" name="rentalrate" value="${fi.rentalrate}" style="width: 28%">${fi.rentalrate}<br> 
						Film Length:	<input type="number" name="length" min="1" max="190" value="${fi.length}" style="width: 30%">${fi.length}<br>
						Replacement Cost:	<input type="number" name="replacementcost" min="0.00" max="100.00" value="${fi.replacementcost}" style="width: 30%">${fi.replacementcost}<br>
							Rating: <select name="rating" id="title" value="${fi.rating }">
								<option>G</option>
								<option>PG</option>
								<option>PG13</option>
								<option>R</option>
								<option>NC17</option>
							</select><br> Special Features: <input type="checkbox"
								name="specialfeatures" VALUE="Trailers">Trailers <input
								type="checkbox" name="specialfeatures" VALUE="Commentaries">Commentaries
							<input type="checkbox" name="specialfeatures"
								VALUE="Deleted Scenes">Deleted Scenes <input
								type="checkbox" name="specialfeatures" VALUE="Behind The Scenes"
								CHECKED>Behind the Scenes <br> <input type="submit"
								value="UpdateFilm" class="submit" /><br>
							<br>


						</form>
						<form action="FilmDeleted.do" method="POST">
							${fi.id }<br>
							<input type="text" name="filmId" placeholder="Enter film's ID above to delete this film!" style="width: 38%">
							<input type="submit" value="REMOVE Film">

						</form>
						<br>
						<br>



					</c:forEach>
				</ul>

			</c:when>
		</c:choose>






	</div>
</body>
</html>