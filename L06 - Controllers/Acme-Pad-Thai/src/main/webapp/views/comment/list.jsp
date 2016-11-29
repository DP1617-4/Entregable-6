<%--
 * list.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="comments" requestURI="${requestURI}" id="row">

	<!-- Attributes -->

	<spring:message code="comment.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	<spring:message code="comment.title" var="titleHeader" />
	<display:column property="text" title="${textHeader}" sortable="true" />
	<spring:message code="comment.text" var="textHeader" />
	<display:column property="stars" title="${starsHeader}" sortable="true" />
	<spring:message code="comment.stars" var="starsHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	<spring:message code="comment.socialUser" var="socialUserHeader" />
	<display:column title="${socialUserHeader}">
		<a href="socialUser/display.do?socialUserId=${row.socialUser.id}"><spring:message
				code="comment.socialUser"/> </a>
	</display:column>
	
	<display:column>
		<a href="recipe/display.do?recipeId=${row.id}"><spring:message
			code="comment.recipe" /></a>
	</display:column>

</display:table>


