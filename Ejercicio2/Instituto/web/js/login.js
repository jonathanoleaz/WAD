function validate() {
    var myForm = document.getElementById("form_id");
    
    var nombre = document.getElementById("nombre").value;
    var password = document.getElementById("password").value;

    if(nombre==="" || password===""){
        window.alert("Todos los campos son requeridos");
        return;
    }
    else
        myForm.submit(this);
    // var form = document.getElementsById("form_id")
    // form.submit
    
}