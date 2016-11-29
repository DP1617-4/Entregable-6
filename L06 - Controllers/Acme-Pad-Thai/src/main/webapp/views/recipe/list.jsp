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


<security:authorize access="hasRole('USER')">
	<div>
		<a href="user/recipe/own/list.do"> <spring:message
				code="recipe.user.list.own" />
		</a>
	</div>
</security:authorize>


<spring:message code="recipe.filter"/>
<form:form action="recipe/filter.do" modelAttribute="FilterString">

	
	<form:input path="filter"/>
	
	<input type="submit" name="type"
	value ="<spring:message code="reipe.filter.button"/>" />

</form:form>



<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="recipe" requestURI="${requestURI}" id="row">
	<jstl:set var="loggedactor" value=<security:authentication property="principal.username" />/>
	<jstl:set var="recipeuser" value="${row.user}"/> 
	<jstl:if test="${recipeuser.userAccount.id==loggedactor.id}">
	<display:column>
			<a href="recipe/edit.do?recipeId=${row.id}">
				<spring:message	code="recipe.edit" />
			</a>
	</display:column>
	</jstl:if>
	
	<!-- Attributes -->
	
	<spring:message code="recipe.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<display:column>
		<a href="user/display.do?userId=${recipeuser.id}">${row.user}</a>
	</display:column>
	
	<spring:message code="recipe.authored" var="authoredHeader" />
	<display:column title="${authoredHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>
	
	
	<spring:message code="recipe.updated" var="updatedHeader" />
	<display:column title="${updatedHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />
	
	<spring:message code="recipe.score" var="scoreHeader" />
	<display:column property="score" title="${scoreHeader}" sortable="false" />
	
	<display:column>
		<a href="category/list.do?recipeId=${row.id}"><spring:message code="recipe.category.list"/></a>
	</display:column>
	
	<display:column>
		<a href="comment/list.do?recipeId=${row.id}"><spring:message code="recipe.comment.list"/></a>
	</display:column>
	
	<display:column>
		<a href="recipe/display.do?recipeId=${row.id}"><spring:message code="recipe.display"/></a>
	</display:column>
	
</display:table>

<security:authorize access="hasRole('USER')">
	<div>
		<a href="recipe/create.do"> <spring:message
				code="recipe.create" />
		</a>
	</div>
</security:authorize>