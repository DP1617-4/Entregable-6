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
	name="endorsers" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->

	<display:column>
			<a href="endorser/nutritionist/edit.do?endorserId=${row.id}">
				<spring:message	code="endorser.edit" />
			</a>
	</display:column>
	
	<!-- Attributes -->
	
	<spring:message code="endorser.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="endorser.homePage" var="homePageHeader" />
	<display:column title="${homePageHeader}" sortable="true">
		<a href="${row.homePage}">${row.homePage} </a>
	</display:column>

</display:table>

<!-- Action links -->

<br/>

<a href="endorser/nutritionist/create.do"> <spring:message
code="endorser.create" />
</a>

<br/>