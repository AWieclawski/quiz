<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="scrollable">

	<table>

		<thead>

			<tr>

				<th>No question</th>

				<th>Selected answer</th>

				<th>Content of answer</th>

				<th>Result</th>

			</tr>

		</thead>

		<tbody>

			<c:forEach items="${results}" var="thisTestRecapitulation">

				<tr>

					<td>${thisTestRecapitulation.questionNumberInRecapitulation}</td>

					<td>${thisTestRecapitulation.selectedAnswerInRecapitulation}</td>

					<td style="text-align: left">${thisTestRecapitulation.selectedAnswerContentInRecapitulation}</td>

					<td>${thisTestRecapitulation.userSelectionsVerificationResult}</td>

				</tr>

			</c:forEach>

		</tbody>

	</table>

	<form action="/results/finish" method="post">

		<button type="submit" name="action" value="save">Save results and exit to main menu</button>

	</form>


</div>