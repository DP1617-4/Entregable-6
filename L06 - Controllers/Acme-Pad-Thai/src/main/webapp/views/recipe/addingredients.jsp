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

<form:form action="recipe/addingredients.do" modelAttribute="quantity">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ingredient"/>
	<form:hidden path="recipe"/>

	<form:label path="quantity">
		<spring:message code="recipe.quantity" />:
	</form:label>
	<form:input path="quantity" />
	<form:errors cssClass="error" path="quantity" />
	<br />
	
	<form:label path="unit">
		<spring:message code="recipe.unit" />:
	</form:label>
	<form:select path="unit">
                <form:option value="grams"><spring:message code="recipe.grams" /></form:option>
                <form:option value="kilograms"><spring:message code="recipe.kilograms" /></form:option>
                <form:option value="ounces"><spring:message code="recipe.ounces" /></form:option>
                <form:option value="pounds"><spring:message code="recipe.pounds" /></form:option>
                <form:option value="millilitres"><spring:message code="recipe.millilitres" /></form:option>
                <form:option value="litres"><spring:message code="recipe.litres" /></form:option>
                <form:option value="spoons"><spring:message code="recipe.spoons" /></form:option>
                <form:option value="cups"><spring:message code="recipe.cups" /></form:option>
                <form:option value="pieces"><spring:message code="recipe.pieces" /></form:option>
            </form:select>
	<form:errors cssClass="error" path="unit" />
		
	<br/>
	
	
	<input type="submit" name="save"
	value="<spring:message code="recipe.save" />"/>&nbsp; 	

	
	<input type="button" name="cancel"
		value="<spring:message code="recipe.cancel" />"/>&nbsp;
	<br />

	

</form:form>