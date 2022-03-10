// Call the dataTables jQuery plugin
$(document).ready(function() {  //ejecuta una vez cargada la pagina
    //on ready!!!
});


//register con fetch
async function iniciarSesion(){
    let datos = {};
    //obtener valores de los inputs del form
    datos.email = document.getElementById("txtEmail").value;
    datos.password = document.getElementById("txtPassword").value;

    //peticion asincrona
    const request = await fetch('login', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos) //convertir JSON a string
      });
    const respuesta = await request.text();

    if(respuesta != "FAIL"){
        localStorage.token = respuesta; //guardo el token en cliente
        localStorage.email = datos.email;
        //redireccion
        window.location.href = "usuarios.html"
    }
    else{
        alert("email/password incorrecto");
    }
}