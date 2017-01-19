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

<form:form action="placeholder/actor/edit.do" modelAttribute="placeholder">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="winners"/>
	<form:hidden path="recipe"/>
	


	<form:label path="unica">
		<spring:message code="placeholder.nick" />:
	</form:label>
	<form:input path="unica" />
	<form:errors cssClass="error" path="unica" />
	<br />
	
	<form:label path="normal">
		<spring:message code="placeholder.picture" />:
	</form:label>
	<form:input path="normal" />
	<form:errors cssClass="error" path="normal" />
	<br />
	
	<form:label path="quantity">
		<spring:message code="placeholder.network.link" />:
	</form:label>
	<form:input type="number" path="quantity" />
	<form:errors cssClass="error" path="quantity" />
	<br />
	
	<form:label path="contest">
		<spring:message code="placeholder.contest" />:
	</form:label>
	<form:select id="contests" path="contest" >
		<form:option value="0" label="----"/>
		<form:options items="${categoryList}" itemValue="id" itemLabel="title"/>
	</form:select>
	<form:errors cssClass="error" path="recipient" />
	<br />

	

	<input type="submit" name="save"
		value="<spring:message code="placeholder.save" />" />&nbsp; 
	<jstl:if test="${placeholder.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="placeholder.delete" />"
			onclick="return confirm('<spring:message code="placeholder.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="placeholder.cancel" />"
		onclick="location.href = ('placeholder/actor/list.do');" />
	<br />

	

</form:form>