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
				<a href="contest/edit.do?contestId=${row.id}">
					<spring:message	code="contest.edit" />
				</a>
		</display:column>
	</security:authorize>
	<display:column>
		<a href="contest/qualify.do?contestId=${row.id}">
			<spring:message	code="contest.qualify" />
		</a>
	</display:column>	
	<!-- Attributes -->
	
	<spring:message code="contest.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="contest.openingTime" var="openingTimeHeader" />
	<display:column title="${openingTimeHeader}" sortable="true" >
		<fmt:formatDate value="${row.openingTime}"pattern ="dd/mm/yyyy"/>
	</display:column>
	
	<spring:message code="contest.closingTime" var="closingTimeHeader" />
	<display:column title="${closingTimeHeader}" sortable="true" >
		<fmt:formatDate value="${row.closingTime}"pattern ="dd/mm/yyyy"/>
	</display:column>
	
	<spring:message code="contest.qualified" var="qualifiedHeader"/>
	<display:column title="${qualifiedHeader}">
		<a href="contest/display.do?recipeId=${row.qualified.id}"><spring:message code="contest.qualified"/> </a>
	</display:column>
	<spring:message code="contest.winners" var="winnersHeader"/>
	<display:column title="${winnersHeader}">
		<a href="contest/display.do?recipeId=${row.winners.id}"><spring:message code="contest.winners"/> </a>
	</display:column>
	
</display:table>

	<!-- Action links -->
<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="contest/create.do"> <spring:message
				code="contest.create" />
		</a>
	</div>
</security:authorize>