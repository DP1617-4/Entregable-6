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

<form:form action="administrator/edit.do" modelAttribute="administrator">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />
	<form:hidden path="folders"/>
	
	<form:label path="name">
		<spring:message code="administrator.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	
	<form:label path="surname">
		<spring:message code="administrator.surname" />:
	</form:label>
	<form:textarea path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />
	
	<form:label path="email">
		<spring:message code="administrator.email" />:
	</form:label>
	<form:textarea path="email" />
	<form:errors cssClass="error" path="email" />
	<br />
	
	<form:label path="postalAddress">
		<spring:message code="administrator.postalAddress" />:
	</form:label>
	<form:textarea path="postalAddress" />
	<form:errors cssClass="error" path="postalAddress" />
	<br />
	
	<form:label path="phone">
		<spring:message code="administrator.phone" />:
	</form:label>
	<form:textarea path="phone" />
	<form:errors cssClass="error" path="phone" />
	<br />
	
	
	
	<jstl:if test="${administrator.id == 0}">
	<form:label path="userAccount.username">
      <spring:message code="administrator.username" />
    </form:label>
    <form:input path="userAccount.username"/>
    <form:errors cssClass="error" path="userAccount.username"/>
    <br />
    
    <form:label path="userAccount.password">
      <spring:message code="administrator.password" />
    </form:label>
    <form:password path="userAccount.password"/>
    <form:errors cssClass="error" path="userAccount.password"/>
    </jstl:if>
    <br />
	
	<input type="submit" name="save"
		value="<spring:message code="administrator.save" />" />&nbsp; 
	<input type="button" name="cancel"
		value="<spring:message code="administrator.cancel" />"
		onclick="javascript: relativeRedir('welcome/index.do');" />
	<br />
	
	
</form:form>