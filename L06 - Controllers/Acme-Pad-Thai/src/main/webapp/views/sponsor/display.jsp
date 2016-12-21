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
name="sponsor" requestURI="${requestURI}" id="row">

	<spring:message code="sponsor.name" var="nameHeader"/>
	<display:column property="name" title="${nameHeader}"/>
	
	<spring:message code="sponsor.surname" var="surnameHeader"/>
	<display:column property="surname" title="${surnameHeader}"/>
	
	<spring:message code="sponsor.email" var="emailHeader"/>
	<display:column property="email" title="${emailHeader}"/>
	
	<spring:message code="sponsor.postalAddress" var="postalAddressHeader"/>
	<display:column property="postalAddress" title="${postalAddressHeader}"/>
	
	<spring:message code="sponsor.phone" var="phoneHeader"/>
	<display:column property="phone" title="${phoneHeader}"/>
	
	<spring:message code="sponsor.companyName" var="companyNameHeader"/>
	<display:column property="companyName" title="${companyNameHeader}"/>
	
	<spring:message code="sponsor.creditCard.holderName" var="holderName"/>
	<display:column property="creditCard.holderName" title="${holderName}"/>
	
	<spring:message code="sponsor.creditCard.brandName" var="brandName"/>
	<display:column property="creditCard.brandName" title="${brandName}"/>
	
<%-- 	<spring:message code="sponsor.creditCard.cCNumber" var="cCNumber"/>
	<display:column property="creditCard.cCNumber" title="${cCNumber}"/> --%>
	
	<spring:message code="sponsor.creditCard.expirationMonth" var="expirationMonth"/>
	<display:column property="creditCard.expirationMonth" title="${expirationMonth}"/>
	
	<spring:message code="sponsor.creditCard.expirationYear" var="expirationYear"/>
	<display:column property="creditCard.expirationYear" title="${expirationYear}"/>
	
	<spring:message code="sponsor.creditCard.CVV" var="CVV"/>
	<display:column property="creditCard.CVV" title="${CVV}"/>
	
</display:table>

	<a href="sponsor/edit.do"><spring:message code="sponsor.edit"/></a>