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

<display:table name="profiles" id="profile"	requestURI="profile/customer/list.do" pagesize="5" class="displaytag">

	<display:column>
	 	<div style="text-align: center;">
	 		<h1><jstl:out value="${profile.name}" /></h1>			
			
			<img src="${profile.picture}" style="width: 100px; height: auto; border-style: solid;"/> <br/>
			
			<jstl:choose>
				<jstl:when test="${likes.contains(profile)}">
					<spring:message code="profile.choice.like"/>.
					<a href="profile/customer/dislike.do?profileId=${profile.id}">
						<spring:message code="profile.choice.change"/>
					</a> 
				</jstl:when>
				<jstl:otherwise>
					<spring:message code="profile.choice.dislike"/>.					
					<a href="profile/customer/like.do?profileId=${profile.id}">
						<spring:message code="profile.choice.change"/>
					</a>	
				</jstl:otherwise>
			</jstl:choose>					
		</div>
	</display:column>

</display:table>
