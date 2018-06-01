<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="cabeceraAdmin.jsp" %>
<body id="myPage">

    <s:div cssClass="banner-gen">
        <h1 class="text-banner">PUBLICACIONES</h1>
    </s:div>
    <s:div cssClass="container-fluid text-center">
        <s:textfield  cssClass="textFileFiltrar"/>
        <s:submit name="btoLogin" cssClass="btn btn-primary filtro" value="Filtrar"/>
    </s:div>
    <s:iterator var="publicacion" value="publicaciones">
        <s:div cssClass="container-fluid text-center">
            <s:div cssClass="row">
                <s:div cssClass="contenedor-publi sombreado">
                    <s:div cssClass="publi-no-imagen">
                        <img src="<s:property value="foto"/>"/>
                    </s:div>
                    <s:div cssClass="datos-publicacion">
                        <h1><s:property value="titulo" /></h1>
                        <p><s:property value="idUsuario.nickname"/></p>
                        <p><s:property value="fechaHoraModificacion"/></p>
                        <p>Numero de votos</p>
                        <p>Numero de comentarios</p>
                    </s:div>
                    <s:div cssClass="contenedor-iconos">
                        <s:form action="removePublicacion" method="post">
                            <s:hidden name="idPublicacionRemove" value="%{id}" /> 
                            <s:submit type="image" src="images/iconos/papelera.png" name="removePublicacion" cssClass="icono"/>
                        </s:form>
                    </s:div>
                </s:div>
            </s:div>
        </s:div>
    </s:iterator>
    <%@include file="footer.jsp" %>
