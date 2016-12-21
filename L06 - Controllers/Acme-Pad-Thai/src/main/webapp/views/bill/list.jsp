<%--
 * list.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="bills" requestURI="${requestURI}" id="row">
	<security:authentication property="principal" var ="loggedsponsor"/>
	<jstl:set var="billsponsor" value="${row.sponsor}"/>
	 
	 
	 
	<display:column>
		<jstl:if test="${row.paymentDate == null}">
			<jstl:if test="${billsponsor.userAccount.username==loggedsponsor.username}">
				<a href="bill/sponsor/pay.do?billId=${row.id}">
					<spring:message	code="bill.pay" />
				</a>
			</jstl:if>
		</jstl:if>
	</display:column>
	
	<!-- Attributes -->
	<spring:message code="bill.creationDate" var="creationDateHeader" />
	<display:column property="creationDate" title="${creationDateHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" >
	</display:column>

	<spring:message code="bill.paymentDate" var="paymentDateHeader" />
	<display:column property="paymentDate" title="${paymentDateHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" >
	</display:column>
	
	<spring:message code="bill.cost" var="costHeader" />
	<display:column property="cost" title="${costHeader}" sortable="true" />
	
	<spring:message code="bill.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />
	
</display:table>