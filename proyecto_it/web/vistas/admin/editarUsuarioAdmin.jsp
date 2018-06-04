<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:set name="theme" value="'simple'" scope="page" />
<%@include file="../admin/cabeceraAdmin.jsp" %>

<div class="banner-gen">
    <h1 class="text-banner">EDITAR USUARIO</h1>
</div>

<s:div cssClass="container-fluid text-center">
    <s:div cssClass="col-sm-2"></s:div>
    <s:div cssClass="col-sm-8 text-left" >
        <s:form method="post" action="editUserPersistencia"> 

            <s:div cssClass="imgcontainer">
                <img class="avatar" src="<s:url value="%{usuario.foto}"/>"/>
                <s:file name="myFile" id="fileImgUser" cssClass="fileImgUser"/>
            </s:div>
            <s:div cssClass="form-group col-sm-6">
                <s:label><b>Nombre:</b></s:label>
                <s:textfield name="nombre" placeholder="Introduzaca el nombre" cssClass="form-control" id="nombre" value="%{usuario.nombre}"/>
            </s:div>
            <s:div cssClass="form-group col-sm-6">
                <s:label><b>Apellidos:</b></s:label>
                <s:textfield name="apellidos" placeholder="Introduzaca los apellidos" cssClass="form-control" id="apellidos" value="%{usuario.apellidos}"/>
            </s:div>
            <s:div cssClass="form-group col-sm-6">
                <s:label><b>Nombre de usuario:</b></s:label>
                <s:textfield name="nickname" placeholder="Introduzaca el nickname" cssClass="form-control" id="nickname" value="%{usuario.nickname}"/>
            </s:div>
            <s:div cssClass="form-group col-sm-6">
                <s:label><b>Email:</b></s:label>
                <s:textfield name="email" placeholder="Introduzaca el email" cssClass="form-control" id="email" value="%{usuario.email}"/>
            </s:div>    
            <s:div cssClass="form-group col-sm-6">
                <s:label><b>Contraseña:</b></s:label>
                <%--<s:password value="%{usuario.password}" name="password" placeholder="Contraseña" cssClass="form-control" id="password"></s:password>--%>
                <input type="password" name="password" placeholder="Contraseña" class="form-control" id="password" value="<s:property value="%{usuario.password}"/>"/>
            </s:div>            
            <s:div cssClass="form-group col-sm-6">
                <s:label><b>Fecha de nacimiento:</b></s:label>                
                <s:textfield name="fechaNacimiento" placeholder="dd/mm/aaaa" cssClass="form-control" id="fechaNacimiento" value="%{usuario.fechaNacimiento}"/>
            </s:div>  
            <s:div cssClass="botonAceptarPerfil">
                <s:hidden name="id" value="%{usuario.id}" /> 
                <s:submit name="addUsuario" cssClass="btn btn-primary filtro" value="Editar"/>
            </s:div>
        </s:form>
    </s:div>
    <s:div cssClass="col-sm-2"></s:div>
</s:div>
<%@include file="../../footer.jsp" %>              
