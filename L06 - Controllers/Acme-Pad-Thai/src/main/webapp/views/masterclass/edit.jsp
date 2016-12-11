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

<form:form action="masterClass/edit.do" modelAttribute="masterClass">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="cook"/>
	<form:hidden path="actors"/>
	<form:hidden path="deleted"/>
	<form:hidden path="promoted"/>
	
	<form:label path="title">
		<spring:message code="masterclass.title" />:
	</form:label>
	<jstl:choose>
		<jstl:when test="${display}">
			${masterClass.title}
		</jstl:when>
		<jstl:otherwise>
			<form:input path="title" />
		</jstl:otherwise>
	</jstl:choose>
	<form:errors cssClass="error" path="title" />
	<br />
	
	<jstl:choose>
		<jstl:when test="${display}">
			<fieldset>
			<legend><spring:message code="masterclass.description" /></legend>
			${masterClass.title}
			</fieldset>
		</jstl:when>
		<jstl:otherwise>
			<form:label path="description">
				<spring:message code="masterclass.description" />:
			</form:label>
			<form:textarea path="description" />
		</jstl:otherwise>
	</jstl:choose>
	<form:errors cssClass="error" path="description" />
	<br />
	
	<security:authorize access="hasRole('COOK')">
	<security:authentication property="principal.username" var ="loggedactor"/>
	<jstl:set var="masterclasscook" value="${row.cook}"/> 
	<jstl:if test="${masterclass.id != 0} && ${masterclasscook.userAccount.id==loggedactor.id}">
		<input type="submit" name="save"
			value="<spring:message code="masterclass.save" />"  />&nbsp; 	
		<input type="submit" name="delete"
			value="<spring:message code="masterclass.delete" />"
			onclick="return confirm('<spring:message code="masterclass.confirm.delete" />')" />&nbsp;
	</jstl:if>
	</security:authorize>
	
	<input type="button" name="cancel"
		value="<spring:message code="masterclass.cancel" />"
		onclick="javascript: window.location.replace('${cancelURI}');" />&nbsp;
	<br />

	

</form:form>