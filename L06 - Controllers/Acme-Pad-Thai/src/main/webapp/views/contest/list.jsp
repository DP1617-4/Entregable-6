<%--
 * list.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="contests" requestURI="${requestURI}" id="row">

	<!-- Action links -->
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
				<a href="contest/admin/edit.do?contestId=${row.id}">
					<spring:message	code="contest.edit" />
				</a>
		</display:column>
	</security:authorize>
	<security:authorize access="hasRole('USER')">
		<display:column>
			<form:form action="contest/user/qualify.do" modelAttribute="addRecipe">

				<form:hidden path="contestId" value="${row.id}"/>

				<form:label path="recipeId">
					<spring:message code="contest.qualify.recipe" />:
				</form:label>
				<form:select id="recipes" path="recipeId" >
					<form:option value="0" label="----"/>
					<form:options items="${recipes}" itemValue="id" itemLabel="title"/>
				</form:select>
				<form:errors cssClass="error" path="recipeId" />
				<input type="submit" name="recipeToQualify"
					value="<spring:message code="contest.save" />" />&nbsp;

	

			</form:form>
		</display:column>
	</security:authorize>	
	<!-- Attributes -->
	
	<spring:message code="contest.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="contest.openingTime" var="openingTimeHeader" />
	<display:column title="${openingTimeHeader}" property="openingTime" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>
	
	
	<spring:message code="contest.closingTime" var="closingTimeHeader" />
	<display:column title="${closingTimeHeader}" property="closingTime" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />
	
	<spring:message code="contest.qualified" var="qualifiedHeader"/>
	<display:column title="${qualifiedHeader}">
		<a href="recipe/listQualified.do?contestId=${row.id}"><spring:message code="contest.qualified"/> </a>
	</display:column>
	<spring:message code="contest.winners" var="winnersHeader"/>
	<display:column title="${winnersHeader}">
		<a href="recipe/listWinners.do?contestId=${row.id}"><spring:message code="contest.winners"/> </a>
	</display:column>
	
</display:table>

	<!-- Action links -->
<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="contest/admin/create.do"> <spring:message
				code="contest.create" />
		</a>
	</div>
</security:authorize>