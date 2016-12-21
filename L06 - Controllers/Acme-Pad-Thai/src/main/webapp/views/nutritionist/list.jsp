<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>




<spring:message code="nutritionist.filter"/>
<form:form action="nutritionist/filter.do" modelAttribute="filterString">

	<form:input path="filter"/>
	<form:errors cssClass="error" path="filter" />
	
	<input type="submit" name="filterButton"
	value ="<spring:message code="nutritionist.filter.button"/>" />

</form:form>

<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="nutritionists" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->

	<security:authentication property="principal" var ="loggedactor"/>
	<display:column>
	<jstl:set var="nutritionist" value="${row.id}"/> 
	
			<a href="nutritionist/display.do?nutritionistId=${row.id}">
				<spring:message	code="nutritionist.display" />
			</a>
	
	</display:column>

	<security:authorize access="hasAnyRole('NUTRITIONIST', 'USER')">
	 <display:column>	
		<jstl:if test="${loggedactor != row.userAccount}">		
			<jstl:choose>
				<jstl:when test="${followed.contains(row)}">
					<spring:message code="nutritionist.unfollow"/>.
					<a href="nutritionist/socialuser/unfollow.do?nutritionistId=${row.id}">
						<spring:message code="nutritionist.unfollow.change"/>
					</a> 
				</jstl:when>
				<jstl:otherwise>
					<spring:message code="nutritionist.follow"/>.					
					<a href="nutritionist/socialuser/follow.do?nutritionistId=${row.id}">
						<spring:message code="nutritionist.follow.change"/>
					</a>	
				</jstl:otherwise>
			</jstl:choose>
		</jstl:if>					
	</display:column> 
	</security:authorize>
	
	<!-- Attributes -->
	
	<spring:message code="nutritionist.name" var="nameHeader"/>
	<display:column property="name" title="${nameHeader}"/>
	
	<spring:message code="nutritionist.surname" var="surnameHeader"/>
	<display:column property="surname" title="${surnameHeader}"/>
	
	<spring:message code="nutritionist.email" var="emailHeader"/>
	<display:column property="email" title="${emailHeader}"/>
	
	<spring:message code="nutritionist.phone" var="phoneHeader"/>
	<display:column property="phone" title="${phoneHeader}"/>
	
	<spring:message code="nutritionist.postalAddress" var="postalAddressHeader"/>
	<display:column property="postalAddress" title="${postalAddressHeader}"/>

</display:table>
 