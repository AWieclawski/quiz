<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="left">

	<h1>Step one</h1>
	<h3>Quiz building</h3>
	<p>Select the test type from among those available in the database.</p>
</div>

<div id="right">

	<table>

		<thead>
			<tr>
				<th>Test types:</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${testTypes}" var="testType">
				<tr>
					<td><label>${testType.testTypeName} <input
							type="radio" name="testType_Id" value="${testType.testTypeId}"></label></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>

</div>