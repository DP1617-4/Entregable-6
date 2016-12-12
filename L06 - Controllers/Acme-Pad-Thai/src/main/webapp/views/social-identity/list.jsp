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
	name="socialIdentities" requestURI="socialidentity/list.do" id="row">
	<display:column>
		<a href="socialidentity/edit.do?socialidentityId=${row.id}">
			<spring:message	code="socialidentity.edit" />
		</a>
	</display:column>
	
	
	<!-- Attributes -->
	
	<spring:message code="socialidentity.nick" var="nickHeader" />
	<display:column property="nick" title="${nickHeader}" sortable="true" />

	<spring:message code="socialidentity.picture" var="pictureHeader" />
	<display:column title="${pictureHeader}" sortable="false">
		<img src="${row.picture}" />
	</display:column>

	<spring:message code="socialidentity.network.link" var="linkHeader" />
	<display:column title="${linkHeader}" sortable="false" >
		<a href="${row.socialNetworkLink}"><spring:message code="socialidentity.network.link"/></a>
	</display:column>
	
	<spring:message code="socialidentity.network.name" var="nameHeader" />
	<display:column property="socialNetworkName" title="${nameHeader}" sortable="false" />
	
</display:table>

<div>
	<a href="socialidentity/create.do"> <spring:message
			code="socialidentity.create" />
	</a>
</div>