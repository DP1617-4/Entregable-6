<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="curriculum/nutritionist/edit.do" modelAttribute="curriculum">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="deleted" />
	<form:hidden path="endorsers" />
	<form:hidden path="nutritionist" />
	
	<form:label path="picture">
		<spring:message code="curriculum.picture" />:
	</form:label>
	<form:input path="picture" />
	<form:errors cssClass="error" path="picture" />
	<br />
	
	<form:label path="education">
		<spring:message code="curriculum.education" />:
	</form:label>
	<form:textarea path="education" />
	<form:errors cssClass="error" path="education" />
	<br />
	
	<form:label path="experience">
		<spring:message code="curriculum.experience" />:
	</form:label>
	<form:textarea path="experience" />
	<form:errors cssClass="error" path="experience" />
	<br />
	
	<form:label path="hobbies">
		<spring:message code="curriculum.hobbies" />:
	</form:label>
	<form:textarea path="hobbies" />
	<form:errors cssClass="error" path="hobbies" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="curriculum.save" />" />&nbsp; 
	<jstl:if test="${curriculum.id != 0}">
		<input type="submit" name="delete"
		value="<spring:message code="curriculum.delete" />"
		onclick="return confirm('<spring:message code="curriculum.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="curriculum.cancel" />"
		onclick="location.href = 'curriculum/nutritionist/display.do';" />&nbsp;
	<br />
	
	
</form:form>