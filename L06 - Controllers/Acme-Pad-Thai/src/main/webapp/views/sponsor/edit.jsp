<%--
 * create.jsp
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

<form:form action="sponsor/edit.do" modelAttribute="sponsor">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />
	<form:hidden path="enroled" />
	<form:hidden path="folders" />
	<form:hidden path="socialIdentities" />
	
	<form:label path="userAccount.username">
      <spring:message code="consumer.username" />
    </form:label>
    <form:input path="userAccount.username"/>
    <form:errors cssClass="error" path="userAccount.username"/>
    <br />
    
    <form:label path="userAccount.password">
      <spring:message code="consumer.password" />
    </form:label>
    <form:password path="userAccount.password"/>
    <form:errors cssClass="error" path="userAccount.password"/>
    <br />

	<form:label path="name">
		<spring:message code="sponsor.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	
	<form:label path="surname">
		<spring:message code="sponsor.surname" />:
	</form:label>
	<form:textarea path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />
	
	<form:label path="email">
		<spring:message code="sponsor.email" />:
	</form:label>
	<form:textarea path="email" />
	<form:errors cssClass="error" path="email" />
	<br />
	
	<form:label path="postalAddress">
		<spring:message code="sponsor.postalAddress" />:
	</form:label>
	<form:textarea path="postalAddress" />
	<form:errors cssClass="error" path="postalAddress" />
	<br />
	
	<form:label path="phone">
		<spring:message code="sponsor.phone" />:
	</form:label>
	<form:textarea path="phone" />
	<form:errors cssClass="error" path="phone" />
	<br />
	
	<form:label path="companyName">
		<spring:message code="sponsor.companyName" />:
	</form:label>
	<form:textarea path="companyName" />
	<form:errors cssClass="error" path="companyName" />
	<br />
	
	<input type="submit" name="continue"
		value="<spring:message code="sponsor.save" />" /> &nbsp;
<!-- 		onclick="javascript: relativeRedir('creditCard/edit.do');" /> &nbsp; -->
		
	<jstl:if test="${sponsor.id != 0}">
		<input type="submit" name="delete"
		value="<spring:message code="sponsor.delete" />"
		onclick="return confirm('<spring:message code="sponsor.confirm.delete" />')" />&nbsp;
		
		<input type="button" name="cancel"
		value="<spring:message code="sponsor.cancel" />"
		onclick="javascript: relativeRedir('sponsor/display.do');" />
	</jstl:if>
		
	<jstl:if test="${sponsor.id == 0}">
		<input type="button" name="cancel"
		value="<spring:message code="sponsor.cancel" />"
		onclick="javascript: relativeRedir('welcome/index.do');" />
	</jstl:if>
	<br />
	
	
</form:form>