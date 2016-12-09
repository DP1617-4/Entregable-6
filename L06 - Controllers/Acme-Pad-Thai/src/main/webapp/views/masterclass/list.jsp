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


<security:authorize access="hasRole('COOK')">
	<div>
		<a href="masterClass/own/list.do"> <spring:message
				code="masterClass.cook.list.own" />
		</a>
	</div>
</security:authorize>

<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="masterClasses" requestURI="${requestURI}" id="row">
	
	<display:column>
		<a href="masterClass/display.do?masterClassId=${row.id}">
			<spring:message	code="masterClass.view" />
		</a>
	</display:column>
	
	<security:authorize access="hasRole('COOK')">
	<security:authentication property="principal.username" var ="loggedactor"/>
	<jstl:set var="masterClasscook" value="${row.cook}"/> 
		<display:column>
			<jstl:if test="${masterClasscook.userAccount.username==loggedactor}">
				<a href="masterClass/edit.do?masterClassId=${row.id}">
					<spring:message	code="masterClass.edit" />
				</a>
			</jstl:if>
		</display:column>
		<display:column>
			<jstl:if test="${masterClasscook.userAccount.username==loggedactor}">
				<a href="learningmaterial/create.do?masterClassId=${row.id}">
					<spring:message code="masterClass.add.material"/>
				</a>
			</jstl:if>
		</display:column>
	</security:authorize>

	
	<!-- Attributes -->

	<spring:message code="masterClass.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="masterClass.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />
	
	<spring:message code="masterClass.promoted" var="promotedHeader" />
	<display:column property="promoted" title="${promotedHeader}" sortable="true" />
	
	<security:authorize access="isAuthenticated()">
		<display:column>
			<a href="learningMaterial/list.do?masterClassId=${row.id}"><spring:message code="masterClass.materials.list"/></a>
		</display:column>
	
	
		<display:column>
			<a href="masterClass/enrol.do"><spring:message code="masterClass.enroll"/></a>
		</display:column>
	</security:authorize>
	
	
</display:table>

<security:authorize access="hasRole('COOK')">
	<div>
		<a href="cook/masterClass/create.do"> <spring:message
				code="masterClass.create" />
		</a>
	</div>
</security:authorize>

${masterClasscook.userAccount.id}