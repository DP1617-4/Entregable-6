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
		<a href="recipe/user/listOwn.do"> <spring:message
				code="recipe.user.list.own" />
		</a>
	</div>
</security:authorize>


<spring:message code="recipe.filter"/>
<form:form action="recipe/filter.do" modelAttribute="filterString">

	<form:input path="filter"/>
	<form:errors cssClass="error" path="filter" />
	
	<input type="submit" name="filterButton"
	value ="<spring:message code="recipe.filter.button"/>" />

</form:form>



<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="recipes" requestURI="${requestURI}" id="row">
	<security:authentication property="principal" var ="loggedactor"/>
	<security:authorize access="hasRole('USER')">
	<display:column>
	<jstl:set var="recipeuser" value="${row.user}"/> 
	<jstl:choose>
		<jstl:when test="${recipeuser.userAccount.username==loggedactor.username}">
			<jstl:set value="${row.contest}" var="cont"/>
			<jstl:if test="${empty cont}">
			<a href="recipe/user/edit.do?recipeId=${row.id}">
				<spring:message	code="recipe.edit" />
			</a>
			</jstl:if>
		</jstl:when>
	</jstl:choose>
	</display:column>
	</security:authorize>

	
	<!-- Attributes -->
	<display:column property="ticker" title="Ticker" sortable="false" />
	
	<spring:message code="recipe.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="recipe.username" var="usernameHeader" />
	<display:column title="${usernameHeader }">
		<a href="user/display.do?userId=${recipeuser.id}">${row.user.name}</a>
	</display:column>
	
	<spring:message code="recipe.authored" var="authoredHeader" />
	<display:column property="authored" title="${authoredHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>
	
	
	<spring:message code="recipe.updated" var="updatedHeader" />
	<display:column property="updated" title="${updatedHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />
	
	<spring:message code="recipe.score" var="scoreHeader" />
	<display:column property="score" title="${scoreHeader}" sortable="false" />
	
	<display:column>
		<a href="comment/socialUser/list.do?recipeId=${row.id}"><spring:message code="recipe.comment.list"/></a>
	</display:column>
	
	<display:column>
		<a href="recipe/display.do?recipeId=${row.id}"><spring:message code="recipe.display"/></a>
	</display:column>
	
</display:table>
<security:authorize access="hasRole('USER')">
	<div>
		<a href="recipe/user/create.do"> <spring:message
				code="recipe.create" />
		</a>
	</div>
</security:authorize>

