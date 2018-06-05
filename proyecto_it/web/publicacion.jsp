<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:set name="theme" value="'simple'" scope="page" />
<%@include file="cabeceraLogado.jsp" %>
<s:if test="publicacion.getFoto().length()>0">
    <div class="banner-gen-publi" style="background: url(<s:property value="publicacion.getFoto()" />) no-repeat center top;">
        <h1 class="text-banner"><span class="letras-titulo-publi"><s:property value="publicacion.getTitulo()" /></span></h1>
    </div>
</s:if>
<s:else>
    <s:div cssClass="banner-gen-publi">
        <h1 class="text-banner"><s:property value="publicacion.getTitulo()" /></h1>
    </s:div>
</s:else>

<s:div cssClass="contenido-publicacion">
    <p><s:property value="publicacion.getContenido()" /></p>
    <a href="<s:property value="publicacion.getRuta()" />" target="_blank"><p>Enlace externo</p></a>
    <p>Fecha: <s:property value="publicacion.getFechaHoraModificacion()" /></p>
    <p>Autor: <s:property value="publicacion.getIdUsuario().getNickname()" /></p>
</s:div>
<s:iterator var="comentario" value="comentarios">
    <s:div cssClass="col-sm-7 sombreado-publicacion">
        <s:div>
            <img src="<s:property value="idUsuario.getFoto()" />">
            <p><s:property value="idUsuario.getNickname()" /></p>
        </s:div>
        <p><s:property value="getContenido()" /></p>
        <p><s:property value="getFechaHoraModificacion()" /></p>
    </s:div>
</s:iterator>

<%@include file="footer.jsp" %>