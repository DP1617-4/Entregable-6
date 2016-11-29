<%--
 * header.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme Pad-Thai Co., Inc." /> <a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<li><a class="fNiv"><spring:message code="master.page.recipe"/></a>
			<ul>
				<li class="arrow"></li>
				<li><a href="recipe/list.do"><spring:message code="master.page.recipe.list"/></a>
				<security:authorize access="hasRole('USER')">
					<li><a href="user/recipe/create.do"><spring:message code="master.page.recipe.create"/></a> </li>
				</security:authorize>
			</ul>
		</li>
		<li><a class="fNiv"><spring:message code="master.page.contest"/></a>
			<ul>
				<li class="arrow"></li>
				<li><a href="contest/list.do"><spring:message code="master.page.contest.list"/></a>
				<security:authorize access="hasRole('ADMIN')">
					<li><a href="admin/contest/create.do"><spring:message code="master.page.contest.create"/></a> </li>
				</security:authorize>
			</ul>
		</li>
		<li><a class="fNiv"><spring:message code="master.page.master.class"/></a>
			<ul>
				<li class="arrow"></li>
				<li><a href="masterclass/list.do"><spring:message code="master.page.master.class.list"/></a></li>
				<security:authorize access="hasRole('COOK')">
					<li><a href="cook/masterclass/create.do"><spring:message code="master.page.master.class.create"/></a> </li>
				</security:authorize>
			</ul>
		</li>	
		<li><a class="fNiv"><spring:message code="master.page.users"/></a>
			<ul>
				<li class="arrow"></li>
				<li><a href="user/list.do"><spring:message code="master.page.user.list"/></a></li>
			</ul>
		</li>	
		<security:authorize access="hasAnyRole('SPONSOR', 'ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.campaign" /></a>
				<ul>
					<li class="arrow"></li>
					<security:authorize access="hasRole('SPONSOR')">
						<li><a href="sponsor/campaign/create.do"><spring:message code="master.page.campaign.create" /></a></li>
					</security:authorize>	
					<li><a href="campaign/list.do"><spring:message code="master.page.campaign.list" /></a></li>	
								
				</ul>
			</li>
		</security:authorize>
		
		
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv"><spring:message code="master.page.register"/></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="user/create.do"><spring:message code="master.page.register.user"/></a></li>
					<li><a href="nutritionist/create.do"><spring:message code="master.page.register.nutritionist"/></a></li>
					<li><a href="sponsor/create.do"><spring:message code="master.page.register.sponsor"/></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="folder/list.do"><spring:message code="master.page.profile.folder" /></a></li>
					<li><a href="folder/create.do"><spring:message code="master.page.profile.folder.create" /></a></li>					
					<li><a href="socialidentity/list.do"><spring:message code="master.page.profile.social.identity.list"/></a></li>					
					<li><a href="socialidentity/create.do"><spring:message code="master.page.profile.social.identity.create"/></a></li>					
					<li><a href="message/create.do"><spring:message code="master.page.profile.message.create" /></a></li>
					<security:authorize access="hasRole('ADMIN')">
						<li><a><spring:message	code="master.page.administrator" /></a>
							<ul>
								<li class="arrow"></li>
								<li><a href="administrator/cook/create.do"><spring:message code="master.page.cook.create" /></a></li>
								<li><a href="administrator/display.do"><spring:message code="master.page.administrator.display" /></a></li>
								<li><a href="administrator/edit.do"><spring:message code="master.page.administrator.edit" /></a></li>
							</ul>
						</li>
					</security:authorize>
					<security:authorize access="hasRole('NUTRITIONIST')">
						<li><a><spring:message	code="master.page.nutritionist" /></a>
							<ul>
								<li class="arrow"></li>
								<li><a href="nutritionist/display.do"><spring:message code="master.page.nutritionist.display" /></a></li>
								<li><a href="nutritionist/edit.do"><spring:message code="master.page.nutritionist.edit" /></a></li>
								<li><a><spring:message code = "master.page.curricula"/></a>
									<ul>
										<li class="arrow"></li>
										<li><a href="nutritionist/curricula/display.do"><spring:message code="master.page.curricula.display" /></a></li>
										<li><a href="nutritionist/curricula/edit.do"><spring:message code="master.page.curricula.edit" /></a></li>
									</ul>
								</li>	
							</ul>
						</li>
					</security:authorize>	
					<security:authorize access="hasRole('USER')">
						<li><a><spring:message	code="master.page.user" /></a>
							<ul>
								<li class="arrow"></li>
								<li><a href="user/display.do"><spring:message code="master.page.user.display" /></a></li>
								<li><a href="user/edit.do"><spring:message code="master.page.user.edit" /></a></li>
							</ul>
						</li>
					</security:authorize>	
					<security:authorize access="hasRole('SPONSOR')">
						<li><a><spring:message	code="master.page.sponsor" /></a>
							<ul>
								<li class="arrow"></li>
								<li><a href="sponsor/creditcard/display.do"><spring:message code="master.page.creditcard.display" /></a></li>
								<li><a href="sponsor/creditcard/create.do"><spring:message code="master.page.creditcard.create" /></a></li>
								<li><a href="sponsor/display.do"><spring:message code="master.page.sponsor.display" /></a></li>
								<li><a href="sponsor/edit.do"><spring:message code="master.page.sponsor.edit" /></a></li>
							</ul>
						</li>
					</security:authorize>	
					<security:authorize access="hasRole('COOK')">
						<li><a><spring:message	code="master.page.cook" /></a>
							<ul>
								<li class="arrow"></li>
								<li><a href="cook/display.do"><spring:message code="master.page.cook.display" /></a></li>
								<li><a href="cook/edit.do"><spring:message code="master.page.cook.edit" /></a></li>
							</ul>
						</li>
					</security:authorize>			
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>


