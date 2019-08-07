<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="scrollable">

		<table>

			<col width="10">

			<tbody>
 
				<c:forEach items="${results}" var="thisTestSelection">

					<tr>
						<td colspan=1><input type="radio" name="submittedTest_Id"
							value="${thisTestSelections.key+1}"></td>
						<td>${thisTestSelections.value}</td>
					</tr>

				</c:forEach>

			</tbody>

		</table>

</div>