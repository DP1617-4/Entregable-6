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
<form:form action="user/filter.do" modelAttribute="filterString">

	<form:input path="filter"/>
	<form:errors cssClass="error" path="filter" />
	
	<input type="submit" name="filterButton"
	value ="<spring:message code="user.filter.button"/>" />

</form:form>

<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="users" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->

	<security:authentication property="principal" var ="loggedactor"/>
	<display:column>
	<jstl:set var="user" value="${row.id}"/> 
	
			<a href="user/display.do?userId=${row.id}">
				<spring:message	code="user.display" />
			</a>
	
	</display:column>

	<security:authorize access="hasAnyRole('NUTRITIONIST', 'USER')">
	 <display:column>	
		<jstl:if test="${loggedactor != row.userAccount }">		
			<jstl:choose>
				<jstl:when test="${followed.contains(row)}">
					<spring:message code="user.unfollow"/>.
					<a href="user/socialuser/unfollow.do?userId=${row.id}">
						<spring:message code="user.unfollow.change"/>
					</a> 
				</jstl:when>
				<jstl:otherwise>
					<spring:message code="user.follow"/>.					
					<a href="user/socialuser/follow.do?userId=${row.id}">
						<spring:message code="user.follow.change"/>
					</a>	
				</jstl:otherwise>
			</jstl:choose>
		</jstl:if>					
	</display:column> 
	</security:authorize>
	
	<!-- Attributes -->
	
	<spring:message code="user.name" var="nameHeader"/>
	<display:column property="name" title="${nameHeader}"/>
	
	<spring:message code="user.surname" var="surnameHeader"/>
	<display:column property="surname" title="${surnameHeader}"/>
	
	<spring:message code="user.email" var="emailHeader"/>
	<display:column property="email" title="${emailHeader}"/>
	
	<spring:message code="user.phone" var="phoneHeader"/>
	<display:column property="phone" title="${phoneHeader}"/>
	
	<spring:message code="user.postalAddress" var="postalAddressHeader"/>
	<display:column property="postalAddress" title="${postalAddressHeader}"/>

</display:table>

<br/>
<security:authorize access="hasAnyRole('NUTRITIONIST', 'USER')">
	<a href="recipe/socialuser/listFollowed.do">
						<spring:message code="user.recipes.followed"/>
	</a> 
</security:authorize>