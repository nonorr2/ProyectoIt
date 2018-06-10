<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="cabeceraLogado.jsp" %>

<div class="banner-gen">
    <h1 class="text-banner">TEMAS</h1>
</div>
<s:div cssClass="container-fluid text-center">
    <s:div cssClass="row">
        <s:iterator var="tema" value="tematicas">
            <s:div cssClass="col-sm-6 sombreado">
                <s:form method="post" action="getPublicacionesByTema">
                    <div class="div-icono" style=" background-image: url('<s:property value="imagen" />')"></div>
                    <s:hidden name="idTema" value="%{getId()}" />
                    <s:submit value="%{getNombre()}" cssClass="titulo-submit-tema"/>
                </s:form>
            </s:div>
        </s:iterator>
    </s:div>
</s:div>
<%@include file="footer.jsp" %>