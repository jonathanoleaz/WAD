function validate() {
    var myForm = document.getElementById("form_id");
    
    var nombre = document.getElementById("nombre").value;
    var password = document.getElementById("password").value;
    var password2 = document.getElementById("password2").value;
    var tipoUsuario=document.getElementById("carrera").value;
    
    console.log(tipoUsuario);

    if(nombre==="" || password==="" || password2===""){
        window.alert("Todos los campos son requeridos");
        return;
        if(password!==password2){
            window.alert("La confirmación de contraseña no coincide, verifique");
            return;
        }
    }
    else
        myForm.submit(this);
    // var form = document.getElementsById("form_id")
    // form.submit
    
}