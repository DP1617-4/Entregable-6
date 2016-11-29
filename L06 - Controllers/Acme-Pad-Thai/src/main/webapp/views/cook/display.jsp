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
name="cook" requestURI="${requestURI}" id="row">

	<spring:message code="cook.name" var=nameHeader/>
	<display:column property="name" title="${nameHeader}"/>
	
	<spring:message code="cook.surname" var=surnameHeader/>
	<display:column property="surname" title="${surnameHeader}"/>
	
	<spring:message code="cook.email" var=emailHeader/>
	<display:column property="email" title="${emailHeader}"/>
	
	<spring:message code="cook.phone" var=phoneHeader/>
	<display:column property="phone" title="${phoneHeader}"/>
	
	<spring:message code="cook.postalAddress" var=postalAddressHeader/>
	<display:column property="postalAddress" title="${postalAddressHeader}"/>
	
</display:table>

<br/>

	<a href="masterclass/list.do?cookId=${cook.id}"><spring:message code="cook.list.master.classes"/></a>

<br />
	<a href="cook/edit.do"><spring:message code="cook.edit"/></a>
	
<br />