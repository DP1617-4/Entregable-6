<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<display:table pagesize="10" class="displaytag" keepStatus="true"
name="user" requestURI="${requestURI}" id="row">

	<jstl:set var="loggedactor" value=<security:authentication property="principal.username" />/>
	<jstl:set var="user" value="${row}"/> 

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
	
	<display:column>
		<a href="recipe/list.do?userId=${row.id}"> <spring:message
			code="user.recipes.list" />
		</a>
	</display:column>
	<jstl:if test="${loggedactor == user.userAccount.username }">
		<display:column>
			<a href="user/edit.do?userId=${row.id}"> <spring:message
					code="user.edit" />
			</a>
		</display:column>
	</jstl:if>
</display:table>
<br/>

	

<br/>



<br/>