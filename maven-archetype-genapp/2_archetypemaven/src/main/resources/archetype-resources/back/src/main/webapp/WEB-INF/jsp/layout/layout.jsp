#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@include file="/WEB-INF/jsp/moduls/includes.jsp"
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="<c:out value="${symbol_dollar}{pageContext.response.locale.language}"/>"  lang="<c:out value="${symbol_dollar}{pageContext.response.locale.language}"/>">
<head>
<%@ include file="/WEB-INF/jsp/moduls/imports.jsp"%>
</head>
<body>

  <!--  INICI CAPÇALERA -->
  
  <tiles:insertAttribute name="cap">
    <tiles:putAttribute name="data" value="${symbol_dollar}{data}" />
  </tiles:insertAttribute>


  <!--  PIPELLES -->
  <div class="row-fluid container main">
    
    <ul class="nav nav-tabs custom-submenu">
    <%
    //session.setAttribute("pipella", )
    
    %>
    
    <li ${symbol_dollar}{(empty pipella)?'class="active"' : '' } >
        <a href="<c:url value="/canviarPipella/"/>"><fmt:message key="inici" /></a>
    </li> 

<%--  DRAW MENU OPTIONS  
    <c:forEach var="rolG" items="${symbol_dollar}{loginInfo.roles}">
    <c:set var="rol" value="${symbol_dollar}{rolG.authority}"/>
    <c:if test="${symbol_dollar}{not(rol eq 'ROLE_USER')}">
    <li ${symbol_dollar}{(pipella eq rol)?'class="active"' : '' }>
       <a href="<c:url value="/canviarPipella/${symbol_dollar}{rol}"/>"><fmt:message key="${symbol_dollar}{rol}" />
       <c:if test="${symbol_dollar}{not(empty avisos[rol])}">
         &nbsp; <span class="badge badge-warning">${symbol_dollar}{avisos[rol]}</span>
       </c:if>
       </a>
    </li>
    </c:if>  
    </c:forEach>
    --%>
    
    <sec:authorize access="hasRole('ROLE_USER')">
    <li ${symbol_dollar}{(pipella eq 'user')?'class="active"' : '' }>
       <a href="<c:url value="/canviarPipella/user"/>">ROLE_USER</a>
    </li>
    </sec:authorize>
    
    <sec:authorize access="hasRole('ROLE_ADMIN')">
    <li ${symbol_dollar}{(pipella eq 'admin')?'class="active"' : '' }>
       <a href="<c:url value="/canviarPipella/admin"/>">ROLE_ADMIN</a>
    </li>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
    <li ${symbol_dollar}{(pipella eq 'webdb')?'class="active"' : '' }>
       <a href="<c:url value="/canviarPipella/webdb"/>"> WebDatabase</a>
    </li>
    </sec:authorize>

    <c:if test="${symbol_dollar}{${prefix}:isDesenvolupament()}">
    <li ${symbol_dollar}{(pipella eq 'desenvolupament')?'class="active"' : '' }>
       <a href="<c:url value="/canviarPipella/desenvolupament"/>"> Desenvolupament</a>
    </li>
    </c:if>
     
    </ul>


    <!-- INICI MENU + CONTINGUT -->
    <div class="well well-white" style="padding:10px">
    <tiles:insertAttribute name="menu_i_contingut" >
       <%--  <tiles:insertTemplate template="/WEB-INF/jsp/moduls/menu_i_contingut.jsp"/>  --%>
       <tiles:putAttribute name="menu" value="${symbol_dollar}{menu_tile}" />
       <tiles:putAttribute name="contingut" value="${symbol_dollar}{contingut_tile}"  />
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
