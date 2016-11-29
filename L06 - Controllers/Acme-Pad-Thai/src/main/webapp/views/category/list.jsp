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

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="categories" requestURI="${requestURI}" id="row">
	
	<display:column>
		<a href="admin/category/edit.do?categoryId=${row.id}" ><spring:message code="category.edit"/> </a>
	</display:column>
	
	<!-- Attributes -->
	
	<spring:message code="category.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />
	<spring:message code="category.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="true" />
	<spring:message code="category.picture" var="pictureHeader" />
	<display:column property="picture" title="${pictureHeader}" sortable="true" />
	<spring:message code="category.tag" var="tagHeader" />
	<display:column property="tag" title="${tagHeader}" sortable="true" />
	<spring:message code="category.father" var="fatherHeader"/>
	<display:column title="${fatherHeader}">
		<a href="category/display.do?categoryId=${row.father.id}"><spring:message code="category.father"/> </a>
	</display:column>
	<spring:message code="category.sons" var="sonsHeader"/>
	<display:column title="${sonsHeader}">
		<jstl:forEach items="${row.sons}" var="son">
			<a href="category/display.do?categoryId=${son.id}"><jstl:out value="${son.name}"/> </a>
		</jstl:forEach>
	</display:column>
	
</display:table>

	<!-- Action links -->

<div>
	<a href="category/create.do"> <spring:message
			code="category.create" />
	</a>
</div>