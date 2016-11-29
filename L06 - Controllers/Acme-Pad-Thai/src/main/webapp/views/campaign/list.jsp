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

<security:authorize access="hasRole('SPONSOR')">
	<div>
		<a href="sponsor/campaign/list.do"> 
			<spring:message code="campaign.list.own" />
		</a>
	</div>
</security:authorize>

<p><spring:message code="campaign.banners" />
</p>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="campaigns" requestURI="campaign/list.do" id="row">
	<jstl:set var="loggedsponsor" value=<security:authentication property="principal.username" /> />
	<jstl:set var="campaignsponsor" value="${row.user}"/>
	 
	<display:column>
		<jstl:if test="${campaignsponsor.userAccount==loggedsponsor}">
			<a href="campaign/edit.do?campaignId=${row.id}">
				<spring:message	code="campaign.edit" />
			</a>
		</jstl:if>
	</display:column>
	
	<display:column>
		<jstl:if test="${campaignsponsor.userAccount==loggedsponsor}">
			<a href="banner/create.do?campaignId=${row.id}">
				<spring:message	code="campaign.addbanners" />
			</a>
		</jstl:if>
	</display:column>
	
	<!-- Attributes -->
	<spring:message code="campaign.startDate" var="startDateHeader" />
	<display:column title="${startDateHeader}" sortable="true" >
		<fmt:formatDate value="${row.startDate}"pattern ="dd/mm/yyyy"/>
	</display:column>

	<spring:message code="campaign.endDate" var="endDateHeader" />
	<display:column title="${endDateHeader}" sortable="true" >
		<jstl:out value="<fmt:formatDate value="${row.endDate}"pattern ="dd/mm/yyyy"/>" />
	</display:column>
	
	<spring:message code="campaign.starred" var="starredHeader" />
	<display:column property="starred" title="${starredHeader}" sortable="true" />
	
	
	<display:column>
		<a href="banner/list.do?campaignId=${row.id}"> <spring:message code="campaign.banner.list" /> </a>
	</display:column>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="banner/star.do?campaignId=${row.id}"><spring:message code="campaign.star" /></a>
		</display:column>
	</security:authorize>
</display:table>

<security:authorize access="hasRole('SPONSOR')">
<div>
	<a href="campaign/create.do"> <spring:message
		code="campaign.create" />
	</a>
</div>
</security:authorize>