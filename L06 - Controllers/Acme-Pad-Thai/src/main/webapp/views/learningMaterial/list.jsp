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
	name="learningMaterials" requestURI="${requestURI}" id="row">
	<security:authentication property="principal.username" var ="loggedactor"/>
	<jstl:set var="learningMaterialcook" value="${row.masterClass.cook}"/> 
	
	<spring:message code="learningMaterial.type" var="bodyHeader" />
	<display:column title="${bodyHeader}" sortable="false">
		<jstl:choose>
			<jstl:when test="${row.getClass().name == 'domain.TextMaterial'}">
				<spring:message code="learningMaterial.type.text"/>
			</jstl:when>
			<jstl:when test="${row.getClass().name == 'domain.PresentationMaterial'}">
				<spring:message code="learningMaterial.type.presentation"/>
			</jstl:when>
			<jstl:when test="${row.getClass().name == 'domain.VideoMaterial'}">
				<spring:message code="learningMaterial.type.video"/>
			</jstl:when>
		</jstl:choose>	
	</display:column>
		
	<jstl:if test="${learningMaterialcook.userAccount.username==loggedactor}">
		<display:column>
			<jstl:choose>
			<jstl:when test="${row.getClass().name == 'domain.TextMaterial'}">
				<a href="textMaterial/cook/edit.do?textMaterialId=${row.id}">
					<spring:message	code="learningMaterial.edit" />
				</a>
			</jstl:when>
			<jstl:when test="${row.getClass().name == 'domain.PresentationMaterial'}">
				<a href="presentationMaterial/cook/edit.do?presentationMaterialId=${row.id}">
					<spring:message	code="learningMaterial.edit" />
				</a>
			</jstl:when>
			<jstl:when test="${row.getClass().name == 'domain.VideoMaterial'}">
				<a href="videoMaterial/cook/edit.do?videoMaterialId=${row.id}">
					<spring:message	code="learningMaterial.edit" />
				</a>
			</jstl:when>
		</jstl:choose>	
		</display:column>
	</jstl:if>

	
	<!-- Attributes -->
	
	<spring:message code="learningMaterial.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="learningMaterial.materialAbstract" var="abstractHeader" />
	<display:column property="materialAbstract" title="${abstractHeader}" sortable="false" />
	
	<spring:message code="learningMaterial.others" var="bodyHeader" />
	<display:column title="${bodyHeader}" sortable="false">		
		<jstl:choose>
			<jstl:when test="${row.getClass().name == 'domain.TextMaterial'}">
				${row.body}
			</jstl:when>
			<jstl:when test="${row.getClass().name == 'domain.PresentationMaterial'}">
				${row.path}
			</jstl:when>
			<jstl:when test="${row.getClass().name == 'domain.VideoMaterial'}">
				${row.identifier}
			</jstl:when>
		</jstl:choose>	
	</display:column>
	
	
</display:table>

	<input type="button" name="cancel"
		value="<spring:message code="masterClass.cancel" />"
		onclick="javascript: window.location.replace('${cancelURI}');" />&nbsp;
	<br />

<security:authorize access="hasRole('COOK')">
	<div>
		<jstl:if test="${learningMaterialcook.userAccount.username==loggedactor}">
			<a href="cook/learningMaterial/create.do"> <spring:message
				code="learningMaterial.create" />
			</a>
		</jstl:if>
		
	</div>
</security:authorize>