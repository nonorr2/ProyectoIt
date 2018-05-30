<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="cabeceraNoLogado.jsp" %>

<div class="banner-gen">
    <h1 class="text-banner">TEMAS</h1>
</div>
<s:div cssClass="container-fluid text-center">
    <s:div cssClass="row">
        <s:iterator var="tema" value="tematicas">
            <s:div cssClass="col-sm-6 sombreado">
                <div class="div-icono" style=" <s:property value="imagen" />"></div>
                <h4><s:property value="nombre" /></h4>
            </s:div>
        </s:iterator>
    </s:div>
</s:div>
<%@include file="footer.jsp" %>