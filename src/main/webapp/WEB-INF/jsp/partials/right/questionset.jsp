<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="scrollable">

	<form action="/questionset/exam" method="post">

		<table>

			<thead>

				<tr>
					<th colspan="1">${currentQuestionSet.question}?</th>
				</tr>

			</thead>

			<tbody>

				<c:set var="counter" value="0" />

				<c:forEach items="${currentQuestionSet.arrayOfAnswers}"
					var="thisQuestionAnswersArray">

					<tr>

						<td colspan="1"><input type="radio" class="button"
							name="submittedAnswer_Id"
							id="<c:out value="${thisQuestionAnswersArray}"  />"
							value="${counter+1}"></input> <label
							for="<c:out value="${thisQuestionAnswersArray}"  />"> <c:out
									value="${thisQuestionAnswersArray}" />(${counter+1})
						</label></td>

					</tr>

					<c:set var="counter" value="${counter+1}" />

				</c:forEach>

			</tbody>

		</table>

		<c:if test="${currentQuestionNumber > 1}">

			<button type="submit" name="action" value="previous">Previous</button>

		</c:if>

		<c:if
			test="${currentQuestionNumber < currentTestTotalNumberOfQuestions}">

			<button type="submit" name="action" value="next">Next</button>

		</c:if>
		<br> <br>

		<div id="finish">
			<button type="submit" name="action" value="finishExam">Finish
				exam</button>
		</div>

	</form>

</div>

