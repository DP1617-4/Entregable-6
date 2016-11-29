<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="user/edit.do" modelAttribute="user">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="scores" />
	<form:hidden path="folders" />
	<form:hidden path="socialIdentities" />
	<form:hidden path="enroled" />
	<form:hidden path="recipes" />
	<form:hidden path="userAccount" />
	
	<form:label path="name">
		<spring:message code="user.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	
	<form:label path="surname">
		<spring:message code="user.surname" />:
	</form:label>
	<form:textarea path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />
	
	<form:label path="email">
		<spring:message code="user.email" />:
	</form:label>
	<form:textarea path="email" />
	<form:errors cssClass="error" path="email" />
	<br />
	
	<form:label path="postalAddress">
		<spring:message code="user.postalAddress" />:
	</form:label>
	<form:textarea path="postalAddress" />
	<form:errors cssClass="error" path="postalAddress" />
	<br />
	
	<form:label path="phone">
		<spring:message code="user.phone" />:
	</form:label>
	<form:textarea path="phone" />
	<form:errors cssClass="error" path="phone" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="user.save" />" />&nbsp; 
		
	<jstl:if test="${user.id != 0}">
		<input type="submit" name="delete"
		value="<spring:message code="user.delete" />"
		onclick="return confirm('<spring:message code="user.confirm.delete" />')" />&nbsp;
		<input type="button" name="cancel"
		value="<spring:message code="user.cancel" />"
		onclick="javascript: relativeRedir('user/display.do');" />
	</jstl:if>
	
	<jstl:if test="${user.id == 0}">
		<input type="button" name="cancel"
		value="<spring:message code="user.cancel" />"
		onclick="javascript: relativeRedir('welcome/index.do');" />
	</jstl:if>
	<br />
	
	
	
	
</form:form>