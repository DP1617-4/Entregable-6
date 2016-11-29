<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="bill/edit.do" modelAttribute="bill">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="sponsor"/>
	<form:hidden path="creationDate"/>
	<form:hidden path="paymentDate"/>
	<form:hidden path="cost"/>
	
	<form:label path="description">
		<spring:message code="bill.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	
	<input type="button" name="back"
		value="<spring:message code="bill.back" />"
		onclick="javascript: relativeRedir('welcome/index.do');" />
	<br />
	
</form:form>