<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="cabeceraAdmin.jsp" %>
<body id="myPage">

    <s:div cssClass="banner-gen">
        <h1 class="text-banner">USUARIOS</h1>
    </s:div>
    <s:div cssClass="container-fluid text-center">
        <s:textfield  cssClass="textFileFiltrar"/>
        <s:submit name="btoLogin" cssClass="btn btn-primary filtro" value="Filtrar"/>
    </s:div>
    <s:iterator var="usuario" value="usuarios">
        <s:div cssClass="container-fluid text-center">
            <s:div cssClass="row">
                <s:div cssClass="contenedor-publi sombreado">
                    <div class="publi-no-imagen" style=" background-image: url('<s:property value="foto" />')">
                    </div>
                    <s:div cssClass="datos-publicacion">
                        <h1><s:property value="nombre"/> <s:property value="apellidos"/></h1>
                        <p><s:property value="nickname"/></p>
                        <p><s:property value="email"/></p>
                    </s:div>
                    <s:div cssClass="contenedor-iconos">
                        
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
    <%@include file="footer.jsp" %>
