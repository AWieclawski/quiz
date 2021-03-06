<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div id="left">

	<div class="description">

		<h1>Finish</h1>

	</div>

</div>

<div id="right">

	<div class="description">

		<h1>Results</h1>

		<c:choose>

			<c:when test="${results==null}">

				<p class="warning">
					<b>No anticipated results.</b>
				</p>

			</c:when>

			<c:otherwise>

				<h3>Selections</h3>

				<jsp:include page="../partials/right/results.jsp"></jsp:include>

			</c:otherwise>

		</c:choose>


	</div>

</div>