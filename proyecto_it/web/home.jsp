<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:set name="theme" value="'simple'" scope="page" />
<%@include file="cabeceraLogado.jsp" %>

<s:div cssClass="banner-gen">
    <h1 class="text-banner">HOME</h1>
</s:div>
<s:div cssClass="container-fluid text-center">
    <s:form method="post" action="">
        <s:textfield  cssClass="textFileFiltrar"/>
        <s:submit name="btoLogin" cssClass="btn btn-primary filtro" value="Filtrar"/>
    </s:form>
</s:div>
<s:if test="%{publicacionesSuscrito.isEmpty()}">
    <s:div cssClass="alert alert-warning noContenido">
        <p>No tiene ninguna suscripci&oacute;n</p>
    </s:div>
</s:if>    
<s:else>  
    <s:iterator value="publicacionesSuscrito" var="publicacion">
        <s:div cssClass="container-fluid text-center">
            <s:div cssClass="row">
                <s:div cssClass="contenedor-publi sombreado">
                    <s:div cssClass="publi-no-imagen">
                        <img src="<s:property value="publicacion.getFoto()"/>" />
                    </s:div>
                    <s:div cssClass="datos-publicacion">
                        <h1><s:property value="publicacion.getTitulo()" /></h1>
                        <p><s:property value="usuario.getNickname()" /></p>
                        <p><s:property value="publicacion.getFechaHoraModificacion()" /></p>
                        <p>Número de votos positivos: <s:property value="numVotosPositivosPublicacion" /></p>
                        <p>Número de votos negativos: <s:property value="numVotosNegativosPublicacion" /></p>
                        <p>Último comentario: <s:property value="ultimoComentario.getFechaHoraModificacion()" /></p>                    
                    </s:div>
                    <s:div cssClass="contenedor-flechas">
                        <s:form method="post" action="votoPositivoPublicacon">
                            <s:hidden name="idPublicacion" value="%{publicacion.getId()}" />
                            <s:submit type="image" src="images/iconos/flechaVerde.png" name="votoPositivoPublicacion" cssClass="flechas"/>
                        </s:form>
                        <s:form method="post" action="unFollowPublicacion">
                            <s:hidden name="idPublicacion" value="%{id}" />
                            <s:submit name="unFollowPublicacion" value="UnFollow" cssClass="btn btn-warning flechas follow" />
                        </s:form>
                        <s:form method="post" action="">
                            <s:hidden name="idPublicacion" value="%{id}" />
                            <s:submit type="image" src="images/iconos/flechaRoja.png" name="votoNegativoPublicacion" cssClass="flechas"/>
                        </s:form>
                    </s:div>
                </s:div>
            </s:div>
        </s:div>
    </s:iterator>
</s:else>  
<%@include file="footer.jsp" %>
