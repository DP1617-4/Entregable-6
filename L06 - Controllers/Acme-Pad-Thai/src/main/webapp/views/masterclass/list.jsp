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
		<a href="masterclass/own/list.do"> <spring:message
				code="masterclass.cook.list.own" />
		</a>
	</div>
</security:authorize>

<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="masterclasses" requestURI="${requestURI}" id="row">
	<jstl:set var="loggedactor" value=<security:authentication property="principal.username" />/>
	<jstl:set var="masterclasscook" value="${row.cook}"/> 
	<jstl:if test="${masterclasscook.userAccount.id==loggedactor.id}">
		<display:column>
			<a href="masterclass/edit.do?masterclassId=${row.id}">
				<spring:message	code="masterclass.edit" />
			</a>
		</display:column>
		<display:column>
			<a href="learningmaterial/create.do?masterclassId=${row.id}">
				<spring:message code="masterclass.add.material"/>
			</a>
		</display:column>
	</jstl:if>

	
	<!-- Attributes -->

	<spring:message code="masterclass.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="masterclass.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />
	
	<spring:message code="masterclass.promoted" var="promotedHeader" />
	<display:column property="promoted" title="${promotedHeader}" sortable="true" />
	
	<display:column>
		<a href="learningMaterial/list.do?masterclassId=${row.id}"><spring:message code="masterclass.materials.list"/></a>
	</display:column>
	
	
	<display:column>
		<a href="masterclass/enrol.do"><spring:message code="masterclass.enroll"/></a>
	</display:column>
	
	
	
</display:table>

<security:authorize access="hasRole('COOK')">
	<div>
		<a href="cook/masterclass/create.do"> <spring:message
				code="masterclass.create" />
		</a>
	</div>
</security:authorize>