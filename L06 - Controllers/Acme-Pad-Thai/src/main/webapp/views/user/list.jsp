<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<spring:message code="user.filter"/>
<form:form action="user/filter.do" modelAttribute="FilterString">

	
	<form:input path="filter"/>
	
	<input type="submit" name="type"
	value ="<spring:message code="user.filter.button"/>" />

</form:form>

<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="users" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->

	<jstl:set var="loggedactor" value=<security:authentication property="principal.username" />/>
	<display:column>
		<jstl:if test="${user.userAccount!=loggedactor}">
			<jstl:set var="contains" value="false" />
				<jstl:forEach var=user items="${loggedactor.followed}">
 					<jstl:if test="${user.id == followed.id}">
    					<jstl:set var="contains" value="true" />
 					</jstl:if>
				</jstl:forEach>
				<jstl:if test="${contains == true}">
    					<input type="button" name="unfollow"
						value="<spring:message code="user.unfollow" />"/>
 				</jstl:if>
 				<jstl:if test="${contains == false}">
    					<input type="button" name="follow"
						value="<spring:message code="user.follow" />"/>
 				</jstl:if>
		</jstl:if>
	</display:column>
	
	
	<!-- Attributes -->
	
	<spring:message code="user.name" var=nameHeader/>
	<display:column property="name" title="${nameHeader}"/>
	
	<spring:message code="user.surname" var=surnameHeader/>
	<display:column property="surname" title="${surnameHeader}"/>
	
	<spring:message code="user.email" var=emailHeader/>
	<display:column property="email" title="${emailHeader}"/>
	
	<spring:message code="user.phone" var=phoneHeader/>
	<display:column property="phone" title="${phoneHeader}"/>
	
	<spring:message code="user.postalAddress" var=postalAddressHeader/>
	<display:column property="postalAddress" title="${postalAddressHeader}"/>

</display:table>
