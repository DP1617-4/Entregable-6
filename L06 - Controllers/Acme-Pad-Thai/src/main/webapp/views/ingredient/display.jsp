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


<h3><jstl:out value="${ingredient.name}"/></h3>
<br/>
<p><jstl:out value="${ingredient.description}"/></P>
<p><spring:message code="ingredient.pictures"/></p>
<p>

<security:authorize access="hasRole('NUTRITIONIST')">
<form:label path="picture"><spring:message code="ingregient.picture.url"/></form:label>
<form:input path="picture" />
<a href="ingredient/picture/add.do?recipeId=${ingredient.id}&picture=${picture}">
	<spring:message	code="recipe.pictures.add" />
</a>
</security:authorize>

</p>
<ul>
	<jstl:forEach var="picture" items="${recipe.pictures}" >
		<li><img src="${picture}"/></li>
	</jstl:forEach>
</ul>
<br/>

<p><spring:message	code="ingredient.properties" />
<security:authorize access="hasRole('NUTRITIONIST')">
	<form:select path="selectedProperty" >
    	<form:options items="${propertylist}" itemValue="id"  itemLabel="name" />
	</form:select>
	<a href="ingredient/addproperties.do?ingredientId=${ingredient.id}&propertytId=${selectedProperty}">
		<spring:message	code="ingredient.addproperties" />
	</a>
</security:authorize>
</p>

<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="properties" requestURI="${ingredient.values}" id="row">
	<!-- Attributes -->
	
	<spring:message code="ingredient.property.name" var="propertyHeader" />
	<display:column property="property.name" title="${propertyHeader}" sortable="false" />
	
	<spring:message code="ingredient.property.value" var="valueHeader" />
	<display:column property="value" title="${valueHeader}" sortable="false" />
	
	<security:authorize access="hasRole('NUTRITIONIST')">
	<display:column>
		<a href="ingredient/property/remove.do?valueId=${row.id}"><spring:message code="ingredient.property.remove"/></a>
	</display:column>
	</security:authorize>
	
</display:table>

<security:authorize access="hasRole('NUTRITIONIST')">
<a href="ingredient/delete.do?ingredientId=${ingredient.id}">
	<spring:message	code="ingredient.delete" />
</a>
</security:authorize>