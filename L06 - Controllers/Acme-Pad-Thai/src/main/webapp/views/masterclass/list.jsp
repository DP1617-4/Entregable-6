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
		<a href="masterClass/cook/listOwn.do"> <spring:message
				code="masterClass.cook.list.own" />
		</a>
	</div>
</security:authorize>

<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="masterClasses" requestURI="${requestURI}" id="row">
	<jstl:set var="masterClasscook" value="${row.cook}"/> 
	
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
<jstl:if test="${error!=null}">
	<spring:message code="${error}"/>
</jstl:if>

<security:authorize access="hasRole('COOK')">
	<div>
		<input type="button" name="cancel"
			value="<spring:message code="masterClass.create" />"
			onclick="javascript: window.location.replace('masterClass/cook/create.do');" />&nbsp;
		<br />
	</div>
</security:authorize>