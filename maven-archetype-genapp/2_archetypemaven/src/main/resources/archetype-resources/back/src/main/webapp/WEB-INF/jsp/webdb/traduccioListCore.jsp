#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
  <c:if test="${symbol_dollar}{empty traduccioItems}">
     <%@include file="traduccioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${symbol_dollar}{not empty traduccioItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="traduccioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="traduccioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="traduccioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="traduccio" items="${symbol_dollar}{traduccioItems}">

        <tr id="traduccio_rowid_${symbol_dollar}{traduccio.traduccioID}">
          <%@include file="traduccioListCoreMultipleSelect.jsp" %>

          <%@include file="traduccioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="traduccioListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
