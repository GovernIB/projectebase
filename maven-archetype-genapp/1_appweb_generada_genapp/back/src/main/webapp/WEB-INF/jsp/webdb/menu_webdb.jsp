<%@ page contentType="text/html;charset=UTF-8" language="java"%>
 <%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 <c:set var="url" value="${urlActual}" />
 <div>
 <h5>WebDatabase</h5>
 <ul class="tree" style="margin:3px; padding:0px;">
 <%-- ==== GENAPP MARK START --%>


    <%-- Fitxer --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'fitxer/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'fitxer/')? "font-weight: bold;" : ""}"><fmt:message key="fitxer.fitxer"/></span></a>
      <ul class="${fn:contains(url, 'fitxer/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/fitxer/new"/>" ><span style="${(fn:contains(url, 'fitxer/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="fitxer.fitxer"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/fitxer/list/1"/>" ><span style="${(fn:contains(url, 'fitxer/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Idioma --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'idioma/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'idioma/')? "font-weight: bold;" : ""}"><fmt:message key="idioma.idioma"/></span></a>
      <ul class="${fn:contains(url, 'idioma/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/idioma/new"/>" ><span style="${(fn:contains(url, 'idioma/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="idioma.idioma"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/idioma/list/1"/>" ><span style="${(fn:contains(url, 'idioma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Traduccio --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'traduccio/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'traduccio/')? "font-weight: bold;" : ""}"><fmt:message key="traduccio.traduccio"/></span></a>
      <ul class="${fn:contains(url, 'traduccio/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/traduccio/new"/>" ><span style="${(fn:contains(url, 'traduccio/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="traduccio.traduccio"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/traduccio/list/1"/>" ><span style="${(fn:contains(url, 'traduccio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>
<%-- ==== GENAPP MARK END --%>
 </ul>
 </div>
