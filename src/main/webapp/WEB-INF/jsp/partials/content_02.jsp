<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="left">

	<div class="description">

		<h1>Step two</h1>

		<div class="scrollable">

			<h3>Quiz building</h3>
			<p>
				Selected test type: <strong>${selectedTestType.testTypeName}</strong>
			</p>
			<p>Now, select the difficulty level from among those available in
				the database.</p>

		</div>

	</div>

</div>

<div id="right">

	<h1>Difficulty levels:</h1>

	<div class="scrollable">

		<form action="/difficultylevel/secondstep" method="post">

			<table>

				<col width="10">

				<thead>
					<tr>
						<th colspan=2><input type="submit" value="Confirm&NextStep">
							<input type="reset" value="Reset"></th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${difficultyLevels}" var="difficultyLevel">
						<tr>
							<td colspan=1><input type="radio" name="difficultyLevel_Id"
								value="${difficultyLevel.difficultyLevelId}"></td>
							<td>${difficultyLevel.difficultyLevelName}</td>
						</tr>
					</c:forEach>

				</tbody>

			</table>

		</form>

	</div>

</div>