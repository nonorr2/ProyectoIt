<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:set name="theme" value="'simple'" scope="page" />
<%@include file="cabeceraLogado.jsp" %>
<s:div cssClass="banner-gen">
    <h1 class="text-banner"><s:property value="publicacion.getTitulo()" /></h1>
</s:div>

<s:div cssClass="container-fluid text-center">
    <s:div cssClass="row">
        <s:div cssClass="contenedor-publi sombreado">
            <p><s:property value="publicacion.getContenido()" /></p>
            <a href="<s:property value="publicacion.getRuta()" />" target="_blank"><p>Enlace externo</p></a>
            <p><s:property value="publicacion.getFechaHoraModificacion()" /></p>
            <p><s:property value="publicacion.getFoto()" /></p>
        </s:div>
    </s:div>
    <s:iterator var="comentario" value="comentarios">
        <s:div cssClass="col-sm-7 sombreado">
            <p><s:property value="idUsuario.getNickname()" /></p>
            <img src="<s:property value="idUsuario.getFoto()" />">
            <p><s:property value="getContenido()" /></p>
            <p><s:property value="getFechaHoraModificacion()" /></p>
        </s:div>
    </s:iterator>
</s:div>

<%@include file="footer.jsp" %>