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
name="nutritionist data" requestURI="${requestURI}" id="row">

	<spring:message code="nutritionist.name" var=nameHeader/>
	<display:column property="name" title="${nameHeader}"/>
	
	<spring:message code="nutritionist.surname" var=surnameHeader/>
	<display:column property="surname" title="${surnameHeader}"/>
	
	<spring:message code="nutritionist.email" var=emailHeader/>
	<display:column property="email" title="${emailHeader}"/>
	
	<spring:message code="nutritionist.phone" var=phoneHeader/>
	<display:column property="phone" title="${phoneHeader}"/>
	
	<spring:message code="nutritionist.postalAddress" var=postalAddressHeader/>
	<display:column property="postalAddress" title="${postalAddressHeader}"/>
	
</display:table>

<br/>

<jstl:if test="${nutritionist.curricula != null}">
	<a href="curricula/display.do?curriculaId=${curriculaHeader}"> <spring:message
			code="nutritionist.curricula.display" />
	</a>
</jstl:if>

<jstl:if test="${nutritionist.curricula == null}">
	<a href="curricula/create.do"> <spring:message
			code="nutritionist.curricula.create" />
	</a>
</jstl:if>
<br />	
	<a href="curricula/edit.do?nutritionistId=${requestURI.id}"> <spring:message
			code="nutritionist.edit" />
	</a>
<br />