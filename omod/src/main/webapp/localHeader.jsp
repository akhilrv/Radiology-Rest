<!-- Add, Edit, Delete, View Orders -->
<!-- Add, Edit, Delete, View Reports -->
<!-- Configure Devices -->


<ul id="menu">
	<li class="first">
		<a href="${pageContext.request.contextPath}/admin"><spring:message code="admin.title.short"/></a>
	</li>
	<openmrs:hasPrivilege privilege="View Orders">
		<li <c:if test='<%= request.getRequestURI().contains("radiologyrest/radiologyrestOrder") %>'>class="active"</c:if>>
			<a href="${pageContext.request.contextPath}/module/radiologyrest/radiologyrestOrder.list">
				<spring:message code="radiologyrest.manageOrders"/>
			</a>
		</li>
	</openmrs:hasPrivilege>

</ul>