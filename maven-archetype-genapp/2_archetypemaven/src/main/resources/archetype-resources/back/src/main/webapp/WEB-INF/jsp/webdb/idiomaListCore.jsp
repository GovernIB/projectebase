#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
  <c:if test="${symbol_dollar}{empty idiomaItems}">
     <%@include file="idiomaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${symbol_dollar}{not empty idiomaItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="idiomaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="idiomaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="idiomaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="idioma" items="${symbol_dollar}{idiomaItems}">

        <tr id="idioma_rowid_${symbol_dollar}{idioma.idiomaID}">
          <%@include file="idiomaListCoreMultipleSelect.jsp" %>

          <%@include file="idiomaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="idiomaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
