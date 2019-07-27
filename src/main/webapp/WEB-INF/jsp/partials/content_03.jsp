<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="left">

	<h1>Step two</h1>
	<h3>Quiz building</h3>
	<p>Select the difficulty level from among those available in the
		database.</p>
	<p>Selected Test Type: <strong>${selectedTestType.testTypeName}</strong></p>

</div>

<div id="right">

	<h1>Difficulty levels:</h1>

	<form action="/difficultylevel/secondstep" method="post">

		<table>

			<thead>
				<tr>
					<th colspan="2"></th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${difficultyLevels}" var="difficultyLevel">
					<tr>
						<td colspan=2><label>${difficultyLevel.difficultyLevelName}
								<input type="radio" name="difficultyLevel_Id"
								value="${difficultyLevel.difficultyLevelId}">
						</label></td>
					</tr>
				</c:forEach>
				<tr>
<!--  					<td><input type="submit" value="Confirm&NextStep"></td>
					<td><input type="reset" value="Reset"></td> 					-->
				</tr>
			</tbody>

		</table>

	</form>
	

</div>