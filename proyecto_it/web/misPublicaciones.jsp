<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:set name="theme" value="'simple'" scope="page" />
<%@include file="cabeceraLogado.jsp" %>
<%@include file="addPublicacion.jsp" %>

<s:div cssClass="banner-gen">
    <h1 class="text-banner">MIS PUBLICACIONES</h1>
</s:div>
<s:div cssClass="container-fluid text-center">
    <s:form method="post" action="">
        <s:textfield  cssClass="textFileFiltrar"/>
        <s:submit name="btoLogin" cssClass="btn btn-primary filtro" value="Filtrar"/>
    </s:form>
    <s:div cssClass="contenedor-icono-add" onclick="document.getElementById('id02').style.display = 'block'">
        <img src="images/iconos/add.png" class="icono"/>
    </s:div>
</s:div>
<s:if test="%{misPublicaciones.isEmpty()}">
    <s:div cssClass="alert alert-warning noContenido">
        <p>No ha creado ninguna publicaci&oacute;n</p>
    </s:div>
</s:if>    
<s:else>       
    <s:iterator value="misPublicaciones" var="publicacion">
        <s:div cssClass="container-fluid text-center">
            <s:div cssClass="row">
                <s:div cssClass="contenedor-publi sombreado">
                    <s:div cssClass="publi-no-imagen">
                        <s:if test="%{publicacion.getFoto().length() > 0}"> 
                            <img src="<s:property value="publicacion.getFoto()"/>" />
                        </s:if>
                        <s:else>
                            <img src="images/noFoto.png"/>
                        </s:else>
                    </s:div>
                    <s:div cssClass="datos-publicacion">
                        <h1><s:property value="publicacion.getTitulo()" /></h1>
                        <p>Fecha de cración: <s:property value="publicacion.getFechaHoraModificacion()" /></p>
                        <p>Número de votos positivos: <s:property value="numVotosPositivosPublicacion" /></p>
                        <p>Número de votos negativos: <s:property value="numVotosNegativosPublicacion" /></p>
                        <p>Numero de comentarios: <s:property value="numComentarios" /></p>                    
                    </s:div>
                    <s:div cssClass="contenedor-iconos">
                        <s:form action="borrarMiPublicacion" method="post">
                            <s:hidden name="idPublicacionRemove" value="%{id}" /> 
                            <s:submit type="image" src="images/iconos/papelera.png" name="borrarPublicacion" cssClass="icono"/>
                        </s:form>
                    </s:div>
                </s:div>
            </s:div>
        </s:div>
    </s:iterator>
</s:else>  
<%@include file="footer.jsp" %>