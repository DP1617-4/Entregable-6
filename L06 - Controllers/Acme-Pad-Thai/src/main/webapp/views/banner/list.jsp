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
	name="banners" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->

	<display:column>
			<a href="banner/edit.do?bannerId=${row.id}">
				<spring:message	code="banner.edit" />
			</a>
	</display:column>
	
	<!-- Attributes -->
	<spring:message code="banner.URL" var="URLHeader" />
	<display:column property="URL" title="${URLHeader}" sortable="false" />
	
	<spring:message code="banner.maxNumber" var="maxNumberHeader" />
	<display:column property="maxNumber" title="${maxNumberHeader}" sortable="true" />
	
	<spring:message code="banner.timesShown" var="timesShownHeader" />
	<display:column property="timesShown" title="${timesShownHeader}" sortable="true" />
	
	<spring:message code="banner.timesShownMonth" var="timesShownMonthHeader" />
	<display:column property="timesShownMonth" title="${timesShownMonthHeader}" sortable="true" />

</display:table>

<!-- Action links -->

<br/>

<a href="banner/create.do"> <spring:message
code="banner.create" />
</a>

<br/>