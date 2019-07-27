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

	<h1>Test types:</h1>

	<form action="/testtype/firststep" method="post">

		<table>

			<thead>
				<tr>
					<th colspan="2"></th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${testTypes}" var="testType">
					<tr>
						<td colspan=2><label>${testType.testTypeName} <input type="radio"
								name="testType_Id" value="${testType.testTypeId}"></label></td>
					</tr>
				</c:forEach>
				<tr>
					<td><input type="submit" value="Confirm&NextStep"></td>
					<td><input type="reset" value="Reset"></td>
				</tr>
			</tbody>

		</table>

	</form>
	

</div>