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
		<a href="campaign/sponsor/list.do"></a>
	</div>
</security:authorize>

<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="campaigns" requestURI="campaign/sponsor/list.do" id="row">
	<security:authentication property="principal" var ="loggedsponsor"/>
	<jstl:set var="campaignsponsor" value="${row.sponsor}"/>
	
	<display:column>
		<jstl:if test="${campaignsponsor.userAccount.username==loggedsponsor.username}">
			<a href="campaign/sponsor/edit.do?campaignId=${row.id}">
				<spring:message	code="campaign.edit" />
			</a>
		</jstl:if>
	</display:column>
	
	<display:column>
		<jstl:if test="${campaignsponsor.userAccount.username==loggedsponsor.username}">
			<a href="banner/sponsor/create.do?campaignId=${row.id}">
				<spring:message	code="campaign.addbanners" />
			</a>
		</jstl:if>
	</display:column>
	
	<!-- Attributes -->
	<spring:message code="campaign.startDate" var="startDateHeader" />
	<display:column property="startDate" title="${startDateHeader}" sortable="true"  format="{0,date,dd/MM/yyyy HH:mm}">
	</display:column>

	<spring:message code="campaign.endDate" var="endDateHeader" />
	<display:column property="endDate" title="${endDateHeader}" sortable="true"  format="{0,date,dd/MM/yyyy HH:mm}">
	</display:column>
	
	<spring:message code="campaign.starred" var="starredHeader" />
	<display:column property="starred" title="${starredHeader}" sortable="true" />
	
	<spring:message code="campaign.banners" var="bannersHeader" />
	<display:column property="banners" title="${bannersHeader}" sortable="false">
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
	<a href="campaign/sponsor/create.do"> <spring:message
		code="campaign.create" />
	</a>
</div>
</security:authorize>