#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@page import="org.fundaciobit.genapp.common.web.exportdata.IDataExporter"%>
<%@page import="java.util.List"%>
<%@page import="org.fundaciobit.genapp.common.web.exportdata.DataExporterManager"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp" %>

<script type="text/javascript">

var currentActionForExporter = "";

function submitForm(action, reassign) {
  if (reassign) {  
	 currentActionForExporter = document.${symbol_dollar}{formName}.action;
  }
  document.${symbol_dollar}{formName}.action = action;
  if (reassign) {
    setTimeout(reassignAction, 3000);
  } 
  document.${symbol_dollar}{formName}.submit();
}

function reassignAction() {
  document.${symbol_dollar}{formName}.action = currentActionForExporter;
  currentActionForExporter = "";
}
</script>
<div id="${symbol_dollar}{formName}_pagination" style="width:100%; text-align:center;" >

  <div style="float:left;" id="${symbol_dollar}{formName}_pagination_left">
  <c:if test="${symbol_dollar}{__theFilterForm.visibleExportList}">
      
      <%
         for(IDataExporter dataExporter : DataExporterManager.getAllDataExporters()) {
      %>
      <c:url var="exportUrl" value="${symbol_dollar}{contexte}/export"/>
          <a href="${symbol_pound}" onclick="submitForm('${symbol_dollar}{exportUrl}/<%=dataExporter.getID()%>', true)">
           <img alt="<%=dataExporter.getName()%>" src="<%=request.getContextPath() + "/common/icon/" + dataExporter.getID()%>"/> 
          </a>
      <% } %>
  </c:if>
  </div>
  
  
  <c:if test="${symbol_dollar}{not empty __theFilterForm.itemsPerPage}">

  <fmt:message var="allitems" key="genapp.form.allitems" />
    <div style="float:right;" id="${symbol_dollar}{formName}_pagination_right" >
      <label><fmt:message key="genapp.form.itemsperpage" />:</label>
      <form:select cssClass="input-small" cssStyle="width:4em;"  onchange="document.${symbol_dollar}{formName}.submit()" path="itemsPerPage" >
        <c:forEach var="num" items="${symbol_dollar}{__theFilterForm.allItemsPerPage}">
           <form:option value="${symbol_dollar}{num}" label="${symbol_dollar}{ (num == -1)? allitems : num}"/>                 
        </c:forEach>
      </form:select>
    </div>


  <div class="pagination pagination-centered" id="${symbol_dollar}{formName}_pagination_center">
    <c:url var="firstUrl" value="${symbol_dollar}{contexte}/list/1" 
    /><c:url var="lastUrl" value="${symbol_dollar}{contexte}/list/${symbol_dollar}{totalPages}" 
    /><c:url var="prevUrl" value="${symbol_dollar}{contexte}/list/${symbol_dollar}{currentIndex - 1}" 
    /><c:url var="nextUrl" value="${symbol_dollar}{contexte}/list/${symbol_dollar}{currentIndex + 1}" />

    <ul>
        <c:choose>
            <c:when test="${symbol_dollar}{currentIndex == 1}">
                <li class="disabled"><a href="${symbol_pound}">&lt;&lt;</a></li>
                <li class="disabled"><a href="${symbol_pound}">&lt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${symbol_pound}" onclick="submitForm('${symbol_dollar}{firstUrl}', false)">&lt;&lt;</a></li>
                <li><a href="${symbol_pound}" onclick="submitForm('${symbol_dollar}{prevUrl}', false)">&lt;</a></li>
            </c:otherwise>
        </c:choose>

        <c:forEach var="i" begin="${symbol_dollar}{beginIndex}" end="${symbol_dollar}{endIndex}">
            <c:url var="pageUrl" value="${symbol_dollar}{contexte}/list/${symbol_dollar}{i}" />
            <c:choose>
                <c:when test="${symbol_dollar}{i == currentIndex}">
                    <li class="active"><a href="${symbol_pound}"><c:out value="${symbol_dollar}{i}" /></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${symbol_pound}" onclick="submitForm('${symbol_dollar}{pageUrl}', false)"><c:out value="${symbol_dollar}{i}" /></a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:choose>
            <c:when test="${symbol_dollar}{currentIndex == totalPages}">
                <li class="disabled"><a href="${symbol_pound}"> &gt;</a></li>
                <li class="disabled"><a href="${symbol_pound}">&gt;&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${symbol_pound}" onclick="submitForm('${symbol_dollar}{nextUrl}', false)">&gt;</a></li>
                <li><a href="${symbol_pound}" onclick="submitForm('${symbol_dollar}{lastUrl}', false)">&gt;&gt;</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
    </div>
    
    </c:if>
    
</div>

