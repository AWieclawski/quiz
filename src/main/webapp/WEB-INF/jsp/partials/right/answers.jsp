<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="scrollable">

	<table>

		<col width="10">
		<col width="30">

		<thead>

			<tr>

				<th>No</th>

				<th>Answer</th>

			</tr>

		</thead>

		<tbody>

			<c:forEach items="${results}" var="thisTestSelection">

				<tr>

					<td>${thisTestSelection.key+1}</td>

					<td>${thisTestSelection.value}</td>

				</tr>

			</c:forEach>

		</tbody>

	</table>

	(N/S - not selected)

</div>