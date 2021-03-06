<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="${requestURI}" modelAttribute="nutritionist">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.id" />
	<form:hidden path="userAccount.version" />
	<form:hidden path="scores" />
	<form:hidden path="folders" />
	<form:hidden path="socialIdentities" />
	<form:hidden path="enroled" />
	<form:hidden path="comments" />
	<form:hidden path="curriculum" />
	<form:hidden path="followers" />
	<form:hidden path="followed" />
	<form:hidden path="userAccount.authorities" />
	
	<jstl:if test="${nutritionist.id!=0}">
	<form:hidden path="userAccount.username" />
	<form:hidden path="userAccount.password" />
	</jstl:if>
	
	<jstl:if test="${nutritionist.id==0}">
	<form:label path="userAccount.username">
      <spring:message code="nutritionist.username" />
    </form:label>
    <form:input path="userAccount.username"/>
    <form:errors cssClass="error" path="userAccount.username"/>
    <br />
    
    <form:label path="userAccount.password">
      <spring:message code="nutritionist.password" />
    </form:label>
    <form:password path="userAccount.password"/>
    <form:errors cssClass="error" path="userAccount.password"/>
    <br />
	</jstl:if>
	
	<form:label path="name">
		<spring:message code="nutritionist.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	
	<form:label path="surname">
		<spring:message code="nutritionist.surname" />:
	</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />
	
	<form:label path="email">
		<spring:message code="nutritionist.email" />:
	</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email" />
	<br />
	
	<form:label path="postalAddress">
		<spring:message code="nutritionist.postalAddress" />:
	</form:label>
	<form:textarea path="postalAddress" />
	<form:errors cssClass="error" path="postalAddress" />
	<br />
	
	<form:label path="phone">
		<spring:message code="nutritionist.phone" />:
	</form:label>
	<form:input path="phone" />
	<form:errors cssClass="error" path="phone" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="nutritionist.save" />" />
		
		
	<jstl:if test="${nutritionist.id != 0}">
		<input type="button" name="cancel"
		value="<spring:message code="nutritionist.cancel" />"
		onclick="location.href = 'nutritionist/display.do';" />&nbsp;
	<br />
	</jstl:if>
	
	<jstl:if test="${nutritionist.id == 0}">
	<input type="button" name="cancel"
		value="<spring:message code="nutritionist.cancel" />"
		onclick="location.href = 'welcome/index.do';" />&nbsp;
	</jstl:if>
	<br />
	
</form:form>