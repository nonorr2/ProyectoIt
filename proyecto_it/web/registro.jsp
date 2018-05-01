<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="cabeceraNoLogado.jsp" %>

<s:div cssClass="container-fluid text-center estapacio-cabecera">
    <s:div cssClass="col-sm-2"></s:div>
    <s:div cssClass="col-sm-8 text-left" >

        <h1 class="h1-registro">&Uacute;nete a nosotros</h1>

        <s:form method="post" action="" theme="simple"> 
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
                <s:label><b>Contraseña:</b></s:label>
                <s:password name="password" placeholder="Introduzaca la contraseña" cssClass="form-control" id="password"/>
            </s:div>
            <s:div cssClass="form-group col-sm-6">
                <s:label><b>Email:</b></s:label>
                <s:textfield name="email" placeholder="Introduzaca el email" cssClass="form-control" id="email"/>
            </s:div>
            <s:div cssClass="form-group col-sm-6">
                <s:label><b>Fecha de nacimiento:</b></s:label>
            </s:div>
            <s:div cssClass="form-group col-sm-6">
                <s:submit name="addUsuario" cssClass="btn btn-default" value="Enviar"/>
            </s:div>
        </s:form>
    </s:div>
    <s:div cssClass="col-sm-2"></s:div>
</s:div>

<%--<%@include file="footer.jsp" %>--%>
