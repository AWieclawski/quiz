<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="scrollable">

	<form action="/testtype/firststep" method="post">

		<table>

			<col width="10">

			<thead>
				<tr>
					<th colspan="2"><input type="submit" value="Confirm&NextStep">
						<input type="reset" value="Reset"></th>
				</tr>
			</thead>

			<tbody>

				<c:forEach items="${results}" var="testType">

					<tr>
						<td colspan=1><input type="radio" name="submittedTestType_Id"
							value="${testType.testTypeId}"></td>
						<td>${testType.testTypeName}</td>
					</tr>

				</c:forEach>

			</tbody>

		</table>

	</form>

</div>