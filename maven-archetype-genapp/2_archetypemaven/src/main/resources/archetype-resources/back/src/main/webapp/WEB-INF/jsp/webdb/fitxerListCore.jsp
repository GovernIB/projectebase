#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
  <c:if test="${symbol_dollar}{empty fitxerItems}">
     <%@include file="fitxerListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${symbol_dollar}{not empty fitxerItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="fitxerListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="fitxerListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="fitxerListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="fitxer" items="${symbol_dollar}{fitxerItems}">

        <tr id="fitxer_rowid_${symbol_dollar}{fitxer.fitxerID}">
          <%@include file="fitxerListCoreMultipleSelect.jsp" %>

          <%@include file="fitxerListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="fitxerListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
