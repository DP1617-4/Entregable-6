<%--
 * edit.jsp
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

<form:form action="step/edit.do" modelAttribute="step">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="recipe"/>

	<form:label path="description">
		<spring:message code="step.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	<form:label path="pictures">
		<spring:message code="step.pictures" />:
	</form:label>
	<form:input path="pictures" />
	<form:errors cssClass="error" path="pictures" />
	<br />
	<form:label path="hints">
		<spring:message code="step.hints" />:
	</form:label>
	<form:input path="hints" />
	<form:errors cssClass="error" path="hints" />
	<br />
	<form:label path="stepNumber">
		<spring:message code="step.stepNumber" />:
	</form:label>
	<form:input path="stepNumber" />
	<form:errors cssClass="error" path="stepNumber" />
	<br />
	

	<input type="submit" name="save"
		value="<spring:message code="step.save" />" />&nbsp; 
	<jstl:if test="${category.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="step.delete" />"
			onclick="return confirm('<spring:message code="step.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="step.cancel" />"
		onclick="javascript: relativeRedir('recipe/list.do');" />
	<br />

</form:form>