<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="systemConfiguration/edit.do" modelAttribute="systemConfiguration">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:label path="keywords">
		<spring:message code="systemConfiguration.keywords" />:
	</form:label>
	<form:textarea path="keywords" />
	<form:errors cssClass="error" path="keywords" />
	<br />
	
	<form:label path="fee">
		<spring:message code="systemConfiguration.fee" />:
	</form:label>
	<form:input path="fee" />
	<form:errors cssClass="error" path="fee" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="systemConfiguration.save" />" /> &nbsp;
		
	<jstl:if test="${admin.id == 0}">
		<input type="button" name="cancel"
		value="<spring:message code="systemConfiguration.cancel" />"
		onclick="javascript: relativeRedir('welcome/index.do');" />
	</jstl:if>
	<br />
	
</form:form>