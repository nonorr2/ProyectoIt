<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="../admin/cabeceraAdmin.jsp" %>

<s:div cssClass="banner-gen">
    <h1 class="text-banner">CHATS</h1>
</s:div>
<s:div cssClass="container-fluid text-center">
    <s:textfield  cssClass="textFileFiltrar"/>
    <s:submit name="btoLogin" cssClass="btn btn-primary filtro" value="Filtrar"/>
</s:div>
<s:div cssClass="container-fluid text-center">
    <s:div cssClass="row">
        <s:iterator var="chat" value="chats">
            <s:div cssClass="col-sm-4 sombreado">
                <h1><s:property value="nombre"/></h1>
                <p><s:property value="fechaHora" /></p>
                <s:form action="borrarChatAdmin" method="post">
                    <s:hidden name="idChat" value="%{id}" /> 
                    <s:submit type="image" src="images/iconos/papelera.png" name="removeChat" cssClass="icono"/>
                </s:form>
                <s:form action="" method="post">
                    <s:hidden name="idChatVisualizar" value="%{id}" /> 
                    <s:submit type="image" src="images/iconos/visualizar.png" name="visualizarChat" cssClass="icono" />
                </s:form>
            </s:div>
        </s:iterator>
    </s:div>
</s:div>
<%@include file="../../footer.jsp" %>