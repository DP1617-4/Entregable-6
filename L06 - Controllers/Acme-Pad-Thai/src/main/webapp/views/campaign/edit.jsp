<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="campaign/sponsor/edit.do" modelAttribute="campaign">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="deleted"/>
	<form:hidden path="sponsor"/>
	<form:hidden path="starred"/>

	<form:label path="startDate">
		<spring:message code="campaign.startDate" />:
	</form:label>
	<form:input placeholder="dd/MM/yyyy HH:mm" path="startDate" />
	<form:errors cssClass="error" path="startDate" />
	<br />
	
	<form:label path="endDate">
		<spring:message code="campaign.endDate" />:
	</form:label>
	<form:input placeholder="dd/MM/yyyy HH:mm" path="endDate" />
	<form:errors cssClass="error" path="endDate" />
	<br />
	
<%-- 	<form:label path="starred">
		<spring:message code="campaign.starred" />:
	</form:label>
	<form:input path="starred" />
	<form:errors cssClass="error" path="starred" />
	<br /> --%>
	
	<input type="submit" name="save"
		value="<spring:message code="campaign.save" />" />&nbsp; 
		
	<jstl:if test="${campaign.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="campaign.delete" />"
			onclick="return confirm('<spring:message code="campaign.confirm.delete" />')" />&nbsp;
	</jstl:if>
	
	<jstl:if test="${sponsor.id != 0}">
		<input type="button" name="cancel"
		value="<spring:message code="campaign.cancel" />"
		onclick="location.href ='campaign/sponsor/list.do';" />
	</jstl:if>
	
</form:form>