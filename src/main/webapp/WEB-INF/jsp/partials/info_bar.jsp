<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	StringBuilder aggregatedCommunicate = new StringBuilder();
	String permissionStatus = (String) session.getAttribute("permission");
	String loggedUserName = (String) session.getAttribute("user_name");
	StringBuilder barInfo = new StringBuilder();
	if (loggedUserName != null) {
		barInfo.append("Logged: ").append(loggedUserName);
	} else {
		barInfo.append("Not logged ");
	}
	if (permissionStatus == "" && loggedUserName != null) {
		barInfo.append(" (A)");
	}
%>

<div class="infobar">

	<div class="commcell">

		<c:choose>

			<c:when test="${errorMessageToDisplay.length()>0}">

				<strong>${errorMessageToDisplay}</strong>

			</c:when>

			<c:when test="${infoMessageToDisplay.length()>0}">

				<span>${infoMessageToDisplay}</span>

			</c:when>

			<c:otherwise>

				<%=aggregatedCommunicate.append("No alerts")%>

			</c:otherwise>

		</c:choose>

	</div>

	<div class="logincell">

		<%=barInfo%>

	</div>

	<div class="eraser"></div>

</div>