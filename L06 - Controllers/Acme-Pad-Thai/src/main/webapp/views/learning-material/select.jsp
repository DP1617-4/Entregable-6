<%--
 * edit.jsp
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


<spring:message code="learningMaterial.select.header"/>
<form:form action="learningMaterial/select.do" modelAttribute="SelectMaterial">

	
	<form:select path="selected">
		<form:option value="0" label="---"/>
		<form:option value="1" label="Presentation"/>
		<form:option value="2" label="Text"/>
		<form:option value="3" label="Video"/>
	</form:select>
	
	<input type="submit" name="type"
	value ="<spring:message code="learningMaterial.select"/>" />

</form:form>