<%--
 * index.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<jstl:if test="${banner != null}">
	<img src="${banner.URL}"/>
</jstl:if>


<p><spring:message code="welcome.greeting.prefix" /> ${name}<spring:message code="welcome.greeting.suffix" /></p>

<p><spring:message code="welcome.greeting.current.time" /> ${moment}</p> 

<p><spring:message code="welcome.promoted.classes"/></p>

<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="masterclassess" requestURI="${requestURI}" id="row">
	<jstl:set var="loggedactor" value=<security:authentication property="principal.username" />/>
	<jstl:set var="masterclasscook" value="${row.cook}"/> 
	<jstl:if test="${masterclasscook.userAccount.id==loggedactor.id}">
		<display:column>
			<a href="masterclass/edit.do?masterclassId=${row.id}">
				<spring:message	code="welcome.masterclass.edit" />
			</a>
		</display:column>
		<display:column>
			<a href="learningmaterial/create.do?masterclassId=${row.id}">
				<spring:message code="welcome.masterclass.add.material"/>
			</a>
		</display:column>
	</jstl:if>

	
	<!-- Attributes -->

	<spring:message code="welcome.masterclass.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="welcome.masterclass.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />
	
	<spring:message code="welcome.masterclass.promoted" var="promotedHeader" />
	<display:column property="promoted" title="${promotedHeader}" sortable="true" />
	
	<display:column>
		<a href="learningMaterial/list.do?masterclassId=${row.id}"><spring:message code="welcome.masterclass.materials.list"/></a>
	</display:column>
	
	<display:column>
		<a href="masterclass/enrol.do"><spring:message code="welcome.masterclass.enroll"/></a>
	</display:column>
	
	
	
</display:table>
