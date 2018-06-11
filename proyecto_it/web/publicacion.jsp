<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:set name="theme" value="'simple'" scope="page" />
<%@include file="cabeceraLogado.jsp" %>
<s:if test="publicacion.getFoto().length()>0">
    <div class="banner-gen-publi" style="background: url(<s:property value="publicacion.getFoto()" />);">
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
    <s:div cssClass="col-sm-12 cont-cent">
        <s:div cssClass="comentario sombreado">
            <div class="img-comentario" style="background: url(<s:property value="getComentario().getIdUsuario().getFoto()" />);">
                <p class="autor-comment"><s:property value="getComentario().getIdUsuario().getNickname()" /></p>
            </div>
            <s:div cssClass="contenido-mensaje">
                <p><s:property value="getComentario().getContenido()" /></p>
                <p class="fecha-comment"><s:property value="getComentario().getFechaHoraModificacion()" /></p>
                <p>Número de votos positivos: <s:property value="getVotosPositivos()" /></p>
                <p>Número de votos negativos: <s:property value="getVotosNegativos()" /></p>
            </s:div>
            <s:div cssClass="contenedor-flechas-comentarios">
                <s:form method="post" action="votoPositivoComentario">
                    <s:hidden name="idComentario" value="%{getId()}" />
                    <s:hidden name="id_publi" value="%{publicacion.getId()}" />
                    <s:submit type="image" src="images/iconos/up.png" name="votoPositivoComentario" cssClass="flechas-comentarios"/>
                </s:form>
                <s:form method="post" action="votoNegativoComentario">
                    <s:hidden name="idComentario" value="%{getId()}" />
                    <s:hidden name="id_publi" value="%{publicacion.getId()}" />
                    <s:submit type="image" src="images/iconos/down.png" name="votoNegativoComentario" cssClass="flechas-comentarios"/>
                </s:form>
            </s:div>
        </s:div>
    </s:div>
</s:iterator>
<s:div cssClass="col-sm-12 cont-cent">
    <s:div cssClass="sombreado con-new-comment">
        <s:form method="post" action="comentarPublicacion">
            <s:hidden name="id_publi" value="%{publicacion.getId()}" />
            <s:fielderror fieldName="textoComentario" cssClass="alert alert-danger" />
            <s:textarea cssClass="texo-comentario" name="textoComentario" value=""/>
            <s:submit value="Comentar" cssClass="btn btn-primary"/>
        </s:form>
    </s:div>
</s:div>

<%@include file="footer.jsp" %>