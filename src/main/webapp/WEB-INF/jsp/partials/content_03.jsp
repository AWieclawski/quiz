<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="left">

	<div class="description">

		<h1>Step Third</h1>
		<h3>Quiz building</h3>
		<p>Select the test from among those available in the database.</p>
		<p>
			Selected test type: <strong>${selectedTestType.testTypeName}</strong>  
		</p>
		<p>
			Selected difficulty level: <strong>${selectedDifficultyLevel.difficultyLevelName}</strong>  
		</p>

	</div>

</div>

<div id="right">

	<h1>Tests:</h1>


	<form action="/test/thirdstep" method="post">

		<table>

			<col width="10">
<!--  
					<thead>
				<tr>
					<th colspan=2><input type="submit" value="Confirm&NextStep">
						<input type="reset" value="Reset"></th>
				</tr>
			</thead>	 
-->
			<tbody>
				<c:forEach items="${tests}" var="test">
					<tr>
						<td colspan=1><input type="radio" name="test_Id"
							value="${test.testId}"></td>
						<td>${test.testName}</td>
					</tr>
				</c:forEach>

			</tbody>

		</table>

	</form>

</div>