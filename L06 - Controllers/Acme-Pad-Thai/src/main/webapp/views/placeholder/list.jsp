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
	name="placeholders" requestURI="${requestURI}" id="row">
	<display:column>
		<a href="placeholder/actor/edit.do?placeholderId=${row.id}">
			<spring:message	code="placeholder.edit" />
		</a>
	</display:column>
	
	
	<!-- Attributes -->
	
	<spring:message code="placeholder.nick" var="nickHeader" />
	<display:column property="unica" title="${nickHeader}" sortable="true" />

	<spring:message code="placeholder.picture" var="pictureHeader" />
	<display:column property="normal" title="${pictureHeader}" sortable="false"/>

	<spring:message code="placeholder.network.link" var="linkHeader" />
	<display:column property="quantity" title="${linkHeader}" sortable="false" />
	
	<spring:message code="placeholder.network.name" var="nameHeader" />
	<display:column property="numero" title="${nameHeader}" sortable="false" />
	
</display:table>

<div>
	<a href="placeholder/actor/create.do"> <spring:message
			code="placeholder.create" />
	</a>
</div>