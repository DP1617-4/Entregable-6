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
name="creditcard" requestURI="${requestURI}" id="row">

	<spring:message code="creditCard.holderName" var=holderNameHeader/>
	<display:column property="holderName" title="${holderNameHeader}"/>
	
	<spring:message code="creditCard.brandName" var=brandNameHeader/>
	<display:column property="brandName" title="${brandNameHeader}"/>
	
	<spring:message code="creditCard.cCNumber" var=cCNumberHeader/>
	<display:column property="cCNumber" title="${cCNumberHeader}"/>
	
	<spring:message code="creditCard.expirationMonth" var=expirationMonthHeader/>
	<display:column property="expirationMonth" title="${expirationMonthHeader}"/>
	
	<spring:message code="creditCard.expirationYear" var=expirationYearHeader/>
	<display:column property="expirationYear" title="${expirationYearHeader}"/>
	
	<spring:message code="creditCard.CVV" var=CVVHeader/>
	<display:column property="CVVHeader" title="${CVVHeader}"/>
	
	<display:column>
		<a href="creditcard/edit.do?${row.id}"><spring:message code="creditCard.edit" /></a>
	</display:column>
	
</display:table>
<br/>

