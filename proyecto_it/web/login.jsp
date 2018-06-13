<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%--<%@taglib prefix="s" uri="/struts-bootstrap-tags" %>--%>


<s:div id="id01" cssClass="modal">
    <s:form theme="simple" cssClass="modal-content animate" action="login">
        <s:div cssClass="imgcontainer">
            <span onclick="document.getElementById('id01').style.display = 'none'" class="close" title="Close Modal">&times;</span>
            <img src="images/imgLogin.png" alt="Avatar" class="avatar">
            <s:if test="errorLogin">
                <s:div cssClass="alert alert-warning noContenido">
                    <p>Usuario o contrase&ntilde;a incorrecta</p>
                </s:div>
            </s:if>
        </s:div>
        <s:div cssClass="container-login">
            <s:fielderror fieldName="usuario" cssClass="alert alert-danger" />
            <s:textfield name="usuario"  placeholder="Nombre de Usuario"/>
            <s:fielderror fieldName="password" cssClass="alert alert-danger" />
            <s:password name="password" placeholder="Contraseña"/>
            <s:submit name="btoLogin" cssClass="btn btn-primary" value="LOGIN"/>
        </s:div>
    </s:form>
</s:div>
