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
name="sponsor data" requestURI="${requestURI}" id="row">

	<spring:message code="sponsor.name" var=nameHeader/>
	<display:column property="name" title="${nameHeader}"/>
	
	<spring:message code="sponsor.surname" var=surnameHeader/>
	<display:column property="surname" title="${surnameHeader}"/>
	
	<spring:message code="sponsor.email" var=emailHeader/>
	<display:column property="email" title="${emailHeader}"/>
	
	<spring:message code="sponsor.postalAddress" var=postalAddressHeader/>
	<display:column property="postalAddress" title="${postalAddressHeader}"/>
	
	<spring:message code="sponsor.phone" var=phoneHeader/>
	<display:column property="phone" title="${phoneHeader}"/>
	
	<spring:message code="sponsor.companyName" var=companyNameHeader/>
	<display:column property="companyName" title="${companyNameHeader}"/>
	
</display:table>
<br/>

<input type="button" name="edit sponsor"
onclick="javascript: relativeRedir('sponsor/edit.do');" />
<br/>

<input type="button" name="view credit card"
onclick="javascript: relativeRedir('creditCard/display.do');" />
<br/>

<jstl:if test="${sponsor.campaign != null}">
	<input type="button" name="view campaigns"
		onclick="javascript: relativeRedir('campaing/list.do');" />
</jstl:if>
<br/>

<jstl:if test="${sponsor.campaign == null}">
	<input type="button" name="create campaign"
		onclick="javascript: relativeRedir('campaing/create.do');" />
</jstl:if>
<br/>

<jstl:if test="${sponsor.bill != null}">
	<input type="button" name="pay bills"
		onclick="javascript: relativeRedir('bill/list.do');" />
</jstl:if>
<br/>