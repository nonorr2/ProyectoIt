<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%--<%@taglib prefix="s" uri="/struts-bootstrap-tags" %>--%>


<s:div id="id01" cssClass="modal">
    <s:form theme="simple" cssClass="modal-content animate" action="">
        <s:div cssClass="imgcontainer">
            <span onclick="document.getElementById('id01').style.display = 'none'" class="close" title="Close Modal">&times;</span>
            <img src="images/imgLogin.png" alt="Avatar" class="avatar">
        </s:div>
        <s:div cssClass="container-login">
            <s:textfield name="usuario"  placeholder="Nombre de Usuario"/>
            <s:password name="password" placeholder="Contraseña"/>
            <s:submit name="btoLogin" cssClass="btn btn-primary" value="LOGIN"/>
        </s:div>
    </s:form>
</s:div>