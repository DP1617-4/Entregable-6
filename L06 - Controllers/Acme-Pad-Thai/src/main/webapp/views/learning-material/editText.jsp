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

<form:form action="learningMaterial/editT.do" modelAttribute="textMaterial">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="masterclass"/>
	

	<form:label path="title">
		<spring:message code="learningMaterial.title" />:
	</form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br />
	
	<form:label path="materialAbstract">
		<spring:message code="learningMaterial.materialAbstract" />:
	</form:label>
	<form:textarea path="materialAbstract" />
	<form:errors cssClass="error" path="materialAbstract" />
	<br />
	
	<form:label path="attachment">
		<spring:message code="learningMaterial.attachment" />:
	</form:label>
	<form:textarea path="attachment" />
	<form:errors cssClass="error" path="attachment" />
	<br />
	<form:label path="body">
		<spring:message code="learningMaterial.body" />:
	</form:label>
	<form:textarea path="body" />
	<form:errors cssClass="error" path="body" />
	<br/>
	
	
	<input type="submit" name="save"
	value="<spring:message code="learningMaterial.save" />" />&nbsp; 
			
	<jstl:if test="${presentationMaterial.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="learningMaterial.delete" />"
			onclick="return confirm('<spring:message code="learningMaterial.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="learningMaterial.cancel" />"
		onclick="javascript: relativeRedir('learningMaterial/list.do');" />&nbsp;
	<br />

	

</form:form>