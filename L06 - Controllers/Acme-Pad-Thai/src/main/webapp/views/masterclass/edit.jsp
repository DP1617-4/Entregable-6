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

<form:form action="${requestURI}" modelAttribute="masterClass">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="cook"/>
	<form:hidden path="actors"/>
	<form:hidden path="deleted"/>
	<form:hidden path="promoted"/>
	
	<form:label path="title">
		<spring:message code="masterClass.title" />:
	</form:label>
	<jstl:choose>
		<jstl:when test="${display}">
			${masterClass.title}
		</jstl:when>
		<jstl:otherwise>
			<form:input path="title" />
		</jstl:otherwise>
	</jstl:choose>
	<form:errors cssClass="error" path="title" />
	<br />
	
	<jstl:choose>
		<jstl:when test="${display}">
			<fieldset>
			<legend><spring:message code="masterClass.description" /></legend>
			${masterClass.title}
			</fieldset>
		</jstl:when>
		<jstl:otherwise>
			<form:label path="description">
				<spring:message code="masterClass.description" />:
			</form:label>
			<form:textarea path="description" />
		</jstl:otherwise>
	</jstl:choose>
	<form:errors cssClass="error" path="description" />
	<br />
	
	<display:table pagesize="10" class="displaytag" keepStatus="true"
		name="learningMaterials" requestURI="${requestURI}" id="row">
		
		<security:authorize access="hasRole('COOK')">
		<security:authentication property="principal.username" var ="loggedactor"/>
		<jstl:if test="${learningMaterialcook.userAccount.username==loggedactor}">
		<display:column>
			<jstl:choose>
				<jstl:when test="${row.getClass().name == 'domain.TextMaterial'}">
					<a href="textMaterial/cook/edit.do?textMaterialId=${row.id}">
						<spring:message	code="masterClass.learningMaterial.edit" />
					</a>
				</jstl:when>
				<jstl:when test="${row.getClass().name == 'domain.PresentationMaterial'}">
					<a href="presentationMaterial/cook/edit.do?presentationMaterialId=${row.id}">
						<spring:message	code="masterClass.learningMaterial.edit" />
					</a>
				</jstl:when>
				<jstl:when test="${row.getClass().name == 'domain.VideoMaterial'}">
					<a href="videoMaterial/cook/edit.do?videoMaterialId=${row.id}">
						<spring:message	code="masterClass.learningMaterial.edit" />
					</a>
				</jstl:when>
			</jstl:choose>	
		</display:column>
		</jstl:if>
		</security:authorize>
		
		<spring:message code="masterClass.learningMaterial.type" var="bodyHeader" />
		<display:column title="${bodyHeader}" sortable="false">
			<jstl:choose>
			<jstl:when test="${row.getClass().name == 'domain.TextMaterial'}">
				<spring:message code="masterClass.learningMaterial.textMaterial"/>
			</jstl:when>
			<jstl:when test="${row.getClass().name == 'domain.PresentationMaterial'}">
				<spring:message code="masterClass.learningMaterial.presentationMaterial"/>
			</jstl:when>
			<jstl:when test="${row.getClass().name == 'domain.VideoMaterial'}">
				<spring:message code="masterClass.learningMaterial.videoMaterial"/>
			</jstl:when>
		</jstl:choose>	
		</display:column>
		
		<spring:message code="masterClass.learningMaterial.title" var="titleHeader" />
		<display:column property="title" title="${titleHeader}" sortable="true" />
	
		<spring:message code="masterClass.learningMaterial.abstract" var="abstractHeader" />
		<display:column property="materialAbstract" title="${abstractHeader}" sortable="false" />
	
		<spring:message code="masterClass.learningMaterial.attachment" var="attachmentHeader" />
		<display:column property="attachment" title="${attachmentHeader}" sortable="false" />

		<spring:message code="masterClass.learningMaterial.others" var="bodyHeader" />
		<display:column title="${bodyHeader}" sortable="false">
			
		<jstl:choose>
			<jstl:when test="${row.getClass().name == 'domain.TextMaterial'}">
				${row.body}
			</jstl:when>
			<jstl:when test="${row.getClass().name == 'domain.PresentationMaterial'}">
				${row.path}
			</jstl:when>
			<jstl:when test="${row.getClass().name == 'domain.VideoMaterial'}">
				${row.identifier}
			</jstl:when>
		</jstl:choose>	
		</display:column>
	</display:table>
	<security:authorize access="hasRole('COOK')">
	<security:authentication property="principal.username" var ="loggedactor"/>
	<jstl:set var="masterClasscook" value="${masterClass.cook}"/> 
	<jstl:if test="${masterClasscook.userAccount.username==loggedactor}">
		<input type="submit" name="save"
			value="<spring:message code="masterClass.save" />"  />&nbsp; 
	
		<jstl:if test="${masterClass.id != 0}">	
			<input type="submit" name="delete"
				value="<spring:message code="masterClass.delete" />"
				onclick="return confirm('<spring:message code="masterClass.confirm.delete" />')" />&nbsp;	
			
			<input type="button" name="newTextMaterial"
				value="<spring:message code="masterClass.add.textMaterial" />"
				onclick="javascript: window.location.replace('textMaterial/cook/create.do?masterClassId=${masterClass.id}');" />&nbsp;
			<input type="button" name="cancel"
				value="<spring:message code="masterClass.add.presentationMaterial" />"
				onclick="javascript: window.location.replace('presentationMaterial/cook/create.do?masterClassId=${masterClass.id}');" />&nbsp;
			<input type="button" name="cancel"
				value="<spring:message code="masterClass.add.videoMaterial" />"
				onclick="javascript: window.location.replace('videoMaterial/cook/create.do?masterClassId=${masterClass.id}');" />&nbsp;
		</jstl:if>
	</jstl:if>
	</security:authorize>
	
	<input type="button" name="cancel"
		value="<spring:message code="masterClass.cancel" />"
		onclick="javascript: window.location.replace('${cancelURI}');" />&nbsp;
	<br />

	

</form:form>