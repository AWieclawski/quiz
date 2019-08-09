<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="scrollable">

	<form action="/difficultylevel/secondstep" method="post">

		<table>

			<thead>
				<tr>
					<th colspan="1"><input type="submit" value="Confirm&NextStep">
						<input type="reset" value="Reset"></th>
				</tr>
			</thead>

			<tbody>

				<c:forEach items="${results}" var="difficultyLevel">

					<tr>

						<td colspan="1"><input type="radio" class="button"
							name="submittedDifficultyLevel_Id"
							id="${difficultyLevel.difficultyLevelName}"
							value="${difficultyLevel.difficultyLevelId}"></input> <label
							for="${difficultyLevel.difficultyLevelName}">${difficultyLevel.difficultyLevelName}</label></td>

					</tr>

				</c:forEach>

			</tbody>

		</table>

	</form>

</div>