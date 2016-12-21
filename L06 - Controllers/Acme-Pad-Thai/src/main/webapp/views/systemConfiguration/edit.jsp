<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form method="post" action="systemConfiguration/administrator/setFee.do">
	<label><spring:message	code="systemConfiguration.fee" /></label>${systemConfiguration.fee}<br />
	<input type="number" min="0" step="any" name="fee" id="hourRate"><br />
	<input type="submit" name="changeFee" value="<spring:message code="systemConfiguration.change.fee" />"/><br />
</form>
<br/>
	<spring:message	code="systemConfiguration.keywords" />
<br/>
<table>
<thead>
		<tr>
		<td><spring:message	code="systemConfiguration.keywords" /></td><td><spring:message	code="systemConfiguration.delete.words" /></td>
	</tr>
</thead>
<tbody>
	<jstl:forEach items="${systemConfiguration.keywords}" var="word">
		<tr>
			<td>${word}</td><td><a href="systemConfiguration/administrator/deleteWord.do?word=${word}"><spring:message	code="systemConfiguration.delete.words" /></a></td>
		</tr>
	</jstl:forEach>
</tbody>
</table>
<br/>
<form method="post" action="systemConfiguration/administrator/addKeyword.do">
	<label><spring:message	code="systemConfiguration.new.word" /></label><br />
		<input type="text" name="keyword" id="keyword"><br />
	<input type="submit" name="addWord" value="<spring:message code="systemConfiguration.add.word" />"/><br />
</form>
<br/>	

<jstl:if test="${admin.id == 0}">
	<input type="button" name="cancel"
	value="<spring:message code="systemConfiguration.cancel" />"
	onclick="javascript: relativeRedir('welcome/index.do');" />
</jstl:if>
<br />