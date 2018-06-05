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
                <%--<s:submit type="image" src="images/iconos/visualizar.png" id="%{id}" name="visualizarChat" cssClass="icono" onmouseover="visualizarUser(this)"/>--%>
                <img src="images/iconos/visualizar.png"  class="icono" onmouseenter="visualizarUserChat(<s:property value="id"/>)" onmouseleave="borrarUserChat()"/>
            </s:div>
            <s:div id="userChat" cssClass="userChat">
            </s:div>
        </s:iterator>
    </s:div>
</s:div>
<script>
    function visualizarUserChat(id_chat) {
        var contenedorUser1 = document.createElement("div");
        contenedorUser1.setAttribute("class", "contenedor_user");

        var contenedorUser2 = document.createElement("div");
        contenedorUser2.setAttribute("class", "contenedor_user");

        var contenedor = document.getElementById("userChat");
        contenedor.appendChild(contenedorUser1);
        contenedor.appendChild(contenedorUser2);

        var xhttp = new XMLHttpRequest();
        xhttp.open("GET", "http://localhost:8080/webService/webresources/ws.chat/getUsuariosDeUnChat/" + id_chat, false);
        xhttp.setRequestHeader("Accept", "application/json");
        xhttp.send();
        var usuarios = xhttp.responseText;
        usuarios = JSON.parse(usuarios);

        
        var pNickName1 = document.createElement("p");
        pNickName1.appendChild(document.createTextNode(usuarios[0]['nickname']));
        var imgUser1 = document.createElement("img");
        imgUser1.setAttribute("src", usuarios[0]['foto']);
        imgUser1.setAttribute("class", "icono");
        contenedorUser1.appendChild(imgUser1);
        contenedorUser1.appendChild(pNickName1);

        var imgUser2 = document.createElement("img");
        imgUser2.setAttribute("src", usuarios[1]['foto']);
        imgUser2.setAttribute("class", "icono");
        var pNickName2 = document.createElement("p");
        pNickName2.appendChild(document.createTextNode(usuarios[1]['nickname']));
        contenedorUser2.appendChild(imgUser2);
        contenedorUser2.appendChild(pNickName2);

    }

    function borrarUserChat() {
        $("#userChat").empty();
    }
</script>
<%@include file="../../footer.jsp" %>