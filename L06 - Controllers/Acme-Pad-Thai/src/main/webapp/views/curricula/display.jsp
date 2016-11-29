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
name="user data" requestURI="${requestURI}" id="row">

	<spring:message code="curricula.picture" var=pictureHeader/>
	<display:column property="picture" title="${pictureHeader}"/>
	
	<spring:message code="curricula.education" var=educationHeader/>
	<display:column property="education" title="${educationHeader}"/>
	
	<spring:message code="curricula.experience" var=experienceHeader/>
	<display:column property="experience" title="${experienceHeader}"/>
	
	<spring:message code="curricula.hobbies" var=hobbiesHeader/>
	<display:column property="hobbies" title="${hobbiesHeader}"/>
	
</display:table>
<br/>

<a href="endorser/list.do"> 
<spring:message code="curricula.endorser.list" />
</a>
<br/>

<br/>