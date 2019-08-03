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
				1. selected criterion: <strong>${selectedTestType.testTypeName}</strong>
			</p>
			<p>Now, select ${resultName} among those available in the
				database.</p>

		</div>

	</div>

</div>

<div id="right">

	<h1>Available ${resultsName}:</h1>

	<c:choose>

		<c:when test="${results==null}">
			<p>
				<strong>No ${resultsName} to select. Search result is
					empty.</strong>
			</p>

		</c:when>

		<c:otherwise>

			<jsp:include page="../partials/right/difficultylevels.jsp"></jsp:include>

		</c:otherwise>

	</c:choose>

</div>