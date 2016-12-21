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
	<a href="welcome/index.do">
	<img src="images/logo.png" alt="Acme Pad-Thai Co., Inc." height="180"/> </a><a href="?language=en">en</a> | <a href="?language=es">es</a>
	
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<li><a class="fNiv"><spring:message code="master.page.recipe"/></a>
			<ul>
				<li class="arrow"></li>
				<li><a href="recipe/list.do"><spring:message code="master.page.recipe.list"/></a>
				<security:authorize access="hasRole('USER')">
					<li><a href="recipe/user/create.do"><spring:message code="master.page.recipe.create"/></a> </li>
				</security:authorize>
			</ul>
		</li>
		<li><a class="fNiv"><spring:message code="master.page.contest"/></a>
			<ul>
				<li class="arrow"></li>
				<li><a href="contest/list.do"><spring:message code="master.page.contest.list"/></a>
				<security:authorize access="hasRole('ADMIN')">
					<li><a href="contest/admin/create.do"><spring:message code="master.page.contest.create"/></a> </li>
				</security:authorize>
			</ul>
		</li>
		<li><a class="fNiv"><spring:message code="master.page.master.class"/></a>
			<ul>
				<li class="arrow"></li>
				<security:authorize access="isAnonymous()">
					<li><a href="masterClass/list.do"><spring:message code="master.page.master.class.list"/></a></li>
				</security:authorize>
				<security:authorize access="isAuthenticated()">
					<li><a href="masterClass/actor/list.do"><spring:message code="master.page.master.class.list"/></a></li>
				</security:authorize>
				<security:authorize access="hasRole('COOK')">
					<li><a href="masterClass/cook/create.do"><spring:message code="master.page.master.class.create"/></a> </li>
				</security:authorize>
			</ul>
		</li>	
		<li><a class="fNiv"><spring:message code="master.page.users"/></a>
			<ul>
				<li class="arrow"></li>
				<li><a href="user/list.do"><spring:message code="master.page.users.list"/></a></li>
			</ul>
		</li>	
		<security:authorize access="hasAnyRole('SPONSOR', 'ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.campaign" /></a>
				<ul>
					<li class="arrow"></li>
					<security:authorize access="hasRole('SPONSOR')">
						<li><a href="campaign/sponsor/create.do"><spring:message code="master.page.campaign.create" /></a></li>
						<li><a href="campaign/sponsor/list.do"><spring:message code="master.page.campaign.list" /></a></li>	
					</security:authorize>	
					<security:authorize access="hasRole('ADMIN')">
						<li><a href="campaign/administrator/list.do"><spring:message code="master.page.campaign.list" /></a></li>
					</security:authorize>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.system.configuration" /></a>
				<ul>
					<li class="arrow"></li>
<<<<<<< HEAD
					<li><a href="systemConfiguration/administrator/edit.do"><spring:message code="master.page.system.configuration.edit" /></a></li>
				</ul>	
=======
					<li><a href="systemConfiguration/administrator/edit.do"><spring:message code="master.page.system.configuration.edit" /></a></li>	
				</ul>
			</li>
>>>>>>> refs/remotes/origin/master
		</security:authorize>

		<security:authorize access="hasAnyRole('SPONSOR', 'ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.bills" /></a>
				<ul>
					<li class="arrow"></li>
					<security:authorize access="hasRole('SPONSOR')">
						<li><a href="bill/sponsor/list.do"><spring:message code="master.page.bills.list" /></a></li>	
					</security:authorize>
          			<security:authorize access="hasRole('ADMIN')">
          				<li><a href="bill/administrator/list.do"><spring:message code="master.page.bills.list"/></a></li>
						<li><a href="bill/administrator/compute.do"><spring:message code="master.page.administrator.compute"/></a></li>	
            			<li><a href="bill/administrator/mail.do"><spring:message code="master.page.administrator.email"/></a></li>	
					</security:authorize>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('NUTRITIONIST')">
			<li><a class="fNiv"><spring:message	code="master.page.ingredient" /></a>
				<ul>
					<li><a href="ingredient/nutritionist/create.do"><spring:message code="master.page.ingredient.create" /></a></li>
					<li><a href="ingredient/nutritionist/list.do"><spring:message code="master.page.ingredient.list" /></a></li>	
								
				</ul>
			</li>
		</security:authorize>


		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.category" /></a>
				<ul>
					<li><a href="category/administrator/list.do"><spring:message code="master.page.category.list" /></a></li>
					<li><a href="category/administrator/create.do"><spring:message code="master.page.category.create" /></a></li>			
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
					<li><a href="folder/actor/list.do"><spring:message code="master.page.profile.folder.list" /></a></li>
					<li><a href="folder/actor/create.do"><spring:message code="master.page.profile.folder.create" /></a></li>					
					<li><a href="socialIdentity/actor/list.do"><spring:message code="master.page.profile.social.identity.list"/></a></li>					
					<li><a href="socialIdentity/actor/create.do"><spring:message code="master.page.profile.social.identity.create"/></a></li>					
					<li><a href="message/actor/create.do"><spring:message code="master.page.profile.message.create" /></a></li>
					<security:authorize access="hasRole('ADMIN')">
						<li><a><spring:message	code="master.page.administrator" /></a>
							<ul>
								<li class="arrow"></li>

								<li><a href="administrator/administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>
								<li><a href="cook/administrator/create.do"><spring:message code="master.page.cook.create" /></a></li>
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
								<li><a href="nutritionist/nutritionist/edit.do"><spring:message code="master.page.nutritionist.edit" /></a></li>
								<li><a><spring:message code = "master.page.curricula"/></a>
									<ul>
										<li class="arrow"></li>
										<li><a href="curricula/nutritionist/display.do"><spring:message code="master.page.curricula.display" /></a></li>
										<li><a href="curricula/nutritionist/edit.do"><spring:message code="master.page.curricula.edit" /></a></li>
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
								<li><a href="user/user/edit.do"><spring:message code="master.page.user.edit" /></a></li>
							</ul>
						</li>
					</security:authorize>	
					<security:authorize access="hasRole('SPONSOR')">
						<li><a href="sponsor/display.do"><spring:message code="master.page.sponsor.display"/></a>
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


