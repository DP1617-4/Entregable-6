<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="curricula/edit.do" modelAttribute="curricula">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="deleted" />
	<form:hidden path="endorsers" />
	<form:hidden path="nutritionist" />
	
	<form:label path="picture">
		<spring:message code="curricula.picture" />:
	</form:label>
	<form:input path="picture" />
	<form:errors cssClass="error" path="picture" />
	<br />
	
	<form:label path="education">
		<spring:message code="curricula.education" />:
	</form:label>
	<form:textarea path="education" />
	<form:errors cssClass="error" path="education" />
	<br />
	
	<form:label path="experience">
		<spring:message code="curricula.experience" />:
	</form:label>
	<form:textarea path="experience" />
	<form:errors cssClass="error" path="experience" />
	<br />
	
	<form:label path="hobbies">
		<spring:message code="curricula.hobbies" />:
	</form:label>
	<form:textarea path="hobbies" />
	<form:errors cssClass="error" path="hobbies" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="curricula.save" />" />&nbsp; 
	<jstl:if test="${curricula.id != 0}">
		<input type="submit" name="delete"
		value="<spring:message code="curricula.delete" />"
		onclick="return confirm('<spring:message code="curricula.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="curricula.cancel" />"
		onclick="javascript: relativeRedir('nutritionist/display.do');" />
	<br />
	
	
</form:form>