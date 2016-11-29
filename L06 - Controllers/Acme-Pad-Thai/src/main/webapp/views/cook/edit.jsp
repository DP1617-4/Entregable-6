<%--
 * action-1.jsp
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

<form:form action="cook/edit.do" modelAttribute="cook">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />
	<form:hidden path="folders"/>
	<form:hidden path="masterclasses"/>
	<form:hidden path="enroled"/>
	
	<form:label path="name">
		<spring:message code="cook.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	
	<form:label path="surname">
		<spring:message code="cook.surname" />:
	</form:label>
	<form:textarea path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />
	
	<form:label path="email">
		<spring:message code="cook.email" />:
	</form:label>
	<form:textarea path="email" />
	<form:errors cssClass="error" path="email" />
	<br />
	
	<form:label path="postalAddress">
		<spring:message code="cook.postalAddress" />:
	</form:label>
	<form:textarea path="postalAddress" />
	<form:errors cssClass="error" path="postalAddress" />
	<br />
	
	<form:label path="phone">
		<spring:message code="cook.phone" />:
	</form:label>
	<form:textarea path="phone" />
	<form:errors cssClass="error" path="phone" />
	<br />
	
	
	
	<jstl:if test="${cook.id == 0}">
	<form:label path="userAccount.username">
      <spring:message code="cook.username" />
    </form:label>
    <form:input path="userAccount.username"/>
    <form:errors cssClass="error" path="userAccount.username"/>
    <br />
    
    <form:label path="userAccount.password">
      <spring:message code="cook.password" />
    </form:label>
    <form:password path="userAccount.password"/>
    <form:errors cssClass="error" path="userAccount.password"/>
    </jstl:if>
    <br />
	
	<input type="submit" name="save"
		value="<spring:message code="cook.save" />" />&nbsp; 
	<input type="button" name="cancel"
		value="<spring:message code="cook.cancel" />"
		onclick="javascript: relativeRedir('welcome/index.do');" />
	<br />
	
	
</form:form>