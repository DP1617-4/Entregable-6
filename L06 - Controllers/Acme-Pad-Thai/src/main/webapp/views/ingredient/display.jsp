<%--
 * list.jsp
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


<h2>${ingredient.name}</h2>
<h3>${ingredient.description}</h3>
<h4><spring:message code="ingredient.pictures"/></h4>
<p>

<security:authorize access="hasRole('NUTRITIONIST')">
<form:form action="ingredient/nutritionist/addPicture.do" modelAttribute="addPicture">

	<form:hidden path="id" value="${ingredient.id}"/>
	
	<form:input path="picture"/>
	<form:errors cssClass="error" path="picture" />
	
	<input type="submit" name="addImage"
	value ="<spring:message code="ingredient.pictures.add"/>" />

</form:form>
</security:authorize>

</p>
<ul>
	<jstl:forEach var="thisPicture" items="${ingredient.pictures}" >
		<li><img src="${thisPicture}" alt="${thisPicture}"/></li>
	</jstl:forEach>
</ul>
<br/>

<h2><spring:message	code="ingredient.properties" /></h2>
<security:authorize access="hasRole('NUTRITIONIST')">
	<form:form action="ingredient/nutritionist/addProperty.do" modelAttribute="addIngredient">

	<form:hidden path="recipeId" value="${ingredient.id}"/>
	
	<form:select path="ingredientId" >
    	<form:options items="${properties}" itemValue="id"  itemLabel="name" />
	</form:select>
	
	<input type="submit" name="addProperty"
	value ="<spring:message code="ingredient.addproperties"/>" />

</form:form>
</security:authorize>

<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="values" requestURI="ingredient/display.do" id="row">
	<!-- Attributes -->
	
	<spring:message code="ingredient.property.name" var="propertyHeader" />
	<display:column property="property.name" title="${propertyHeader}" sortable="false" />
	
	<spring:message code="ingredient.property.value" var="valueHeader" />
	<display:column property="value" title="${valueHeader}" sortable="false" />
	
	<security:authorize access="hasRole('NUTRITIONIST')">
	<display:column>
		<a href="ingredient/nutritionist/removeProperty.do?valueId=${row.id}"><spring:message code="ingredient.property.remove"/></a>
	</display:column>
	</security:authorize>
	
</display:table>

<security:authorize access="hasRole('NUTRITIONIST')">
<a href="ingredient/nutritionist/delete.do?ingredientId=${ingredient.id}">
	<spring:message	code="ingredient.delete" />
</a>
</security:authorize>