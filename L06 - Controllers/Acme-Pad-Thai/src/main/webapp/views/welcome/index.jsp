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

<security:authorize access="isAnonymous()">
	<p><b><spring:message code="welcome.greeting.anonymous" /> </b></p>
</security:authorize>

<security:authorize access="isAuthenticated()">
	<p><b><spring:message code="welcome.greeting.prefix" /> ${name}<spring:message code="welcome.greeting.suffix" /></b></p>
</security:authorize>

<p><b><spring:message code="welcome.greeting.current.time" /></b> ${moment}</p> 

<p><spring:message code="welcome.promoted.classes"/></p>


<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="masterClasses" requestURI="${requestURI}" id="row">
	<jstl:set var="masterClasscook" value="${row.cook}"/> 
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
		<jstl:choose>
			<jstl:when test="${row.promoted}">
				<a href="masterClass/administrator/demote.do?masterClassId=${row.id}">
					<spring:message	code="masterClass.demote" />
				</a>
			</jstl:when>
			<jstl:otherwise>
				<a href="masterClass/administrator/promote.do?masterClassId=${row.id}">
					<spring:message	code="masterClass.promote" />
				</a>
			</jstl:otherwise>
		</jstl:choose>
		</display:column>
	</security:authorize>
	
	<security:authorize access="isAuthenticated()">
			<display:column>
				<a href="masterClass/actor/display.do?masterClassId=${row.id}">
					<spring:message	code="masterClass.view" />
				</a>
			</display:column>
	</security:authorize>
	<security:authorize access="isAnonymous()">
			<display:column>
				<a href="masterClass/display.do?masterClassId=${row.id}">
					<spring:message	code="masterClass.view" />
				</a>
			</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('COOK')">
	<security:authentication property="principal.username" var ="loggedactor"/>
		<display:column>
			<jstl:if test="${masterClasscook.userAccount.username==loggedactor}">
				<a href="masterClass/cook/edit.do?masterClassId=${row.id}">
					<spring:message	code="masterClass.edit" />
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
	<security:authentication property="principal.username" var ="loggedactor"/>
		<display:column>
			<jstl:set var="canEnrol" value="true"/> 
			<jstl:if test="${masterClasscook.userAccount.username==loggedactor}">
				<jstl:set var="canEnrol" value="false"/> 
			</jstl:if>
			<jstl:forEach items="${actor.enroled}" var="masterClass">
				<jstl:if test="${masterClass.id==row.id}">
					<jstl:set var="canEnrol" value="false"/> 
				</jstl:if>
			</jstl:forEach>
			<jstl:if test="${canEnrol}">
				<a href="masterClass/actor/enrol.do?masterClassId=${row.id}"><spring:message code="masterClass.enroll"/></a>
			</jstl:if>
		</display:column>
		
		<display:column>
			<jstl:if test="${!canEnrol}">
				<a href="learningMaterial/actor/list.do?masterClassId=${row.id}"><spring:message code="masterClass.materials.list"/></a>
			</jstl:if>
		</display:column>
	
	</security:authorize>
	
	
</display:table>
