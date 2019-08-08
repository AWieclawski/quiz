<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="scrollable">

	<table>

		<col width="10">

		<thead>

			<tr>
				<th colspan=2>${questionSet.question}?</th>
			</tr>

		</thead>

		<tbody>
		
		   <c:set var="counter" value="0" />
		
		<c:forEach items="${questionSet.arrayOfAnswers}" var="thisQuestionAnswersArray">

			<tr>

				<td colspan=1><input type="radio" name="submittedAnswer_Id"
					value="${counter+1}"></td>

				<td><c:out value="${thisQuestionAnswersArray}"  /></td>

			</tr>

			<c:set var="counter" value="${counter+1}" />   
			
			</c:forEach>

		</tbody>

	</table>

</div>

