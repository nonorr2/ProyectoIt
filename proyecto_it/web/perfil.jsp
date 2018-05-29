<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:set name="theme" value="'simple'" scope="page" />
<%@include file="cabeceraLogado.jsp" %>

<div class="banner-gen">
        <h1 class="text-banner">TU PERFIL</h1>
</div>

<s:div cssClass="container-fluid text-center">
    <s:div cssClass="col-sm-2"></s:div>
    <s:div cssClass="col-sm-8 text-left" >

        <s:form method="post" action=""> 
            <s:div cssClass="form-group col-sm-6">
                <s:label><b>Nombre:</b></s:label>
                <s:textfield name="nombre" placeholder="Introduzaca el nombre" cssClass="form-control" id="nombre"/>
            </s:div>
            <s:div cssClass="form-group col-sm-6">
                <s:label><b>Apellidos:</b></s:label>
                <s:textfield name="apellidos" placeholder="Introduzaca los apellidos" cssClass="form-control" id="apellidos"/>
            </s:div>
            <s:div cssClass="form-group col-sm-6">
                <s:label><b>Nombre de usuario:</b></s:label>
                <s:textfield name="nickname" placeholder="Introduzaca el nickname" cssClass="form-control" id="nickname"/>
            </s:div>
            <s:div cssClass="form-group col-sm-6">
                <s:label><b>Email:</b></s:label>
                <s:textfield name="email" placeholder="Introduzaca el email" cssClass="form-control" id="email"/>
            </s:div>    
            <s:div cssClass="form-group col-sm-6">
                <s:label><b>Contraseña:</b></s:label>
                <s:password name="password" placeholder="Introduzaca la contraseña" cssClass="form-control" id="password"/>
            </s:div>
            <s:div cssClass="form-group col-sm-6">
                <s:label><b>Confirmar contraseña:</b></s:label>
                <s:password name="confirmarPassword" placeholder="Introduzaca la contraseña" cssClass="form-control" id="confirmarPassword"/>
            </s:div>             
            <s:div cssClass="form-group col-sm-6">
                <s:label><b>Fecha de nacimiento:</b></s:label>                
                <input type="date" name="fechaNacimiento" class="form-control" id="fechaNacimiento"/>
            </s:div>  
            <s:div cssClass="botonAceptarPerfil">
                <s:submit name="addUsuario" cssClass="btn btn-success" value="Enviar"/>
            </s:div>
        </s:form>
    </s:div>
    <s:div cssClass="col-sm-2"></s:div>
</s:div>

<%--<%@include file="footer.jsp" %>--%>