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

    <%--<s:div onclick="document.getElementById('id01').style.display = 'block'">--%>
        <%--<s:form action="" method="post">--%>
        <%--<s:submit type="image" src="images/iconos/add.png" name="anadirPublicacion" cssClass="icono"/>--%>
        <%--</s:form>--%>
    <%--</s:div>--%>

    <s:div cssClass="contenedor-icono-add" onclick="document.getElementById('id02').style.display = 'block'">
        <img src="images/iconos/add.png" class="icono"/>
    </s:div>
</s:div>
<s:iterator value="misPublicaciones" var="publicacion">
    <s:div cssClass="container-fluid text-center">
        <s:div cssClass="row">
            <s:div cssClass="contenedor-publi sombreado">
                <s:div cssClass="publi-no-imagen">
                    <img src="<s:property value="foto"/>" />
                    <%--<s:div cssClass="letras-imagen">--%>
                    <!--<p class="iniciales-no-imagen">NR</p>-->
                    <%--</s:div>--%>
                </s:div>
                <s:div cssClass="datos-publicacion">
                    <h1><s:property value="titulo" /></h1>
                    <p><s:property value="idUsuario.getNombre()" /></p>
                    <p><s:property value="fechaHoraModificacion" /></p>
                    <p>Numero de votos</p>
                    <p>Numero de comentarios</p>                    
                </s:div>
                <s:div cssClass="contenedor-iconos">
                    <s:form action="borrarMiPublicacion" method="post">
                        <s:hidden name="idPublicacion" value="%{id}" /> 
                        <s:submit type="image" src="images/iconos/papelera.png" name="borrarPublicacion" cssClass="icono"/>
                    </s:form>
                </s:div>
            </s:div>
        </s:div>
    </s:div>
</s:iterator>
<%@include file="footer.jsp" %>