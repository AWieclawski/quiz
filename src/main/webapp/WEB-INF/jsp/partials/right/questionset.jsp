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

			<tr>
				<td colspan=1><input type="radio" name="submittedAnswer_Id"
					value="1"></td>
				<td>${questionSet.arrayOfAnswers[0]}</td>
			</tr>

			<tr>
				<td colspan=1><input type="radio" name="submittedAnswer_Id"
					value="2"></td>
				<td>${questionSet.arrayOfAnswers[1]}</td>
			</tr>

			<tr>
				<td colspan=1><input type="radio" name="submittedAnswer_Id"
					value="3"></td>
				<td>${questionSet.arrayOfAnswers[2]}</td>
			</tr>

			<tr>
				<td colspan=1><input type="radio" name="submittedAnswer_Id"
					value="4"></td>
				<td>${questionSet.arrayOfAnswers[3]}</td>
			</tr>

		</tbody>

	</table>

</div>

