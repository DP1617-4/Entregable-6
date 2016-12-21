<%--
 * action-1.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<table>
<thead>
	<tr>
		<th colspan = "3"><spring:message	code="administrator.recipes.per.user" /></th>
	</tr>
	<tr>
		<th><spring:message	code="administrator.min" /></th>
		<th><spring:message	code="administrator.average" /></th>
		<th><spring:message	code="administrator.max" /></th>
	</tr>
</thead>
<tbody>
	<tr>
		<td>${recipesPerUser[0][0]}</td>
		<td>${recipesPerUser[0][1]}</td>
		<td>${recipesPerUser[0][2]}</td>
	</tr>
</tbody>
</table>

<spring:message	code="administrator.user.most.recipes" /> <a href="user/display.do?userId=${userWithMostRecipes.id }">${userWithMostRecipes.name} ${userWithMostRecipes.surname}</a><br/>

<table>
<thead>
	<tr>
		<th colspan = "3"><spring:message	code="administrator.recipes.qualified.contest" /></th>
	</tr>
	<tr>
		<th><spring:message	code="administrator.min" /></th>
		<th><spring:message	code="administrator.average" /></th>
		<th><spring:message	code="administrator.max" /></th>
	</tr>
</thead>
<tbody>
	<tr>
		<td>${recipesQualifiedForConstest[0]}</td>
		<td>${recipesQualifiedForConstest[0]}</td>
		<td>${recipesQualifiedForConstest[0]}</td>
	</tr>
</tbody>
</table>


<spring:message	code="administrator.contest.most.qualified.recipes" /> <a href="contest/display.do?userId=${contestWithMostQualifiedRecipes.id }">${contestWithMostQualifiedRecipes.title}</a><br/>

<table>
<thead>
	<tr>
		<th colspan = "2"><spring:message	code="administrator.steps.per.recipe" /></th>
	</tr>
	<tr>
		<th><spring:message	code="administrator.average" /></th>
		<th><spring:message	code="administrator.stddev" /></th>
	</tr>
</thead>
<tbody>
	<tr>
		<td>${stepsPerRecipe[0]}</td>
		<td>${stepsPerRecipe[0]}</td>
	</tr>
</tbody>
</table>

<table>
<thead>
	<tr>
		<th colspan = "2"><spring:message	code="administrator.ingredients.per.recipe" /></th>
	</tr>
	<tr>
		<th><spring:message	code="administrator.average" /></th>
		<th><spring:message	code="administrator.stddev" /></th>
	</tr>
</thead>
<tbody>
	<tr>
		<td>${ingredientsPerRecipe[0]}</td>
		<td>${ingredientsPerRecipe[0]}</td>
	</tr>
</tbody>
</table>

<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="usersByPopularity" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->

	<security:authentication property="principal" var ="loggedactor"/>
	<display:column>
	<jstl:set var="user" value="${row.id}"/> 
	
			<a href="user/display.do?userId=${row.id}">
				<spring:message	code="administrator.user.display" />
			</a>
	
	</display:column>
	
	<!-- Attributes -->
	
	<spring:message code="administrator.user.name" var="nameHeader"/>
	<display:column property="name" title="${nameHeader}"/>
	
	<spring:message code="administrator.user.surname" var="surnameHeader"/>
	<display:column property="surname" title="${surnameHeader}"/>
	
	<spring:message code="administrator.user.email" var="emailHeader"/>
	<display:column property="email" title="${emailHeader}"/>
	
	<spring:message code="administrator.user.phone" var="phoneHeader"/>
	<display:column property="phone" title="${phoneHeader}"/>
	
	<spring:message code="administrator.user.postalAddress" var="postalAddressHeader"/>
	<display:column property="postalAddress" title="${postalAddressHeader}"/>
	
	<spring:message code="administrator.user.popularity" var="popularityHeader"/>
	<display:column title="${popularityHeader}">
		${row.followers.size()}
	</display:column>

</display:table>

<input type="button" name="back" value="<spring:message code="administrator.back"/>" 
		onclick="javascript: window.location.replace('welcome/index.do')"/>
