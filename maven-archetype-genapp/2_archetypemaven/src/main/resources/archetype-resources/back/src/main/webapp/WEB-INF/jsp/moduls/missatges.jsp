#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
﻿<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<c:if test="${symbol_dollar}{not empty missatges}">
  
  <c:forEach items="${symbol_dollar}{missatges}" var="tipusList" varStatus="status">
    
      <c:forEach items="${symbol_dollar}{tipusList.value}" var="msg" >
      <div class="alert alert-${symbol_dollar}{tipusList.key}">
      <button type="button" class="close" data-dismiss="alert">&times;</button>
      ${symbol_dollar}{msg}
      </div>
      </c:forEach>
    
  </c:forEach>
</c:if>
<c:remove var="missatges" scope="session" />

<div class="spacer"></div>
