<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@include file="/WEB-INF/jsp/moduls/includes.jsp"
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="<c:out value="${pageContext.response.locale.language}"/>"  lang="<c:out value="${pageContext.response.locale.language}"/>">
<head>
<%@ include file="/WEB-INF/jsp/moduls/imports.jsp"%>
</head>
<body>

  <!--  INICI CAPÇALERA -->
  
  <tiles:insertAttribute name="cap">
    <tiles:putAttribute name="data" value="${data}" />
  </tiles:insertAttribute>


  <!--  PIPELLES -->
  <div class="row-fluid container main">
    
    <ul class="nav nav-tabs custom-submenu">
    <%
    //session.setAttribute("pipella", )
    
    %>
    
    <li ${(empty pipella)?'class="active"' : '' } >
        <a href="<c:url value="/canviarPipella/"/>"><fmt:message key="inici" /></a>
    </li> 

<%--  DRAW MENU OPTIONS  
    <c:forEach var="rolG" items="${loginInfo.roles}">
    <c:set var="rol" value="${rolG.authority}"/>
    <c:if test="${not(rol eq 'ROLE_USER')}">
    <li ${(pipella eq rol)?'class="active"' : '' }>
       <a href="<c:url value="/canviarPipella/${rol}"/>"><fmt:message key="${rol}" />
       <c:if test="${not(empty avisos[rol])}">
         &nbsp; <span class="badge badge-warning">${avisos[rol]}</span>
       </c:if>
       </a>
    </li>
    </c:if>  
    </c:forEach>
    --%>
    
    <sec:authorize access="hasRole('ROLE_USER')">
    <li ${(pipella eq 'user')?'class="active"' : '' }>
       <a href="<c:url value="/canviarPipella/user"/>">ROLE_USER</a>
    </li>
    </sec:authorize>
    
    <sec:authorize access="hasRole('ROLE_ADMIN')">
    <li ${(pipella eq 'admin')?'class="active"' : '' }>
       <a href="<c:url value="/canviarPipella/admin"/>">ROLE_ADMIN</a>
    </li>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
    <li ${(pipella eq 'webdb')?'class="active"' : '' }>
       <a href="<c:url value="/canviarPipella/webdb"/>"> WebDatabase</a>
    </li>
    </sec:authorize>

    <c:if test="${pbs:isDesenvolupament()}">
    <li ${(pipella eq 'desenvolupament')?'class="active"' : '' }>
       <a href="<c:url value="/canviarPipella/desenvolupament"/>"> Desenvolupament</a>
    </li>
    </c:if>
     
    </ul>


    <!-- INICI MENU + CONTINGUT -->
    <div class="well well-white" style="padding:10px">
    <tiles:insertAttribute name="menu_i_contingut" >
       <%--  <tiles:insertTemplate template="/WEB-INF/jsp/moduls/menu_i_contingut.jsp"/>  --%>
       <tiles:putAttribute name="menu" value="${menu_tile}" />
       <tiles:putAttribute name="contingut" value="${contingut_tile}"  />
    </tiles:insertAttribute>
    <!-- FINAL MENU + CONTINGUT -->
    </div>

  <!-- FINAL DIV PIPELLES -->
  </div>

  <div class="container row-fluid">
    <tiles:insertAttribute name="peu">     
    </tiles:insertAttribute>
  </div>

</body>
</html>
