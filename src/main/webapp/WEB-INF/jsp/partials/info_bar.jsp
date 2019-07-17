<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    StringBuilder aggregatedCommunicate = new StringBuilder();
    String errorCommunicate = (String) request.getAttribute("error");
    String infoCommunicate = (String) request.getAttribute("info");
    String communicateColor = "black";

    if (errorCommunicate != null || infoCommunicate != null) {
        if (errorCommunicate != null) {
            aggregatedCommunicate.append(errorCommunicate);
            communicateColor = "red";
        }
        if (infoCommunicate != null) {
            aggregatedCommunicate.append(infoCommunicate);
            communicateColor = "green";
        }
    } else {
        aggregatedCommunicate.append("No alerts");
    }

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

    <div class="commcell" style="color:<%=communicateColor%>;"><%=aggregatedCommunicate%>
    </div>

    <div class="logincell">

        <%= barInfo %>

    </div>

    <div class="eraser">
    </div>

</div>