<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>


<div id="id01" class="modal">
    <s:form cssClass='modal-content animate' action="" theme="simple">
        <div class="container-login">
            <span onclick="document.getElementById('id01').style.display = 'none'" class="close" title="Close Modal">&times;</span>
            <img src="img_avatar2.png" alt="Avatar" class="login">
        </div>
        <div class="container-login" style="background-color:#f1f1f1">
            <s:label for="uname">Usuario</s:label>
            <s:textfield name="user"/>

            <s:label for="psw">Contraseña</s:label>
            <s:password name="psw"/>

            <s:submit name="btoLogin" value="Login"/>
        </div>
    </s:form>
</div>

<!--<div id="id01" class="modal">
    <form class="modal-content animate" action="/action_page.php">


        <div class="container-login">
            <label for="uname"><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="uname" required>

            <label for="psw"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="psw" required>

            <button type="submit">Login</button>
            <label>
                <input type="checkbox" checked="checked" name="remember"> Remember me
            </label>
        </div>

        <div class="container-login" style="background-color:#f1f1f1">
            <button type="button" onclick="document.getElementById('id01').style.display = 'none'" class="cancelbtn">Cancel</button>
            <span class="psw">Forgot <a href="#">password?</a></span>
        </div>
    </form>
</div>-->