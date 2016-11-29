<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Probablemente esta vista no se use porque es identica a List y pasaremos distintos parámetros a List. -->

<display:table pagesize="10" class="displaytag" keepStatus="true"
name="category" requestURI="${requestURI}" id="row">

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

<br/>

	<a href="category/edit.do"><spring:message code="category.edit"/></a>
	
<br />

<input type="button" name="list"
		value="<spring:message code="category.return.list" />"
		onclick="javascript: relativeRedir('category/list.do');" />
	<br />