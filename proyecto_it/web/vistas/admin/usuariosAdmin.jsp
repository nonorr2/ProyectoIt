<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:set name="theme" value="'simple'" scope="page" />
<%@include file="../admin/cabeceraAdmin.jsp" %>
<body id="myPage">

    <s:div cssClass="banner-gen">
        <h1 class="text-banner">USUARIOS</h1>
    </s:div>
    <s:div cssClass="container-fluid text-center">
        <s:form action="filtrarUser">
            <s:textfield name="filtroUser"  cssClass="textFileFiltrar"/>
            <s:submit name="btoFiltrarUser" cssClass="btn btn-primary filtro" value="Filtrar"/>
        </s:form>
    </s:div>
    <s:if test="%{usuarios.isEmpty()}">
        <s:div cssClass="alert alert-warning noContenido">
            <p>No hay ningun usuario que empiece por <s:property value="filtroUser"/></p>
        </s:div>
    </s:if>
    <s:else>
        <s:iterator var="usuario" value="usuarios">
            <s:div cssClass="container-fluid text-center">
                <s:div cssClass="row">
                    <s:div cssClass="contenedor-publi sombreado">
                        <s:if test="%{foto.length() > 0}">
                            <div class="publi-no-imagen" style="background-image: url(<s:property value="foto"/>)"></div>  
                        </s:if>
                        <s:else>
                            <div class="publi-no-imagen" style="background-image: url(images/imgLogin.png)"></div> 
                        </s:else> 
                        <s:div cssClass="datos-publicacion">
                            <h1><s:property value="nombre"/> <s:property value="apellidos"/></h1>
                            <p><s:property value="nickname"/></p>
                            <p><s:property value="email"/></p>
                        </s:div>
                        <s:div>
                            <s:form action="removeUser" method="post">
                                <s:hidden name="idUsuarioRemove" value="%{id}" /> 
                                <s:submit type="image" src="images/iconos/papelera.png" name="removeUser" cssClass="icono"/>
                            </s:form>
                            <s:form action="editUser" method="post">
                                <s:hidden name="idUsuarioEdit" value="%{id}" /> 
                                <s:submit type="image" src="images/iconos/editar.png" name="editUser" cssClass="icono"/>
                            </s:form>
                        </s:div>
                    </s:div>
                </s:div>
            </s:div>
        </s:iterator>
    </s:else>
    <%@include file="../../footer.jsp" %>
