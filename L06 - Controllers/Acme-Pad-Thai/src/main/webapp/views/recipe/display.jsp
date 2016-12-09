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


<security:authentication property="principal" var ="loggedactor"/>
<jstl:set var="recipeuser" value="${recipe.user}"/> 

<h3>${recipe.title}</h3>
<br/>
<p>${recipe.summary}</P>
<p>${recipe.ticker}</P>
<P><spring:message code="recipe.authored"/>${recipe.authored}</P>
<P><spring:message code="recipe.updated"/>${recipe.updated}</p>
<p><spring:message code="recipe.pictures"/></p>
<p>

<jstl:if test="${recipeuser.userAccount.username==loggedactor}">
<form:label path="picture"><spring:message code="recipe.picture.url"/></form:label>
<form:input path="picture" />
<a href="recipe/user/picture/add.do?recipeId=${recipe.id}&picture=${picture}">
	<spring:message	code="recipe.pictures.add" />
</a>
</jstl:if>

</p>
<ul>
	<jstl:forEach var="picture" items="${recipe.pictures}" >
		<li><img src="${picture}"/></li>
	</jstl:forEach>
</ul>
<p>${recipe.hints}"</p>
<p>${recipe.score}"</p>
<br/>

<p><spring:message	code="recipe.ingredients" />
<jstl:if test="${recipeuser.userAccount.username==loggedactor}">
	<form:select path="selectedIngredient" >
    	<form:options items="${ingredients}" itemValue="id"  itemLabel="name" />
	</form:select>
	<a href="recipe/user/addingredients.do?recipeId=${recipe.id}&ingredientId=${selectedIngredient}">
		<spring:message	code="recipe.addingredients" />
	</a>
</jstl:if>
</p>

<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="quantities" requestURI="recipe/display.do" id="row">
	<!-- Attributes -->
	
	<security:authorize access="hasRole('NUTRITIONIST')">
	<display:column sortable="true">
		<a href="ingredient/nutritionist/display.do?ingredientId=${row.ingredient.id}">${row.ingredient.name}</a>
	</display:column>
	</security:authorize>
	
	<spring:message code="recipe.ingredient.quantity" var="quantityHeader" />
	<display:column property="quantity" title="${quantityHeader}" sortable="false" />
	
	<spring:message code="recipe.ingredient.unit" var="unitHeader" />
	<display:column property="unit" title="${unitHeader}" sortable="false" />
	
	<jstl:if test="${recipeuser.userAccount.username==loggedactor}">
	<display:column>
		<a href="recipe/user/removeIngredient.do?quantityId=${row.id}"><spring:message code="recipe.ingredient.remove"/></a>
	</display:column>
	</jstl:if>
	
	
</display:table>



<p><spring:message	code="recipe.steps" />
<jstl:set var="recipeuser" value="${recipe.user}"/> 
<jstl:if test="${recipeuser.userAccount.username==loggedactor}">
	<a href="recipe/user/addsteps.do?recipeId=${recipe.id}">
		<spring:message	code="recipe.addsteps" />
	</a>
</jstl:if>
</p>

<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="steps" requestURI="recipe/display.do" id="row">
	<!-- Attributes -->
	
	<spring:message code="recipe.step.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />
	
	<spring:message code="recipe.step.hints" var="hintsHeader" />
	<display:column property="hints" title="${hintsHeader}" sortable="true" />
	
	<spring:message code="recipe.step.pictures" var="picturesHeader" />
	<display:column property="pictures" title="${picturesHeader}" sortable="false" />
	
	<jstl:if test="${recipeuser.userAccount.username==loggedactor}">
	<display:column>
		<a href="recipe/user/deleteStep.do?stepId=${row.id}"><spring:message code="recipe.step.remove"/></a>
	</display:column>
	</jstl:if>
	
</display:table>

<p><a href="comment/list.do?recipeId=${recipe.id}"><spring:message code="recipe.comment.list"/></a></p>

<security:authorize access="hasAnyRole('USER', 'NUTRITIONIST')">
<p><a href="comment/socialUser/add.do?recipeId=${recipe.id}"><spring:message code="recipe.comment.do"/></a></p>
</security:authorize>

<jstl:if test="${recipeuser.userAccount.username==loggedactor}">
<a href="recipe/user/delete.do?recipeId=${recipe.id}">
	<spring:message	code="recipe.delete" />
</a>
</jstl:if>