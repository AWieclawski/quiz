<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div id="left">

	<div class="description">

		<h1>Answers</h1>

		<c:choose>

			<c:when test="${results==null}">

				<p class="warning">
					<b>No anticipated results.</b>
				</p>

			</c:when>

			<c:otherwise>

				<h3>Selections (N/S - not selected)</h3>

				<jsp:include page="../partials/right/answers.jsp"></jsp:include>

			</c:otherwise>

		</c:choose>

	</div>

</div>

<div id="right">

	<div class="description">

		<h1>Exam</h1>

		<c:choose>

			<c:when test="${currentQuestionSet==null}">

				<p class="warning">
					<b>No ${resultsName} to select. Search result is
						empty.</b>
				</p>

			</c:when>

			<c:otherwise>

				<h3>
					Test name: <strong>${thisTest.testName}</strong> | Question no: <strong>${currentQuestionNumber}</strong>
				</h3>

				<jsp:include page="../partials/right/questionset.jsp"></jsp:include>

			</c:otherwise>

		</c:choose>

	</div>

</div>