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


<security:authorize access="hasRole('cook')">
	<div>
		<a href="learningMaterial/own/list.do"> <spring:message
				code="learningMaterial.cook.list.own" />
		</a>
	</div>
</security:authorize>

<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="learningMaterial" requestURI="${requestURI}" id="row">
	<jstl:set var="loggedactor" value=<security:authentication property="principal.username" />/>
	<jstl:set var="learningMaterialcook" value="${row.masterclass.cook}"/> 
	<jstl:if test="${learningMaterialcook.userAccount.id==loggedactor.id}">
		<display:column>
			<a href="learningMaterial/edit.do?learningMaterialId=${row.id}">
				<spring:message	code="learningMaterial.edit" />
			</a>
		</display:column>
	</jstl:if>

	
	<!-- Attributes -->
	
	<spring:message code="learningMaterial.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="learningMaterial.abstract" var="abstractHeader" />
	<display:column property="materialAbstract" title="${abstractHeader}" sortable="false" />
	
	<spring:message code="learningMaterial.attachment" var="attachmentHeader" />
	<display:column property="attachment" title="${attachmentHeader}" sortable="false" />
	
	<jstl:choose>
		<jstl:when test="${row.body != null}">
			<spring:message code="learningMaterial.body" var="bodyHeader" />
			<display:column property="bdoy" title="${bodyHeader}" sortable="false" />
		</jstl:when>
		<jstl:when test="${row.identifier != null}">
			<spring:message code="learningMaterial.identitifier" var="identifierHeader" />
			<display:column property="identifier" title="${identifierHeader}" sortable="false" />
		</jstl:when>
		<jstl:when test="${row.path != null}">
			<spring:message code="learningMaterial.path" var="pathHeader" />
			<display:column property="path" title="${pathHeader}" sortable="false" />
		</jstl:when>
	
	</jstl:choose>
	
	
</display:table>

<security:authorize access="hasRole('COOK')">
	<div>
		<jstl:if test="${learningMaterialcook.userAccount.id==loggedactor.id}">
			<a href="cook/learningMaterial/create.do"> <spring:message
				code="learningMaterial.create" />
			</a>
		</jstl:if>
		
	</div>
</security:authorize>