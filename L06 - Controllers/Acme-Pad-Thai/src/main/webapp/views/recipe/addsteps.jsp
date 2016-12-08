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

<form:form action="recipe/addsteps.do" modelAttribute="step">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="stepNumber"/>
	<form:hidden path="recipe"/>

	<form:label path="desccription">
		<spring:message code="recipe.step.description" />:
	</form:label>
	<form:textarea path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	
	<form:label path="hints">
		<spring:message code="recipe.hints" />:
	</form:label>
	<form:input path="hints" />
	<form:errors cssClass="error" path="hints" />
		
	<br/>
	
	
	<input type="submit" name="save"
	value="<spring:message code="recipe.save" />" 
	onclick="javascript: relativeRedir('recipe/display.do?recipeId=${recipe.id}');" />&nbsp; 	

	
	<input type="button" name="cancel"
		value="<spring:message code="recipe.cancel" />"
		onclick="javascript: relativeRedir('recipe/display.do?recipeId=${recipe.id}');" />&nbsp;
	<br />

	

</form:form>