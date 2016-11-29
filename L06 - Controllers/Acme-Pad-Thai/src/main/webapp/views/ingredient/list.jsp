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



<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="ingredient" requestURI="${requestURI}" id="row">
	<security:authorize access="hasRole('NUTRITIONIST')">
		<display:column>
			<a href="ingredient/edit.do?ingredientId=${row.id}">
				<spring:message	code="ingredient.edit" />
			</a>
		</display:column>
	</security:authorize>
	
	<!-- Attributes -->
	
	<spring:message code="ingredient.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />
	
	<spring:message code="ingredient.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />
	
	<display:column>
		<a href="ingredient/display.do?ingredientId=${row.id}"><spring:message code="ingredient.display"/></a>
	</display:column>
	
</display:table>

<security:authorize access="hasRole('NUTRITIONIST')">
	<div>
		<a href="ingredient/create.do"> <spring:message
				code="ingredient.create" />
		</a>
	</div>
</security:authorize>