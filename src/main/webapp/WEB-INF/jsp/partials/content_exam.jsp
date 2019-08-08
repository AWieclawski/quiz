<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div id="left">

	<div class="description">

		<h1>Answers</h1>
		
		<h3>User's selections</h3>

		<jsp:include page="../partials/right/answers.jsp"></jsp:include>

	</div>

</div>

<div id="right">

	<h1>Exam</h1>

	<h3>Test name: <strong>${thisTest.testName}</strong> | Question no: <strong>${currentQuestionNumber}</strong></h3>

	<jsp:include page="../partials/right/questionset.jsp"></jsp:include>

</div>