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

<h2>${recipe.title}</h2>
<h3>${recipe.summary}</h3>
<p><b>ticker: </b>${recipe.ticker}</P>
<P><b><spring:message code="recipe.authored"/>:</b> ${recipe.authored}</P>
<P><b><spring:message code="recipe.updated"/>:</b> ${recipe.updated}</p>
<h4><spring:message code="recipe.pictures"/></h4>
<p>
<security:authorize access="hasRole('USER')">
<jstl:if test="${recipeuser.userAccount.username==loggedactor.username}">
<form:form action="recipe/user/addPicture.do" modelAttribute="addPicture">

	<form:hidden path="id" value="${recipe.id}"/>
	
	<form:input path="picture"/>
	<form:errors cssClass="error" path="picture" />
	
	<input type="submit" name="addImage"
	value ="<spring:message code="recipe.pictures.add"/>" />

</form:form>
</jstl:if>
</security:authorize>

</p>
	<jstl:forEach var="thisPicture" items="${recipe.pictures}" >
		<img src="${thisPicture}" alt="${thisPicture}" height="150"> &nbsp;
	</jstl:forEach>
<p>${recipe.hints}</p>
<p><b><spring:message code="recipe.score"/>: </b>${recipe.score}</p>
<br/>

<h2><spring:message	code="recipe.ingredients" /></h2>
<security:authorize access="hasRole('USER')">
<jstl:if test="${recipeuser.userAccount.username==loggedactor.username}">
	<form:form action="recipe/user/addIngredient.do" modelAttribute="addIngredient">

	<form:hidden path="recipeId" value="${recipe.id}"/>
	
	<form:select path="ingredientId" >
    	<form:options items="${ingredients}" itemValue="id"  itemLabel="name" />
	</form:select>
	
	<input type="submit" name="addImage"
	value ="<spring:message code="recipe.addingredients"/>" />

</form:form>
</jstl:if>
</security:authorize>

<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="quantities" requestURI="recipe/display.do" id="row">
	<!-- Attributes -->
	
	
	<display:column sortable="true">
		<a href="ingredient/display.do?ingredientId=${row.ingredient.id}">${row.ingredient.name}</a>
	</display:column>
	
	<spring:message code="recipe.ingredient.quantity" var="quantityHeader" />
	<display:column property="quantity" title="${quantityHeader}" sortable="false" />
	
	<spring:message code="recipe.ingredient.unit" var="unitHeader" />
	<display:column property="unit" title="${unitHeader}" sortable="false" />
	
	<security:authorize access="hasRole('USER')">
	<display:column>
	<jstl:choose>
		<jstl:when test="${recipeuser.userAccount.username==loggedactor.username}">
			<a href="recipe/user/removeIngredient.do?quantityId=${row.id}"><spring:message code="recipe.ingredient.remove"/></a>
		</jstl:when>
	</jstl:choose>
	</display:column>
	</security:authorize>
	
	
</display:table>



<h2><spring:message	code="recipe.steps" /></h2>
<security:authorize access="hasRole('USER')">
<jstl:if test="${recipeuser.userAccount.username==loggedactor.username}">
	<a href="recipe/user/addsteps.do?recipeId=${recipe.id}">
		<b><spring:message	code="recipe.addsteps" /></b>
	</a>
</jstl:if>
</security:authorize>


<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="steps" requestURI="recipe/display.do" id="row">
	<!-- Attributes -->
	
	<spring:message code="recipe.step.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />
	
	<spring:message code="recipe.step.hints" var="hintsHeader" />
	<display:column property="hints" title="${hintsHeader}" sortable="true" />
	
	<spring:message code="recipe.step.pictures" var="picturesHeader" />
	<display:column property="pictures" title="${picturesHeader}" sortable="false" />
	
	<security:authorize access="hasRole('USER')">
	<display:column>
	<jstl:if test="${recipeuser.userAccount.username==loggedactor.username}">
		<a href="recipe/user/deleteStep.do?stepId=${row.id}"><spring:message code="recipe.step.remove"/></a>
	</jstl:if>
	</display:column>
	</security:authorize>
	
</display:table>

<a href="comment/list.do?recipeId=${recipe.id}"><spring:message code="recipe.comment.list"/></a>

<security:authorize access="hasAnyRole('USER', 'NUTRITIONIST')">
	<a href="comment/socialUser/add.do?recipeId=${recipe.id}"><spring:message code="recipe.comment.do"/></a>
</security:authorize>
<security:authorize access="hasRole('USER')">
<jstl:if test="${recipeuser.userAccount.username==loggedactor.username}">
<a href="recipe/user/delete.do?recipeId=${recipe.id}">
	<spring:message	code="recipe.delete" />
</a>
</jstl:if>
</security:authorize>