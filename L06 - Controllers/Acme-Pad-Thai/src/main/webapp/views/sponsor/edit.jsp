<%--
 * create.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="${requestURI}" modelAttribute="sponsor">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />
	<form:hidden path="userAccount.id" />
	<form:hidden path="userAccount.version" />
	<form:hidden path="enroled" />
	<form:hidden path="folders" />
	<form:hidden path="socialIdentities" />
	<form:hidden path="creditCard.id" />
	<form:hidden path="creditCard.version" />
	<form:hidden path="campaigns" />
	<form:hidden path="bills" />

	<jstl:if test="${sponsor.id == 0}">
		<form:label path="userAccount.username">
			<td><spring:message code="sponsor.username" /></td>
		</form:label>
		<td><form:input path="userAccount.username" /></td>
		<form:errors cssClass="error" path="userAccount.username" />
		<br />
		<br />
		<form:label path="userAccount.password">
			<spring:message code="sponsor.password" />
		</form:label>
		<form:password path="userAccount.password" />
		<form:errors cssClass="error" path="userAccount.password" />
		<br />
		<br />
	</jstl:if>

	<jstl:if test="${sponsor.id != 0}">
		<form:hidden path="userAccount.username" />
		<form:hidden path="userAccount.password" />
	</jstl:if>

	<form:label path="name">
		<spring:message code="sponsor.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	<br />
	<form:label path="surname">
		<spring:message code="sponsor.surname" />:
	</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />
	<br />
	<form:label path="email">
		<spring:message code="sponsor.email" />:
	</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email" />
	<br />
	<br />
	<form:label path="postalAddress">
		<spring:message code="sponsor.postalAddress" />:
	</form:label>
	<form:textarea path="postalAddress" />
	<form:errors cssClass="error" path="postalAddress" />
	<br />
	<br />
	<form:label path="phone">
		<spring:message code="sponsor.phone" />:
	</form:label>
	<form:input path="phone" />
	<form:errors cssClass="error" path="phone" />
	<br />
	<br />
	<form:label path="companyName">
		<spring:message code="sponsor.companyName" />:
	</form:label>
	<form:input path="companyName" />
	<form:errors cssClass="error" path="companyName" />
	<br />

	<br />
	<div style="padding-left: 15px;">
		<b><font size=5><spring:message
					code="sponsor.creditCard.create" /></font></b>
	</div>
	<br />

	<form:label path="creditCard.holderName">
		<spring:message code="sponsor.creditCard.holderName" />:
	</form:label>
	<form:input path="creditCard.holderName" />
	<form:errors cssClass="error" path="creditCard.holderName" />
	<br />
	<br />v
	<form:label path="creditCard.brandName">
		<spring:message code="sponsor.creditCard.brandName" />:
	</form:label>
	<form:input path="creditCard.brandName" />
	<form:errors cssClass="error" path="creditCard.brandName" />
	<br />
	<br />
	<form:label path="creditCard.creditCardNumber">
		<spring:message code="sponsor.creditCard.cCNumber" />:
	</form:label>
	<form:input path="creditCard.creditCardNumber" />
	<form:errors cssClass="error" path="creditCard.creditCardNumber" />
	<br />
	<br />
	<form:label path="creditCard.expirationMonth">
		<spring:message code="sponsor.creditCard.expirationMonth" />:
	</form:label>
	<form:input path="creditCard.expirationMonth" />
	<form:errors cssClass="error" path="creditCard.expirationMonth" />
	<br />
	<br />
	<form:label path="creditCard.expirationYear">
		<spring:message code="sponsor.creditCard.expirationYear" />:
	</form:label>
	<form:input path="creditCard.expirationYear" />
	<form:errors cssClass="error" path="creditCard.expirationYear" />
	<br />
	<br />
	<form:label path="creditCard.CVV">
		<spring:message code="sponsor.creditCard.CVV" />:
	</form:label>
	<form:input path="creditCard.CVV" />
	<form:errors cssClass="error" path="creditCard.CVV" />
	<br />

	<br />
	<input type="submit" name="save"
		value="<spring:message code="sponsor.save" />" /> &nbsp;
		
	<jstl:if test="${sponsor.id != 0}">
		<input type="button" name="cancel"
			value="<spring:message code="sponsor.cancel" />"
			onclick="location.href ='sponsor/display.do';" />
	</jstl:if>

	<jstl:if test="${sponsor.id == 0}">
		<input type="button" name="cancel"
			value="<spring:message code="sponsor.cancel" />"
			onclick="location.href = 'welcome/index.do';" />
	</jstl:if>
	<br />

</form:form>