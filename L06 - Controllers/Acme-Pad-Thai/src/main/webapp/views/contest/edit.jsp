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

<form:form action="contest/edit.do" modelAttribute="contest">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="qualified"/>
	<form:hidden path="winners"/>
	<form:hidden path="deleted"/>

	<form:label path="title">
		<spring:message code="contest.title" />:
	</form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br />
	<form:label path="openingTime">
		<spring:message code="contest.openingTime" />:
	</form:label>
	<form:input path="openingTime" />
	<form:errors cssClass="error" path="openingTime" />
	<br />
	<form:label path="closingTime">
		<spring:message code="contest.closingTime" />:
	</form:label>
	<form:input path="closingTime" />
	<form:errors cssClass="error" path="closingTime" />
	<br />


	<input type="submit" name="save"
		value="<spring:message code="contest.save" />" />&nbsp; 
	<jstl:if test="${category.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="contest.delete" />"
			onclick="return confirm('<spring:message code="contest.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="contest.cancel" />"
		onclick="javascript: relativeRedir('contest/list.do');" />
	<br />

	

</form:form>