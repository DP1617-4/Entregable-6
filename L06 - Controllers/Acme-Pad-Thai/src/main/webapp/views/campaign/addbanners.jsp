<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="campaign/edit.do" modelAttribute="campaign">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="campaign"/>
	<form:hidden path="sponsor"/>
	<form:hidden path='maxNumber'/>
	<form:hidden path='timesShown'/>
	<form:hidden path='timesShownMonth'/>
	
	<form:label path="URL">
		<spring:message code="campaign.banner.URL" />:
	</form:label>
	<form:textarea path="URL" />
	<form:errors cssClass="error" path="URL" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="campaign.save" />" 
		onclick="javascript: relativeRedir('campaign/list.do?campaignId=${campaign.id}');" />&nbsp; 	
		
	<input type="button" name="cancel"
		value="<spring:message code="campaign.cancel" />"
		onclick="javascript: relativeRedir('campaign/list.do?campaignId=${campaign.id}');" />&nbsp;
	<br />
	
</form:form>