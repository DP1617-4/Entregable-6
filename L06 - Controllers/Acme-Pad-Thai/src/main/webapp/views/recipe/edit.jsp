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

<form:form action="recipe/edit.do" modelAttribute="recipe">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="user"/>
	<form:hidden path="ticker"/>
	<form:hidden path="deleted"/>
	<form:hidden path="score"/>
	<form:hidden path="authored"/>
	<form:hidden path="updated"/>
	<form:hidden path="scores"/>
	<form:hidden path="steps"/>
	<form:hidden path="contest"/>
	<form:hidden path="wonContest"/>
	<form:hidden path="comments"/>
	<form:hidden path="categories"/>
	<form:hidden path="quantities"/>
	<form:hidden path="pictures"/>

	<form:label path="title">
		<spring:message code="recipe.title" />:
	</form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br />
	
	<form:label path="summary">
		<spring:message code="recipe.summary" />:
	</form:label>
	<form:textarea path="summary" />
	<form:errors cssClass="error" path="summary" />
	<br />
	
	<form:label path="hints">
		<spring:message code="recipe.hints" />:
	</form:label>
	<form:input path="hints" />
	<form:errors cssClass="error" path="hints" />
	<br />

	<input type="submit" name="save"
	value="<spring:message code="recipe.save" />" />&nbsp; 
			
	<jstl:if test="${recipe.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="recipe.delete" />"/>&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="recipe.cancel" />"/>&nbsp;
	<br />

	

</form:form>